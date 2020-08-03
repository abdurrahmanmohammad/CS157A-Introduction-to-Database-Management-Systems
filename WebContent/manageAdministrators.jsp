<%@ page
	import="administrators.Administrators,java.util.ArrayList,java.util.HashMap"
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Administrators</title>
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
.content-table {
  border-collapse: collapse;
  margin: 25px 0;
  font-size: 0.9em;
  min-width: 400px;
  border-radius: 5px 5px 0 0;
  overflow: hidden;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
  margin-left: auto;
  margin-right: auto;
  width: 90%;
}

.content-table thead tr {
  background-color: #003398;
  color: #ffffff;
  text-align: center;
  font-weight: bold;
}

.content-table th,.content-table td {
  padding: 12px 15px;
}

.content-table tbody tr {
  border-bottom: 1px solid #000000;
}

.content-table tbody tr:nth-of-type(even) {
  background-color: #e3e4e6;
}

.content-table tbody tr:nth-of-type(odd) {
  background-color: #f0f2f5;
}

.content-table tbody tr.active-row {
  font-weight: bold;
  color: #003398;
}
</style>
</head>
<body>
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
				<form action="updateAdministrator.jsp" method="post">
					<input type="hidden" id="adminID" name="adminID" value=<%=administrator.get("adminID")%>> 
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