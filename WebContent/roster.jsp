<%@ page
	import="teaches.Teaches,registers.Registers,java.util.ArrayList,java.util.HashMap"
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
			<td></td>
			<td></td>
		</tr>
		<%
			ArrayList<HashMap<String, String>> courses = Teaches.viewTaughtCourses(instructorID);
		for (int i = 0; i < courses.size(); i++) {
			HashMap<String, String> course = courses.get(i);
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
			<td>
				<form action="unteachCourse" method="post">
					<input type="hidden" id="instructorID" name="instructorID" value=<%=instructorID%>> 
					<input type="hidden" id="department" name="department" value=<%=course.get("department")%>>
					<input type="hidden" id="number" name="number" value=<%=course.get("number")%>>
					<input type="hidden" id="configID" name="configID" value=<%=course.get("configID")%>>
					<input type="submit" value="DROP"> 
				</form>
			</td>
			<td>
				<form action="manageRoster.jsp" method="post">
					<input type="hidden" id="instructorID" name="instructorID" value=<%=instructorID%>> 
					<input type="hidden" id="department" name="department" value=<%=course.get("department")%>>
					<input type="hidden" id="number" name="number" value=<%=course.get("number")%>>
					<input type="hidden" id="configID" name="configID" value=<%=course.get("configID")%>>
					<input type="submit" value="Manage"> 
				</form>
			</td>
		</tr>
			<%
				}
			%>
	</table>
		
</body>
</html>