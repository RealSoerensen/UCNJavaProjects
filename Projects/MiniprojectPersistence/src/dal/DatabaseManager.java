package dal;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatabaseManager implements CRUD {
    private final Connection con;

    public DatabaseManager() throws SQLException {
        con = DBConnection.getConnection();
        con.setAutoCommit(false);
    }

    @Override
    public <T> boolean create(T obj) throws SQLException {
        boolean result = false;
        String[] columnNames = getColumnNames(obj);
        String[] values = getValues(obj);
        String tableName = getTableName(obj);

        for (int i = 0; i < columnNames.length; i++) {
            values[i] = "?";
        }

        String sql = String.format("INSERT INTO %s (%s) VALUES (%s)", tableName, String.join(", ", columnNames), String.join(", ", values));

        try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            setValues(stmt, obj);
            stmt.execute();
            con.commit();
            result = true;
        } catch (SQLException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            con.rollback();
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public <T> T get(Class<T> type, long id) throws SQLException {
        DatabaseUtils.TableInfo tableInfo = DatabaseUtils.getTableInfo(type);
        String tableName = tableInfo.tableName;
        String[] fieldNames = tableInfo.fieldNames;
        T result = null;

        String sql = String.format("SELECT %s FROM %s WHERE %s = ?", String.join(", ", fieldNames), tableName, fieldNames[0]);

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    result = getObjectFromResultSet(rs, type);
                }
            }
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public <T> List<T> getAll(Class<T> type) throws SQLException {
        DatabaseUtils.TableInfo tableInfo = DatabaseUtils.getTableInfo(type);
        String tableName = tableInfo.tableName;
        String[] fieldNames = tableInfo.fieldNames;
        List<T> result = new ArrayList<>();

        String sql = String.format("SELECT %s FROM %s", String.join(", ", fieldNames), tableName);

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    T obj = getObjectFromResultSet(rs, type);
                    result.add(obj);
                }
            }
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean update(Object obj) throws SQLException {
        boolean result = false;
        String tableName = getTableName(obj);
        String[] fieldNames = getFieldNames(obj);
        String primaryKeyFieldName = getPrimaryKeyFieldName(obj);


        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ").append(tableName).append(" SET ");
        for (String fieldName : fieldNames) {
            if (!fieldName.equals(primaryKeyFieldName)) {
                sb.append(fieldName).append(" = ?, ");
            }
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append(" WHERE ").append(primaryKeyFieldName).append(" = ?");

        String sql = sb.toString();

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            setValues(stmt, obj);
            stmt.setLong(fieldNames.length, getValueByFieldName(obj, primaryKeyFieldName));
            stmt.executeUpdate();
            result = true;
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
            con.rollback();
        } catch (InvocationTargetException | NoSuchMethodException e) {
            con.rollback();
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public <T> boolean delete(Class<T> type, long id) throws SQLException {
        boolean result = false;
        DatabaseUtils.TableInfo tableInfo = DatabaseUtils.getTableInfo(type);
        String tableName = tableInfo.tableName;
        String[] fieldNames = tableInfo.fieldNames;

        String sql = String.format("DELETE FROM %s WHERE %s = ?", tableName, fieldNames[0]);

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
            result = true;
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }
        return result;
    }

    private String[] getFieldNames(Object obj) {
        String[] result;
        DatabaseUtils.TableInfo tableInfo = DatabaseUtils.getTableInfo(obj.getClass());
        result = tableInfo.fieldNames;
        return result;
    }

    private String getPrimaryKeyFieldName(Object obj) {
        DatabaseUtils.TableInfo tableInfo = DatabaseUtils.getTableInfo(obj.getClass());
        return tableInfo.fieldNames[0];
    }

    private String[] getColumnNames(Object obj) {
        String[] fieldNames = getFieldNames(obj);
        return Arrays.copyOfRange(fieldNames, 1, fieldNames.length);
    }

    private String[] getValues(Object obj) {
        return new String[getColumnNames(obj).length];
    }

    private <T> String getTableName(T obj) {
        DatabaseUtils.TableInfo tableInfo = DatabaseUtils.getTableInfo(obj.getClass());
        return tableInfo.tableName;
    }

    private long getValueByFieldName(Object obj, String fieldName) {
        long result = 0;
        try {
            Method getterMethod = obj.getClass().getDeclaredMethod("get" + capitalize(fieldName));
            result = (long) getterMethod.invoke(obj);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    private <T> void setValues(PreparedStatement stmt, T obj) throws SQLException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        String[] fieldNames = getFieldNames(obj);
        Class<?>[] fieldTypes = getFieldTypes(obj);
        for (int i = 1; i < fieldNames.length; i++) {
            String fieldName = fieldNames[i];
            Class<?> fieldType = fieldTypes[i];
            String getterName = "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
            Method getter = obj.getClass().getMethod(getterName);
            Object value = getter.invoke(obj);
            if (fieldType == String.class) {
                stmt.setString(i, (String) value);
            } else if (fieldType == Double.class || fieldType == double.class) {
                stmt.setDouble(i, ((Number) value).doubleValue());
            } else if (fieldType == Long.class || fieldType == long.class) {
                stmt.setLong(i, ((Number) value).longValue());
            } else if (fieldType == int.class) {
                stmt.setInt(i, (int) value);
            } else if (fieldType == boolean.class) {
                stmt.setBoolean(i, (boolean) value);
            } else if (fieldType == float.class) {
                stmt.setFloat(i, (float) value);
            } else if (fieldType == Timestamp.class) {
                stmt.setTimestamp(i, (Timestamp) value);
            } else {
                throw new RuntimeException("Unsupported type: " + fieldType);
            }
        }
    }

    private <T> Class<?>[] getFieldTypes(T obj) {
        DatabaseUtils.TableInfo tableInfo = DatabaseUtils.getTableInfo(obj.getClass());
        return tableInfo.fieldTypes;
    }

    private <T> T getObjectFromResultSet(ResultSet rs, Class<T> type) throws SQLException {
        T result = null;
        try {
            result = type.getDeclaredConstructor().newInstance();
            Field[] fields = type.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = rs.getObject(field.getName());
                if (value instanceof BigDecimal && field.getType() == long.class) {
                    value = ((BigDecimal) value).longValue();
                } else if (value instanceof BigDecimal && field.getType() == int.class) {
                    value = ((BigDecimal) value).intValue();
                } else if (value instanceof BigDecimal && field.getType() == double.class) {
                    value = ((BigDecimal) value).doubleValue();
                } else if (value instanceof BigDecimal && field.getType() == float.class) {
                    value = ((BigDecimal) value).floatValue();
                }
                field.set(result, value);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return result;
    }
}
