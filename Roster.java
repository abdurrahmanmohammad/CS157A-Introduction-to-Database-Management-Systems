import java.sql.*;

public class Roster {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String connection = "jdbc:mysql://localhost:3306/demo?serverTimezone=UTC";
    private static final String user = "root"; // 'root' is default username
    private static final String password = "root"; // 'root' is default password
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
        if ( !error.equals("Successfully connected to database.") ) return error;
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
            if ( !error.equals("Database closed successfully.") ) return error; // If error, return error
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
        if ( !error.equals("Successfully connected to database.") ) return error;
        // Attempt to delete
        try {
            // using PreparedStatement
            pstate = con.prepareStatement("DELETE FROM Roster WHERE crn = ?");
            pstate.setString(1, "crn");
            pstate.executeUpdate(); // int value = pstate.executeUpdate();
            error = closeConnection(); // Close connection
            if ( !error.equals("Database closed successfully.") ) return error; // If error, return error
            return "Successfully deleted course '" + crn + "' from roster";
        } catch (SQLException e) {
            return "Query error.";
        }
    }

    // Update (course number, total seats, seats left, total waitlist, remaining waitlist)
    public static String update(String crn, int total_seats, int seats_remaining, int total_waitlist, int waitlist_remaining) {
        // Check for errors in input and return errors to caller
        if (crn == null) return "Attribute course number is null";
        if (total_seats < 0) return "Attribute total seats is null";
        if (seats_remaining < 0) return "Attribute remaining seats is null";
        if (total_waitlist < 0) return "Attribute total waitlist is null";
        if (waitlist_remaining < 0) return "Attribute remaining waitlist is null";
        // Connect to DB: Return error message if connection failed
        String error = mysqlConnect();
        if ( !error.equals("Successfully connected to database.") ) return error;
        // Attempt to insert
        try {
            // using PreparedStatement
            pstate = con.prepareStatement("UPDATE Roster(crn, total_seats, seats_remaining, total_waitlist, waitlist_remaining)"
                    + "values(?, ?, ?)");
            pstate.setString(1, crn);
            pstate.setInt(2, total_seats);
            pstate.setInt(3, seats_remaining);
            pstate.setInt(4, total_waitlist);
            pstate.setInt(5, waitlist_remaining);
            pstate.executeUpdate(); // int value = pstate.executeUpdate();
            error = closeConnection(); // Close connection
            if ( !error.equals("Database closed successfully.") ) return error; // If error, return error
            return "Successfully updated course roster: '" + crn;
        } catch (SQLException e) {
            return "Query error";
        }
    }

    // Search
    public static String search(String crn, int total_seats, int seats_remaining, int total_waitlist, int waitlist_remaining) {
        String error = mysqlConnect(); // Connect to DB: Return error message if connection failed
        if ( !error.equals("Successfully connected to database.") ) return error;
        try {
            searchHelper(crn, total_seats, seats_remaining, total_waitlist, waitlist_remaining);
            int value = pstate.executeUpdate();
            error = closeConnection(); // Close connection
            if ( !error.equals("Database closed successfully.") ) return error; // If error, return error
            return "Successfully updated course '" + crn + "' roster to: "
                    + total_seats + " " + seats_remaining + " " + total_waitlist + " " + waitlist_remaining;
        } catch (SQLException e) {
            return "Query error";
        }
    }

    private static void searchHelper(String crn, int total_seats, int seats_remaining, int total_waitlist, int waitlist_remaining) throws SQLException {
        Object arr[] = new Object[5];
        int index = 0;
        String sqlStatement = "SELECT * FROM WHERE ";
        if (crn != null) {
            sqlStatement += "crn = ?";
            arr[index] = crn;
            index++;
        } if (total_seats >= 0) {
            sqlStatement += "total_seats = ?";
            arr[index] = total_seats;
            index++;
        } if (seats_remaining >= 0) {
            sqlStatement += "seats_remaining = ?";
            arr[index] = seats_remaining;
            index++;
        } if (total_waitlist >= 0) {
            sqlStatement += "total_waitlist = ?";
            arr[index] = total_waitlist;
            index++;
        } if (waitlist_remaining >= 0) {
            sqlStatement += "waitlist_remaining = ?";
            arr[index] = waitlist_remaining;
            index++;
        } index = 0;
        pstate = con.prepareStatement(sqlStatement);
        while (index < 5 && arr[index] != null) {
            if(arr[index] instanceof String) {
                pstate.setString(index+1, (String) arr[index]);
            } else {
                pstate.setInt(index+1, (Integer) arr[index]);
            }
            index++;
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
