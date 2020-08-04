package instructorPortal;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import configurations.Configurations;
import courses.Courses;
import teaches.Teaches;

/**
 * Servlet implementation class teachCourse
 */
@WebServlet("/teachCourse")
public class teachCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public teachCourse() {
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
		String instructorID = request.getParameter("instructorID");
		String department = request.getParameter("department");
		String number = request.getParameter("number");
		int configID = Integer.parseInt(request.getParameter("configID"));
		HashMap<String, String> course = Courses.search(department, number); // Check if course exists
		HashMap<String, String> config = Configurations.search(configID); // Check if config exists
		// If course and config exists, add
		if (course.size() != 0 && config.size() != 0) Teaches.insert(instructorID, department, number, configID); 
		RequestDispatcher req = request.getRequestDispatcher("teachCourse.jsp?instructorID=" + instructorID);
		req.forward(request, response);
	}

}
