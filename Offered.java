import java.sql.*;

public class Offered {
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String connection = "jdbc:mysql://localhost:3306/demo?serverTimezone=UTC";
	private static String user = "root"; // 'root' is default username
	private static String password = "root"; // 'root' is default password
	private static Connection con = null;
	private static Statement state = null;
	private static ResultSet result;
	private static PreparedStatement pstate;

	
	public static String insert(String department, String number, String term, String year, String start, String end, String days, String time) {
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
