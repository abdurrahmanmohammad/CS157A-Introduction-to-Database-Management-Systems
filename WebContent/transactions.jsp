<%@ page
	import="transactions.Transactions,java.util.ArrayList,java.util.HashMap"
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transactions</title>
<link href="portal.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<% String studentID = request.getParameter("studentID");%>
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
	<h1 style="text-align:center;">Make a Transaction:</h1>
	<form action="transactionAction" method="post">
	<table class="content-table">
		<tr>
			<td>Credit Card</td>
			<td><input type="text" id="creditcard" name="creditcard"> <td>
		</tr>
		<tr>
			<td>Amount</td>
			<td><input type="text" id="amount" name="amount"><td>
		</tr>
		<tr>
			<td><input type="hidden" id="studentID" name="studentID" value=<%=studentID%>></td>
			<td><input type="submit" value="Submit"><td>
		</tr>
	</table>
	</form>
	
	<h1 style="text-align:center;">Transaction History</h1>
	<table class="content-table">
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