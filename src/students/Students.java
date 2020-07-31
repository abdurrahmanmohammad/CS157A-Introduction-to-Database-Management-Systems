package students;

import SQL.SQLMethods;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Students {
    private static ResultSet result;
    private static PreparedStatement pstate;

    public static boolean register(String SID, String department, String number, String configID) {
        if (SID == null) return false;
        if (department == null) return false;
        if (number == null) return false;
        if (configID == null) return false;
        SQLMethods.mysqlConnect(); // Connect to DB
        try { // Attempt to insert
            pstate = SQLMethods.con.prepareStatement("SELECT count(*) AS rowcount FROM Registers");
            result = pstate.executeQuery();
            result.next();
            int registeredCount = result.getInt("registeredcount");
            //System.out.printf("rowcount %d\n", registeredCount);
            result.close();
            pstate = SQLMethods.con.prepareStatement("SELECT seats AS seatcap FROM Configuration WHERE configID = ?");
            pstate.setString(1, configID);
            result = pstate.executeQuery(); // Execute query
            result.next();
            int seatCount = result.getInt("seatcap");
            //System.out.printf("seatcount %d\n", seatCount);
            if (registeredCount < seatCount) {
                pstate = SQLMethods.con.prepareStatement("INSERT INTO Registers(studentID, department, number, configID)" + "values(?, ?, ?, ?)");
            }
            else
            {
                pstate = SQLMethods.con.prepareStatement("INSERT INTO Wailtlists(studentID, department, number, configID)" + "values(?, ?, ?, ?)");
            }
            pstate.setString(1, SID);
            pstate.setString(2, department);
            pstate.setString(3, number);
            pstate.setString(4, configID);
            int value = pstate.executeUpdate();
            SQLMethods.closeConnection(); // Close connection
            return true; // Success

        } catch (SQLException e) {
            SQLMethods.mysql_fatal_error("Query error");
        }

        return false; // Return false as a default value
    }

    public static boolean drop(String department, String number) {
        if (department == null) return false; // Attribute ID is null
        if (number == null) return false; // Attribute balance is null
        SQLMethods.mysqlConnect();
        try { // Attempt to insert
            pstate = SQLMethods.con.prepareStatement("DELETE FROM Registers, Waitlists WHERE department = ? AND number = ?");
            pstate.setString(1, department);
            pstate.setString(2, number);
            int value = pstate.executeUpdate();
            SQLMethods.closeConnection(); // Close connection
            return true; // Success
        } catch (SQLException e) {
            SQLMethods.mysql_fatal_error("Query error");
        }
        return false; // Return false as a default value
    }


    /*public static boolean waitlist() {

    }*/

    /**
     * Method to create and insert a student in the DB. Returns true if successful,
     * otherwise false.
     */
    public static boolean insert(String ID, String balance, String unit_cap) {
        // Check if inputs are null
        if (ID == null) return false; // Attribute ID is null
        if (balance == null) return false; // Attribute balance is null
        if (unit_cap == null) return false; // Attribute unit_cap is null
        SQLMethods.mysqlConnect(); // Connect to DB
        try { // Attempt to insert
            pstate = SQLMethods.con.prepareStatement("INSERT INTO Students(ID, balance, unit_cap)" + "values(?, ?, ?)");
            pstate.setString(1, ID);
            pstate.setString(2, balance);
            pstate.setString(3, unit_cap);
            int value = pstate.executeUpdate();
            SQLMethods.closeConnection(); // Close connection
            return true; // Success
        } catch (SQLException e) {
            SQLMethods.mysql_fatal_error("Query error");
        }
        return false; // Return false as a default value
    }

    /**
     * Method to delete a user using ID. Returns true if successful, otherwise
     * false.
     */
    public static boolean delete(String ID) {
        if (ID == null) return false; // Check if ID is null
        SQLMethods.mysqlConnect(); // Connect to DB
        try { // Attempt to delete
            pstate = SQLMethods.con.prepareStatement("DELETE FROM Students WHERE ID = ?");
            pstate.setString(1, ID);
            int value = pstate.executeUpdate();
            SQLMethods.closeConnection(); // Close connection
            return true; // Success
        } catch (SQLException e) {
            SQLMethods.mysql_fatal_error("Query error");
        }
        return false; // Return false as a default value
    }

    public static boolean updateUnitCap(String ID, String unit_cap) {
        if (ID == null) return false; // Check if ID is null
        if (unit_cap == null) return false; // Check if unit_cap is null
        SQLMethods.mysqlConnect(); // Connect to DB
        try { // Attempt to update
            pstate = SQLMethods.con.prepareStatement("UPDATE Students SET unit_cap = ? WHERE ID = ?");
            pstate.setString(1, unit_cap); // New username
            pstate.setString(2, ID); // ID of user
            int value = pstate.executeUpdate(); // Execute statement
            SQLMethods.closeConnection(); // Close connection
            return true; // Success
        } catch (SQLException e) {
            SQLMethods.mysql_fatal_error("Query error"); // Print error and exit
        }
        return false; // Return false as a default value
    }

    public static boolean updateBalance(String ID, String balance) {
        if (ID == null) return false; // Check if ID is null
        if (balance == null) return false; // Check if balance is null
        SQLMethods.mysqlConnect(); // Connect to DB
        try { // Attempt to update
            pstate = SQLMethods.con.prepareStatement("UPDATE Students SET balance = ? WHERE ID = ?");
            pstate.setString(1, balance); // New password
            pstate.setString(2, ID); // ID of user
            int value = pstate.executeUpdate(); // Execute statement
            SQLMethods.closeConnection(); // Close connection
            return true; // Success
        } catch (SQLException e) {
            SQLMethods.mysql_fatal_error("Query error"); // Print error and exit
        }
        return false; // Return false as a default value
    }

    public static ArrayList<ArrayList<String>> searchBalance(String balance) {
        ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
        if (balance == null) return output; // Check if balance is null, return empty list if so
        SQLMethods.mysqlConnect(); // Connect to DB
        try { // Attempt to search
            pstate = SQLMethods.con.prepareStatement("SELECT * Students WHERE balance = ?");
            pstate.setString(1, balance);
            result = pstate.executeQuery(); // Execute query
            SQLMethods.closeConnection(); // Close connection
            while (result.next()) {
                ArrayList<String> tuple = new ArrayList<String>();
                tuple.add(result.getString("ID"));
                tuple.add(result.getString("balance"));
                tuple.add(result.getString("unit_cap"));
                output.add(tuple);
            }
            result.close(); // Close result
            return output; // Success
        } catch (SQLException e) {
            SQLMethods.mysql_fatal_error("Query error"); // Print error and exit
        }
        return output; // Return false as a default value
    }

    public static ArrayList<ArrayList<String>> searchUnitCap(String unit_cap) {
        ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
        if (unit_cap == null) return output; // Check if unit_cap is null, return empty list if so
        SQLMethods.mysqlConnect(); // Connect to DB
        try { // Attempt to search
            pstate = SQLMethods.con.prepareStatement("SELECT * Students WHERE unit_cap = ?");
            pstate.setString(1, unit_cap);
            result = pstate.executeQuery(); // Execute query
            SQLMethods.closeConnection(); // Close connection
            while (result.next()) {
                ArrayList<String> tuple = new ArrayList<String>();
                tuple.add(result.getString("ID"));
                tuple.add(result.getString("balance"));
                tuple.add(result.getString("unit_cap"));
                output.add(tuple);
            }
            result.close(); // Close result
            return output; // Success
        } catch (SQLException e) {
            SQLMethods.mysql_fatal_error("Query error"); // Print error and exit
        }
        return output; // Return false as a default value
    }
}