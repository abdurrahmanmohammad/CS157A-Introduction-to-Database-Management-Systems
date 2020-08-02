package registers;

import SQL.SQLMethods;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/** Registers(studentID, department, number, configID) */
public class Registers {
	private static ResultSet result;
	private static PreparedStatement pstate;

	/**
	 * Method that allows students to register for a course. Method adds values to
	 * table Registers(studentID, department, number, configID) if course is open
	 * and student has not already registered for this course. If seats are filled,
	 * the course is waitlisted.
	 * 
	 * @param studentID
	 * @param department
	 * @param number
	 * @param configID
	 * @return true if successful, false if failure
	 */
	public static boolean register(String studentID, String department, String number, String configID) {
		/** Check for invalid inputs. If any input is null, return false */
		if (studentID == null || department == null || number == null || configID == null) return false;
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to insert
			/** Check if student already registered for this course */
			// Return false if already registered
			if (isRegistered(studentID, department, number, configID)) return false;
			/** Check if seats are open */
			if (isOpen(department, number, configID)) { // If course is open, register for course
				pstate = SQLMethods.con.prepareStatement(
						"INSERT INTO Registers(studentID, department, number, configID) VALUES(?, ?, ?, ?);");
			} else { // If course is not open, waitlist course
				pstate = SQLMethods.con.prepareStatement(
						"INSERT INTO Wailtlists(studentID, department, number, configID) VALUES(?, ?, ?, ?);");
			}
			pstate.setString(1, studentID);
			pstate.setString(2, department);
			pstate.setString(3, number);
			pstate.setString(4, configID);
			int rowcount = pstate.executeUpdate(); // Number of rows affected
			SQLMethods.closeConnection(); // Close connection
			return (rowcount == 1); // If rowcount == 1, row successfully inserted
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return false; // Default value: false
	}

	/**
	 * Checks if a student is already registered for a certain course in
	 * Registers(studentID, department, number, configID).
	 * 
	 * @throws SQLException
	 */
	private static boolean isRegistered(String studentID, String department, String number, String configID)
			throws SQLException {
		pstate = SQLMethods.con.prepareStatement(
				"SELECT Count(*) FROM Registers WHERE studentID = ? AND department = ? AND number = ? AND configID = ?;");
		pstate.setString(1, studentID);
		pstate.setString(2, department);
		pstate.setString(3, number);
		pstate.setString(4, configID);
		result = pstate.executeQuery();
		result.next();
		int rowcount = result.getInt(1);
		result.close(); // Close result
		return (rowcount == 1); // True if already registered, false if not registered
	}

	/**
	 * Checks if a course is open by counting the number of registered students and
	 * comparing them with the seats available for the course's configuration.
	 */
	private static boolean isOpen(String department, String number, String configID) throws SQLException {
		/** Get number of registrants for a particular course */
		pstate = SQLMethods.con.prepareStatement(
				"SELECT Count(*) FROM Registers WHERE department = ? AND number = ? AND configID = ?;");
		pstate.setString(1, department);
		pstate.setString(2, number);
		pstate.setString(3, configID);
		result = pstate.executeQuery();
		result.next();
		int rowcount = result.getInt(1);
		result.close();
		/**
		 * Compare number of registrants with the number of available seats for a course
		 */
		return (rowcount <= getSeats(configID)); // True if registrants < seats, otherwise false
	}

	/** Gets the seats for a particular configuration */
	public static int getSeats(String configID) throws SQLException {
		pstate = SQLMethods.con.prepareStatement("SELECT seats FROM Configurations WHERE configID = ?;");
		pstate.setString(1, configID);
		result = pstate.executeQuery(); // Execute query
		result.next();
		int rowcount = result.getInt(1);
		result.close();
		return rowcount;
	}

	/**
	 * Method that allows students to drop a course. Remove course from Waitlists
	 * and Registers.
	 * 
	 * @param department
	 * @param number
	 * @return true if successful, false if failure
	 */
	public static boolean drop(String studentID, String department, String number, String configID) {
		if (studentID == null) return false;
		if (department == null) return false;
		if (number == null) return false;
		if (configID == null) return false;
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to delete
			pstate = SQLMethods.con.prepareStatement(
					"DELETE FROM Registers, Waitlists WHERE studentID = ? AND department = ? AND number = ? AND configID = ?;");
			pstate.setString(1, studentID);
			pstate.setString(2, department);
			pstate.setString(3, number);
			pstate.setString(4, configID);
			int value = pstate.executeUpdate();
			SQLMethods.closeConnection(); // Close connection
			return true; // Success
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return false; // Return false as a default value
	}

	/**
	 * Returns an ArrayList of all courses registered by a student identified by
	 * studentID
	 * 
	 * @param studentID
	 * @return
	 */
	public static ArrayList<HashMap<String, String>> viewRegisteredCourses(String studentID) {
		ArrayList<HashMap<String, String>> output = new ArrayList<HashMap<String, String>>();
		/** Check for invalid inputs. If any input is null, return false */
		if (studentID == null) return output; // Check if studentID is null, return empty list if so
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to search
			/** Search and retrieve tuple */
			pstate = SQLMethods.con.prepareStatement(
					"SELECT * FROM Registers JOIN Configurations Using (configID) WHERE studentID = ? ORDER BY year DESC;");
			pstate.setString(1, studentID);
			result = pstate.executeQuery(); // Execute query
			/** Extract tuple data */
			while (result.next()) {
				HashMap<String, String> tuple = new HashMap<String, String>();
				tuple.put("term", result.getString("term"));
				tuple.put("year", result.getString("year"));
				tuple.put("department", result.getString("department"));
				tuple.put("number", result.getString("number"));
				tuple.put("days", result.getString("days"));
				tuple.put("time", result.getString("time"));
				tuple.put("room", result.getString("room"));
				output.add(tuple);
			}
			result.close(); // Close result
			SQLMethods.closeConnection(); // Close connection
			return output; // Return output
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return output; // Return false as a default value
	}

	/**
	 * Returns an ArrayList of all courses waitlisted by a student identified by
	 * studentID
	 * 
	 * @param studentID
	 * @return
	 */
	public static ArrayList<HashMap<String, String>> viewWaitlistedCourses(String studentID) {
		ArrayList<HashMap<String, String>> output = new ArrayList<HashMap<String, String>>();
		/** Check for invalid inputs. If any input is null, return false */
		if (studentID == null) return output; // Check if studentID is null, return empty list if so
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to search
			/** Search and retrieve tuple */
			pstate = SQLMethods.con.prepareStatement(
					"SELECT * FROM Waitlists JOIN Configurations Using (configID) WHERE studentID = ? ORDER BY year DESC;");
			pstate.setString(1, studentID);
			result = pstate.executeQuery(); // Execute query
			/** Extract tuple data */
			while (result.next()) {
				HashMap<String, String> tuple = new HashMap<String, String>();
				tuple.put("term", result.getString("term"));
				tuple.put("year", result.getString("year"));
				tuple.put("department", result.getString("department"));
				tuple.put("number", result.getString("number"));
				tuple.put("days", result.getString("days"));
				tuple.put("time", result.getString("time"));
				tuple.put("room", result.getString("room"));
				output.add(tuple);
			}
			result.close(); // Close result
			SQLMethods.closeConnection(); // Close connection
			return output; // Return output
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return output; // Return false as a default value
	}

}
