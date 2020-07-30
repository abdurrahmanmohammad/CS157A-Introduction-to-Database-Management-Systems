package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLMethods {
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String connection = "jdbc:mysql://localhost:3306/demo?serverTimezone=UTC";
	private static String user = "root"; // 'root' is default username
	private static String password = "root"; // 'root' is default password
	public static Connection con = null;
	
	/** Attempts to connect to DB. Exits if error. */
	public static void mysqlConnect() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(connection, user, password); // Successfully connected to database
		} catch (ClassNotFoundException e) {
			mysql_fatal_error("Couldn't load driver."); // Print error and exit
		} catch (SQLException e) {
			mysql_fatal_error("Couldn't connect to database."); // Print error and exit
		}
	}

	/** Close connection to DB */
	public static void closeConnection() {
		try {
			if (!con.isClosed()) con.close(); // Database closed successfully.
		} catch (NullPointerException e) {
			mysql_fatal_error("Couldn't load driver."); // Print error and exit
		} catch (SQLException e) {
			mysql_fatal_error("Couldn't close database."); // Print error and exit
		}
	}

	/** Customized error function */
	public static void mysql_fatal_error(String error) {
		System.out.println(error); // We want to print this to the browser
		System.exit(1); // Exit with error
	}
}
