<%@ page import="configurations.Configurations,java.util.ArrayList,java.util.HashMap" 
	language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Configurations</title>
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
<h1 style="text-align:center;">Manage Configurations</h1>
	<table class="content-table">
		<tr>
			<td>ConfigID</td>
			<td>Term</td>
			<td>Year</td>
			<td>Days</td>
			<td>Time</td>
			<td>Room</td>
			<td>Seats</td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<%
			ArrayList<HashMap<String, String>> configurations = Configurations.getAll();
		for (int i = 0; i < configurations.size(); i++) {
			HashMap<String, String> configuration = configurations.get(i);
		%>
		<tr>
			<td><%=configuration.get("configID")%></td>
			<td><%=configuration.get("term")%></td>
			<td><%=configuration.get("year")%></td>
			<td><%=configuration.get("days")%></td>
			<td><%=configuration.get("time")%></td>
			<td><%=configuration.get("room")%></td>
			<td><%=configuration.get("seats")%></td>
			<td>
			<td>
			<form action="updateConfigurations.jsp" method="post">
				<input type="hidden" id="configID" name="configID" value=<%=configuration.get("configID")%>>
				<input type="submit" value="Update">
			</form>
			</td>
			<td>
			<form action="deleteConfiguration" method="post">
				<input type="hidden" id="configID" name="configID" value=<%=configuration.get("configID")%>>
				<input type="submit" value="Delete">
			</form>
			</td>
		</tr>
		<%
			}
		%>
	</table>
	<h1>Create Configuration</h1>
	<form action="insertConfiguration" method="post">
<table>
	<tr>
		<td>Term</td>
		<td><input type="text" id="term" name="term"><td>
	</tr>
	<tr>
		<td>Year</td>
		<td><input type="text" id="year" name="year"><td>
	</tr>
	<tr>
		<td>Days</td>
		<td><input type="text" id="days" name="days"><td>
	</tr>
	<tr>
		<td>Time</td>
		<td><input type="text" id="time" name="time"><td>
	</tr>
	<tr>
		<td>Room</td>
		<td><input type="text" id="room" name="room"><td>
	</tr>
	<tr>
		<td>Seats</td>
		<td><input type="text" id="seats" name="seats"><td>
	</tr>
	<tr><td><input type="submit" value="Submit"></td></tr>
</table>
</form> 
	
</body>
</html>