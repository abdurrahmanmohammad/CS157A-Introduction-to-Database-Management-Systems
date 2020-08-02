package administrators;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import SQL.SQLMethods;

/** Administrators(adminID, clearance) */
// Clearance 1 = manage accounts
// Clearance 2 = manage courses
// Clearance 3 = manage all

public class Administrators {
	private static ResultSet result;
	private static PreparedStatement pstate;

	/**
	 * Insert an admin in Admin table
	 * 
	 * @param adminID
	 * @param clearance
	 * @return Returns true if successful,otherwise false
	 */
	public static boolean insertAdmin(String adminID, int clearance) {
		if (adminID == null || clearance < 0 || clearance > 3) return false;
		if (adminID == null || clearance < 0 || clearance > 3) return false;
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to insert
			pstate = SQLMethods.con.prepareStatement("INSERT INTO Administrators Values (?, ?);");
			pstate.setString(1, adminID);
			pstate.setInt(2, clearance);
			int rowcount = pstate.executeUpdate();
			SQLMethods.closeConnection(); // Close connection
			return (rowcount == 1); // If rowcount == 1, row successfully inserted
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return false; // Default value: false
	}

	/**
	 * Delete an admin in Admin table
	 * 
	 * @param adminID
	 * @return
	 */
	public static boolean deleteAdmin(String adminID) {
		/** Check for invalid inputs. If any input is null, return false */
		if (adminID == null) return false;
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to delete
			pstate = SQLMethods.con.prepareStatement("DELETE FROM Administrators WHERE adminID = ?;");
			pstate.setString(1, adminID);
			int rowcount = pstate.executeUpdate();
			SQLMethods.closeConnection(); // Close connection
			return (rowcount == 1); // If rowcount == 1, row successfully deleted
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return false; // Default value: false
	}

	/**
	 * Change clearance of an existing admin with specified adminID
	 * 
	 * @param adminID
	 * @param newClearance
	 * @return true if successful, false if failure
	 */
	public static boolean setClearance(String adminID, int newClearance) {
		/** Check for invalid inputs. If any input is null, return false */
		if (adminID == null || newClearance < 0 || newClearance > 3) return false;
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to update
			pstate = SQLMethods.con.prepareStatement("UPDATE Administrators SET clearance = ? WHERE adminID = ?;");
			pstate.setInt(1, newClearance); // New clearance
			pstate.setString(2, adminID); // adminID of admin
			int rowcount = pstate.executeUpdate(); // Execute statement
			SQLMethods.closeConnection(); // Close connection
			return (rowcount == 1); // If rowcount == 1, row successfully updated
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return false; // Default value: false
	}

	/**
	 * Retrieve clearance of an existing admin with specified adminID
	 * 
	 * @param adminID
	 * @return clearance of an existing admin
	 */
	public static int getClearance(String adminID) {
		/** Check for invalid inputs. If any input is null, return false */
		if (adminID == null) return -1;
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to search
			/** Search and retrieve tuple */
			pstate = SQLMethods.con.prepareStatement("SELECT * FROM Administrators WHERE adminID = ?;");
			pstate.setString(1, adminID);
			result = pstate.executeQuery(); // Execute query
			/** Extract tuple data */
			result.next();
			int clearance = result.getInt(1); // Get clearance
			result.close(); // Close result
			SQLMethods.closeConnection(); // Close connection
			return clearance; // Return clearance
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return -1; // Default value: -1
	}

	/* ############################################################ */
	/* #################### Unused Methods Below #################### */
	/* ############################################################ */

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
