<%@ page
	import="users.Users,instructors.Instructors,members.Members,java.util.HashMap"
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Instructor Portal</title>
<link href="portal.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<%
		String username = request.getParameter("username");
	String instructorID = request.getParameter("instructorID");
	if (username == null) username = Users.search(instructorID).get("username");
	if (instructorID == null) instructorID = Users.getID(username);
	HashMap<String, String> admin = Members.search(instructorID);
	String status = Instructors.getStatus(instructorID);
	%>
	<div class="topnav">
		<a class="active"
			href=<%="InstructorPortal.jsp?instructorID=" + instructorID%>>Instructor
			Portal</a>
		<% if (status.equalsIgnoreCase("Active")) { %>
		<a href=<%="roster.jsp?instructorID=" + instructorID%>>Manage Courses</a> 
		<a href=<%="teachCourse.jsp?instructorID=" + instructorID%>>Teach Course</a> 
		<%
			}
		%>
		<a href="index.jsp">Logout</a>
	</div>
	<h1 style="text-align: center;">Instructor Portal</h1>
	<table class="content-table">
		<tr>
			<td>
				<h2>Welcome Administrator <%out.println(admin.get("firstname") + " " + admin.get("lastname"));out.println("(" + instructorID + ")");%>
				</h2>
			</td>
		</tr>
	</table>
</body>
</html>