package courses;

import SQL.SQLMethods;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/** Courses(department, number, title, units, cost) */
public class Courses {
	private static ResultSet result;
	private static PreparedStatement pstate;

	/**
	 * Insert a course
	 * 
	 * @param department
	 * @param number
	 * @param title
	 * @param units
	 * @param cost
	 * @return true if successful insert, else false
	 */
	public static boolean insert(String department, String number, String title, int units, int cost) {
		/** Check for invalid inputs. If any input is null, return false */
		if (department == null || number == null || title == null || units < 0 || cost < 0) return false;
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to insert
			pstate = SQLMethods.con.prepareStatement("INSERT INTO Courses Values (?, ?, ?, ?, ?, ?);");
			pstate.setString(1, department);
			pstate.setString(2, number);
			pstate.setString(3, title);
			pstate.setInt(4, units);
			pstate.setInt(5, cost);
			int rowcount = pstate.executeUpdate(); // Number of rows affected
			SQLMethods.closeConnection(); // Close connection
			return (rowcount == 1); // If rowcount == 1, row successfully inserted
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return false; // Default value: false
	}

	/**
	 * Delete a course
	 * 
	 * @param department
	 * @param number
	 * @return true if successful insert, else false
	 */
	public static boolean delete(String department, String number) {
		/** Check for invalid inputs. If any input is null, return false */
		if (department == null || number == null) return false;
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to delete using PreparedStatement
			pstate = SQLMethods.con.prepareStatement("DELETE FROM Courses WHERE department = ? AND number = ?;");
			pstate.setString(1, department);
			pstate.setString(2, number);
			int rowcount = pstate.executeUpdate(); // Number of rows affected
			SQLMethods.closeConnection(); // Close connection
			return (rowcount == 1); // If rowcount == 1, row successfully inserted
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return false; // Default value: false
	}

	/** Update a course's department */
	public static boolean updateDepartment(String oldDepartment, String number, String newDepartment) {
		if (oldDepartment == null) return false; // Check if oldDepartment is null
		if (number == null) return false; // Check if number is null
		if (newDepartment == null) return false; // Check if newDepartment is null
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to update
			pstate = SQLMethods.con
					.prepareStatement("UPDATE Courses SET department = ? WHERE department = ? AND number = ?");
			pstate.setString(1, newDepartment);
			pstate.setString(2, oldDepartment);
			pstate.setString(3, number);
			int value = pstate.executeUpdate(); // Execute statement
			SQLMethods.closeConnection(); // Close connection
			return true; // Success
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error"); // Print error and exit
		}
		return false; // Return false as a default value
	}

	/** Update a course's number */
	public static boolean updateNumber(String department, String oldNumber, String newNumber) {
		if (department == null) return false; // Check if department is null
		if (oldNumber == null) return false; // Check if oldNumber is null
		if (newNumber == null) return false; // Check if newNumber is null
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to update
			pstate = SQLMethods.con
					.prepareStatement("UPDATE Courses SET number = ? WHERE department = ? AND number = ?");
			pstate.setString(1, newNumber);
			pstate.setString(2, department);
			pstate.setString(2, oldNumber);
			int value = pstate.executeUpdate(); // Execute statement
			SQLMethods.closeConnection(); // Close connection
			return true; // Success
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error"); // Print error and exit
		}
		return false; // Return false as a default value
	}

	/** Update a course's title */
	public static boolean updateTitle(String department, String number, String title) {
		if (department == null) return false; // Check if department is null
		if (number == null) return false; // Check if number is null
		if (title == null) return false; // Check if title is null
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to update
			pstate = SQLMethods.con
					.prepareStatement("UPDATE Courses SET title = ? WHERE department = ? AND number = ?");
			pstate.setString(1, title);
			pstate.setString(2, department);
			pstate.setString(2, number);
			int value = pstate.executeUpdate(); // Execute statement
			SQLMethods.closeConnection(); // Close connection
			return true; // Success
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error"); // Print error and exit
		}
		return false; // Return false as a default value
	}

	/** Update a course's units */
	public static boolean updateUnits(String department, String number, String units) {
		if (department == null) return false; // Check if department is null
		if (number == null) return false; // Check if number is null
		if (units == null) return false; // Check if units is null
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to update
			pstate = SQLMethods.con
					.prepareStatement("UPDATE Courses SET units = ? WHERE department = ? AND number = ?");
			pstate.setString(1, units);
			pstate.setString(2, department);
			pstate.setString(2, number);
			int value = pstate.executeUpdate(); // Execute statement
			SQLMethods.closeConnection(); // Close connection
			return true; // Success
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error"); // Print error and exit
		}
		return false; // Return false as a default value
	}

	/** Update a course's units */
	public static boolean updateCost(String department, String number, String cost) {
		if (department == null) return false; // Check if department is null
		if (number == null) return false; // Check if number is null
		if (cost == null) return false; // Check if cost is null
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to update
			pstate = SQLMethods.con.prepareStatement("UPDATE Courses SET cost = ? WHERE department = ? AND number = ?");
			pstate.setString(1, cost);
			pstate.setString(2, department);
			pstate.setString(2, number);
			int value = pstate.executeUpdate(); // Execute statement
			SQLMethods.closeConnection(); // Close connection
			return true; // Success
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error"); // Print error and exit
		}
		return false; // Return false as a default value
	}

	// Search

	public static ArrayList<ArrayList<String>> search(String department, String number) {
		ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
		if (department == null) return output; // Check if department is null, return empty list if so
		if (number == null) return output; // Check if number is null, return empty list if so
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to search
			pstate = SQLMethods.con.prepareStatement("SELECT * Courses WHERE department = ? AND number = ?");
			pstate.setString(1, department);
			pstate.setString(1, number);
			result = pstate.executeQuery(); // Execute query
			SQLMethods.closeConnection(); // Close connection
			while (result.next()) {
				ArrayList<String> tuple = new ArrayList<String>();
				tuple.add(result.getString("department"));
				tuple.add(result.getString("number"));
				tuple.add(result.getString("title"));
				tuple.add(result.getString("units"));
				tuple.add(result.getString("cost"));
				output.add(tuple);
			}
			result.close(); // Close result
			return output; // Success
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error"); // Print error and exit
		}
		return output; // Return false as a default value
	}

	public static ArrayList<ArrayList<String>> searchDepartment(String department) {
		ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
		if (department == null) return output; // Check if department is null, return empty list if so
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to search
			pstate = SQLMethods.con.prepareStatement("SELECT * Courses WHERE department = ?");
			pstate.setString(1, department);
			result = pstate.executeQuery(); // Execute query
			SQLMethods.closeConnection(); // Close connection
			while (result.next()) {
				ArrayList<String> tuple = new ArrayList<String>();
				tuple.add(result.getString("department"));
				tuple.add(result.getString("number"));
				tuple.add(result.getString("title"));
				tuple.add(result.getString("units"));
				tuple.add(result.getString("cost"));
				output.add(tuple);
			}
			result.close(); // Close result
			return output; // Success
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error"); // Print error and exit
		}
		return output; // Return false as a default value
	}

	public static ArrayList<ArrayList<String>> searchNumber(String number) {
		ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
		if (number == null) return output; // Check if number is null, return empty list if so
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to search
			pstate = SQLMethods.con.prepareStatement("SELECT * Courses WHERE number = ?");
			pstate.setString(1, number);
			result = pstate.executeQuery(); // Execute query
			SQLMethods.closeConnection(); // Close connection
			while (result.next()) {
				ArrayList<String> tuple = new ArrayList<String>();
				tuple.add(result.getString("department"));
				tuple.add(result.getString("number"));
				tuple.add(result.getString("title"));
				tuple.add(result.getString("units"));
				tuple.add(result.getString("cost"));
				output.add(tuple);
			}
			result.close(); // Close result
			return output; // Success
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error"); // Print error and exit
		}
		return output; // Return false as a default value
	}

	public static ArrayList<ArrayList<String>> searchTitle(String title) {
		ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
		if (title == null) return output; // Check if title is null, return empty list if so
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to search
			pstate = SQLMethods.con.prepareStatement("SELECT * Courses WHERE title = ?");
			pstate.setString(1, title);
			result = pstate.executeQuery(); // Execute query
			SQLMethods.closeConnection(); // Close connection
			while (result.next()) {
				ArrayList<String> tuple = new ArrayList<String>();
				tuple.add(result.getString("department"));
				tuple.add(result.getString("number"));
				tuple.add(result.getString("title"));
				tuple.add(result.getString("units"));
				tuple.add(result.getString("cost"));
				output.add(tuple);
			}
			result.close(); // Close result
			return output; // Success
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error"); // Print error and exit
		}
		return output; // Return false as a default value
	}

	public static ArrayList<ArrayList<String>> searchUnits(String units) {
		ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
		if (units == null) return output; // Check if units is null, return empty list if so
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to search
			pstate = SQLMethods.con.prepareStatement("SELECT * Courses WHERE units = ?");
			pstate.setString(1, units);
			result = pstate.executeQuery(); // Execute query
			SQLMethods.closeConnection(); // Close connection
			while (result.next()) {
				ArrayList<String> tuple = new ArrayList<String>();
				tuple.add(result.getString("department"));
				tuple.add(result.getString("number"));
				tuple.add(result.getString("title"));
				tuple.add(result.getString("units"));
				tuple.add(result.getString("cost"));
				output.add(tuple);
			}
			result.close(); // Close result
			return output; // Success
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error"); // Print error and exit
		}
		return output; // Return false as a default value
	}

	public static ArrayList<ArrayList<String>> searchCost(String cost) {
		ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
		if (cost == null) return output; // Check if cost is null, return empty list if so
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to search
			pstate = SQLMethods.con.prepareStatement("SELECT * Courses WHERE cost = ?");
			pstate.setString(1, cost);
			result = pstate.executeQuery(); // Execute query
			SQLMethods.closeConnection(); // Close connection
			while (result.next()) {
				ArrayList<String> tuple = new ArrayList<String>();
				tuple.add(result.getString("department"));
				tuple.add(result.getString("number"));
				tuple.add(result.getString("title"));
				tuple.add(result.getString("units"));
				tuple.add(result.getString("cost"));
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
