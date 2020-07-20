package courses;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import users.Users;

/**
 * Servlet implementation class ManageCourses
 */
@WebServlet("/ManageCourses")
public class ManageCourses extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageCourses() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String option = request.getParameter("option"); // Get option number
		if (option.equals("3")) { // Option 3
			String ID = request.getParameter("createCourseID"); // Get Course's ID
			String department = request.getParameter("createCourseDepartment"); // Get Course's department
			String number = request.getParameter("createCourseNumber"); // Get Course's number
			String title = request.getParameter("createCourseTitle"); // Get Course's number
			String units = request.getParameter("createCourseUnits"); // Get Course's units
			String cost = request.getParameter("createCourseCost"); // Get Course's cost

			if (ID.isEmpty() || department.isEmpty()
					|| number.isEmpty() | title.isEmpty() | units.isEmpty() | cost.isEmpty()) { // If a field is
																								// empty
				RequestDispatcher req = request.getRequestDispatcher("AdministratorPortal.jsp");
				req.include(request, response); // Redirect to admin portal
			}
			Courses.insert(ID, department, number, title, units, cost); // Create and add course to DB
		} else if (option.equals("4")) { // Option 4
			String ID = request.getParameter("deleteCourseID"); // Get Course's ID
			if (ID.isEmpty()) { // If a field is empty
				RequestDispatcher req = request.getRequestDispatcher("AdministratorPortal.jsp");
				req.include(request, response); // Redirect to admin portal
			}
			Courses.delete(ID); // Delete course from DB
			// !!!!!!!!!! Must delete from all tables associated with this ID !!!!!!!!!!
		}
	}

}
