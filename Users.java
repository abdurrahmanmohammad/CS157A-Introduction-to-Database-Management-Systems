

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// import java.sql.Statement;

// Users(ID, username, password)
public class Users {
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String connection = "jdbc:mysql://localhost:3306/demo?serverTimezone=UTC";
	private static String user = "root"; // 'root' is default username
	private static String password = "root"; // 'root' is default password
	private static Connection con = null;
	// private static Statement state = null;
	private static ResultSet result;
	private static PreparedStatement pstate;

	public static boolean authenticate(String username, String password) {
		mysqlConnect(); // Connect to DB
		try {
			pstate = con.prepareStatement("SELECT * FROM Users WHERE username = ?");
			pstate.setString(1, username); // Usernames are unique
			result = pstate.executeQuery(); // ResultSet result = pstate.executeQuery();
			result.next();
			String userPswd = result.getString("password"); // Retrieve password from result
			result.close(); // Close result
			closeConnection(); // Close connection to DB
			if (userPswd.equals(password))
				return true; // Return true if passwords match
			else
				return false; // Return false if passwords do not match
		} catch (SQLException e) {
			mysql_fatal_error("Query error."); // Print error and exit
		} catch (NullPointerException e) {
			mysql_fatal_error(e.toString()); // Print error and exit
		}
		return false; // Return false as a default value
	}

	public static int getType(String username) {
		mysqlConnect(); // Connect to DB
		try {
			// Check if user is an administrator
			pstate = con.prepareStatement(
					"SELECT COUNT(*) FROM Administrators WHERE Users.username = ? AND Administrators.ID = Users.ID");
			pstate.setString(1, username); // Sanitize input
			result = pstate.executeQuery(); // Execute query
			result.next();
			int rowcount = result.getInt(1); // Get row count
			result.close(); // Close result
			if (rowcount == 1) return 1; // User is an administrator

			// Check if user is an instructor
			pstate.setString(1, username); // Sanitize input
			result = pstate.executeQuery(); // Execute query
			result.next();
			rowcount = result.getInt(1); // Get row count
			result.close(); // Close result
			if (rowcount == 1) return 2; // User is an instructor

			// Check if user is a student
			pstate.setString(1, username); // Sanitize input
			result = pstate.executeQuery(); // Execute query
			result.next();
			rowcount = result.getInt(1); // Get row count
			result.close(); // Close result
			if (rowcount == 1) return 3; // User is an instructor

			result.close(); // Close result
			closeConnection(); // Close connection to DB

			return 0; // If username is in none of these tables, it does not exist
		} catch (SQLException e) {
			mysql_fatal_error("Query error."); // Print error and exit
		} catch (NullPointerException e) {
			mysql_fatal_error(e.toString()); // Print error and exit
		}
		return 0; // Return 0 as a default value
	}

	/**
	 * Method to create and insert a user in the DB. Returns true if successful,
	 * otherwise false.
	 */
	public static boolean insert(String username, String password) {
		// Check if inputs are null
		if (username == null) return false; // Attribute username is null
		if (password == null) return false; // Attribute password is null
		mysqlConnect(); // Connect to DB
		try { // Attempt to insert
			pstate = con.prepareStatement("INSERT INTO Users(ID, username, password)" + "values(?, ?, ?)");
			String ID = (int) ( (Math.random() * ((999999999 - 100000000) + 1)) + 100000000 ) + "";
			pstate.setString(1, ID);
			pstate.setString(2, username);
			pstate.setString(3, password);
			int value = pstate.executeUpdate();
			closeConnection(); // Close connection
			return true; // Success
		} catch (SQLException e) {
			mysql_fatal_error("Query error");
		}
		return false; // Return false as a default value
	}

	/**
	 * Method to delete a user using ID. Returns true if successful, otherwise
	 * false.
	 */
	public static boolean delete(String ID) {
		if (ID == null) return false; // Check if ID is null
		mysqlConnect(); // Connect to DB
		try { // Attempt to delete
			pstate = con.prepareStatement("DELETE FROM Users WHERE ID = ?");
			pstate.setString(1, ID);
			int value = pstate.executeUpdate();
			closeConnection(); // Close connection
			return true; // Success
		} catch (SQLException e) {
			mysql_fatal_error("Query error");
		}
		return false; // Return false as a default value
	}

	public static boolean updateUserName(String ID, String username) {
		if (ID == null) return false; // Check if ID is null
		mysqlConnect(); // Connect to DB
		try { // Attempt to update
			pstate = con.prepareStatement("UPDATE Users SET username = ? WHERE ID = ?");
			pstate.setString(1, username); // New username
			pstate.setString(2, ID); // ID of user
			int value = pstate.executeUpdate(); // Execute statement
			closeConnection(); // Close connection
			return true; // Success
		} catch (SQLException e) {
			mysql_fatal_error("Query error"); // Print error and exit
		}
		return false; // Return false as a default value
	}

	public static boolean updatePassword(String ID, String password) {
		if (ID == null) return false; // Check if ID is null
		mysqlConnect(); // Connect to DB
		try { // Attempt to update
			pstate = con.prepareStatement("UPDATE Users SET password = ? WHERE ID = ?");
			pstate.setString(1, password); // New password
			pstate.setString(2, ID); // ID of user
			int value = pstate.executeUpdate(); // Execute statement
			closeConnection(); // Close connection
			return true; // Success
		} catch (SQLException e) {
			mysql_fatal_error("Query error"); // Print error and exit
		}
		return false; // Return false as a default value
	}

	public static boolean updateEmail(String ID, String email) {
		if (ID == null) return false; // Check if ID is null
		mysqlConnect(); // Connect to DB
		try { // Attempt to update
			pstate = con.prepareStatement("UPDATE Users SET email = ? WHERE ID = ?");
			pstate.setString(1, email); // New password
			pstate.setString(2, ID); // ID of user
			int value = pstate.executeUpdate(); // Execute statement
			closeConnection(); // Close connection
			return true; // Success
		} catch (SQLException e) {
			mysql_fatal_error("Query error"); // Print error and exit
		}
		return false; // Return false as a default value
	}

	/** Attempts to connect to DB. Exits if error. */
	private static void mysqlConnect() {
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
	private static void closeConnection() {
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
