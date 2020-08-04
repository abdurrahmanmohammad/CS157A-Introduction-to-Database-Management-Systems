package adminPortal;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import members.Members;
import registers.Registers;
import students.Students;
import transactions.Transactions;
import users.Users;

/**
 * Servlet implementation class updateStudents
 */
@WebServlet("/updateStudent")
public class updateStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public updateStudent() {
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
		String oldStudentID = request.getParameter("oldStudentID");
		String newStudentID = request.getParameter("newStudentID");
		int balance = Integer.parseInt(request.getParameter("balance"));
		int unit_cap = Integer.parseInt(request.getParameter("unit_cap"));

		// If new ID conflicts with an existing member, return
		if (Members.search(newStudentID).size() == 0) {
			// Update references of ID in all tables (if we want to change ID)
			Members.updateID(oldStudentID, newStudentID); // Update reference in Members
			Users.updateID(oldStudentID, newStudentID); // Update reference in Users
			Registers.updateID(oldStudentID, newStudentID); // Update reference in Registers & Waitlists
			Transactions.updateID(oldStudentID, newStudentID); // Update reference in Transactions
			// Update student
			Students.update(oldStudentID, newStudentID, balance, unit_cap);
		}
		RequestDispatcher req = request.getRequestDispatcher("manageStudents.jsp?adminID=" + adminID);
		req.forward(request, response);
	}
}
