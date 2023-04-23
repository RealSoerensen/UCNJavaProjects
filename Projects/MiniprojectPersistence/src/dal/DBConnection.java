package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private static Connection con;
    private static DBConnection instance;

    private static final String DBNAME = "DMA-CSD-S223_10461259";
    private static final String SERVERNAME = "hildur.ucn.dk";
    private static final String USERNAME = "DMA-CSD-S223_10461259";
    private static final String PASSWORD = "Password1!";

    private DBConnection() throws SQLException {
        String urlString = String.format("jdbc:sqlserver://%s;databaseName=%s;encrypt=false", SERVERNAME,
                DBNAME);
        con = DriverManager.getConnection(urlString, USERNAME, PASSWORD);
    }

    public static DBConnection getInstance() throws SQLException {
        if(instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public static Connection getConnection() {
        return con;
    }

    public boolean getOpenStatus() {
        boolean isOpen = false;
        try {
            isOpen = (!con.isClosed());
        } catch (Exception sclExc) {
            System.out.println("Error checking connection status");
        }
        return isOpen;
    }

    public void dropTables(){
        // Drop tables if they exist
        Connection connection = getConnection();
        try(Statement statement = connection.createStatement()) {
            // Drop tables if they exist
            statement.addBatch("DROP TABLE IF EXISTS SaleOrder");
            statement.addBatch("DROP TABLE IF EXISTS OrderLine");
            statement.addBatch("DROP TABLE IF EXISTS Product");
            statement.addBatch("DROP TABLE IF EXISTS Supplier");
            statement.addBatch("DROP TABLE IF EXISTS Customer");
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTables(){
        Connection connection = getConnection();
        try (Statement statement = connection.createStatement()) {
            statement.addBatch("CREATE TABLE Customer (" +
                    "    customerId int NOT NULL IDENTITY(1,1)," +
                    "    name varchar(50) NOT NULL," +
                    "    address varchar(50) NOT NULL," +
                    "    phoneNo varchar(50) NOT NULL," +
                    "" +
                    "    CONSTRAINT PK_Customer PRIMARY KEY (customerId)" +
                    ")");
            statement.addBatch("CREATE TABLE Supplier (" +
                    "    supplierId int NOT NULL IDENTITY(1,1)," +
                    "    name varchar(50) NOT NULL," +
                    "    address varchar(50) NOT NULL," +
                    "    country varchar(50) NOT NULL," +
                    "    phoneNo varchar(50) NOT NULL," +
                    "    email varchar(50) NOT NULL," +
                    "" +
                    "    CONSTRAINT PK_Supplier PRIMARY KEY (supplierId)" +
                    ")");
            statement.addBatch("CREATE TABLE Product (" +
                    "    productId int NOT NULL IDENTITY(1,1)," +
                    "    name varchar(50) NOT NULL," +
                    "    brand varchar(50) NOT NULL," +
                    "    purchasePrice decimal(10,2) NOT NULL," +
                    "    salesPrice decimal(10,2) NOT NULL," +
                    "    countryOfOrigin varchar(50) NOT NULL," +
                    "    minStock int NOT NULL," +
                    "    stock int NOT NULL," +
                    "    description varchar(500) NOT NULL," +
                    "    category varchar(50) NOT NULL," +
                    "    supplierId int NOT NULL," +
                    "" +
                    "    CONSTRAINT PK_Product PRIMARY KEY (productId)," +
                    "    CONSTRAINT FK_Product_Supplier FOREIGN KEY (supplierId) REFERENCES Supplier(supplierId)" +
                    ")");
            statement.addBatch("CREATE TABLE OrderLine (" +
                    "    orderLineId int NOT NULL IDENTITY(1,1)," +
                    "    quantity int NOT NULL," +
                    "    discount decimal(10,2) NOT NULL," +
                    "    productId int NOT NULL," +
                    "" +
                    "    CONSTRAINT PK_OrderLine PRIMARY KEY (orderLineId)," +
                    "    CONSTRAINT FK_OrderLine_Product FOREIGN KEY (productId) REFERENCES Product(productId)" +
                    ")");
            statement.addBatch("CREATE TABLE SaleOrder (" +
                    "    saleOrderId int NOT NULL IDENTITY(1,1)," +
                    "    date datetime2 NOT NULL," +
                    "    deliveryStatus varchar(50) NOT NULL," +
                    "    deliveryDate datetime2 NOT NULL," +
                    "    paymentDate datetime2 NOT NULL," +
                    "    customerId int NOT NULL," +
                    "    orderLineId int NOT NULL," +
                    "" +
                    "    CONSTRAINT PK_SaleOrder PRIMARY KEY (saleOrderid)," +
                    "    CONSTRAINT FK_SaleOrder_Customer FOREIGN KEY (customerId) REFERENCES Customer(customerId)," +
                    "    CONSTRAINT FK_SaleOrder_OrderLine FOREIGN KEY (orderLineId) REFERENCES OrderLine(orderLineId)" +
                    ")");

            // Execute batch
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void resetDatabase() throws SQLException {
        Connection connection = getConnection();
        dropTables();
        createTables();
        connection.commit();
    }

    public static void closeConnection() {
        try {
            con.close();
            instance = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
