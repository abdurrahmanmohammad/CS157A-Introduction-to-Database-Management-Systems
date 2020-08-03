<%@ page import="users.Users" language="java"
		 contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Student Portal</title>
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
<div class="topnav">
	<%String username = request.getParameter("username");%>
	<% String studentID = request.getParameter("studentID");%>
	<%if(studentID == null)  studentID = Users.getID(username);%>
	<a class="active" href=<%="/CMS/studentPortal.jsp?studentID="+studentID%>>Student Portal</a>
	<a href=<%="add.jsp?studentID="+studentID%>>Add Courses</a>
	<a href=<%="drop.jsp?studentID="+studentID%>>Drop Courses</a>
	<a href=<%="viewCourses.jsp?studentID="+studentID%>>View Courses</a>
	<a href=<%="transcript.jsp?studentID="+studentID%>>Transcript</a>
	<a href=<%="transactions.jsp?studentID="+studentID%>>Transactions</a>
	<a href="/CMS/index.jsp">Logout</a>
</div>
<h1>Student Portal</h1>
<h2>Welcome Student
	<%out.println(username);out.println(studentID);%></h2>
<h1>Manage Courses</h1>
<form action="ManageStudentCourses" method="post">
	<input type="submit" class="btn" value="View Enrolled Courses">
	<input type="submit" class="btn" value="Search Courses">
	<table>
		<tr></tr>
		<tr>
			<td></td>
			<td></td>
			<td><a href="login.jsp"><b>Logout</b></a></td>
		</tr>
	</table>
</form>
</body>