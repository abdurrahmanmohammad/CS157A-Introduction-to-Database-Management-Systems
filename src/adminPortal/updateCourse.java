package adminPortal;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courses.Courses;
import registers.Registers;
import teaches.Teaches;

/**
 * Servlet implementation class updateCourse
 */
@WebServlet("/updateCourse")
public class updateCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public updateCourse() {
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
		/** Get inputs */
		String adminID = request.getParameter("adminID");
		String oldDept = request.getParameter("oldDepartment");
		String oldNum = request.getParameter("oldNumber");
		String newDept = request.getParameter("department");
		String newNum = request.getParameter("number");
		String title = request.getParameter("title");
		int units = Integer.parseInt(request.getParameter("units"));
		int cost = Integer.parseInt(request.getParameter("cost"));
		// If new attributes conflict with an existing course, return
		if (Courses.search(newDept, newNum).size() == 0) {
			// Update references to the course
			Registers.updateCourse(oldDept, oldNum, newDept, newNum);
			Teaches.updateCourse(oldDept, oldNum, newDept, newNum);
			// Update course
			Courses.update(oldDept, oldNum, newDept, newNum, title, units, cost);
		}
		RequestDispatcher req = request.getRequestDispatcher("manageCourses.jsp?adminID=" + adminID);
		req.forward(request, response);
	}

}
