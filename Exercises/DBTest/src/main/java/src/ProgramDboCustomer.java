package src;

import java.sql.*;

public class ProgramDboCustomer {

    // The values of the constants should be read from a configuration file, etc.
    // You need to change the constants to the connection properties of your DB
    private static final String DBNAME = "TutorialDB";
    private static final String SERVERNAME = "localhost";
    private static final String PORTNUMBER = "1433";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "secret2023*";

    private static final String SQL_QUERY = "SELECT * FROM dbo.Customers";

    public static void main(String[] args) {
        try {
            new ProgramDboCustomer().tryMe();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void tryMe() throws SQLException {

        Connection connection = getConnection();
        printMetadata(connection);
        queryDatabase(connection);
        connection.close();
    }

    public Connection getConnection() throws SQLException {

        String urlString = String.format("jdbc:sqlserver://%s:%s;databaseName=%s;encrypt=false", SERVERNAME, PORTNUMBER,
                DBNAME);
        return DriverManager.getConnection(urlString, USERNAME, PASSWORD);
    }

    public void printMetadata(Connection connection) throws SQLException {
        DatabaseMetaData metadata;
        metadata = connection.getMetaData();
        System.out.println("Database Product Name: " + metadata.getDatabaseProductName());
        System.out.println("Database Product Version: " + metadata.getDatabaseProductVersion());
        System.out.println("Logged User: " + metadata.getUserName());
        System.out.println("JDBC Driver: " + metadata.getDriverName());
        System.out.println("Driver Version: " + metadata.getDriverVersion());
        System.out.println("\n");

    }

    // Depends on the Table, Columns
    public void queryDatabase(Connection connection) throws SQLException {

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(SQL_QUERY);
        System.out.println("Table Customers");

        while (rs.next()) {
            String name = rs.getString(2);
            System.out.println(name);
            String email = rs.getString(4);
            System.out.println("--- " + email);
        }
        stmt.close();
    }

}
