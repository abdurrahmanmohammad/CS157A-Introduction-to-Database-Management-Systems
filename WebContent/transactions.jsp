<%@ page
	import="transactions.Transactions,java.util.ArrayList,java.util.HashMap"
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transactions</title>
<style>
body {
	background: url(pictures/studentPortal.jpg) no-repeat;
	background-size: cover;
	margin: 0;
	padding: 0;
	font-family: Arial, Helvetica, sans-serif;
}

.topnav {
	overflow: hidden;
	background-color: #030024;
}

.topnav a {
	float: left;
	color: #f2f2f2;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
	font-size: 17px;
}

.topnav a:hover {
	background-color: #ddd;
	color: black;
}

.topnav a.active {
	background-color: #010d42;
	color: white;
}
</style>
</head>
<body>
	<%
		String studentID = request.getParameter("studentID"); // get ID from previous page
	%>
	<div class="topnav">
		<a class="active" href=<%="studentPortal.jsp?studentID="+studentID%>>Student Portal</a> 
		<a href=<%="add.jsp?studentID=" + studentID%>>Add Courses</a> 
		<a href=<%="drop.jsp?studentID=" + studentID%>>Drop Courses</a> 
		<a href=<%="viewCourses.jsp?studentID="+studentID%>>View Courses</a>
		<a href=<%="transcript.jsp?studentID=" + studentID%>>Transcript</a> 
		<a href=<%="transactions.jsp?studentID=" + studentID%>>Transactions</a> 
		<a href="index.jsp">Logout</a>
	</div>
	<%
		ArrayList<HashMap<String, String>> transactions = Transactions.viewTransactions(studentID);
	%>
	<h1>Make a Transaction:</h1>
	<form action="transactionAction" method="post">
		<label for="credit card">Credit Card:</label><br> <input
			type="text" id="creditcard" name="creditcard"><br> <label
			for="amount">Amount:</label><br> <input type="text" id="amount"
			name="amount"><br> <input type="hidden" id="studentID"
			name="studentID" value=<%=request.getParameter("studentID")%>>
		<input type="submit" value="Submit">
	</form>

	<table style="width: 100%">
		<tr>
			<td>Credit Card</td>
			<td>Amount</td>
			<td>Timestamp</td>
		</tr>

		<%
			for (int i = 0; i < transactions.size(); i++) {
			HashMap<String, String> transaction = transactions.get(i);
		%>
		<tr>
			<td><%=transaction.get("creditcard")%></td>
			<td><%=transaction.get("amount")%></td>
			<td><%=transaction.get("timestamp")%></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>