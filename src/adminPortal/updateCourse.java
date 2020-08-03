package adminPortal;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courses.Courses;

/**
 * Servlet implementation class updateCourse
 */
@WebServlet("/updateCourse")
public class updateCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateCourse() {
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
		String oldDepartment = request.getParameter("oldDepartment");
		String oldNumber = request.getParameter("oldNumber");
		String newDepartment = request.getParameter("department");
		String newNumber = request.getParameter("number");
		String newTitle = request.getParameter("title");
		int newUnits = Integer.parseInt(request.getParameter("units"));
		int newCost = Integer.parseInt(request.getParameter("cost"));
		Courses.update(oldDepartment, oldNumber, newDepartment, newNumber, newTitle, newUnits, newCost);
		RequestDispatcher req = request.getRequestDispatcher("manageCourses.jsp");
		req.forward(request, response);
	}

}
