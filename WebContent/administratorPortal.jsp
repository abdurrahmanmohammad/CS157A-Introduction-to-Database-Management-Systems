<%@ page import="users.Users,administrators.Administrators,members.Members,java.util.HashMap" language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Administrator Portal</title>
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
	<% 	String username = request.getParameter("username");
		String adminID = request.getParameter("adminID");
	 	if(username == null) username = Users.search(adminID).get("username");
	 	if(adminID == null)  adminID = Users.getID(username);
	 	HashMap<String, String> admin = Members.search(adminID);
		int clearance = Administrators.getClearance(adminID);%>
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
	<h1 style="text-align:center;">Administrator Portal</h1>
	<h2>
		Welcome Administrator
		<%out.println(admin.get("firstname") + " " + admin.get("lastname"));out.println("(" + adminID + ")");%>
	</h2>
</body>
</html>