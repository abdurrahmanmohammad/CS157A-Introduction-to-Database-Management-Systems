package users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import SQL.SQLMethods;

// Users(ID, username, password)
public class Users {
	private static ResultSet result;
	private static PreparedStatement pstate;

	public static boolean authenticate(String username, String password) {
		/** Check for invalid inputs. If any input is null, return false */
		if (username == null || password == null) return false;
		SQLMethods.mysqlConnect(); // Connect to DB
		try {
			pstate = SQLMethods.con.prepareStatement("SELECT COUNT(*) FROM Users WHERE username = ? AND password = ?;");
			pstate.setString(1, username); // Usernames are unique
			pstate.setString(2, password); // Check password
			result = pstate.executeQuery();
			result.next();
			int rowcount = result.getInt(1); // Get row count
			result.close(); // Close result
			SQLMethods.closeConnection(); // Close connection to DB
			return (rowcount == 1); // If rowcount == 1, username and pasword match
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error: " + e.toString()); // Print error and exit
		} catch (NullPointerException e) {
			SQLMethods.mysql_fatal_error(e.toString()); // Print error and exit
		}
		return false; // Return false as a default value
	}

	/**
	 * Returns type of member: 1 = administrator, 2 = instructor, 3 = student, 0 =
	 * none, -1 = error
	 */

	public static int getType(String username) {
		if (username == null) return -1;
		SQLMethods.mysqlConnect(); // Connect to DB
		try {
			// Check if user is an administrator
			pstate = SQLMethods.con.prepareStatement(
					"SELECT COUNT(*) FROM Administrators, Users WHERE Users.username = ? AND Administrators.ID = Users.ID;");
			pstate.setString(1, username); // Sanitize input
			result = pstate.executeQuery(); // Execute query
			result.next();
			int rowcount = result.getInt(1); // Get row count
			result.close(); // Close result
			if (rowcount == 1) return 1; // User is an administrator

			// Check if user is an instructor
			pstate = SQLMethods.con.prepareStatement(
					"SELECT COUNT(*) FROM Instructors, Users WHERE Users.username = ? AND Instructors.ID = Users.ID;");
			pstate.setString(1, username); // Sanitize input
			result = pstate.executeQuery(); // Execute query
			result.next();
			rowcount = result.getInt(1); // Get row count
			result.close(); // Close result
			if (rowcount == 1) return 2; // User is an instructor

			// Check if user is a student
			pstate = SQLMethods.con.prepareStatement(
					"SELECT COUNT(*) FROM Students, Users WHERE Users.username = ? AND Students.ID = Users.ID;");
			pstate.setString(1, username); // Sanitize input
			result = pstate.executeQuery(); // Execute query
			result.next();
			rowcount = result.getInt(1); // Get row count
			result.close(); // Close result
			if (rowcount == 1) return 3; // User is an instructor

			result.close(); // Close result
			SQLMethods.closeConnection(); // Close connection to DB

			return 0; // If username is in none of these tables, it does not exist
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error: " + e.toString()); // Print error and exit
		} catch (NullPointerException e) {
			SQLMethods.mysql_fatal_error(e.toString()); // Print error and exit
		}
		return 0; // Return 0 as a default value
	}

	/**
	 * Method to create and insert a user in the DB. Returns true if successful,
	 * otherwise false.
	 */
	public static boolean insert(String ID, String username, String password) {
		/** Check for invalid inputs. If any input is null, return false */
		if (username == null || password == null) return false;
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to insert
			pstate = SQLMethods.con.prepareStatement("INSERT INTO Users VALUES (?, ?, ?);");
			pstate.setString(1, ID);
			pstate.setString(2, username);
			pstate.setString(3, password);
			int rowcount = pstate.executeUpdate(); // Number of rows affected
			SQLMethods.closeConnection(); // Close connection
			return (rowcount == 1); // If rowcount == 1, row successfully inserted
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return false; // Default value: false
	}

	/**
	 * Method to delete a user using ID. Returns true if successful, otherwise
	 * false.
	 */
	public static boolean delete(String ID) {
		/** Check for invalid inputs. If any input is null, return false */
		if (ID == null) return false; // Check if ID is null
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to delete
			pstate = SQLMethods.con.prepareStatement("DELETE FROM Users WHERE ID = ?;");
			pstate.setString(1, ID);
			int rowcount = pstate.executeUpdate(); // Number of rows affected
			SQLMethods.closeConnection(); // Close connection
			return (rowcount == 1); // If rowcount == 1, row successfully inserted
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return false; // Default value: false
	}

	public static HashMap<String, String> search(String ID) {
		HashMap<String, String> output = new HashMap<String, String>();
		if (ID == null) return output; // Check if ID is null
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to search
			/** Search for user using ID */
			pstate = SQLMethods.con.prepareStatement("SELECT * FROM Users WHERE ID = ?");
			pstate.setString(1, ID); // ID of member
			result = pstate.executeQuery(); // Execute query
			/** Extract member data */
			result.next();
			output.put("ID", result.getString("ID"));
			output.put("username", result.getString("username"));
			output.put("password", result.getString("password"));
			result.close(); // Close result
			SQLMethods.closeConnection(); // Close connection
			return output; // Success
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return output; // Default value: empty HashMap
	}

	public static boolean update(String ID, String username, String password) {
		/** Check for invalid inputs. If any input is null, return false */
		if (ID == null) return false; // Check if ID is null
		SQLMethods.mysqlConnect(); // Connect to DB
		try {
			HashMap<String, String> member = search(ID); // Search for member to update
			if (username == null) username = member.get("username"); // Determine if we want to change old value
			if (password == null) password = member.get("password"); // Determine if we want to change old value
			pstate = SQLMethods.con.prepareStatement("UPDATE Users SET username = ? AND password = ? WHERE ID = ?;");
			pstate.setString(1, username);
			pstate.setString(2, password);
			pstate.setString(3, ID);
			int rowcount = pstate.executeUpdate(); // Number of rows affected
			SQLMethods.closeConnection(); // Close connection
			return (rowcount == 1); // If rowcount == 1, row successfully inserted
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return false; // Default value: false
	}

	/* ############################################################ */
	/* #################### Unused Methods Below #################### */
	/* ############################################################ */

	public static boolean updateUserName(String ID, String username) {
		if (ID == null) return false; // Check if ID is null
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to update
			pstate = SQLMethods.con.prepareStatement("UPDATE Users SET username = ? WHERE ID = ?");
			pstate.setString(1, username); // New username
			pstate.setString(2, ID); // ID of user
			int value = pstate.executeUpdate(); // Execute statement
			SQLMethods.closeConnection(); // Close connection
			return true; // Success
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error"); // Print error and exit
		}
		return false; // Return false as a default value
	}

	public static boolean updatePassword(String ID, String password) {
		if (ID == null) return false; // Check if ID is null
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to update
			pstate = SQLMethods.con.prepareStatement("UPDATE Users SET password = ? WHERE ID = ?");
			pstate.setString(1, password); // New password
			pstate.setString(2, ID); // ID of user
			int value = pstate.executeUpdate(); // Execute statement
			SQLMethods.closeConnection(); // Close connection
			return true; // Success
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error"); // Print error and exit
		}
		return false; // Return false as a default value
	}

	public static boolean updateEmail(String ID, String email) {
		if (ID == null) return false; // Check if ID is null
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to update
			pstate = SQLMethods.con.prepareStatement("UPDATE Users SET email = ? WHERE ID = ?");
			pstate.setString(1, email); // New password
			pstate.setString(2, ID); // ID of user
			int value = pstate.executeUpdate(); // Execute statement
			SQLMethods.closeConnection(); // Close connection
			return true; // Success
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error"); // Print error and exit
		}
		return false; // Return false as a default value
	}

}
