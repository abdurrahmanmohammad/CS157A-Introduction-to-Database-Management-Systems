package administrators;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import SQL.SQLMethods;

// Administrators(ID, clearance)
public class Administrators {
	private static ResultSet result;
	private static PreparedStatement pstate;

	/**
	 * Method to create and insert a student in the DB. Returns true if successful,
	 * otherwise false.
	 */
	public static boolean insert(String ID, String clearance) {
		// Check if inputs are null
		if (ID == null) return false; // Attribute ID is null
		if (clearance == null) return false; // Attribute clearance is null
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to insert
			pstate = SQLMethods.con.prepareStatement("INSERT INTO Administrators(ID, clearance)" + "values(?, ?)");
			pstate.setString(1, ID);
			pstate.setString(2, clearance);
			int value = pstate.executeUpdate();
			SQLMethods.closeConnection(); // Close connection
			return true; // Success
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error");
		}
		return false; // Return false as a default value
	}

	/**
	 * Method to delete a user using ID. Returns true if successful, otherwise
	 * false.
	 */
	public static boolean delete(String ID) {
		if (ID == null) return false; // Check if ID is null
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to delete
			pstate = SQLMethods.con.prepareStatement("DELETE FROM Administrators WHERE ID = ?");
			pstate.setString(1, ID);
			int value = pstate.executeUpdate();
			SQLMethods.closeConnection(); // Close connection
			return true; // Success
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error");
		}
		return false; // Return false as a default value
	}

	public static boolean updateclearance(String ID, String clearance) {
		if (ID == null) return false; // Check if ID is null
		if (clearance == null) return false; // Check if unit_cap is null
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to update
			pstate = SQLMethods.con.prepareStatement("UPDATE Administrators SET clearance = ? WHERE ID = ?");
			pstate.setString(1, clearance); // New clearance
			pstate.setString(2, ID); // ID of user
			int value = pstate.executeUpdate(); // Execute statement
			SQLMethods.closeConnection(); // Close connection
			return true; // Success
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error"); // Print error and exit
		}
		return false; // Return false as a default value
	}

	public static ArrayList<ArrayList<String>> searchClearance(String clearance) {
		ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
		if (clearance == null) return output; // Check if clearance is null, return empty list if so
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to search
			pstate = SQLMethods.con.prepareStatement("SELECT * Administrators WHERE clearance = ?");
			pstate.setString(1, clearance);
			result = pstate.executeQuery(); // Execute query
			SQLMethods.closeConnection(); // Close connection
			while (result.next()) {
				ArrayList<String> tuple = new ArrayList<String>();
				tuple.add(result.getString("ID"));
				tuple.add(result.getString("clearance"));
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