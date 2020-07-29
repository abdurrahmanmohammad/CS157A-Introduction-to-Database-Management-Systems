import SQL.SQLMethods;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Configurations {
    private static ResultSet result;
    private static PreparedStatement pstate;

    private static ArrayList<String> generatedIDs = new ArrayList<>();

    public static boolean insert(String room, String seats, String term, String time, String days, String year) {
        if(room == null) return false;
        if(seats == null) return false;
        if(term == null) return false;
        if(time == null) return false;
        if(days == null) return false;
        if(year == null) return false;
        SQLMethods.mysqlConnect();
        try {
            pstate = SQLMethods.con.prepareStatement(
                    "INSERT INTO Configurations(ID, room, seats, term, time, days, year)"+"values(?, ?, ?, ?, ?, ?, ?)");
            String ID = (int) ( (Math.random() * ((999999999 - 100000000) + 1)) + 100000000 ) + "";
            while (!generatedIDs.isEmpty() && generatedIDs.contains(ID) ) {
                ID = (int) ( (Math.random() * ((999999999 - 100000000) + 1)) + 100000000 ) + "";
            } generatedIDs.add(ID);
            pstate.setString(1, ID);
            pstate.setString(2, room);
            pstate.setString(3, seats);
            pstate.setString(4, term);
            pstate.setString(5, time);
            pstate.setString(6, days);
            pstate.setString(7, year);
            int value = pstate.executeUpdate();
            SQLMethods.closeConnection(); // Close connection
            return true; // Success
        } catch (SQLException e) {
            SQLMethods.mysql_fatal_error("Query error");
        } return false;
    }

    public static boolean delete(String ID) {
        // Check for errors in input and return errors to caller
        if (ID == null) return false;
        SQLMethods.mysqlConnect(); // Connect to DB
        try { // Attempt to delete using PreparedStatement
            pstate = SQLMethods.con.prepareStatement("DELETE FROM Configurations WHERE ID = ?");
            pstate.setString(1, "ID");
            pstate.executeUpdate(); // int value = pstate.executeUpdate();
            SQLMethods.closeConnection(); // Close connection
            return true;
        } catch (SQLException e) {
            SQLMethods.mysql_fatal_error("Query error");
        }
        return false; // Return false as a default value
    }

    public static boolean updateRoom(String ID, String newRoom) {
        if (ID == null) return false; // Check if ID is null
        if (newRoom == null) return false; // Check if newRoom is null
        SQLMethods.mysqlConnect(); // Connect to DB
        try { // Attempt to update
            pstate = SQLMethods.con.prepareStatement("UPDATE Configurations SET room = ? WHERE ID = ?");
            pstate.setString(1, newRoom); // New room
            pstate.setString(2, ID); // ID of user
            int value = pstate.executeUpdate(); // Execute statement
            SQLMethods.closeConnection(); // Close connection
            return true; // Success
        } catch (SQLException e) {
            SQLMethods.mysql_fatal_error("Query error"); // Print error and exit
        }
        return false; // Return false as a default value
    }

    public static boolean updateSeats(String ID, String newSeats) {
        if (ID == null) return false; // Check if ID is null
        if (newSeats == null) return false; // Check if newSeats is null
        SQLMethods.mysqlConnect(); // Connect to DB
        try { // Attempt to update
            pstate = SQLMethods.con.prepareStatement("UPDATE Configurations SET seats = ? WHERE ID = ?");
            pstate.setString(1, newSeats); // New seats
            pstate.setString(2, ID); // ID of user
            int value = pstate.executeUpdate(); // Execute statement
            SQLMethods.closeConnection(); // Close connection
            return true; // Success
        } catch (SQLException e) {
            SQLMethods.mysql_fatal_error("Query error"); // Print error and exit
        }
        return false; // Return false as a default value
    }

    public static boolean updateTerm(String ID, String newTerm) {
        if (ID == null) return false; // Check if ID is null
        if (newTerm == null) return false; // Check if newTerm is null
        SQLMethods.mysqlConnect(); // Connect to DB
        try { // Attempt to update
            pstate = SQLMethods.con.prepareStatement("UPDATE Configurations SET term = ? WHERE ID = ?");
            pstate.setString(1, newTerm); // New term
            pstate.setString(2, ID); // ID of user
            int value = pstate.executeUpdate(); // Execute statement
            SQLMethods.closeConnection(); // Close connection
            return true; // Success
        } catch (SQLException e) {
            SQLMethods.mysql_fatal_error("Query error"); // Print error and exit
        }
        return false; // Return false as a default value
    }

    public static boolean updateTime(String ID, String newTime) {
        if (ID == null) return false; // Check if ID is null
        if (newTime == null) return false; // Check if newTime is null
        SQLMethods.mysqlConnect(); // Connect to DB
        try { // Attempt to update
            pstate = SQLMethods.con.prepareStatement("UPDATE Configurations SET time = ? WHERE ID = ?");
            pstate.setString(1, newTime); // New time
            pstate.setString(2, ID); // ID of user
            int value = pstate.executeUpdate(); // Execute statement
            SQLMethods.closeConnection(); // Close connection
            return true; // Success
        } catch (SQLException e) {
            SQLMethods.mysql_fatal_error("Query error"); // Print error and exit
        }
        return false; // Return false as a default value
    }

    public static boolean updateDays(String ID, String newDays) {
        if (ID == null) return false; // Check if ID is null
        if (newDays == null) return false; // Check if newDays is null
        SQLMethods.mysqlConnect(); // Connect to DB
        try { // Attempt to update
            pstate = SQLMethods.con.prepareStatement("UPDATE Configurations SET days = ? WHERE ID = ?");
            pstate.setString(1, newDays); // New days
            pstate.setString(2, ID); // ID of user
            int value = pstate.executeUpdate(); // Execute statement
            SQLMethods.closeConnection(); // Close connection
            return true; // Success
        } catch (SQLException e) {
            SQLMethods.mysql_fatal_error("Query error"); // Print error and exit
        }
        return false; // Return false as a default value
    }

    public static boolean updateYear(String ID, String newYear) {
        if (ID == null) return false; // Check if ID is null
        if (newYear == null) return false; // Check if newSeats is null
        SQLMethods.mysqlConnect(); // Connect to DB
        try { // Attempt to update
            pstate = SQLMethods.con.prepareStatement("UPDATE Configurations SET seats = ? WHERE ID = ?");
            pstate.setString(1, newYear); // New year
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
