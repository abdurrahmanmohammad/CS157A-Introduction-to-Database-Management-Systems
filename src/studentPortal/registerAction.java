package studentPortal;

import java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import members.Members;

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
		// If any fields are empty, return back to the form
		if (firstname.isEmpty() || lastname.isEmpty() || phone.isEmpty() || email.isEmpty() || address.isEmpty()) {
			RequestDispatcher req = request.getRequestDispatcher("register.jsp"); // Return to the form
			req.include(request, response);
		} else {
			// Add member info to DB
			Random rand = new Random();
			String ID = Integer.toString(rand.nextInt(1000000000)); // Generate ID (9 digits)
			// Check if ID already exists
			while (!Members.checkID(ID)) // Keep on generating until find an ID that DNE
				ID = Integer.toString(rand.nextInt(1000000000)); // Generate ID (9 digits)
			Members.insert(ID, firstname, lastname, phone, email, address); // Add member to DB
			RequestDispatcher req = request.getRequestDispatcher("confirmation.jsp"); // Go to confirmation page
			req.forward(request, response);
		}
	}
}
