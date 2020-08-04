package instructorPortal;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import registers.Registers;

/**
 * Servlet implementation class dropFromRoster
 */
@WebServlet("/dropFromRoster")
public class dropFromRoster extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public dropFromRoster() {
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
		String studentID = request.getParameter("studentID");
		String department = request.getParameter("department");
		String number = request.getParameter("number");
		int configID = Integer.parseInt(request.getParameter("configID"));
		Registers.drop(studentID, department, number, configID);
		RequestDispatcher req = request.getRequestDispatcher(
				"manageRoster.jsp?studentID=" + studentID + "?department=" + department + "?number=" + number + "?configID=" + configID);
		req.forward(request, response);
	}

}
