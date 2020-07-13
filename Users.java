import java.sql.*;

public class Users {

    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String connection = "jdbc:mysql://localhost:3306/demo?serverTimezone=UTC";
    private static String user = "root"; // 'root' is default username
    private static String password = "root"; // 'root' is default password
    private static Connection con = null;
    private static Statement state = null;
    private static ResultSet result;
    private static PreparedStatement pstate;

    // Insert
    //        Users: username password email         timestamp
    //Example Users: bob21    happy    bob@gmail.com NULL
    // Primary key: (username)
    public static String insert(String username, String password, String email, String timestamp) {
        // Check for errors in input and return errors to caller
        if (username == null) return "Attribute username is null";
        if (password == null) return "Attribute password is null";
        if (email == null) return "Attribute email is null";
        if (timestamp == null) return "Attribute timestamp is null";

        // Connect to DB: Return error message if connection failed
        String error = mysqlConnect();
        if (error != "Successfully connected to database.") return error;
        // Attempt to insert
        try {
            // using PreparedStatement
            pstate = con.prepareStatement("INSERT INTO Users(username, password, email, timestamp)" + "values(?, ?, ?, ?)");
            pstate.setString(1, username);
            pstate.setString(2, password);
            pstate.setString(3, email);
            pstate.setString(4, timestamp);
            pstate.executeUpdate(); // int value = pstate.executeUpdate();
            // Close connection
            error = closeConnection();
            if (error != "Database closed successfully.") return error; // If error, return error
            return "Successfully inserted user: " + username + " " + password + " " + " " + email + " " + timestamp;
        } catch (SQLException e) {
            return "Query error";
        }
    }

    // Delete
    public static String delete(String username, String password) {
        // Check for errors in input and return errors to caller
        if (username == null) return "Attribute username is null";
        if (password == null) return "Attribute password is null";
        // Connect to DB: Return error message if connection failed
        String error = mysqlConnect();
        if (error != "Successfully connected to database.") return error;
        // Attempt to delete
        try {
            // using PreparedStatement
            pstate = con.prepareStatement("DELETE FROM Users WHERE username = ? AND password = ?");
            pstate.setString(1, "username");
            pstate.setString(2, "password");
            pstate.executeUpdate(); // int value = pstate.executeUpdate();
            error = closeConnection(); // Close connection
            if (error != "Database closed successfully.") return error; // If error, return error
            return "Successfully deleted user: " + username + " " + password + " ";
        } catch (SQLException e) {
            return "Query error.";
        }
    }

    // Update (name of course)
    public static String update(String username, String password, String email) {
        // Check for errors in input and return errors to caller
        if (username == null) return "Attribute username is null";
        if (password == null) return "Attribute password is null";
        if (email == null) return "Attribute email is null";
        // Connect to DB: Return error message if connection failed
        String error = mysqlConnect();
        if (error != "Successfully connected to database.") return error;
        // Attempt to insert
        try {
            // using PreparedStatement
            pstate = con.prepareStatement("UPDATE Users SET name = ? WHERE username = ? AND password = ?");
            pstate.setString(1, email);
            pstate.setString(2, username);
            pstate.setString(3, password);
            pstate.executeUpdate(); // int value = pstate.executeUpdate();
            error = closeConnection(); // Close connection
            if (error != "Database closed successfully.") return error; // If error, return error
            return "Successfully updated course to: " + username + " " + password + " " + " " + email;
        } catch (SQLException e) {
            return "Query error";
        }
    }

    // Search
    public static String search(String username, String password, String name) {
        String error = mysqlConnect(); // Connect to DB: Return error message if connection failed
        if (error != "Successfully connected to database.") return error;
        try {
            searchHelper(username, password, name);
            int value = pstate.executeUpdate();
            error = closeConnection(); // Close connection
            if (error != "Database closed successfully.") return error; // If error, return error
            return "Successfully updated course to: " + username + " " + password + " " + " " + name;
        } catch (SQLException e) {
            return "Query error";
        }
    }

    private static void searchHelper(String username, String password, String email) throws SQLException {
        if (username != null && password != null && email != null) {
            pstate = con.prepareStatement("SELECT * FROM Users WHERE username = ? AND password = ? AND name = ?");
            pstate.setString(1, username);
            pstate.setString(2, password);
            pstate.setString(3, email);
        } else if (username != null && password != null) {
            pstate = con.prepareStatement("SELECT * FROM Users WHERE username = ? AND password = ?");
            pstate.setString(1, username);
            pstate.setString(2, password);
        } else if (password != null && email != null) {
            pstate = con.prepareStatement("SELECT * FROM Users WHERE number = ? AND name = ?");
            pstate.setString(1, password);
            pstate.setString(2, email);
        } else if (username != null && email!= null) {
            pstate = con.prepareStatement("SELECT * FROM Users WHERE username = ? AND name = ?");
            pstate.setString(1, username);
            pstate.setString(2, email);
        } else if (username != null) {
            pstate = con.prepareStatement("SELECT * FROM Users WHERE username = ?");
            pstate.setString(1, username);
        } else if (password != null) {
            pstate = con.prepareStatement("SELECT * FROM Users WHERE password = ?");
            pstate.setString(1, password);
        } else if (email != null) {
            pstate = con.prepareStatement("SELECT * FROM Users WHERE name = ?");
            pstate.setString(1, email);
        } else { // If all 3 fields are null, return all entries
            pstate = con.prepareStatement("SELECT * FROM Users");
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