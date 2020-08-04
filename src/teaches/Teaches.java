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
	public static boolean insert(String instructorID, String department, String number, int configID) {
		/** Check for invalid inputs. If any input is null, return false */
		if (instructorID == null || department == null || number == null || configID < 0) return false;
		/** Insert tuple */
		SQLMethods.mysqlConnect(); // Connect to DB
		try {
			// Check if course is already being taught
			if (isTeaching(department, number, configID)) return false;
			pstate = SQLMethods.con.prepareStatement("INSERT INTO Teaches VALUES (?, ?, ?, ?);");
			pstate.setString(1, instructorID);
			pstate.setString(2, department);
			pstate.setString(3, number);
			pstate.setInt(4, configID);
			pstate.executeUpdate(); // Execute query
			SQLMethods.closeConnection(); // Close connection
			return true; // Successful insert
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return false; // Default value: false
	}

	private static boolean isTeaching(String department, String number, int configID) throws SQLException {
		/** Check for invalid inputs. If any input is null, return false */
		if (department == null || number == null || configID < 0) return false;
		pstate = SQLMethods.con
				.prepareStatement("SELECT COUNT(*) FROM Teaches WHERE department = ? AND number = ? AND configID = ?;");
		pstate.setString(1, department);
		pstate.setString(2, number);
		pstate.setInt(3, configID);
		result = pstate.executeQuery();
		result.next();
		int rowcount = result.getInt(1); // Get row count
		result.close(); // Close result
		return (rowcount != 0); // If rowcount == 0, course is not taught
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
	public static boolean delete(String instructorID, String department, String number, int configID) {
		/** Check for invalid inputs. If any input is null, return false */
		if (instructorID == null || department == null || number == null || configID < 0) return false;
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to delete
			pstate = SQLMethods.con.prepareStatement(
					"DELETE FROM Teaches WHERE instructorID = ? AND department = ? AND number = ? AND configID = ?;");
			pstate.setString(1, instructorID);
			pstate.setString(2, department);
			pstate.setString(3, number);
			pstate.setInt(4, configID);
			int rowcount = pstate.executeUpdate(); // Execute query

			// When course is dropped, remove registered and waitlisted students
			pstate = SQLMethods.con
					.prepareStatement("DELETE FROM Registers WHERE department = ? AND number = ? AND configID = ?;");
			pstate.setString(1, department);
			pstate.setString(2, number);
			pstate.setInt(3, configID);
			rowcount += pstate.executeUpdate(); // Execute query

			pstate = SQLMethods.con
					.prepareStatement("DELETE FROM Waitlists WHERE department = ? AND number = ? AND configID = ?;");
			pstate.setString(1, department);
			pstate.setString(2, number);
			pstate.setInt(3, configID);
			rowcount += pstate.executeUpdate(); // Execute query

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
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to search
			pstate = SQLMethods.con.prepareStatement(
					"SELECT * FROM Teaches JOIN Members ON instructorID = ID JOIN Configurations Using (configID) ORDER BY year DESC;");
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
				tuple.put("configID", Integer.toString(result.getInt("configID")));
				output.add(tuple);
			}
			result.close(); // Close result
			return output; // Successful search
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return output; // Default value: empty list
	}

	public static boolean dropAll(String instructorID) {
		/** Check for invalid inputs. If any input is null, return false */
		if (instructorID == null) return false;
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to delete
			pstate = SQLMethods.con.prepareStatement("DELETE FROM Teaches WHERE instructorID = ?;");
			pstate.setString(1, instructorID);
			pstate.executeUpdate(); // Execute query
			SQLMethods.closeConnection(); // Close connection
			return true; // Successful insert
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return false; // Default value: false

	}

	public static ArrayList<HashMap<String, String>> viewTaughtCourses(String instructorID) {
		ArrayList<HashMap<String, String>> output = new ArrayList<HashMap<String, String>>();
		/** Check for invalid inputs. If any input is null, return false */
		if (instructorID == null) return output; // Check if studentID is null, return empty list if so
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to search
			/** Search and retrieve tuple */
			pstate = SQLMethods.con.prepareStatement(
					"SELECT * FROM Teaches JOIN Configurations Using (configID) WHERE instructorID = ? ORDER BY year DESC;");
			pstate.setString(1, instructorID);
			result = pstate.executeQuery(); // Execute query
			/** Extract tuple data */
			while (result.next()) {
				HashMap<String, String> tuple = new HashMap<String, String>();
				tuple.put("instructorID", result.getString("instructorID"));
				tuple.put("department", result.getString("department"));
				tuple.put("number", result.getString("number"));
				tuple.put("term", result.getString("term"));
				tuple.put("year", result.getString("year"));
				tuple.put("days", result.getString("days"));
				tuple.put("time", result.getString("time"));
				tuple.put("room", result.getString("room"));
				tuple.put("seats", result.getString("seats"));
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

	public static boolean updateID(String oldInstructorID, String newInstructorID) {
		SQLMethods.mysqlConnect(); // Connect to DB
		try {
			/** Update ID */
			pstate = SQLMethods.con.prepareStatement("UPDATE Teaches SET instructorID = ? WHERE instructorID = ?;");
			pstate.setString(1, newInstructorID);
			pstate.setString(2, oldInstructorID);
			int rowcount = pstate.executeUpdate();
			return (rowcount == 1); // If rowcount == 1, row successfully updated
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return false; // Default value: false
	}

	public static boolean updateConfigID(int oldConfigID, int newConfigID) {
		SQLMethods.mysqlConnect(); // Connect to DB
		try {
			pstate = SQLMethods.con.prepareStatement("UPDATE Teaches SET configID = ? WHERE configID = ?;");
			pstate.setInt(1, newConfigID);
			pstate.setInt(2, oldConfigID);
			int rowcount = pstate.executeUpdate();

			SQLMethods.closeConnection(); // Close connection
			return (rowcount == 1); // If rowcount == 1, row successfully updated
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return false; // Default value: false
	}

	public static boolean updateCourse(String oldDept, String oldNum, String newDept, String newNum) {
		SQLMethods.mysqlConnect(); // Connect to DB
		try {
			pstate = SQLMethods.con.prepareStatement(
					"UPDATE Teaches SET department = ?, number = ? WHERE department = ? AND number = ?;");
			pstate.setString(1, newDept);
			pstate.setString(2, newNum);
			pstate.setString(3, oldDept);
			pstate.setString(4, oldNum);
			int rowcount = pstate.executeUpdate();
			SQLMethods.closeConnection(); // Close connection
			return (rowcount == 1); // If rowcount == 1, row successfully updated
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return false; // Default value: false
	}

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

	public static ArrayList<HashMap<String, String>> ins_viewAssignedCourses(String instructorID) {
		ArrayList<HashMap<String, String>> output = new ArrayList<HashMap<String, String>>();
		/** Check for invalid inputs. If any input is null, return false */
		if (instructorID == null) return output; // Check if studentID is null, return empty list if so
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to search
			/** Search and retrieve tuple */
			pstate = SQLMethods.con.prepareStatement(
					"SELECT Courses.department, Courses.number, Courses.title, Courses.units, Configurations. * FROM Instructors join Teaches on Instructors.instructorId = Teaches.instructorId join Courses on Teaches.department = Courses.department AND Courses.number = Teaches.number join Configurations on Configurations.configID = Teaches.configID  WHERE Instructors.instructorID = ? ORDER BY year DESC;");
			pstate.setString(1, instructorID);
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
				tuple.put("configID", result.getString("configID"));
				tuple.put("title", result.getString("title"));
				tuple.put("units", result.getString("units"));
				tuple.put("seats", result.getString("seats"));
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
