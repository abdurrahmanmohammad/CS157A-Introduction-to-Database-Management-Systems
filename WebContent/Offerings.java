/*package offerings;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import SQL.SQLMethods;

// Offerings(ID, term, year, days, time, room, seats)
public class Offerings {
	private static ResultSet result;
	private static PreparedStatement pstate;

	public static boolean insert(String ID, String term, String year, String days, String time, String room,
			String seats) {
		// Check for errors in input and return errors to caller
		if (ID == null) return false;
		if (term == null) return false;
		if (year == null) return false;
		if (days == null) return false;
		if (time == null) return false;
		if (room == null) return false;
		if (seats == null) return false;
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to insert
			pstate = SQLMethods.con.prepareStatement(
					"INSERT INTO Offerings(department, number, title, units, cost)" + "values(?, ?, ?, ?, ?)");
			pstate.setString(1, department);
			pstate.setString(2, number);
			pstate.setString(3, title);
			pstate.setString(4, units);
			pstate.setString(5, cost);
			int value = pstate.executeUpdate();
			SQLMethods.closeConnection(); // Close connection
			return true; // Success
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error");
		}
		return false; // Return false as a default value
	}

	// Delete
	public static boolean delete(String ID) {
		// Check for errors in input and return errors to caller
		if (ID == null) return false;
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to delete using PreparedStatement
			pstate = SQLMethods.con.prepareStatement("DELETE FROM Courses WHERE ID = ?");
			pstate.setString(1, "ID");
			pstate.executeUpdate(); // int value = pstate.executeUpdate();
			SQLMethods.closeConnection(); // Close connection
			return true;
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error");
		}
		return false; // Return false as a default value
	}

}*/
