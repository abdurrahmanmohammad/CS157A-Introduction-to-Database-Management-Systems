package members;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import SQL.SQLMethods;
import administrators.Administrators;
import instructors.Instructors;
import registers.Registers;
import students.Students;
import teaches.Teaches;
import transactions.Transactions;
import users.Users;

/** Members(ID, firstname, lastname, phone, email, address) */
public class Members {
	private static ResultSet result;
	private static PreparedStatement pstate;

	/**
	 * Checks if a member with ID already exists
	 * 
	 * @param ID
	 * @return true if ID DNE, false if exists
	 */
	public static boolean checkID(String ID) {
		if (ID == null) return false; // Attribute ID is null
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to check
			pstate = SQLMethods.con.prepareStatement("SELECT COUNT(*) FROM Members WHERE ID = ?;");
			pstate.setString(1, ID);
			result = pstate.executeQuery();
			result.next();
			int rowcount = result.getInt(1);
			result.close(); // Close result
			SQLMethods.closeConnection(); // Close connection
			return (rowcount == 0); // Returns true if ID DNE
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error");
		}
		return false; // Return false as a default value
	}

	/**
	 * Method to create and insert a member into the DB
	 * 
	 * @param ID
	 * @param firstname
	 * @param lastname
	 * @param phone
	 * @param email
	 * @param address
	 * @return Returns true if successful, otherwise false.
	 */
	public static boolean insert(String ID, String firstname, String lastname, String phone, String email,
			String address) {
		/** Check for invalid inputs. If any input is null, return false */
		if (ID == null || firstname == null || lastname == null || phone == null || email == null || address == null)
			return false;
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to insert
			pstate = SQLMethods.con.prepareStatement("INSERT INTO Members VALUES(?, ?, ?, ?, ?, ?);");
			pstate.setString(1, ID);
			pstate.setString(2, firstname);
			pstate.setString(3, lastname);
			pstate.setString(4, phone);
			pstate.setString(5, email);
			pstate.setString(6, address);
			int rowcount = pstate.executeUpdate();
			SQLMethods.closeConnection(); // Close connection
			return (rowcount == 1); // If rowcount == 1, row successfully inserted
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return false; // Default value: false
	}

	/**
	 * Method to delete a user using ID
	 * 
	 * @param ID
	 * @return Returns true if successful, otherwise false.
	 */
	public static boolean delete(String ID) {
		if (ID == null) return false; // Check if ID is null
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to delete
			pstate = SQLMethods.con.prepareStatement("DELETE FROM Members WHERE ID = ?;");
			pstate.setString(1, ID);
			int rowcount = pstate.executeUpdate();
			SQLMethods.closeConnection(); // Close connection
			return (rowcount == 1); // If rowcount == 1, row successfully inserted
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return false; // Default value: false
	}

	/**
	 * Method to search for a member using its PK
	 * 
	 * @param department
	 * @param number
	 * @return a HashMap containing the attributes of the member
	 */
	public static HashMap<String, String> search(String ID) {
		HashMap<String, String> output = new HashMap<String, String>();
		if (ID == null) return output; // Check if ID is null
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to search
			/** Search for member using ID */
			pstate = SQLMethods.con.prepareStatement("SELECT * FROM Members NATURAL JOIN Users WHERE ID = ?");
			pstate.setString(1, ID); // ID of member
			result = pstate.executeQuery(); // Execute query
			/** Extract member data */
			// Members(ID, firstname, lastname, phone, email, address)
			while (result.next()) {
				output.put("ID", result.getString("ID"));
				output.put("firstname", result.getString("firstname"));
				output.put("lastname", result.getString("lastname"));
				output.put("phone", result.getString("phone"));
				output.put("email", result.getString("email"));
				output.put("address", result.getString("address"));
				output.put("username", result.getString("username"));
				output.put("password", result.getString("password"));
			}
			result.close(); // Close result
			SQLMethods.closeConnection(); // Close connection
			return output; // Success
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return output; // Default value: empty HashMap
	}

	public static boolean update(String oldID, String newID, String firstname, String lastname, String phone,
			String email, String address) {
		SQLMethods.mysqlConnect(); // Connect to DB
		try {
			/** Update tuple in Members */
			pstate = SQLMethods.con.prepareStatement(
					"UPDATE Members SET ID = ?, firstname = ?, lastname = ?, phone = ?, email = ?, address = ? WHERE ID = ?;");
			pstate.setString(1, newID);
			pstate.setString(2, firstname);
			pstate.setString(3, lastname);
			pstate.setString(4, phone);
			pstate.setString(5, email);
			pstate.setString(6, address);
			pstate.setString(7, oldID);
			int rowcount = pstate.executeUpdate(); // Number of rows affected
			SQLMethods.closeConnection(); // Close connection
			return (rowcount == 1); // If rowcount == 1, row successfully inserted
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return false; // Default value: false
	}

	public static boolean updateID(String oldID, String newID) {
		SQLMethods.mysqlConnect(); // Connect to DB
		try {
			/** Update ID */
			pstate = SQLMethods.con.prepareStatement("UPDATE Members SET ID = ? WHERE ID = ?;");
			pstate.setString(1, newID);
			pstate.setString(2, oldID);
			int rowcount = pstate.executeUpdate();
			return (rowcount == 1); // If rowcount == 1, row successfully updated
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return false; // Default value: false
	}

	/**
	 * Method to retrieve all the members and their info in table Members
	 * 
	 * @return all the members and their info in table Members in an ArrayList
	 */
	public static ArrayList<HashMap<String, String>> getAll() {
		ArrayList<HashMap<String, String>> output = new ArrayList<HashMap<String, String>>();
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to search
			pstate = SQLMethods.con.prepareStatement("SELECT * FROM Members NATURAL JOIN Users ORDER BY lastname ASC;");
			result = pstate.executeQuery(); // Execute query
			while (result.next()) {
				HashMap<String, String> tuple = new HashMap<String, String>();
				tuple.put("ID", result.getString("ID"));
				tuple.put("firstname", result.getString("firstname"));
				tuple.put("lastname", result.getString("lastname"));
				tuple.put("phone", result.getString("phone"));
				tuple.put("email", result.getString("email"));
				tuple.put("address", result.getString("address"));
				tuple.put("username", result.getString("username"));
				tuple.put("password", result.getString("password"));
				output.add(tuple);
			}
			result.close(); // Close result
			return output; // Successful search
		} catch (SQLException e) { // Print error and terminate program
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return output;
	}

	/* ############################################################ */
	/* #################### Unused Methods Below #################### */
	/* ############################################################ */

	/**
	 * Update email of a member
	 */
	public static boolean updatePhone(String ID, String phone) {
		if (ID == null) return false; // Check if ID is null
		if (phone == null) return false; // Check if phone is null
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to update
			pstate = SQLMethods.con.prepareStatement("UPDATE Users SET phone = ? WHERE ID = ?");
			pstate.setString(1, phone); // New email
			pstate.setString(2, ID); // ID of user
			int value = pstate.executeUpdate(); // Execute statement
			SQLMethods.closeConnection(); // Close connection
			return true; // Success
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error"); // Print error and exit
		}
		return false; // Return false as a default value
	}

	/**
	 * Update email of a member
	 */
	public static boolean updateEmail(String ID, String email) {
		if (ID == null) return false; // Check if ID is null
		if (email == null) return false; // Check if email is null
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to update
			pstate = SQLMethods.con.prepareStatement("UPDATE Users SET email = ? WHERE ID = ?");
			pstate.setString(1, email); // New email
			pstate.setString(2, ID); // ID of user
			int value = pstate.executeUpdate(); // Execute statement
			SQLMethods.closeConnection(); // Close connection
			return true; // Success
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error"); // Print error and exit
		}
		return false; // Return false as a default value
	}

	/**
	 * Update address of a member
	 */
	public static boolean updateAddress(String ID, String address) {
		if (ID == null) return false; // Check if ID is null
		if (address == null) return false; // Check if email is null
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to update
			pstate = SQLMethods.con.prepareStatement("UPDATE Users SET address = ? WHERE ID = ?");
			pstate.setString(1, address); // New email
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
