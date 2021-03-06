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
	public static boolean register(String studentID, String department, String number, int configID) {
		/** Check for invalid inputs. If any input is null, return false */
		if (studentID == null || department == null || number == null || configID < 0) return false;
		/** Check if student already registered for this course */
		if (isRegistered(studentID, department, number, configID)) return false;
		/** Check if student already waitlisted this course */
		if (isWaitlisted(studentID, department, number, configID)) return false;
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to insert
			/** Check if seats are open */
			// If course is open, register for course
			// If course is not open, waitlist course
			if (isOpen(department, number, configID))
				pstate = SQLMethods.con.prepareStatement("INSERT INTO Registers VALUES (?, ?, ?, ?);");
			else
				pstate = SQLMethods.con.prepareStatement("INSERT INTO Wailtlists VALUES (?, ?, ?, ?);");
			pstate.setString(1, studentID);
			pstate.setString(2, department);
			pstate.setString(3, number);
			pstate.setInt(4, configID);
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
	private static boolean isRegistered(String studentID, String department, String number, int configID) {
		SQLMethods.mysqlConnect(); // Connect to DB
		try {
			pstate = SQLMethods.con.prepareStatement(
					"SELECT Count(*) FROM Registers WHERE studentID = ? AND department = ? AND number = ? AND configID = ?;");
			pstate.setString(1, studentID);
			pstate.setString(2, department);
			pstate.setString(3, number);
			pstate.setInt(4, configID);
			result = pstate.executeQuery();
			result.next();
			int rowcount = result.getInt(1);
			result.close(); // Close result
			return (rowcount == 1); // True if already registered, false if not registered
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
	private static boolean isWaitlisted(String studentID, String department, String number, int configID) {
		SQLMethods.mysqlConnect(); // Connect to DB
		try {
			pstate = SQLMethods.con.prepareStatement(
					"SELECT Count(*) FROM Waitlists WHERE studentID = ? AND department = ? AND number = ? AND configID = ?;");
			pstate.setString(1, studentID);
			pstate.setString(2, department);
			pstate.setString(3, number);
			pstate.setInt(4, configID);
			result = pstate.executeQuery();
			result.next();
			int rowcount = result.getInt(1);
			result.close(); // Close result
			return (rowcount == 1); // True if already registered, false if not registered
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return false; // Default value: false
	}

	/**
	 * Checks if a course is open by counting the number of registered students and
	 * comparing them with the seats available for the course's configuration.
	 */
	private static boolean isOpen(String department, String number, int configID) throws SQLException {
		/** Get number of registrants for a particular course */
		pstate = SQLMethods.con.prepareStatement(
				"SELECT Count(*) FROM Registers WHERE department = ? AND number = ? AND configID = ?;");
		pstate.setString(1, department);
		pstate.setString(2, number);
		pstate.setInt(3, configID);
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
	public static int getSeats(int configID) throws SQLException {
		pstate = SQLMethods.con.prepareStatement("SELECT seats FROM Configurations WHERE configID = ?;");
		pstate.setInt(1, configID);
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
	public static boolean drop(String studentID, String department, String number, int configID) {
		if (studentID == null || department == null | number == null || configID < 0) return false;
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to delete
			/**
			 * Check if student is in Registers. If so, we check else statement. If not, we
			 * have another student waiting on waitlist, so we move waiting student to
			 * Registers and delete old student.
			 */
			int value = 0;
			if (isWaitlisted(studentID, department, number, configID)) { // If student is on waitlist
				pstate = SQLMethods.con.prepareStatement(
						"DELETE FROM Waitlists WHERE studentID = ? AND department = ? AND number = ? AND configID = ?;");
				pstate.setString(1, studentID);
				pstate.setString(2, department);
				pstate.setString(3, number);
				pstate.setInt(4, configID);
				value = pstate.executeUpdate();
			} else {
				pstate = SQLMethods.con.prepareStatement(
						"DELETE FROM Registers WHERE studentID = ? AND department = ? AND number = ? AND configID = ?;");
				pstate.setString(1, studentID);
				pstate.setString(2, department);
				pstate.setString(3, number);
				pstate.setInt(4, configID);
				value = pstate.executeUpdate();

				/** Adjust the watlist if needed */
				adjustWaitlist(studentID, studentID, number, configID);
			}
			SQLMethods.closeConnection(); // Close connection
			return true; // Success
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return false; // Return false as a default value
	}

	/**
	 * If someone is on the Registers and drops, the next person on the waitlist
	 * will get that course. Bump up the waitlist.
	 * 
	 * @param department
	 * @param number
	 * @param configID
	 * @throws SQLException
	 */
	private static void adjustWaitlist(String oldStudentID, String department, String number, int configID)
			throws SQLException {
		/** Find waiting student on top of waitlist */
		pstate = SQLMethods.con.prepareStatement(
				"SELECT studentID FROM Waitlists WHERE department = ? AND number = ? AND configID = ?;");
		pstate.setString(1, department);
		pstate.setString(2, number);
		pstate.setInt(3, configID);
		result = pstate.executeQuery(); // Execute query
		if (result.next() == false) return; // Return back if empty
		String newStudentID = result.getString(1); // Get first studentID in list

		/** Register new student from waitlist */
		register(newStudentID, department, number, configID); // Register student who was on top of waitlist

		/** Remove new student from waitlist */
		pstate = SQLMethods.con.prepareStatement(
				"DELETE FROM Waitlists WHERE studentID = ? AND department = ? AND number = ? AND configID = ?;");
		pstate.setString(1, newStudentID);
		pstate.setString(2, department);
		pstate.setString(3, number);
		pstate.setInt(4, configID);
		return;
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
				tuple.put("configID", Integer.toString(result.getInt("configID")));
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

	/**
	 * Drop all courses (registered and waitlisted)
	 * 
	 * @param studentID
	 * @return
	 */
	public static boolean dropAll(String studentID) {
		/** Check for invalid inputs. If any input is null, return false */
		if (studentID == null) return false;
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to delete
			pstate = SQLMethods.con.prepareStatement("DELETE FROM Registers WHERE studentID = ?;");
			pstate.setString(1, studentID);
			int rowcount = pstate.executeUpdate();

			pstate = SQLMethods.con.prepareStatement("DELETE FROM Waitlists WHERE studentID = ?;");
			pstate.setString(1, studentID);
			rowcount += pstate.executeUpdate(); // Execute query

			SQLMethods.closeConnection(); // Close connection
			return (rowcount == 2);
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return false; // Default value: false

	}

	public static boolean updateID(String oldStudentID, String newStudentID) {
		SQLMethods.mysqlConnect(); // Connect to DB
		try {
			/** Update ID */
			pstate = SQLMethods.con.prepareStatement("UPDATE Registers SET studentID = ? WHERE studentID = ?;");
			pstate.setString(1, newStudentID);
			pstate.setString(2, oldStudentID);
			int rowcount = pstate.executeUpdate();

			pstate = SQLMethods.con.prepareStatement("UPDATE Waitlists SET studentID = ? WHERE studentID = ?;");
			pstate.setString(1, newStudentID);
			pstate.setString(2, oldStudentID);
			rowcount += pstate.executeUpdate();

			SQLMethods.closeConnection(); // Close connection
			return (rowcount == 2);
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return false; // Default value: false
	}

	public static boolean updateConfigID(int oldConfigID, int newConfigID) {
		SQLMethods.mysqlConnect(); // Connect to DB
		try {
			pstate = SQLMethods.con.prepareStatement("UPDATE Registers SET configID = ? WHERE configID = ?;");
			pstate.setInt(1, newConfigID);
			pstate.setInt(2, oldConfigID);
			int rowcount = pstate.executeUpdate();

			pstate = SQLMethods.con.prepareStatement("UPDATE Waitlists SET configID = ? WHERE configID = ?;");
			pstate.setInt(1, newConfigID);
			pstate.setInt(2, oldConfigID);
			rowcount += pstate.executeUpdate();

			SQLMethods.closeConnection(); // Close connection
			return (rowcount == 2);
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return false; // Default value: false
	}

	public static boolean updateCourse(String oldDept, String oldNum, String newDept, String newNum) {
		SQLMethods.mysqlConnect(); // Connect to DB
		try {
			pstate = SQLMethods.con.prepareStatement(
					"UPDATE Registers SET department = ?, number = ? WHERE department = ? AND number = ?;");
			pstate.setString(1, newDept);
			pstate.setString(2, newNum);
			pstate.setString(3, oldDept);
			pstate.setString(4, oldNum);
			int rowcount = pstate.executeUpdate();

			pstate = SQLMethods.con.prepareStatement(
					"UPDATE Waitlists SET department = ?, number = ? WHERE department = ? AND number = ?;");
			pstate.setString(1, newDept);
			pstate.setString(2, newNum);
			pstate.setString(3, oldDept);
			pstate.setString(4, oldNum);
			rowcount += pstate.executeUpdate();
			SQLMethods.closeConnection(); // Close connection
			return (rowcount == 2); // If rowcount == 1, row successfully updated
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return false; // Default value: false
	}

	public static ArrayList<HashMap<String, String>> getRegisteredStudents(String department, String number,
			int configID) {
		ArrayList<HashMap<String, String>> output = new ArrayList<HashMap<String, String>>();
		SQLMethods.mysqlConnect(); // Connect to DB
		try {
			pstate = SQLMethods.con.prepareStatement(
					"SELECT * FROM Registers JOIN Members ON studentID = ID WHERE department = ? AND number = ? AND configID = ?;");
			pstate.setString(1, department);
			pstate.setString(2, number);
			pstate.setInt(3, configID);
			result = pstate.executeQuery(); // Execute query
			/** Extract tuple data */
			while (result.next()) {
				HashMap<String, String> tuple = new HashMap<String, String>();
				tuple.put("studentID", result.getString("studentID"));
				tuple.put("firstname", result.getString("firstname"));
				tuple.put("lastname", result.getString("lastname"));
				output.add(tuple);
			}
			result.close(); // Close result
			SQLMethods.closeConnection(); // Close connection
			return output; // Return output
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return output;
	}

	public static ArrayList<HashMap<String, String>> getWaitlistedStudents(String department, String number,
			int configID) {
		ArrayList<HashMap<String, String>> output = new ArrayList<HashMap<String, String>>();
		SQLMethods.mysqlConnect(); // Connect to DB
		try {
			pstate = SQLMethods.con.prepareStatement(
					"SELECT * FROM Waitlists JOIN Members ON studentID = ID WHERE department = ? AND number = ? AND configID = ?;");
			pstate.setString(1, department);
			pstate.setString(2, number);
			pstate.setInt(3, configID);
			result = pstate.executeQuery(); // Execute query
			/** Extract tuple data */
			while (result.next()) {
				HashMap<String, String> tuple = new HashMap<String, String>();
				tuple.put("studentID", result.getString("studentID"));
				tuple.put("firstname", result.getString("firstname"));
				tuple.put("lastname", result.getString("lastname"));
				output.add(tuple);
			}
			result.close(); // Close result
			SQLMethods.closeConnection(); // Close connection
			return output; // Return output
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return output;
	}

	public static boolean overrideRegister(String studentID, String department, String number, int configID) {
		/** Check for invalid inputs. If any input is null, return false */
		if (studentID == null || department == null || number == null || configID < 0) return false;
		/** Check if student already registered for this course */
		if (isRegistered(studentID, department, number, configID)) return false;
		drop(studentID, department, number, configID); // Drop student from waitlist if possible
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to insert
				// Ignore if seats are filled
			pstate = SQLMethods.con.prepareStatement("INSERT INTO Registers VALUES (?, ?, ?, ?);");
			pstate.setString(1, studentID);
			pstate.setString(2, department);
			pstate.setString(3, number);
			pstate.setInt(4, configID);
			int rowcount = pstate.executeUpdate(); // Number of rows affected
			SQLMethods.closeConnection(); // Close connection
			return (rowcount == 1); // If rowcount == 1, row successfully inserted
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return false; // Default value: false
	}
}
