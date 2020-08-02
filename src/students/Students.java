package students;

import SQL.SQLMethods;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/** Students(studentID, balance, unit_cap) */
public class Students {
	private static ResultSet result;
	private static PreparedStatement pstate;

	/**
	 * Method to create and insert a student in the DB. Returns true if successful,
	 * otherwise false.
	 */
	public static boolean insert(String studentID, int balance, int unit_cap) {
		/** Check for invalid inputs. If any input is null, return false */
		if (studentID == null || unit_cap < 0) return false;
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to insert
			pstate = SQLMethods.con.prepareStatement("INSERT INTO Students VALUES(?, ?, ?);");
			pstate.setString(1, studentID);
			pstate.setInt(2, balance);
			pstate.setInt(3, unit_cap);
			int rowcount = pstate.executeUpdate(); // Number of rows affected
			SQLMethods.closeConnection(); // Close connection
			return (rowcount == 1); // If rowcount == 1, row successfully inserted
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return false; // Default value: false
	}

	/**
	 * Method to delete a user using studentID. Returns true if successful,
	 * otherwise false.
	 */
	public static boolean delete(String studentID) {
		/** Check for invalid inputs. If any input is null, return false */
		if (studentID == null) return false; // Check if ID is null
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to delete
			pstate = SQLMethods.con.prepareStatement("DELETE FROM Students WHERE studentID = ?;");
			pstate.setString(1, studentID);
			int rowcount = pstate.executeUpdate(); // Number of rows affected
			SQLMethods.closeConnection(); // Close connection
			return (rowcount == 1); // If rowcount == 1, row successfully inserted
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return false; // Default value: false
	}

	public static boolean updateUnitCap(String studentID, int unit_cap) {
		/** Check for invalid inputs. If any input is null, return false */
		if (studentID == null || unit_cap < 0) return false;
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to update
			pstate = SQLMethods.con.prepareStatement("UPDATE Students SET unit_cap = ? WHERE studentID = ?;");
			pstate.setInt(1, unit_cap); // New username
			pstate.setString(2, studentID); // ID of user
			int rowcount = pstate.executeUpdate(); // Number of rows affected
			SQLMethods.closeConnection(); // Close connection
			return (rowcount == 1); // If rowcount == 1, row successfully inserted
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return false; // Default value: false
	}

	public static boolean updateBalance(String studentID, int balance) {
		/** Check for invalid inputs. If any input is null, return false */
		if (studentID == null) return false;
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to update
			pstate = SQLMethods.con.prepareStatement("UPDATE Students SET balance = ? WHERE studentID = ?;");
			pstate.setInt(1, balance); // New password
			pstate.setString(2, studentID); // ID of user
			int rowcount = pstate.executeUpdate(); // Number of rows affected
			SQLMethods.closeConnection(); // Close connection
			return (rowcount == 1); // If rowcount == 1, row successfully inserted
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return false; // Default value: false
	}

	/**
	 * Search a student by its ID and return the values of its attributes as strings
	 * 
	 * @param balance
	 * @return
	 */
	public static HashMap<String, String> searchByStudentID(String studentID) {
		HashMap<String, String> output = new HashMap<String, String>();
		if (studentID == null) return output;
		try { // Attempt to search
			pstate = SQLMethods.con.prepareStatement("SELECT * Students WHERE studentID = ?;");
			pstate.setString(1, studentID);
			result = pstate.executeQuery(); // Execute query
			/** Should return only 1 student because studentID is a primary key */
			result.next();
			/** Students(studentID, balance, unit_cap) */
			output.put("studentID", result.getString("studentID"));
			output.put("balance", Integer.toString(result.getInt("balance")));
			output.put("unit_cap", Integer.toString(result.getInt("unit_cap")));
			result.close(); // Close result
			SQLMethods.closeConnection(); // Close connection
			return output; // Return student info
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error: " + e.toString()); // Print error and exit
		}
		return output; // default value
	}

	/* ############################################################ */
	/* #################### Unused Methods Below #################### */
	/* ############################################################ */

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