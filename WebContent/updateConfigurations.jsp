<%@ page import="administrators.Administrators,configurations.Configurations,java.util.HashMap" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Configurations</title>
<link href="portal.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<% int configID = Integer.parseInt(request.getParameter("configID"));
	HashMap<String, String> course = Configurations.search(configID);
	String adminID = request.getParameter("adminID");
	int clearance = Administrators.getClearance(adminID);%>
	<div class="topnav">
		<a class="active" href=<%="administratorPortal.jsp?adminID="+adminID%>>Administrator Portal</a> 
		<% if(clearance == 1 || clearance == 3) { // Clearance 1 = manage courses and configs %>
		<a href=<%="manageCourses.jsp?adminID="+adminID%>>Manage Courses</a>
		<a href=<%="manageConfigurations.jsp?adminID="+adminID%>>Manage Configurations</a>
		<% } %>
		<% if(clearance == 2 || clearance == 3) { // Clearance 2 = manage accounts %>
		<a href=<%="manageMembers.jsp?adminID="+adminID%>>Manage Members</a>
		<a href=<%="manageStudents.jsp?adminID="+adminID%>>Manage Students</a>
		<a href=<%="manageInstructors.jsp?adminID="+adminID%>>Manage Instructors</a>
		<a href=<%="manageAdministrators.jsp?adminID="+adminID%>>Manage Administrators</a>
		<% } %>
		<a href="index.jsp">Logout</a>
	</div>
<h1 style="text-align:center;">New Values</h1>
<form action="updateConfiguration" method="post">
<table class="content-table">
	<tr>
		<td><input type="hidden" id="configID" name="configID" value=<%=course.get("configID")%>></td>
		<td><input type="hidden" id="adminID" name="adminID" value=<%=adminID%>></td>
		<td></td>
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
		<td><input type="text" id="time" name="time" value='<%=course.get("time")%>'><td>
	</tr>
	<tr>
		<td>Room</td>
		<td><input type="text" id="room" name="room" value='<%=course.get("room")%>'><td>
	</tr>
	<tr>
		<td>Seats</td>
		<td><input type="text" id="seats" name="seats" value=<%=course.get("seats")%>><td>
	</tr>
	<tr>
		<td></td>
		<td></td>
		<td><input type="submit" value="Submit"></td>
	</tr>
</table>
</form> 
</body>
</html>
