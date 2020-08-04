package userPortal;

import java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import administrators.Administrators;
import instructors.Instructors;
import members.Members;
import students.Students;

/**
 * Servlet implementation class registerAction
 */
@WebServlet("/registerAction")
public class registerAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public registerAction() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get fields from form
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String type = request.getParameter("type");

		/** If any fields are empty, return back to the form */
		if (firstname.isEmpty() || lastname.isEmpty() || phone.isEmpty() || email.isEmpty() || address.isEmpty()
				|| type.isEmpty()) {
			RequestDispatcher req = request.getRequestDispatcher("register.jsp"); // Return to the form
			req.include(request, response);
		} else {
			/** Generate ID */
			Random rand = new Random();
			String ID = Integer.toString(rand.nextInt(1000000000)); // Generate ID (9 digits)
			/** Check if ID already exists. Keep on generating until find an ID that DNE */
			while (!Members.checkID(ID))
				ID = Integer.toString(rand.nextInt(1000000000)); // Generate ID (9 digits)
			/** Get type of user (student, instructor, administrator). */
			if (type.equalsIgnoreCase("administrator")) {
				Administrators.insert(ID, 0); // Clearance = 0 (no access until an existing admin eupdates)
				Members.insert(ID, firstname, lastname, phone, email, address); // Add member to DB
				/** Go to confirmation page */
				RequestDispatcher req = request.getRequestDispatcher("confirmation.jsp"); // Go to confirmation page
				req.forward(request, response);
			} else if (type.equalsIgnoreCase("instructor")) {
				Instructors.insert(ID, "Inactive");
				Members.insert(ID, firstname, lastname, phone, email, address); // Add member to DB
				/** Go to confirmation page */
				RequestDispatcher req = request.getRequestDispatcher("confirmation.jsp"); // Go to confirmation page
				req.forward(request, response);
			} else if (type.equalsIgnoreCase("instructor")) { // Student
				Students.insert(ID, 0, 12); // Initial unit cap is 12
				Members.insert(ID, firstname, lastname, phone, email, address); // Add member to DB
				/** Go to confirmation page */
				RequestDispatcher req = request.getRequestDispatcher("confirmation.jsp"); // Go to confirmation page
				req.forward(request, response);
			} else { // Invalid type. Return to form.
				/** If invalid type, return back to form */
				RequestDispatcher req = request.getRequestDispatcher("register.jsp"); // Return to the form
				req.include(request, response);
			}
		}
	}
}
