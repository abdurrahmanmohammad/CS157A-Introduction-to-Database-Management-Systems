<%@ page
	import="courses.Courses,configurations.Configurations,teaches.Teaches,registers.Registers,java.util.ArrayList,java.util.HashMap"
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Course</title>
<link href="portal.css" rel="stylesheet" type="text/css" />
</head>
<body>

	<%
		String instructorID = request.getParameter("instructorID");
	%>
		<div class="topnav">
		<a class="active"
			href=<%="InstructorPortal.jsp?instructorID=" + instructorID%>>Instructor
			Portal</a>
		<a href=<%="roster.jsp?instructorID=" + instructorID%>>Manage Courses</a> 
		<a href=<%="teachCourse.jsp?instructorID=" + instructorID%>>Teach Course</a> 
		<a href="index.jsp">Logout</a>
	</div>

	<h1 style="text-align: center;">Teach Course</h1>
	<form action="teachCourse" method="post">
		<table class="content-table">
			<tr>
				<td>Department</td>
				<td>Number</td>
				<td>Config ID</td>
				<td></td>
				<td></td>
			</tr>

			<tr>
				<td><input type="text" id="department" name="department"></td>
				<td><input type="text" id="number" name="number"></td>
				<td><input type="text" id="configID" name="configID"></td>
				<td><input type="hidden" id="instructorID" name="instructorID"
					value=<%=instructorID%>></td>
				<td><input type="submit" value="Teach"></td>
			</tr>
		</table>
	</form>

	<h1 style="text-align: center;">Taught Courses</h1>
	<table class="content-table">
		<tr>
			<td>Term</td>
			<td>Year</td>
			<td>Department</td>
			<td>Number</td>
			<td>Days</td>
			<td>Time</td>
			<td>Room</td>
			<td>Seats</td>
			<td>ConfigID</td>
		</tr>
		<%
			ArrayList<HashMap<String, String>> taughtCourses = Teaches.viewTaughtCourses(instructorID);
		for (int i = 0; i < taughtCourses.size(); i++) {
			HashMap<String, String> course = taughtCourses.get(i);
		%>
		<tr>
			<td><%=course.get("term")%></td>
			<td><%=course.get("year")%></td>
			<td><%=course.get("department")%></td>
			<td><%=course.get("number")%></td>
			<td><%=course.get("days")%></td>
			<td><%=course.get("time")%></td>
			<td><%=course.get("room")%></td>
			<td><%=course.get("seats")%></td>
			<td><%=course.get("configID")%></td>
		</tr>
		<%
			}
		%>
	</table>

	<h1 style="text-align: center;">Offered Courses</h1>
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
		</tr>
		<%
			}
		%>
	</table>

	<h1 style="text-align: center;">Configurations</h1>
	<table class="content-table">
		<tr>
			<td>ConfigID</td>
			<td>Term</td>
			<td>Year</td>
			<td>Days</td>
			<td>Time</td>
			<td>Room</td>
			<td>Seats</td>
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
		</tr>
		<%
			}
		%>
	</table>

</body>
</html>
