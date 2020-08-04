<%@ page import="registers.Registers,java.util.ArrayList,java.util.HashMap"
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transcript</title>
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
	<h1 style="text-align:center;">Transcript</h1>
	<table class="content-table">
		<tr>
			<td>Term</td>
			<td>Year</td>
			<td>Department</td>
			<td>Number</td>
		</tr>
	<%
	ArrayList<HashMap<String, String>> courses = Registers.viewRegisteredCourses(studentID);
	for (int i = 0; i < courses.size(); i++) {
		HashMap<String, String> course = courses.get(i);
	%>
			<tr>
				<td><%=course.get("term")%></td>
				<td><%=course.get("year")%></td>
				<td><%=course.get("department")%></td>
				<td><%=course.get("number")%></td>
			</tr>
	<%
		}
	%>
	</table>

</body>
</html>
