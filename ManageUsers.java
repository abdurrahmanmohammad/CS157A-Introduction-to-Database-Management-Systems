package users;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ManageUsers
 */
@WebServlet("/ManageUsers")
public class ManageUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageUsers() {
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
		if (option.equals("1")) { // Option 1
			// !!!!!!!!!! Must create a Member first and link with User !!!!!!!!!!
			String ID = request.getParameter("createUserID"); // Get User's ID
			String username = request.getParameter("createUserUsername"); // Get User's username
			String password = request.getParameter("createUserPassword"); // Get User's password
			if (ID.isEmpty() || username.isEmpty() || password.isEmpty()) { // If a field is empty
				RequestDispatcher req = request.getRequestDispatcher("AdministratorPortal.jsp");
				req.include(request, response); // Redirect to admin portal
			}
			Users.insert(ID, username, password); // Create and add user to DB
		} else if (option.equals("2")) { // Option 2
			String ID = request.getParameter("deleteUserID"); // Get User's ID
			if (ID.isEmpty()) { // If a field is empty
				RequestDispatcher req = request.getRequestDispatcher("AdministratorPortal.jsp");
				req.include(request, response); // Redirect to admin portal
			}
			Users.delete(ID); // Delete user from DB
			// !!!!!!!!!! Must delete from all tables associated with this ID !!!!!!!!!!
		}
	}
}
