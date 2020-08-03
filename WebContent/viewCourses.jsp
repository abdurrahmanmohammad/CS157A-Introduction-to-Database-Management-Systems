<%@ page import="teaches.Teaches,registers.Registers,java.util.ArrayList,java.util.HashMap" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Courses</title>
<link href="portal.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<% String studentID = request.getParameter("studentID"); // get ID from previous page %>
	<div class="topnav">
		<a class="active" href=<%="studentPortal.jsp?studentID="+studentID%>>Student Portal</a> 
		<a href=<%="add.jsp?studentID="+studentID%>>Add Courses</a>
		<a href=<%="drop.jsp?studentID="+studentID%>>Drop Courses</a>
		<a href=<%="viewCourses.jsp?studentID="+studentID%>>View Courses</a>
		<a href=<%="transcript.jsp?studentID="+studentID%>>Transcript</a>
		<a href=<%="transactions.jsp?studentID="+studentID%>>Transactions</a>
		<a href="index.jsp">Logout</a>
	</div>
	<h1>Offered Courses</h1>
	<table class="content-table">
		<tr>
			<td>Term</td>
			<td>Year</td>
			<td>Department</td>
			<td>Number</td>
			<td>Firstname</td>
			<td>Lastname</td>
			<td>Days</td>
			<td>Time</td>
			<td>Room</td>
		</tr>
	<%
		ArrayList<HashMap<String, String>> courses = Teaches.getCourses();
	for (int i = 0; i < courses.size(); i++) {
		HashMap<String, String> course = courses.get(i);
	%>
		 
			<tr>
				<td><%=course.get("term")%></td>
				<td><%=course.get("year")%></td>
				<td><%=course.get("department")%></td>
				<td><%=course.get("number")%></td>
				<td><%=course.get("firstname")%></td>
				<td><%=course.get("lastname")%></td>
				<td><%=course.get("days")%></td>
				<td><%=course.get("time")%></td>
				<td><%=course.get("room")%></td>
			</tr>
	<%
		}
	%>
	</table>
</body>
</html>