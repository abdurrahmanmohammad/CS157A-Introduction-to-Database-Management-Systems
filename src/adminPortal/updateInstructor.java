package adminPortal;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courses.Courses;
import instructors.Instructors;
import members.Members;
import teaches.Teaches;
import users.Users;

/**
 * Servlet implementation class updateInstructor
 */
@WebServlet("/updateInstructor")
public class updateInstructor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public updateInstructor() {
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
		String oldInstructorID = request.getParameter("oldInstructorID");
		String newInstructorID = request.getParameter("newInstructorID");
		String status = request.getParameter("status");

		// If new ID conflicts with an existing member, return
		if (oldInstructorID.equals(newInstructorID) || Members.search(newInstructorID).size() == 0) {
			// Update references of ID in all tables (if we want to change ID)
			Members.updateID(oldInstructorID, newInstructorID); // Update reference in Members
			Users.updateID(oldInstructorID, newInstructorID); // Update reference in Users
			Teaches.updateID(oldInstructorID, newInstructorID); // Update reference in Teaches
			// Update instructor
			Instructors.update(oldInstructorID, newInstructorID, status);
		}
		RequestDispatcher req = request.getRequestDispatcher("manageInstructors.jsp?adminID=" + adminID);
		req.forward(request, response);
	}

}
