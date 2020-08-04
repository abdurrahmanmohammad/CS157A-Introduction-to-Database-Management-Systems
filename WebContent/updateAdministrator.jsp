<%@ page import="administrators.Administrators,java.util.ArrayList,java.util.HashMap"
	language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Administrators</title>
<link href="portal.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<% String adminID = request.getParameter("adminID");
		int clearance = Integer.parseInt(request.getParameter("clearance"));
		int clearance1 = Administrators.getClearance(adminID);
	%>
	<div class="topnav">
		<a class="active" href=<%="administratorPortal.jsp?adminID="+adminID%>>Administrator Portal</a> 
		<% if(clearance1 == 1 || clearance1 == 3) { // Clearance 1 = manage courses and configs %>
		<a href=<%="manageCourses.jsp?adminID="+adminID%>>Manage Courses</a>
		<a href=<%="manageConfigurations.jsp?adminID="+adminID%>>Manage Configurations</a>
		<% } %>
		<% if(clearance1 == 2 || clearance1 == 3) { // Clearance 2 = manage accounts %>
		<a href=<%="manageMembers.jsp?adminID="+adminID%>>Manage Members</a>
		<a href=<%="manageStudents.jsp?adminID="+adminID%>>Manage Students</a>
		<a href=<%="manageInstructors.jsp?adminID="+adminID%>>Manage Instructors</a>
		<a href=<%="manageAdministrators.jsp?adminID="+adminID%>>Manage Administrators</a>
		<% } %>
		<a href="index.jsp">Logout</a>
	</div>
<h1 style="text-align:center;">New Values</h1>
<form action="updateAdministrator" method="post">
<table class="content-table">
	<tr>
		<td><%="Name: " + request.getParameter("firstname") + " " + request.getParameter("lastname")%></td>
		<td><input type="hidden" id="oldAdminID" name="oldAdminID" value=<%=adminID%>></td>
		<td><input type="hidden" id="adminID" name="adminID" value=<%=adminID%>></td>
	</tr>
	<tr>
		<td>Administrator ID</td>
		<td><input type="text" id="newAdminID" name="newAdminID" value=<%=adminID%>><td>
	</tr>
	<tr>
		<td>Clearance</td>
		<td><input type="text" id="clearance" name="clearance" value=<%=clearance%>><td>
	</tr>
	<tr>
		<td></td>
		<td></td>
		<td><input type="submit" value="Submit"></td></tr>
</table>
</form>
</body>
</html>
