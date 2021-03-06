<%@ page
	import="teaches.Teaches,registers.Registers,java.util.ArrayList,java.util.HashMap"
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Courses</title>
<link href="portal.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<% String studentID = request.getParameter("studentID"); %>
	<div class="topnav">
		<a class="active" href=<%="studentPortal.jsp?studentID="+studentID%>>Student Portal</a> 
		<a href=<%="add.jsp?studentID="+studentID%>>Add Courses</a>
		<a href=<%="drop.jsp?studentID="+studentID%>>Drop Courses</a>
		<a href=<%="viewCourses.jsp?studentID="+studentID%>>View Courses</a>
		<a href=<%="transcript.jsp?studentID="+studentID%>>Transcript</a>
		<a href=<%="transactions.jsp?studentID="+studentID%>>Transactions</a>
		<a href="index.jsp">Logout</a>
	</div>
	<h1 style="text-align:center;">Registered Courses</h1>
	<table class="content-table">
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
	<h1 style="text-align:center;">Add Courses</h1>
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
				<td>
					<form action="addCourse" method="post">
					<input type="submit" value="Add">
					<input type="hidden" id="studentID" name="studentID" value=<%=studentID%>>
					<input type="hidden" id="configID" name="configID" value=<%=course.get("configID")%>>
					<input type="hidden" id="department" name="department" value=<%=course.get("department")%>>
					<input type="hidden" id="number" name="number" value=<%=course.get("number")%>>
					</form>
				</td>
			</tr>
	<%
		}
	%>
	</table>
</body>
</html>