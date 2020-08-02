package students;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import students.Students;

/**
 * Servlet implementation class ManageStudentCourses
 */
@WebServlet("/ManageStudentCourses")
public class ManageStudentCourses extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageStudentCourses() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String option = request.getParameter("option"); // Get option number
		if (option.equals("3")) { // Option 3
			String ID = request.getParameter("username"); // Get student ID
      ArrayList<ArrayList<String>> result = Students.viewRegisteredCourses(ID); // Retrieve courses from DB

		} else if (option.equals("4")) { // Option 4
		}
	}
}
