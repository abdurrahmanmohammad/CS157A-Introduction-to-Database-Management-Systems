<%@ page import="instructors.Instructors,teaches.Teaches,java.util.ArrayList,java.util.HashMap"
	language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Instructors</title>
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
	<% String instructorID = request.getParameter("instructorID");%>
	<% String status = request.getParameter("status");%>
	<% String adminID = request.getParameter("adminID");%>	
	<div class="topnav">
		<a class="active" href=<%="administratorPortal.jsp?adminID="+adminID%>>Administrator Portal</a> 
		<a href=<%="manageCourses.jsp?adminID="+adminID%>>Manage Courses</a>
		<a href=<%="manageConfigurations.jsp?adminID="+adminID%>>Manage Configurations</a>
		<a href=<%="manageMembers.jsp?adminID="+adminID%>>Manage Members</a>
		<a href=<%="manageStudents.jsp?adminID="+adminID%>>Manage Students</a>
		<a href=<%="manageInstructors.jsp?adminID="+adminID%>>Manage Instructors</a>
		<a href=<%="manageAdministrators.jsp?adminID="+adminID%>>Manage Administrators</a>
		<a href="index.jsp">Logout</a>
	</div>
<form action="updateInstructor" method="post">
<table>
	<tr>
		<td><%="Name: " + request.getParameter("firstname") + " " + request.getParameter("lastname")%></td>
		<td><input type="hidden" id="oldInstructorID" name="oldInstructorID" value=<%=instructorID%>></td>
	</tr>
	<tr>
		<td>Instructor ID</td>
		<td><input type="text" id="newInstructorID" name="newInstructorID" value=<%=instructorID%>><td>
	</tr>
	<tr>
		<td>Status</td>
		<td><input type="text" id="status" name="status" value=<%=status%>><td>
	</tr>
	<tr><td><input type="submit" value="Submit"></td></tr>
</table>
</form> 

<h1>Courses Taught</h1>
	<table style="width: 100%">
		<tr>
			<td>InstructorID</td>
			<td>Department</td>
			<td>Number</td>
			<td>Term</td>
			<td>Year</td>
			<td>Days</td>
			<td>Room</td>
			<td>Seats</td>
		</tr>
	<%
	ArrayList<HashMap<String, String>> courses = Teaches.viewTaughtCourses(instructorID);
	for (int i = 0; i < courses.size(); i++) {
		HashMap<String, String> course = courses.get(i);
	%>
		<tr>
			<td><%=course.get("instructorID")%></td>
			<td><%=course.get("department")%></td>
			<td><%=course.get("number")%></td>
			<td><%=course.get("term")%></td>
			<td><%=course.get("year")%></td>
			<td><%=course.get("days")%></td>
			<td><%=course.get("room")%></td>
			<td><%=course.get("seats")%></td>
		</tr>

	<%
		}
	%>
	</table>
</body>
</html>