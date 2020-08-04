<%@ page
	import="administrators.Administrators,java.util.ArrayList,java.util.HashMap"
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Administrators</title>
<link href="portal.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<% 
	String adminID = request.getParameter("adminID");
	int clearance = Administrators.getClearance(adminID);
	%>	
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
	<h1 style="text-align: center;">Manage Administrators</h1>
	<table class="content-table">
		<tr>
			<td>ID</td>
			<td>First Name</td>
			<td>Last Name</td>
			<td>Clearance</td>
			<td></td>
		</tr>
		<%
			ArrayList<HashMap<String, String>> administrators = Administrators.getAll();
		for (int i = 0; i < administrators.size(); i++) {
			HashMap<String, String> administrator = administrators.get(i);
		%>
		<tr>
			<td><%=administrator.get("adminID")%></td>
			<td><%=administrator.get("firstname")%></td>
			<td><%=administrator.get("lastname")%></td>
			<td><%=administrator.get("clearance")%></td>
			<td>
				<form action=<%="updateAdministrator.jsp?adminID="+adminID%> method="post">
					<input type="hidden" id="adminID" name="adminID" value=<%=adminID%>> 
					<input type="hidden" id="clearance" name="clearance" value=<%=administrator.get("clearance")%>> 
					<input type="hidden" id="firstname" name="firstname" value=<%=administrator.get("firstname")%>> 
					<input type="hidden" id="lastname" name="lastname" value=<%=administrator.get("lastname")%>> 
					<input type="submit" value="Options">
				</form>
			</td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>
