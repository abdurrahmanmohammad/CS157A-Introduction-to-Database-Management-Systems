<%@ page import="users.Users" language="java"
		 contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Instructor Portal</title>
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
	<% String instructorID = request.getParameter("instructorID");%>
	<%if(instructorID == null)  instructorID = Users.getID(username);%>
	<a class="active" href=<%="/CMS/instructorPortal.jsp?instructorID="+instructorID%>>Instructor Portal</a>
	<a href=<%="ins_add.jsp?instructorID="+instructorID%>>Add Courses</a>
	<a href=<%="ins_drop.jsp?instructorID="+instructorID%>>Drop Courses</a>
	<%--	<a href=<%="viewCourses.jsp?instructorID="+instructorID%>>View Courses</a>--%>
	<%--	<a href=<%="transcript.jsp?instructorID="+instructorID%>>Transcript</a>--%>
	<%--	<a href=<%="transactions.jsp?instructorID="+instructorID%>>Transactions</a>--%>
	<a href="/CMS/index.jsp">Logout</a>
</div>
<h1>Instructor Portal</h1>
<h2><%String instructorName = request.getParameter("username");%>
	<a>Welcome Instructor <%out.println(instructorName);%></a>
</h2>
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