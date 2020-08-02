package instructors;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import SQL.SQLMethods;

/** Instructors(instructorID, status) */
public class Instructors {
	private static ResultSet result;
	private static PreparedStatement pstate;

	/**
	 * Method to create and insert a student in the DB. Returns true if successful,
	 * otherwise false.
	 */
	public static boolean insert(String instructorID, String status) {
		// Check if inputs are null
		if (instructorID == null) return false; // Attribute instructorID is null
		if (status == null) return false; // Attribute status is null
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to insert
			pstate = SQLMethods.con.prepareStatement("INSERT INTO Instructors(instructorID, status)" + "values(?, ?)");
			pstate.setString(1, instructorID);
			pstate.setString(2, status);
			int value = pstate.executeUpdate();
			SQLMethods.closeConnection(); // Close connection
			return true; // Success
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error");
		}
		return false; // Return false as a default value
	}

	/**
	 * Method to delete a user using instructorID. Returns true if successful, otherwise
	 * false.
	 */
	public static boolean delete(String instructorID) {
		if (instructorID == null) return false; // Check if instructorID is null
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to delete
			pstate = SQLMethods.con.prepareStatement("DELETE FROM Instructors WHERE instructorID = ?");
			pstate.setString(1, instructorID);
			int value = pstate.executeUpdate();
			SQLMethods.closeConnection(); // Close connection
			return true; // Success
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error");
		}
		return false; // Return false as a default value
	}

	public static boolean updateStatus(String instructorID, String status) {
		if (instructorID == null) return false; // Check if instructorID is null
		if (status == null) return false; // Check if unit_cap is null
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to update
			pstate = SQLMethods.con.prepareStatement("UPDATE Instructors SET status = ? WHERE instructorID = ?");
			pstate.setString(1, status); // New status
			pstate.setString(2, instructorID); // instructorID of user
			int value = pstate.executeUpdate(); // Execute statement
			SQLMethods.closeConnection(); // Close connection
			return true; // Success
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error"); // Print error and exit
		}
		return false; // Return false as a default value
	}

	public static ArrayList<ArrayList<String>> searchStatus(String status) {
		ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
		if (status == null) return output; // Check if status is null, return empty list if so
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to search
			pstate = SQLMethods.con.prepareStatement("SELECT * Instructors WHERE status = ?");
			pstate.setString(1, status);
			result = pstate.executeQuery(); // Execute query
			SQLMethods.closeConnection(); // Close connection
			while (result.next()) {
				ArrayList<String> tuple = new ArrayList<String>();
				tuple.add(result.getString("instructorID"));
				tuple.add(result.getString("status"));
				output.add(tuple);
			}
			result.close(); // Close result
			return output; // Success
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error"); // Print error and exit
		}
		return output; // Return false as a default value
	}
} 
