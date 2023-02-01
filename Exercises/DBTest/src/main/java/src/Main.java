package src;

import java.sql.*;

public class Main {

    // The values of the constants should be read from a configuration file, etc.
    // You need to change the constants to the connection properties of your DB
    private static final String DBNAME = "TestDB";
    private static final String SERVERNAME = "\\MSI";
    private static final String PORTNUMBER = "1433";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "secret2023*";

    public static void main(String[] args)  {
        try {
            new Main().tryMe();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void tryMe() throws SQLException {
        Connection connection = getConnection();
        printMetadata(connection);
    }

    public Connection getConnection() throws SQLException {
        Connection conn;
        String urlString = String.format("jdbc:sqlserver://%s:%s;databaseName=%s;encrypt=false", SERVERNAME, PORTNUMBER, DBNAME);
        //String urlString =String.format("jdbc:sqlserver://%s:%s;databaseName=%s;encrypt=true;trustServerCertificate=true", SERVERNAME, PORTNUMBER, DBNAME);
        conn = DriverManager.getConnection(urlString, USERNAME, PASSWORD);
        System.out.println("Connected to database");
        return conn;
    }



    public void printMetadata(Connection connection) throws SQLException  {
        DatabaseMetaData metadata;
        metadata = connection.getMetaData();
        System.out.println("Database Product Name: " + metadata.getDatabaseProductName());
        System.out.println("Database Product Version: " + metadata.getDatabaseProductVersion());
        System.out.println("Logged User: " + metadata.getUserName());
        System.out.println("JDBC Driver: " + metadata.getDriverName());
        System.out.println("Driver Version: " + metadata.getDriverVersion());
        System.out.println("\n");
        connection.close();
    }

}
