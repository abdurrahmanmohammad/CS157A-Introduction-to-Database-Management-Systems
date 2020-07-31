<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Administrator Portal</title>
</head>
<body>
	<h1>Administrator Portal</h1>
	<%String username = request.getParameter("username");%>
	<h2>
		Welcome Administrator
		<%out.println(username);%>
	</h2>

	<h1>Manage Users</h1>
	<form action="ManageUsers" method="post">
		<table>
			<tr>
				<td>Create User:</td>
				<td>User ID: <input type="text" name="createUserID" /></td>
				<td>User Username: <input type="text" name="createUserUsername" /></td>
				<td>User Password: <input type="text" name="createUserPassword" /></td>
			</tr>
		</table>
		<input type="hidden" name="option" value="1">
		<input type="submit" name="createUser" value="Create User" />
	</form>

	<form action="ManageUsers" method="post">
		<table>
			<tr>
				<td>Delete User:</td>
				<td>User ID: <input type="text" name="deleteUserID" /></td>
			</tr>
		</table>
		<input type="hidden" name="option" value="2">
		<input type="submit" name="deleteUser" value="Delete User" />
	</form>

	<h1>Manage Courses</h1>
	<form action="ManageCourses" method="post">
		<table>
			<tr>
				<td>Create Course:</td>
				<td>Course ID: <input type="text" name="createCourseID" /></td>
				<td>Course Department: <input type="text" name="createCourseDepartment" /></td>
				<td>Course Number: <input type="text" name="createCourseNumber" /></td>
				<td>Course Title: <input type="text" name="createCourseTitle" /></td>
				<td>Course Units: <input type="text" name="createCourseUnits" /></td>
				<td>Course Cost: <input type="text" name="createCourseCost" /></td>
			</tr>
		</table>
		<input type="hidden" name="option" value="3">
		<input type="submit" name="createCourse" value="Create Course" />
	</form>

	<form action="ManageCourses" method="post">
		<table style="with: 50%">
			<tr>
				<td>Delete User:</td>
				<td>User ID: <input type="text" name="deleteCourseID" /></td>

			</tr>
		</table>
		<input type="hidden" name="option" value="4">
		<input type="submit" name="deleteCourse" value="Delete Course" />
	</form>
	<a href="login.jsp"><b>Logout</b>
</body>
</html>