<%@ page import="students.Students,registers.Registers,transactions.Transactions,java.util.ArrayList,java.util.HashMap"
	language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Students</title>
</head>
<body>
<% String studentID = request.getParameter("studentID"); // get ID from previous page %>
<% int balance = Integer.parseInt(request.getParameter("balance"));%>
<% int unit_cap = Integer.parseInt(request.getParameter("unit_cap")); %>
<h1 style="text-align:center;">New Values</h1>

<form action="updateStudent" method="post">
<table>
	<tr>
		<td><%="Name: " + request.getParameter("firstname") + " " + request.getParameter("lastname")%></td>
		<td><input type="hidden" id="oldStudentID" name="oldStudentID" value=<%=studentID%>></td>
	</tr>
	<tr>
		<td>Student ID</td>
		<td><input type="text" id="newStudentID" name="newStudentID" value=<%=studentID%>><td>
	</tr>
	<tr>
		<td>Balance</td>
		<td><input type="text" id="balance" name="balance" value=<%=balance%>><td>
	</tr>
	<tr>
		<td>Unit Cap</td>
		<td><input type="text" id="unit_cap" name="unit_cap" value=<%=unit_cap%>><td>
	</tr>
	<tr><td><input type="submit" value="Submit"></td></tr>
</table>
</form> 

<h1>Registered Courses</h1>
	<table style="width: 100%">
		<tr>
			<td>Term</td>
			<td>Year</td>
			<td>Department</td>
			<td>Number</td>
			<td>ConfigID</td>
		</tr>
	<%
	ArrayList<HashMap<String, String>> regCourses = Registers.viewRegisteredCourses(studentID);
	for (int i = 0; i < regCourses.size(); i++) {
		HashMap<String, String> regCourse = regCourses.get(i);
	%>
		<tr>
			<td><%=regCourse.get("term")%></td>
			<td><%=regCourse.get("year")%></td>
			<td><%=regCourse.get("department")%></td>
			<td><%=regCourse.get("number")%></td>
			<td><%=regCourse.get("configID")%></td>
		</tr>

	<%
		}
	%>
	</table>
	
	<h1>Transactions</h1>
	<%
		ArrayList<HashMap<String, String>> transactions = Transactions.viewTransactions(studentID);
	%>
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