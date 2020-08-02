<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Portal</title>
</head>
<body>
	<h1>Student Portal</h1>
	<%String username = request.getParameter("username");%>
	<h2>
		Welcome Student
		<%out.println(username);%>
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
