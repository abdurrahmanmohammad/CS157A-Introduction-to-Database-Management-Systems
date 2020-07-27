import java.sql.*;

public class Courses {

	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String connection = "jdbc:mysql://localhost:3306/demo?serverTimezone=UTC";
	private static String user = "root"; // 'root' is default username
	private static String password = "root"; // 'root' is default password
	private static Connection con = null;
	private static Statement state = null;
	private static ResultSet result;
	private static PreparedStatement pstate;

	// Insert
	// Example Course: CS 157A Introduction to Database Management Systems
	// Primary key: (department, number)
	public static String insert(String department, String number, String name) {
		// Check for errors in input and return errors to caller
		if (department == null) return "Attribute department is null";
		if (number == null) return "Attribute number is null";
		if (name == null) return "Attribute name is null";
		// Connect to DB: Return error message if connection failed
		String error = mysqlConnect();
		if (error != "Successfully connected to database.") return error;
		// Attempt to insert
		try {
			// using PreparedStatement
			pstate = con.prepareStatement("INSERT INTO Courses(department, number, name)" + "values(?, ?, ?)");
			pstate.setString(1, department);
			pstate.setString(2, number);
			pstate.setString(3, name);
			pstate.executeUpdate(); // int value = pstate.executeUpdate();
			// Close connection
			error = closeConnection();
			if (error != "Database closed successfully.") return error; // If error, return error
			return "Successfully inserted course: " + department + " " + number + " " + " " + name;
		} catch (SQLException e) {
			return "Query error";
		}
	}

	// Delete
	public static String delete(String department, String number) {
		// Check for errors in input and return errors to caller
		if (department == null) return "Attribute department is null";
		if (number == null) return "Attribute number is null";
		// Connect to DB: Return error message if connection failed
		String error = mysqlConnect();
		if (error != "Successfully connected to database.") return error;
		// Attempt to delete
		try {
			// using PreparedStatement
			pstate = con.prepareStatement("DELETE FROM Courses WHERE department = ? AND number = ?");
			pstate.setString(1, "department");
			pstate.setString(2, "number");
			pstate.executeUpdate(); // int value = pstate.executeUpdate();
			error = closeConnection(); // Close connection
			if (error != "Database closed successfully.") return error; // If error, return error
			return "Successfully deleted course: " + department + " " + number + " ";
		} catch (SQLException e) {
			return "Query error.";
		}
	}

	// Update (name of course)
	public static String update(String department, String number, String name) {
		// Check for errors in input and return errors to caller
		if (department == null) return "Attribute department is null";
		if (number == null) return "Attribute number is null";
		if (name == null) return "Attribute name is null";
		// Connect to DB: Return error message if connection failed
		String error = mysqlConnect();
		if (error != "Successfully connected to database.") return error;
		// Attempt to insert
		try {
			// using PreparedStatement
			pstate = con.prepareStatement("UPDATE Courses SET name = ? WHERE department = ? AND number = ?");
			pstate.setString(1, name);
			pstate.setString(2, department);
			pstate.setString(3, number);
			pstate.executeUpdate(); // int value = pstate.executeUpdate();
			error = closeConnection(); // Close connection
			if (error != "Database closed successfully.") return error; // If error, return error
			return "Successfully updated course to: " + department + " " + number + " " + " " + name;
		} catch (SQLException e) {
			return "Query error";
		}
	}

	public static String updateDept(String ID, String department) {
		// Check for errors in input and return errors to caller
		if (department == null) return "Department not given";
		// Connect to DB: Return error message if connection failed
		String error = mysqlConnect();
		if (error != "Successfully connected to database.") return error;
		// Attempt to insert
		try {
			// using PreparedStatement
			pstate = con.prepareStatement("UPDATE Courses SET department = ? WHERE ID = ?");
			pstate.setString(1, department);
			pstate.setString(2, ID);
			pstate.executeUpdate(); // int value = pstate.executeUpdate();
			error = closeConnection(); // Close connection
			if (error != "Database closed successfully.") return error; // If error, return error
			return "Successfully updated course department";
		} catch (SQLException e) {
			return "Query error";
		}
	}

	public static String updateNum(String ID, String number) {
		// Check for errors in input and return errors to caller
		if (number == null) return "Course number not given";
		// Connect to DB: Return error message if connection failed
		String error = mysqlConnect();
		if (error != "Successfully connected to database.") return error;
		// Attempt to insert
		try {
			// using PreparedStatement
			pstate = con.prepareStatement("UPDATE Courses SET number = ? WHERE ID = ?");
			pstate.setString(1, number);
			pstate.setString(2, ID);
			pstate.executeUpdate(); // int value = pstate.executeUpdate();
			error = closeConnection(); // Close connection
			if (error != "Database closed successfully.") return error; // If error, return error
			return "Successfully updated course department";
		} catch (SQLException e) {
			return "Query error";
		}
	}

	public static String updateName(String ID, String name) {
		// Check for errors in input and return errors to caller
		if (name == null) return "Course name not given";
		// Connect to DB: Return error message if connection failed
		String error = mysqlConnect();
		if (error != "Successfully connected to database.") return error;
		// Attempt to insert
		try {
			// using PreparedStatement
			pstate = con.prepareStatement("UPDATE Courses SET name = ? WHERE ID = ?");
			pstate.setString(1, name);
			pstate.setString(2, ID);
			pstate.executeUpdate(); // int value = pstate.executeUpdate();
			error = closeConnection(); // Close connection
			if (error != "Database closed successfully.") return error; // If error, return error
			return "Successfully updated course department";
		} catch (SQLException e) {
			return "Query error";
		}
	}

	// Search
	public static String search(String department, String number, String name) {
		String error = mysqlConnect(); // Connect to DB: Return error message if connection failed
		if (error != "Successfully connected to database.") return error;
		try {
			searchHelper(department, number, name);
			int value = pstate.executeUpdate();
			error = closeConnection(); // Close connection
			if (error != "Database closed successfully.") return error; // If error, return error
			return "Successfully updated course to: " + department + " " + number + " " + " " + name;
		} catch (SQLException e) {
			return "Query error";
		}
	}

	private static void searchHelper(String department, String number, String name) throws SQLException {
		if (department != null && number != null && name != null) {
			pstate = con.prepareStatement("SELECT * FROM Courses WHERE department = ? AND number = ? AND name = ?");
			pstate.setString(1, department);
			pstate.setString(2, number);
			pstate.setString(3, name);
		} else if (department != null && number != null) {
			pstate = con.prepareStatement("SELECT * FROM Courses WHERE department = ? AND number = ?");
			pstate.setString(1, department);
			pstate.setString(2, number);
		} else if (number != null && name != null) {
			pstate = con.prepareStatement("SELECT * FROM Courses WHERE number = ? AND name = ?");
			pstate.setString(1, number);
			pstate.setString(2, name);
		} else if (department != null && name != null) {
			pstate = con.prepareStatement("SELECT * FROM Courses WHERE department = ? AND name = ?");
			pstate.setString(1, department);
			pstate.setString(2, name);
		} else if (department != null) {
			pstate = con.prepareStatement("SELECT * FROM Courses WHERE department = ?");
			pstate.setString(1, department);
		} else if (number != null) {
			pstate = con.prepareStatement("SELECT * FROM Courses WHERE number = ?");
			pstate.setString(1, number);
		} else if (name != null) {
			pstate = con.prepareStatement("SELECT * FROM Courses WHERE name = ?");
			pstate.setString(1, name);
		} else { // If all 3 fields are null, return all entries
			pstate = con.prepareStatement("SELECT * FROM Courses");
		}
	}

	private static String mysqlConnect() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(connection, user, password);
			return "Successfully connected to database.";
		} catch (ClassNotFoundException e) {
			return "Couldn't load driver.";
		} catch (SQLException e) {
			return "Couldn't connect to database.";
		}
	}

	private static String closeConnection() {
		try {
			if (!con.isClosed()) {
				con.close();
				return "Database closed successfully.";
			}
		} catch (NullPointerException e) {
			return "Couldn't load driver.";
		} catch (SQLException e) {
			return "Couldn't close database.";
		}
		return "Database closed successfully."; // If database was closed successfully
	}
}
