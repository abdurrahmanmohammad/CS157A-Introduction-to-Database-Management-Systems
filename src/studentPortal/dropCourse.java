package studentPortal;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import registers.Registers;

/**
 * Servlet implementation class dropCourse
 */
@WebServlet("/dropCourse")
public class dropCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dropCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/** Get inputs */
		String studentID = request.getParameter("studentID");
		String configID = request.getParameter("configID");
		String department = request.getParameter("department");
		String number = request.getParameter("number");
		Registers.drop(studentID, department, number, configID);
		RequestDispatcher req = request.getRequestDispatcher("drop.jsp");
		req.forward(request, response);
	}

}
