<%@ page import="courses.Courses,java.util.HashMap" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Course</title>
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
	<% String department = request.getParameter("department");%>
	<% String number = request.getParameter("number");%>
	<% HashMap<String, String> course = Courses.search(department, number);%>
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
<h1 style="text-align:center;">New Values</h1>
<form action="updateCourse" method="post">
<table>
	<tr>
		<td><input type="hidden" id="oldDepartment" name="oldDepartment" value=<%=course.get("department")%>></td>
		<td><input type="hidden" id="oldNumber" name="oldNumber" value=<%=course.get("number")%>></td>
	</tr>
	<tr>
		<td>Department</td>
		<td><input type="text" id="department" name="department" value=<%=course.get("department")%>><td>
	</tr>
	<tr>
		<td>Number</td>
		<td><input type="text" id="number" name="number" value=<%=course.get("number")%>><td>
	</tr>
	<tr>
		<td>Title</td>
		<td><input type="text" id="title" name="title" value=<%=course.get("title")%>><td>
	</tr>
	<tr>
		<td>Units</td>
		<td><input type="text" id="units" name="units" value=<%=course.get("units")%>><td>
	</tr>
	<tr>
		<td>Cost</td>
		<td><input type="text" id="cost" name="cost" value=<%=course.get("cost")%>><td>
	</tr>
	<tr><td><input type="submit" value="Submit"></td></tr>
</table>
</form> 

</body>
</html>