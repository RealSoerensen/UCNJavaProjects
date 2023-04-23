package dal;

import java.sql.SQLException;
import java.util.List;

public interface CRUD {
    <T> boolean create(T obj) throws SQLException;
    <T> T get(Class<T> type, long id) throws SQLException;
    <T> List<T> getAll(Class<T> type) throws SQLException;
    <T> boolean update(T type) throws SQLException;
    <T> boolean delete(Class<T> type, long id) throws SQLException;
}
