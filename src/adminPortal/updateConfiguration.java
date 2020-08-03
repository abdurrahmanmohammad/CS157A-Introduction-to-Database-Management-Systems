package adminPortal;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import configurations.Configurations;
import courses.Courses;

/**
 * Servlet implementation class updateConfiguration
 */
@WebServlet("/updateConfiguration")
public class updateConfiguration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public updateConfiguration() {
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
		int oldConfigID = Integer.parseInt(request.getParameter("oldConfigID"));
		int newConfigID = Integer.parseInt(request.getParameter("newConfigID"));
		String term = request.getParameter("term");
		int year = Integer.parseInt(request.getParameter("year"));
		String days = request.getParameter("days");
		String time = request.getParameter("time");
		String room = request.getParameter("room");
		int seats = Integer.parseInt(request.getParameter("seats"));
		Configurations.update(oldConfigID, newConfigID, term, year, days, time, room, seats);
		RequestDispatcher req = request.getRequestDispatcher("manageConfigurations.jsp");
		req.forward(request, response);
	}
}
