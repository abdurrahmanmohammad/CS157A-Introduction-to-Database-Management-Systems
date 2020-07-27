
public class Student {

	public static String register(String course, String department, String number, String name) {
		String error = Courses.mysqlConnect(); // Connect to DB: Return error message if connection failed
		if (error != "Successfully connected to database.") return error;
		try {
			Courses.searchHelper(department, number, name);
			int value = Courses.getPstate().executeUpdate();
			error = Courses.closeConnection(); // Close connection
			if (error != "Database closed successfully.") return error; // If error, return error
			return "Successfully updated course to: " + department + " " + number + " " + " " + name;
		} catch (SQLException e) {
			return "Query error";
		}

	}
	public static String drop(String ID, String name) {
		String error = Courses.mysqlConnect(); // Connect to DB: Return error message if connection failed
		if (error != "Successfully connected to database.") return error;
		try {
			Courses.delete(ID);
			int value = Courses.getPstate().executeUpdate();
			error = Courses.closeConnection(); // Close connection
			if (error != "Database closed successfully.") return error; // If error, return error
			return "Successfully dropped course: " + name;
		} catch (SQLException e) {
			return "Query error";
		}
	}
	public static void waitlist(String course) {
		
	}
	public static void buy(String course) {
		
	}
	
	public static void printTranscript() {
		
	}
}
