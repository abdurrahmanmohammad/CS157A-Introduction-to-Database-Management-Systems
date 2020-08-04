package adminPortal;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import configurations.Configurations;

/**
 * Servlet implementation class deleteConfiguration
 */
@WebServlet("/deleteConfiguration")
public class deleteConfiguration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteConfiguration() {
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
		String adminID = request.getParameter("adminID");
		String configID = request.getParameter("configID");
		/** Delete configuration */
		Configurations.delete(configID); // Remove configuration
		/** Return to manageConfigurations.jsp */
		RequestDispatcher req = request.getRequestDispatcher("manageConfigurations.jsp?adminID=" + adminID);
		req.forward(request, response); // Return to manageConfigurations.jsp
	}

}
