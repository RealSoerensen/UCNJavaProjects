package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {

	private Connection connection = null; // the connection to the database
	private static DbConnection dbConnection = new DbConnection(); // unique instance of the class

	// The values of the constants should be read from a configuration file, etc.
	// You need to change the constants to the connection properties of your DB
	private static final String DBNAME = "DbFidoFitness";
	private static final String SERVERNAME = "localhost";
	private static final String PORTNUMBER = "1433";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "secret2023*";

	// constructor - private because of singleton pattern
	private DbConnection() {
		String urlString = String.format("jdbc:sqlserver://%s:%s;databaseName=%s;encrypt=false", SERVERNAME, PORTNUMBER,
				DBNAME);
		try {
			connection = DriverManager.getConnection(urlString, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static DbConnection getInstance() {
		if (dbConnection == null) {
			dbConnection = new DbConnection();
		}
		return dbConnection;
	}

	public Connection getConnection() {
		return connection;
	}

	/* return the generated key */
	public int executeSqlInsertWithIdentity(String sql) throws SQLException {

		int key = -1; // assumption seed and increment positives
		try (Statement s = connection.createStatement()) {
			int res = s.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			if (res > 0) {
				ResultSet rs = s.getGeneratedKeys();
				if (rs != null && rs.next()) {
					key = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return key;
	}

}
