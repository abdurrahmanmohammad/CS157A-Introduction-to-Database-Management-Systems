package adminPortal;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import administrators.Administrators;
import instructors.Instructors;
import members.Members;
import registers.Registers;
import students.Students;
import teaches.Teaches;
import transactions.Transactions;
import users.Users;

/**
 * Servlet implementation class updateMember
 */
@WebServlet("/updateMember")
public class updateMember extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public updateMember() {
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
		String oldID = request.getParameter("oldID");
		String newID = request.getParameter("newID");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// If new ID conflicts with an existing member, return
		if (Members.search(newID).size() == 0) {
			// Update references in all tables
			Students.updateID(oldID, newID);
			Instructors.updateID(oldID, newID);
			Administrators.updateID(oldID, newID);
			Registers.updateID(oldID, newID);
			Transactions.updateID(oldID, newID);
			Teaches.updateID(oldID, newID);
			// Update Members
			Members.update(oldID, newID, firstname, lastname, phone, email, address);
			// Update User
			Users.update(oldID, newID, username, password);
		}
		RequestDispatcher req = request.getRequestDispatcher("manageMembers.jsp?adminID=" + adminID);
		req.forward(request, response);
	}

}
