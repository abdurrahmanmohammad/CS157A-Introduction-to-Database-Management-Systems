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
	 * Create and insert a student in the DB
	 * 
	 * @param instructorID
	 * @param status
	 * @return Returns true if successful, otherwise false.
	 */
	public static boolean insert(String instructorID, String status) {
		/** Check for invalid inputs. If any input is null, return false */
		if (instructorID == null || status == null) return false;
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to insert
			pstate = SQLMethods.con.prepareStatement("INSERT INTO Instructors Values(?, ?);");
			pstate.setString(1, instructorID);
			pstate.setString(2, status);
			int rowcount = pstate.executeUpdate();
			SQLMethods.closeConnection(); // Close connection
			return (rowcount == 1); // If rowcount == 1, row successfully inserted
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return false; // Default value: false
	}

	/**
	 * Delete a user using instructorID
	 * 
	 * @param instructorID
	 * @return Returns true if successful, otherwise false.
	 */
	public static boolean delete(String instructorID) {
		/** Check for invalid inputs. If any input is null, return false */
		if (instructorID == null) return false; // Check if instructorID is null
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to delete
			pstate = SQLMethods.con.prepareStatement("DELETE FROM Instructors WHERE instructorID = ?;");
			pstate.setString(1, instructorID);
			int rowcount = pstate.executeUpdate();
			SQLMethods.closeConnection(); // Close connection
			return (rowcount == 1); // If rowcount == 1, row successfully inserted
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return false; // Default value: false
	}

	/**
	 * Update an instructor's status using instructorID
	 * 
	 * @param instructorID
	 * @param newStatus
	 * @return Returns true if successful, otherwise false.
	 */
	public static boolean updateStatus(String instructorID, String newStatus) {
		/** Check for invalid inputs. If any input is null, return false */
		if (instructorID == null || newStatus == null) return false;
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to update
			pstate = SQLMethods.con.prepareStatement("UPDATE Instructors SET status = ? WHERE instructorID = ?;");
			pstate.setString(1, newStatus); // New status
			pstate.setString(2, instructorID); // instructorID of user
			int rowcount = pstate.executeUpdate();
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
