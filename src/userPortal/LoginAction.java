package userPortal;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import users.Users;

/**
 * Servlet implementation class LoginAction
 */
@WebServlet("/LoginAction")
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/** Get inputs */
		String username = request.getParameter("username"); // Get username
		String password = request.getParameter("password"); // Get password
		/** Check if inputs username and/or password are empty */
		if (username.isEmpty() || password.isEmpty()) {
			RequestDispatcher req = request.getRequestDispatcher("login.jsp"); // Return to login page
			req.include(request, response); // Request username and password, get the response, redirect to login page
		} else {
			/** Check password and authenticate user */
			// Users.authenticate(username, password): Checks is username and password combo exists in DB
			if (Users.authenticate(username, password)) {
				/** Check if User is a student, instructor, or administrator */
				int type = Users.getType(username);
				// Users.getType(username) checks if the user is a student, instructor, or
				// administrator
				// 1 = Administrator, 2 = Instructor, 3 = Student
				/** Redirect to respective portal/page */
				if (type == 1) { // If the user is an administrator
					RequestDispatcher req = request.getRequestDispatcher("administratorPortal.jsp");
					req.forward(request, response);
				} else if (type == 2) { // If the user is an instructor
					RequestDispatcher req = request.getRequestDispatcher("InstructorPortal.jsp");
					req.forward(request, response);
				} else if (type == 3){ // If the user is a student
					RequestDispatcher req = request.getRequestDispatcher("studentPortal.jsp");
					req.forward(request, response);
				} else { // If member does not have an account (not approved by admin)
					RequestDispatcher req = request.getRequestDispatcher("confirmation.jsp");
					req.include(request, response);
				}
			} else { // If incorrect, go to invalid login page
				RequestDispatcher req = request.getRequestDispatcher("invalidLogin.jsp");
				req.include(request, response);
			}
		}
	}
}
