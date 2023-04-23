package dal;

import model.*;

import java.sql.Timestamp;

public class DatabaseUtils {
    public static class TableInfo {
        public final String tableName;
        public final String[] fieldNames;
        public final Class<?>[] fieldTypes;

        public TableInfo(String tableName, String[] fieldNames, Class<?>[] fieldTypes) {
            this.tableName = tableName;
            this.fieldNames = fieldNames;
            this.fieldTypes = fieldTypes;
        }
    }

    public static TableInfo getTableInfo(Object type) {
        String tableName;
        String[] fieldNames;
        Class<?>[] fieldTypes;

        if (type == Customer.class) {
            tableName = "Customer";
            fieldNames = new String[]{"customerId", "name", "address", "phoneNo"};
            fieldTypes = new Class<?>[]{long.class, String.class, String.class, String.class};
        } else if (type == Supplier.class) {
            tableName = "Supplier";
            fieldNames = new String[]{"supplierId", "name", "address", "country", "phoneNo", "email"};
            fieldTypes = new Class<?>[]{long.class, String.class, String.class, String.class, String.class, String.class};
        } else if (type == Product.class) {
            tableName = "Product";
            fieldNames = new String[]{"productId", "name", "brand", "purchasePrice", "salesPrice", "countryOfOrigin", "minStock", "stock", "description", "category", "supplierId"};
            fieldTypes = new Class<?>[]{long.class, String.class, String.class, double.class, double.class, String.class, int.class, int.class, String.class, String.class, long.class};
        } else if (type == SaleOrder.class) {
            tableName = "SaleOrder";
            fieldNames = new String[]{"saleOrderId", "date", "deliveryStatus", "deliveryDate", "paymentDate", "customerId", "orderLineId"};
            fieldTypes = new Class<?>[]{long.class, Timestamp.class, String.class, Timestamp.class, Timestamp.class, long.class, long.class};
        } else if (type == OrderLine.class) {
            tableName = "OrderLine";
            fieldNames = new String[]{"orderLineId", "discount", "productId", "quantity"};
            fieldTypes = new Class<?>[]{long.class, double.class, long.class, int.class};
        } else {
            throw new IllegalArgumentException("Unknown object type: " + type.getClass());
        }

        return new TableInfo(tableName, fieldNames, fieldTypes);
    }
}
