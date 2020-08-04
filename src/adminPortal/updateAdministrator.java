package adminPortal;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import administrators.Administrators;
import members.Members;
import users.Users;

/**
 * Servlet implementation class updateAdministrator
 */
@WebServlet("/updateAdministrator")
public class updateAdministrator extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public updateAdministrator() {
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
		String oldAdminID = request.getParameter("oldAdminID");
		String newAdminID = request.getParameter("newAdminID");
		int clearance = Integer.parseInt(request.getParameter("clearance"));
		// If new ID conflicts with an existing member, return
		if (oldAdminID.equals(newAdminID) || Members.search(newAdminID).size() == 0) {
			// Update references
			Members.updateID(oldAdminID, newAdminID);
			Users.updateID(oldAdminID, newAdminID);
			// Update admin
			Administrators.update(oldAdminID, newAdminID, clearance);
		}
		RequestDispatcher req = request.getRequestDispatcher("manageAdministrators.jsp?adminID=" + adminID);
		req.forward(request, response);
	}
}
