<%@ page import="users.Users,administrators.Administrators,members.Members,java.util.HashMap" language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Administrator Portal</title>
<link href="portal.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<% 	String username = request.getParameter("username");
		String adminID = request.getParameter("adminID");
	 	if(username == null) username = Users.search(adminID).get("username");
	 	if(adminID == null)  adminID = Users.getID(username);
	 	HashMap<String, String> admin = Members.search(adminID);
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
	<h1 style="text-align:center;">Administrator Portal</h1>
	<table class="content-table">
		<tr>
		<td><h2>Welcome Administrator <%out.println(admin.get("firstname") + " " + admin.get("lastname"));out.println("(" + adminID + ")");%></h2></td>
		</tr>
	</table>
		
</body>
</html>
