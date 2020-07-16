import java.sql.*;

public class Roster {
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
    // Primary key: (crn) Course Number
    public static String insert(String crn, int total_seats, int seats_remaining, int total_waitlist, int waitlist_remaining) {
        // Check for errors in input and return errors to caller
        if (crn == null) return "Attribute course number is null";
        if (total_seats < 0) return "Attribute total seats is null";
        if (seats_remaining < 0) return "Attribute remaining seats is null";
        if (total_waitlist < 0) return "Attribute total waitlist is null";
        if (waitlist_remaining < 0) return "Attribute remaining waitlist is null";
        // Connect to DB: Return error message if connection failed
        String error = mysqlConnect();
        if (error != "Successfully connected to database.") return error;
        // Attempt to insert
        try {
            // using PreparedStatement
            pstate = con.prepareStatement("INSERT INTO Roster(crn, total_seats, seats_remaining, total_waitlist, waitlist_remaining)"
                    + "values(?, ?, ?)");
            pstate.setString(1, crn);
            pstate.setInt(2, total_seats);
            pstate.setInt(3, seats_remaining);
            pstate.setInt(4, total_waitlist);
            pstate.setInt(5, waitlist_remaining);
            pstate.executeUpdate(); // int value = pstate.executeUpdate();
            // Close connection
            error = closeConnection();
            if (error != "Database closed successfully.") return error; // If error, return error
            return "Successfully inserted course: '" + crn + "' into roster";
        } catch (SQLException e) {
            return "Query error";
        }
    }

    // Delete
    public static String delete(String crn) {
        // Check for errors in input and return errors to caller
        if (crn == null) return "Attribute course number is null";
        // Connect to DB: Return error message if connection failed
        String error = mysqlConnect();
        if (error != "Successfully connected to database.") return error;
        // Attempt to delete
        try {
            // using PreparedStatement
            pstate = con.prepareStatement("DELETE FROM Roster WHERE crn = ?");
            pstate.setString(1, "crn");
            pstate.executeUpdate(); // int value = pstate.executeUpdate();
            error = closeConnection(); // Close connection
            if (error != "Database closed successfully.") return error; // If error, return error
            return "Successfully deleted course '" + crn + "' from roster";
        } catch (SQLException e) {
            return "Query error.";
        }
    }

    // Update (name of course)
    public static String update(String crn, int total_seats, int seats_remaining, int total_waitlist, int waitlist_remaining) {
        // Check for errors in input and return errors to caller
        if (crn == null) return "Attribute course number is null";
        if (total_seats < 0) return "Attribute total seats is null";
        if (seats_remaining < 0) return "Attribute remaining seats is null";
        if (total_waitlist < 0) return "Attribute total waitlist is null";
        if (waitlist_remaining < 0) return "Attribute remaining waitlist is null";
        // Connect to DB: Return error message if connection failed
        String error = mysqlConnect();
        if (error != "Successfully connected to database.") return error;
        // Attempt to insert
        try {
            // using PreparedStatement
            pstate = con.prepareStatement("INSERT INTO Roster(crn, total_seats, seats_remaining, total_waitlist, waitlist_remaining)"
                    + "values(?, ?, ?)");
            pstate.setString(1, crn);
            pstate.setInt(2, total_seats);
            pstate.setInt(3, seats_remaining);
            pstate.setInt(4, total_waitlist);
            pstate.setInt(5, waitlist_remaining);
            pstate.executeUpdate(); // int value = pstate.executeUpdate();
            error = closeConnection(); // Close connection
            if (error != "Database closed successfully.") return error; // If error, return error
            return "Successfully updated course: '" + crn + "' in roster";
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
