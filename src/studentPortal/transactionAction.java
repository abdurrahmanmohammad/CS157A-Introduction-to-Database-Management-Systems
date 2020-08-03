package studentPortal;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import transactions.Transactions;

/**
 * Servlet implementation class transactionAction
 */
@WebServlet("/transactionAction")
public class transactionAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public transactionAction() {
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
		String studentID = request.getParameter("studentID");
		String creditcard = request.getParameter("creditcard");
		int amount = Integer.parseInt(request.getParameter("amount"));
		if (!creditcard.isEmpty() && !studentID.isEmpty()) Transactions.pay(studentID, creditcard, amount);
		RequestDispatcher req = request.getRequestDispatcher("transactions.jsp?studentID=" + studentID);
		req.forward(request, response);
	}

}
