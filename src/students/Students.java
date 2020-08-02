package students;

import SQL.SQLMethods;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Students {
	private static ResultSet result;
	private static PreparedStatement pstate;

	// Students(studentID, balance, unit_cap)

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
		if (studentID == null) return false;
		if (department == null) return false;
		if (number == null) return false;
		if (configID == null) return false;
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to insert
			/** Check if student already registered for this course */
			if (isRegistered(studentID, department, number, configID)) return false; // Return false if already
																						// registered
			/** Check if seats are open */
			if (isOpen(department, number, configID)) { // If course is open, register course
				pstate = SQLMethods.con.prepareStatement(
						"INSERT INTO Registers(studentID, department, number, configID) VALUES(?, ?, ?, ?)");
			} else { // If course is open, waitlist course
				pstate = SQLMethods.con.prepareStatement(
						"INSERT INTO Wailtlists(studentID, department, number, configID) VALUES(?, ?, ?, ?)");
			}
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
	 * Checks if a student is already registered for a certain course in
	 * Registers(studentID, department, number, configID).
	 * 
	 * @throws SQLException
	 */
	public static boolean isRegistered(String studentID, String department, String number, String configID)
			throws SQLException {
		pstate = SQLMethods.con.prepareStatement(
				"SELECT count(*) FROM Registers WHERE studentID = ? AND department = ? AND number = ? AND configID = ?");
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
	public static boolean isOpen(String department, String number, String configID) throws SQLException {
		/** Get number of registrants for a particular course */
		pstate = SQLMethods.con.prepareStatement(
				"SELECT count(*) FROM Registers WHERE department = ? AND number = ? AND configID = ?");
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
		pstate = SQLMethods.con.prepareStatement("SELECT seats FROM Configurations WHERE configID = ?");
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