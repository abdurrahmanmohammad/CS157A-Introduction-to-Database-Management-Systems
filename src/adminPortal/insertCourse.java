package adminPortal;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courses.Courses;

/**
 * Servlet implementation class insertCourse
 */
@WebServlet("/insertCourse")
public class insertCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public insertCourse() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String adminID = request.getParameter("adminID");
		String department = request.getParameter("department");
		String number = request.getParameter("number");
		String title = request.getParameter("title");
		int units = Integer.parseInt(request.getParameter("units"));
		int cost = Integer.parseInt(request.getParameter("cost"));
		// Add only if course does not already exist
		if (Courses.search(department, number).size() == 0) Courses.insert(department, number, title, units, cost);
		RequestDispatcher req = request.getRequestDispatcher("manageCourses.jsp?adminID=" + adminID);
		req.forward(request, response);
	}

}
