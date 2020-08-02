package students;

import SQL.SQLMethods;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Students {
	private static ResultSet result;
	private static PreparedStatement pstate;
	
	/**
	 * Method to create and insert a student in the DB. Returns true if successful,
	 * otherwise false.
	 */
	public static boolean insert(String studentID, int balance, String unit_cap) {
		// Check if inputs are null
		if (studentID == null) return false; // Attribute ID is null
		if (unit_cap == null) return false; // Attribute unit_cap is null
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to insert
			pstate = SQLMethods.con.prepareStatement("INSERT INTO Students VALUES(?, ?, ?)");
			pstate.setString(1, studentID);
			pstate.setInt(2, balance);
			pstate.setString(3, unit_cap);
			int value = pstate.executeUpdate();
			SQLMethods.closeConnection(); // Close connection
			return true; // Success
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error");
		}
		return false; // Return false as a default value
	}

	/**
	 * Method to delete a user using studentID. Returns true if successful,
	 * otherwise false.
	 */
	public static boolean delete(String studentID) {
		if (studentID == null) return false; // Check if ID is null
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to delete
			pstate = SQLMethods.con.prepareStatement("DELETE FROM Students WHERE studentID = ?");
			pstate.setString(1, studentID);
			int value = pstate.executeUpdate();
			SQLMethods.closeConnection(); // Close connection
			return true; // Success
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error");
		}
		return false; // Return false as a default value
	}

	public static boolean updateUnitCap(String studentID, String unit_cap) {
		if (studentID == null) return false; // Check if ID is null
		if (unit_cap == null) return false; // Check if unit_cap is null
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to update
			pstate = SQLMethods.con.prepareStatement("UPDATE Students SET unit_cap = ? WHERE studentID = ?");
			pstate.setString(1, unit_cap); // New username
			pstate.setString(2, studentID); // ID of user
			int value = pstate.executeUpdate(); // Execute statement
			SQLMethods.closeConnection(); // Close connection
			return true; // Success
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error"); // Print error and exit
		}
		return false; // Return false as a default value
	}

	public static boolean updateBalance(String studentID, int balance) {
		if (studentID == null) return false; // Check if ID is null
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to update
			pstate = SQLMethods.con.prepareStatement("UPDATE Students SET balance = ? WHERE studentID = ?");
			pstate.setInt(1, balance); // New password
			pstate.setString(2, studentID); // ID of user
			int value = pstate.executeUpdate(); // Execute statement
			SQLMethods.closeConnection(); // Close connection
			return true; // Success
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error"); // Print error and exit
		}
		return false; // Return false as a default value
	}

	/**
	 * Search a student by its ID and return the values of its attributes as strings
	 * in a linked list.
	 * 
	 * @param balance
	 * @return
	 */
	public static ArrayList<String> searchByStudentID(String studentID) {
		ArrayList<String> output = new ArrayList<String>();
		if (studentID == null) return output;
		try { // Attempt to search
			pstate = SQLMethods.con.prepareStatement("SELECT * Students WHERE studentID = ?");
			pstate.setString(1, studentID);
			result = pstate.executeQuery(); // Execute query
			SQLMethods.closeConnection(); // Close connection
			/** Should return only 1 student because studentID is a primary key */
			result.next();
			output.add(result.getString("ID"));
			output.add(result.getString("balance"));
			output.add(result.getString("unit_cap"));
			result.close(); // Close result
			return output; // Success
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error"); // Print error and exit
		}
		return output; // default value
	}

	public static ArrayList<ArrayList<String>> searchByBalance(int balance) {
		ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to search
			pstate = SQLMethods.con.prepareStatement("SELECT * Students WHERE balance = ?");
			pstate.setInt(1, balance);
			result = pstate.executeQuery(); // Execute query
			SQLMethods.closeConnection(); // Close connection
			while (result.next()) {
				ArrayList<String> tuple = new ArrayList<String>();
				tuple.add(result.getString("studentID"));
				tuple.add(result.getString("balance"));
				tuple.add(result.getString("unit_cap"));
				output.add(tuple);
			}
			result.close(); // Close result
			return output; // Success
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error"); // Print error and exit
		}
		return output; // default value
	}

	public static ArrayList<ArrayList<String>> searchByUnitCap(String unit_cap) {
		ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
		if (unit_cap == null) return output; // Check if unit_cap is null, return empty list if so
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to search
			pstate = SQLMethods.con.prepareStatement("SELECT * FROM Students WHERE unit_cap = ?");
			pstate.setString(1, unit_cap);
			result = pstate.executeQuery(); // Execute query
			SQLMethods.closeConnection(); // Close connection
			while (result.next()) {
				ArrayList<String> tuple = new ArrayList<String>();
				tuple.add(result.getString("studentID"));
				tuple.add(result.getString("balance"));
				tuple.add(result.getString("unit_cap"));
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