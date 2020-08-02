package transactions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import students.Students;

import SQL.SQLMethods;

/** Transactions(studentID, creditcard, amount, timestamp) */
public class Transactions {
	private static ResultSet result;
	private static PreparedStatement pstate;

	/**
	 * Method to pay for a course and then update student balance.
	 * 
	 * @param studentID
	 * @param creditcard
	 * @param amount
	 * @return
	 */
	public static boolean pay(String studentID, String creditcard, int amount) {
		/** Check for invalid inputs. If any input is null, return false */
		if (studentID == null || creditcard == null || amount < 0) return false;
		/** Generate timestsamp */
		// Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SQLMethods.mysqlConnect(); // Connect to DB
		try {
			/** Add to Transactions table */
			pstate = SQLMethods.con.prepareStatement("INSERT INTO Transactions VALUES (?, ?, ?, NOW());");
			pstate.setString(1, studentID);
			pstate.setString(2, creditcard);
			pstate.setInt(3, amount);
			int rowcount = pstate.executeUpdate(); // Number of rows affected
			// Timestamp is auto-generated in SQL table
			/** Retrieve student's balance */
			HashMap<String, String> student = Students.searchByStudentID(studentID); // Search and retrieve student
			int balance = Integer.parseInt(student.get("balance")) + amount; // Calculate new balance
			/** Update student's balance */
			Students.updateBalance(studentID, balance);
			SQLMethods.closeConnection(); // Close connection
			return true;
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return false; // Default value: false
	}

	/**
	 * View transaction history of a student using studentID
	 * 
	 * @param studentID
	 * @return a linked list containing the transaction history
	 */
	public static ArrayList<HashMap<String, String>> viewTransactions(String studentID) {
		ArrayList<HashMap<String, String>> output = new ArrayList<HashMap<String, String>>();
		if (studentID == null) return output; // Return empty list
		SQLMethods.mysqlConnect(); // Connect to DB
		try { // Attempt to search
			pstate = SQLMethods.con.prepareStatement("SELECT * FROM Transactions WHERE studentID = ?;");
			pstate.setString(1, studentID);
			result = pstate.executeQuery(); // Execute query
			while (result.next()) {
				HashMap<String, String> tuple = new HashMap<String, String>();
				tuple.put("studentID", result.getString("studentID"));
				tuple.put("creditcard", result.getString("creditcard"));
				tuple.put("amount", result.getString("amount"));
				tuple.put("timestamp", result.getString("timestamp"));
				output.add(tuple);
			}
			result.close(); // Close result
			SQLMethods.closeConnection(); // Close connection
			return output; // Success
		} catch (SQLException e) {
			SQLMethods.mysql_fatal_error("Query error: " + e.toString());
		}
		return output; // default value
	}

}
