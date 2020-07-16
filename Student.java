
public class Student {
	
	public static void register(String course) {
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
	public static void drop(String course) {
		
	}
	public static void waitlist(String course) {
		
	}
	public static void buy(String course) {
		
	}
	
	public static void printTranscript() {
		
	}
}
