import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import students.Students;

@WebServlet("/ManageStudents")
public class ManageStudents extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String option = request.getParameter("option"); // Get option number
        if (option.equals("3")) { // Option 3
            String ID = request.getParameter("createStudentID"); // Get Student's ID
            String balance = request.getParameter("showBalance"); // Get Student's balance
            String unit_cap = request.getParameter("showUnitCap"); // Get Student's number


            if (ID.isEmpty() || balance.isEmpty()
                    || unit_cap.isEmpty()) { // If a field is
                // empty
                RequestDispatcher req = request.getRequestDispatcher("AdministratorPortal.jsp");
                req.include(request, response); // Redirect to admin portal
            }
            Students.insert(ID, balance, unit_cap); // Create and add course to DB
        } else if (option.equals("4")) { // Option 4
            String ID = request.getParameter("deleteStudentID"); // Get Course's ID
            if (ID.isEmpty()) { // If a field is empty
                RequestDispatcher req = request.getRequestDispatcher("AdministratorPortal.jsp");
                req.include(request, response); // Redirect to admin portal
            }
            Students.delete(ID); // Delete course from DB
            // !!!!!!!!!! Must delete from all tables associated with this ID !!!!!!!!!!
        }
    }
}
