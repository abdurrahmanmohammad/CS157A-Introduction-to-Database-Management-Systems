package teaches;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import SQL.SQLMethods;

/** Teaches(instructorID, department, number, configID) */
public class Teaches {
	private static ResultSet result;
	private static PreparedStatement pstate;

	/**
	 * Insert a taught course into DB
	 * 
	 * @param instructorID
	 * @param department
	 * @param number
	 * @param configID
	 * @return true if successful insert, else false
	 */
	public static boolean insert(String instructorID, String department, String number, String configID) {
		/** Check for invalid inputs. If any input is null, return false */
		if (instructorID == null || department == null || number == null || configID == null) return false;
		/** Insert tuple */
		SQLMethods.mysqlConnect(); // Connect to DB
		try {
			pstate = SQLMethods.con.prepareStatement("INSERT INTO Teaches VALUES (?, ?, ?, ?);");
			pstate.setString(3, instructorID);
			pstate.setString(2, department);
			pstate.setString(3, number);
			pstate.setString(4, configID);
			pstate.executeUpdate(); // Execute query
			SQLMethods.closeConnection(); // Close connection
			return true; // Successful insert
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return false; // Default value: false
	}

	/**
	 * Delete a taught course using its keys
	 * 
	 * @param instructorID
	 * @param department
	 * @param number
	 * @param configID
	 * @return true if successful delete, else false
	 */
	public static boolean delete(String instructorID, String department, String number, String configID) {
		/** Check for invalid inputs. If any input is null, return false */
		if (instructorID == null || department == null || number == null || configID == null) return false;
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to delete
			pstate = SQLMethods.con.prepareStatement(
					"DELETE FROM Teaches WHERE instructorID = ? AND department = ? AND number = ? AND configID = ?;");
			pstate.setString(3, instructorID);
			pstate.setString(2, department);
			pstate.setString(3, number);
			pstate.setString(4, configID);
			pstate.executeUpdate(); // Execute query
			SQLMethods.closeConnection(); // Close connection
			return true; // Successful insert
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return false; // Default value: false
	}

	/**
	 * Get all courses taught with instructor name and configuration information
	 * 
	 * @return
	 */
	public static ArrayList<HashMap<String, String>> getCourses() {
		ArrayList<HashMap<String, String>> output = new ArrayList<HashMap<String, String>>();
		try { // Attempt to search
			pstate = SQLMethods.con.prepareStatement(
					"SELECT * FROM Teaches JOIN Members Using (instructorID) JOIN Configurations Using (configID) ORDER BY year DESC;");
			result = pstate.executeQuery(); // Execute query
			while (result.next()) {
				HashMap<String, String> tuple = new HashMap<String, String>();
				tuple.put("term", result.getString("term"));
				tuple.put("year", Integer.toString(result.getInt("year")));
				tuple.put("department", result.getString("department"));
				tuple.put("number", result.getString("number"));
				tuple.put("firstname", result.getString("firstname"));
				tuple.put("lastname", result.getString("lastname"));
				tuple.put("days", result.getString("days"));
				tuple.put("time", result.getString("time"));
				tuple.put("room", result.getString("room"));
				output.add(tuple);
			}
			result.close(); // Close result
			return output; // Successful search
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}

		return output; // Default value: empty list
	}

	/* ############################################################ */
	/* #################### Unused Methods Below #################### */
	/* ############################################################ */

	/**
	 * Get all tuples as HashMap in the table Teaches in an ArrayList
	 * 
	 * @return all tuples in table Teaches
	 */
	public static ArrayList<HashMap<String, String>> getAll() {
		ArrayList<HashMap<String, String>> output = new ArrayList<HashMap<String, String>>();
		try { // Attempt to search
			pstate = SQLMethods.con.prepareStatement("SELECT * FROM Teaches;");
			result = pstate.executeQuery(); // Execute query
			while (result.next()) {
				HashMap<String, String> tuple = new HashMap<String, String>();
				tuple.put("instructorID", result.getString("instructorID"));
				tuple.put("department", result.getString("department"));
				tuple.put("number", result.getString("number"));
				tuple.put("configID", result.getString("configID"));
				output.add(tuple);
			}
			result.close(); // Close result
			SQLMethods.closeConnection(); // Close connection
			return output; // Successful search
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return output; // Default value: empty list
	}
}
