package configurations;

import SQL.SQLMethods;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

/** Configurations(configID, term, year, days, time, room, seats) */
public class Configurations {
	private static ResultSet result;
	private static PreparedStatement pstate;

	/**
	 * Insert a configuration
	 * 
	 * @param room
	 * @param seats
	 * @param term
	 * @param time
	 * @param days
	 * @param year
	 * @return true if successful insert, else false
	 */
	public static boolean insert(String term, int year, String days, String time, String room, int seats) {
		/** Check for invalid inputs. If any input is null, return false */
		SQLMethods.mysqlConnect();
		try {
			// configID is auto incremented in SQL table
			pstate = SQLMethods.con.prepareStatement("INSERT INTO Configurations VALUES (NULL, ?, ?, ?, ?, ?, ?);");
			pstate.setString(1, term);
			pstate.setInt(2, year);
			pstate.setString(3, days);
			pstate.setString(4, time);
			pstate.setString(5, room);
			pstate.setInt(6, seats);
			int rowcount = pstate.executeUpdate(); // Number of rows affected
			SQLMethods.closeConnection(); // Close connection
			return (rowcount == 1); // If rowcount == 1, row successfully inserted
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return false; // Default value: false
	}

	/**
	 * Delete a configuration
	 * 
	 * @param configID
	 * @return
	 */
	public static boolean delete(String configID) {
		/** Check for invalid inputs. If any input is null, return false */
		if (configID == null) return false;
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to delete using PreparedStatement
			pstate = SQLMethods.con.prepareStatement("DELETE FROM Configurations WHERE configID = ?;");
			pstate.setString(1, configID);
			int rowcount = pstate.executeUpdate(); // Number of rows affected
			SQLMethods.closeConnection(); // Close connection
			return (rowcount == 1); // If rowcount == 1, 1 row successfully inserted
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return false; // Return false as a default value
	}

	/**
	 * Return configuration information based on configuration ID
	 * 
	 * @param ID
	 * @return
	 */
	public static HashMap<String, String> search(String configID) {
		HashMap<String, String> output = new HashMap<String, String>();
		if (configID == null) return output; // Check if ID is null, return empty list if so
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to search
			/** Search and retrieve tuple */
			pstate = SQLMethods.con.prepareStatement("SELECT * FROM Configurations WHERE configID = ?;");
			pstate.setString(1, configID);
			result = pstate.executeQuery(); // Execute query
			/** Extract tuple data */
			result.next();
			// String term, int year, String days, String time, String room, int seats
			output.put("configID", Integer.toString(result.getInt("configID")));
			output.put("term", result.getString("term"));
			output.put("year", Integer.toString(result.getInt("year")));
			output.put("days", result.getString("days"));
			output.put("time", result.getString("time"));
			output.put("room", result.getString("room"));
			output.put("seats", Integer.toString(result.getInt("seats")));
			result.close(); // Close result
			SQLMethods.closeConnection(); // Close connection
			return output; // Return output
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return output; // Return false as a default value
	}

	/* ############################################################ */
	/* #################### Unused Methods Below #################### */
	/* ############################################################ */

	public static boolean updateRoom(String ID, String newRoom) {
		if (ID == null) return false; // Check if ID is null
		if (newRoom == null) return false; // Check if newRoom is null
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to update
			pstate = SQLMethods.con.prepareStatement("UPDATE Configurations SET room = ? WHERE ID = ?");
			pstate.setString(1, newRoom); // New room
			pstate.setString(2, ID); // ID of user
			int value = pstate.executeUpdate(); // Execute statement
			SQLMethods.closeConnection(); // Close connection
			return true; // Success
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error"); // Print error and exit
		}
		return false; // Return false as a default value
	}

	public static boolean updateSeats(String ID, String newSeats) {
		if (ID == null) return false; // Check if ID is null
		if (newSeats == null) return false; // Check if newSeats is null
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to update
			pstate = SQLMethods.con.prepareStatement("UPDATE Configurations SET seats = ? WHERE ID = ?");
			pstate.setString(1, newSeats); // New seats
			pstate.setString(2, ID); // ID of user
			int value = pstate.executeUpdate(); // Execute statement
			SQLMethods.closeConnection(); // Close connection
			return true; // Success
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error"); // Print error and exit
		}
		return false; // Return false as a default value
	}

	public static boolean updateTerm(String ID, String newTerm) {
		if (ID == null) return false; // Check if ID is null
		if (newTerm == null) return false; // Check if newTerm is null
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to update
			pstate = SQLMethods.con.prepareStatement("UPDATE Configurations SET term = ? WHERE ID = ?");
			pstate.setString(1, newTerm); // New term
			pstate.setString(2, ID); // ID of user
			int value = pstate.executeUpdate(); // Execute statement
			SQLMethods.closeConnection(); // Close connection
			return true; // Success
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error"); // Print error and exit
		}
		return false; // Return false as a default value
	}

	public static boolean updateTime(String ID, String newTime) {
		if (ID == null) return false; // Check if ID is null
		if (newTime == null) return false; // Check if newTime is null
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to update
			pstate = SQLMethods.con.prepareStatement("UPDATE Configurations SET time = ? WHERE ID = ?");
			pstate.setString(1, newTime); // New time
			pstate.setString(2, ID); // ID of user
			int value = pstate.executeUpdate(); // Execute statement
			SQLMethods.closeConnection(); // Close connection
			return true; // Success
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error"); // Print error and exit
		}
		return false; // Return false as a default value
	}

	public static boolean updateDays(String ID, String newDays) {
		if (ID == null) return false; // Check if ID is null
		if (newDays == null) return false; // Check if newDays is null
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to update
			pstate = SQLMethods.con.prepareStatement("UPDATE Configurations SET days = ? WHERE ID = ?");
			pstate.setString(1, newDays); // New days
			pstate.setString(2, ID); // ID of user
			int value = pstate.executeUpdate(); // Execute statement
			SQLMethods.closeConnection(); // Close connection
			return true; // Success
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error"); // Print error and exit
		}
		return false; // Return false as a default value
	}

	public static boolean updateYear(String ID, String newYear) {
		if (ID == null) return false; // Check if ID is null
		if (newYear == null) return false; // Check if newSeats is null
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to update
			pstate = SQLMethods.con.prepareStatement("UPDATE Configurations SET seats = ? WHERE ID = ?");
			pstate.setString(1, newYear); // New year
			pstate.setString(2, ID); // ID of user
			int value = pstate.executeUpdate(); // Execute statement
			SQLMethods.closeConnection(); // Close connection
			return true; // Success
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error"); // Print error and exit
		}
		return false; // Return false as a default value
	}
}
