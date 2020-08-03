<%@ page import="courses.Courses,java.util.ArrayList,java.util.HashMap"
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Courses</title>
<link href="portal.css" rel="stylesheet" type="text/css"/>
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
<h1 style="text-align:center;">Manage Courses</h1>
	<table class="content-table">
		<tr>
			<td>Department</td>
			<td>Number</td>
			<td>Title</td>
			<td>Units</td>
			<td>Cost</td>
		</tr>
		<%
			ArrayList<HashMap<String, String>> courses = Courses.getAll();
		for (int i = 0; i < courses.size(); i++) {
			HashMap<String, String> course = courses.get(i);
		%>
		<tr>
			<td><%=course.get("department")%></td>
			<td><%=course.get("number")%></td>
			<td><%=course.get("title")%></td>
			<td><%=course.get("units")%></td>
			<td><%=course.get("cost")%></td>			
			<td>
				<form action="updateCourse.jsp" method="post">
					<input type="hidden" id="department" name="department" value=<%=course.get("department")%>>
					<input type="hidden" id="number" name="number" value=<%=course.get("number")%>>
					<input type="submit" value="Update">
				</form>
			</td>
			<td>
				<form action="deleteCourse" method="post">
					<input type="hidden" id="department" name="department" value=<%=course.get("department")%>>
					<input type="hidden" id="number" name="number" value=<%=course.get("number")%>>
					<input type="submit" value="Delete">
				</form>
			</td>
		</tr>
		<%
			}
		%>
	</table>
	<h1>Create Course</h1>
	<form action="insertCourse" method="post">
<table class="content-table">
	<tr>
		<td>Department</td>
		<td><input type="text" id="department" name="department"><td>
	</tr>
	<tr>
		<td>Number</td>
		<td><input type="text" id="number" name="number"><td>
	</tr>
	<tr>
		<td>Title</td>
		<td><input type="text" id="title" name="title"><td>
	</tr>
	<tr>
		<td>Units</td>
		<td><input type="text" id="units" name="units"><td>
	</tr>
	<tr>
		<td>Cost</td>
		<td><input type="text" id="cost" name="cost"><td>
	</tr>
	<tr><td><input type="submit" value="Submit"></td></tr>
</table>
</form> 
</body>
</html>