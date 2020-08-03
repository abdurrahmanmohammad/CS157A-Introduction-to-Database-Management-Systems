<%@ page import="configurations.Configurations,java.util.HashMap" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Configurations</title>
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
<% int configID = Integer.parseInt(request.getParameter("configID"));%>
<% HashMap<String, String> course = Configurations.search(configID);%>
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
<h1 style="text-align:center;">New Values</h1>
<form action="updateConfiguration" method="post">
<table>
	<tr>
		<td><input type="hidden" id="oldConfigID" name="oldConfigID" value=<%=course.get("configID")%>></td>
	</tr>
	<tr>
		<td>ConfigID</td>
		<td><input type="text" id="newConfigID" name="newConfigID" value=<%=course.get("configID")%>><td>
	</tr>
	<tr>
		<td>Term</td>
		<td><input type="text" id="term" name="term" value=<%=course.get("term")%>><td>
	</tr>
	<tr>
		<td>Year</td>
		<td><input type="text" id="year" name="year" value=<%=course.get("year")%>><td>
	</tr>
	<tr>
		<td>Days</td>
		<td><input type="text" id="days" name="days" value=<%=course.get("days")%>><td>
	</tr>
	<tr>
		<td>Time</td>
		<td><input type="text" id="time" name="time" value=<%=course.get("time")%>><td>
	</tr>
	<tr>
		<td>Room</td>
		<td><input type="text" id="room" name="room" value=<%=course.get("room")%>><td>
	</tr>
	<tr>
		<td>Seats</td>
		<td><input type="text" id="seats" name="seats" value=<%=course.get("seats")%>><td>
	</tr>
	<tr><td><input type="submit" value="Submit"></td></tr>
</table>
</form> 

</body>
</html>