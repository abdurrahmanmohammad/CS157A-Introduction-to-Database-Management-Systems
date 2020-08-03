package adminPortal;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import administrators.Administrators;
import courses.Courses;
import instructors.Instructors;
import members.Members;
import registers.Registers;
import students.Students;
import teaches.Teaches;
import transactions.Transactions;
import users.Users;

/**
 * Servlet implementation class deleteMember
 */
@WebServlet("/deleteMember")
public class deleteMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteMember() {
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
		String ID = request.getParameter("ID");
		String number = request.getParameter("number");
		/** Delete Member and User */
		Members.delete(ID);
		Users.delete(ID);
		/** Delete all dependencies to this ID */
		Administrators.delete(ID);
		Students.delete(ID);
		Registers.dropAll(ID);
		Transactions.deleteAll(ID);
		Instructors.delete(ID);
		Teaches.dropAll(ID);
		
		/** Return back */
		RequestDispatcher req = request.getRequestDispatcher("manageMembers.jsp");
		req.forward(request, response); 
	}

}
