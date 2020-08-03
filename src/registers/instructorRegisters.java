package registers;

import SQL.SQLMethods;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import instructors.Instructors;
import instructors.Instructors.java;

/** InstructorRegisters(instructorID, department, number, configID) */
public class InstructorRegisters {
    private static ResultSet result;
    private static PreparedStatement pstate;

    /**
     * Method that allows instructors to register for a course. Method adds values to
     * table Registers(instructorID, department, number, configID) if course is open
     * and instructor has not already registered for this course. If class is taken by another instructor,
     * the course will not appear as an option to register.
     *
     * @param instructorID
     * @param department
     * @param number
     * @param configID
     * @return true if successful, false if failure
     */
    public static boolean register(String instructorID, String department, String number, String configID) {
        /** Check for invalid inputs. If any input is null, return false */
        if (instructorID == null || department == null || number == null || configID == null) return false;
        SQLMethods.mysqlConnect(); // Connect to DB
        try { // Attempt to insert
            /** Check if instructor already registered for this course */
            // Return false if already registered
            if (isRegistered(instructorID))
                return false;
            /** Check if seats are open */
            if (isOpen(department, number, configID)) { // If course is open, register for course
                Instructors.updateStatus(instructorID, "Registered");
            }
            pstate.setString(1, instructorID);
            pstate.setString(2, department);
            pstate.setString(3, number);
            pstate.setString(4, configID);
            int rowcount = pstate.executeUpdate(); // Number of rows affected
            SQLMethods.closeConnection(); // Close connection
            return (rowcount == 1); // If rowcount == 1, row successfully inserted
        } catch (SQLException e) { // Print error and terminate program
            SQLMethods.mysql_fatal_error("Query error: " + e.toString());
        }
        return false; // Default value: false
    }

    /**
     * Checks if an instructor is already registered for a certain course in
     * Registers(instructorID, department, number, configID).
     *
     * @throws SQLException
     */
    private static boolean isRegistered(String instructorID)
            throws SQLException {
        String status = Instructors.getStatus(instructorID);
        if (status.equals("available") || status.equals("")) return false;
        else return true;
    }

    /** Gets the seats for a particular configuration */
    public static int getSeats(String configID) throws SQLException {
        pstate = SQLMethods.con.prepareStatement("SELECT seats FROM Configurations WHERE configID = ?;");
        pstate.setString(1, configID);
        result = pstate.executeQuery(); // Execute query
        result.next();
        int rowcount = result.getInt(1);
        result.close();
        return rowcount;
    }

    /**
     * Method that allows students to drop a course. Remove course from Waitlists
     * and Registers.
     *
     * @param department
     * @param number
     * @return true if successful, false if failure
     */
    public static boolean drop(String instructorID, String department, String number, String configID) {
        if (instructorID == null || department == null | number == null || configID == null) return false;
        SQLMethods.mysqlConnect(); // Connect to DB
        try { // Attempt to delete
            /**
             * Check if instructor is in Registers. If so, we check else statement. If not, we
             * have another instructor waiting on waitlist, so we move waiting instructor to
             * Registers and delete old instructor.
             */
            if (isWaitlisted(instructorID, instructorID, number, configID)) { // If student is on waitlist
                pstate = SQLMethods.con.prepareStatement(
                        "DELETE FROM Waitlists WHERE instructorID = ? AND department = ? AND number = ? AND configID = ?;");
            } else {
                pstate = SQLMethods.con.prepareStatement(
                        "DELETE FROM Registers WHERE instructorID = ? AND department = ? AND number = ? AND configID = ?;");
                pstate.setString(1, instructorID);
                pstate.setString(2, department);
                pstate.setString(3, number);
                pstate.setString(4, configID);
                int value = pstate.executeUpdate();

                /** Adjust the waitlist if needed */
                adjustWaitlist(instructorID, instructorID, number, configID);
            }
            SQLMethods.closeConnection(); // Close connection
            return true; // Success
        } catch (SQLException e) {
            SQLMethods.mysql_fatal_error("Query error: " + e.toString());
        }
        return false; // Return false as a default value
    }

    /**
     * If someone is on the Registers and drops, the next person on the waitlist will
     * get that course. Bump up the waitlist.
     *
     * @param department
     * @param number
     * @param configID
     * @throws SQLException
     */
    private static void adjustWaitlist(String oldinstructorID, String department, String number, String configID)
            throws SQLException {
        /** Find waiting student on top of waitlist */
        pstate = SQLMethods.con.prepareStatement(
                "SELECT instructorID FROM Waitlists WHERE department = ? AND number = ? AND configID = ?;");
        pstate.setString(1, department);
        pstate.setString(2, number);
        pstate.setString(3, configID);
        result = pstate.executeQuery(); // Execute query
        if (result.next() == false) return; // Return back if empty
        String newinstructorID = result.getString(1); // Get first instructorID in list

        /** Register new student from waitlist */
        register(newinstructorID, department, number, configID); // Register student who was on top of waitlist

        /** Remove new student from waitlist */
        pstate = SQLMethods.con.prepareStatement(
                "DELETE FROM Waitlists WHERE instructorID = ? AND department = ? AND number = ? AND configID = ?;");
        pstate.setString(1, newinstructorID);
        pstate.setString(2, department);
        pstate.setString(3, number);
        pstate.setString(4, configID);
        return;
    }

    /**
     * Returns an ArrayList of all courses registered by a student identified by
     * instructorID
     *
     * @param instructorID
     * @return
     */
    public static ArrayList<HashMap<String, String>> viewRegisteredCourses(String instructorID) {
        ArrayList<HashMap<String, String>> output = new ArrayList<HashMap<String, String>>();
        /** Check for invalid inputs. If any input is null, return false */
        if (instructorID == null) return output; // Check if instructorID is null, return empty list if so
        SQLMethods.mysqlConnect(); // Connect to DB
        try { // Attempt to search
            /** Search and retrieve tuple */
            pstate = SQLMethods.con.prepareStatement(
                    "SELECT * FROM Registers JOIN Configurations Using (configID) WHERE instructorID = ? ORDER BY year DESC;");
            pstate.setString(1, instructorID);
            result = pstate.executeQuery(); // Execute query
            /** Extract tuple data */
            while (result.next()) {
                HashMap<String, String> tuple = new HashMap<String, String>();
                tuple.put("term", result.getString("term"));
                tuple.put("year", result.getString("year"));
                tuple.put("department", result.getString("department"));
                tuple.put("number", result.getString("number"));
                tuple.put("days", result.getString("days"));
                tuple.put("time", result.getString("time"));
                tuple.put("room", result.getString("room"));
                tuple.put("configID", result.getString("configID"));
                output.add(tuple);
            }
            result.close(); // Close result
            SQLMethods.closeConnection(); // Close connection
            return output; // Return output
        } catch (SQLException e) { // Print error and terminate program
            SQLMethods.mysql_fatal_error("Query error: " + e.toString());
        }
        return output; // Return false as a default value
    }

    public static ArrayList<HashMap<String, String>> viewTeachingCourses(String instructorID) {
        ArrayList<HashMap<String, String>> output = new ArrayList<HashMap<String, String>>();
        /** Check for invalid inputs. If any input is null, return false */
        if (instructorID == null) return output; // Check if instructorID is null, return empty list if so
        SQLMethods.mysqlConnect(); // Connect to DB
        try { // Attempt to search
            /** Search and retrieve tuple */
            pstate = SQLMethods.con.prepareStatement(
                    "SELECT * FROM Registers JOIN Configurations Using (configID) WHERE instructorID = ? ORDER BY year DESC;");
            pstate.setString(1, instructorID);
            result = pstate.executeQuery(); // Execute query
            /** Extract tuple data */
            while (result.next()) {
                HashMap<String, String> tuple = new HashMap<String, String>();
                tuple.put("term", result.getString("term"));
                tuple.put("year", result.getString("year"));
                tuple.put("department", result.getString("department"));
                tuple.put("number", result.getString("number"));
                tuple.put("days", result.getString("days"));
                tuple.put("time", result.getString("time"));
                tuple.put("room", result.getString("room"));
                tuple.put("configID", result.getString("configID"));
                output.add(tuple);
            }
            result.close(); // Close result
            SQLMethods.closeConnection(); // Close connection
            return output; // Return output
        } catch (SQLException e) { // Print error and terminate program
            SQLMethods.mysql_fatal_error("Query error: " + e.toString());
        }
        return output; // Return false as a default value
    }

    /**
     * Returns an ArrayList of all courses waitlisted by a student identified by
     * instructorID
     *
     * @param instructorID
     * @return
     */
    public static ArrayList<HashMap<String, String>> viewWaitlistedCourses(String instructorID) {
        ArrayList<HashMap<String, String>> output = new ArrayList<HashMap<String, String>>();
        /** Check for invalid inputs. If any input is null, return false */
        if (instructorID == null) return output; // Check if instructorID is null, return empty list if so
        SQLMethods.mysqlConnect(); // Connect to DB
        try { // Attempt to search
            /** Search and retrieve tuple */
            pstate = SQLMethods.con.prepareStatement(
                    "SELECT * FROM Waitlists JOIN Configurations Using (configID) WHERE instructorID = ? ORDER BY year DESC;");
            pstate.setString(1, instructorID);
            result = pstate.executeQuery(); // Execute query
            /** Extract tuple data */
            while (result.next()) {
                HashMap<String, String> tuple = new HashMap<String, String>();
                tuple.put("term", result.getString("term"));
                tuple.put("year", result.getString("year"));
                tuple.put("department", result.getString("department"));
                tuple.put("number", result.getString("number"));
                tuple.put("days", result.getString("days"));
                tuple.put("time", result.getString("time"));
                tuple.put("room", result.getString("room"));
                output.add(tuple);
            }
            result.close(); // Close result
            SQLMethods.closeConnection(); // Close connection
            return output; // Return output
        } catch (SQLException e) { // Print error and terminate program
            SQLMethods.mysql_fatal_error("Query error: " + e.toString());
        }
        return output; // Return false as a default value
    }
}
