<%@ page
	import="teaches.Teaches,registers.Registers,java.util.ArrayList,java.util.HashMap"
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Courses</title>
</head>
<body>
	<h1>Registered Courses</h1>
	<table style="width: 100%">
		<tr>
			<td>Term</td>
			<td>Year</td>
			<td>Department</td>
			<td>Number</td>
			<td>ConfigID</td>
		</tr>
	<%
		String studentID = request.getParameter("studentID"); // get ID from previous page
	ArrayList<HashMap<String, String>> regCourses = Registers.viewRegisteredCourses(studentID);
	for (int i = 0; i < regCourses.size(); i++) {
		HashMap<String, String> regCourse = regCourses.get(i);
	%>
		<tr>
			<td><%=regCourse.get("term")%></td>
			<td><%=regCourse.get("year")%></td>
			<td><%=regCourse.get("department")%></td>
			<td><%=regCourse.get("number")%></td>
			<td><%=regCourse.get("configID")%></td>
		</tr>

	<%
		}
	%>
	</table>
	<h1>Add Courses</h1>
	<table style="width: 100%">
		<tr>
			<td>Term</td>
			<td>Year</td>
			<td>Department</td>
			<td>Number</td>
			<td>Firstname</td>
			<td>Lastname</td>
			<td>Days</td>
			<td>Time</td>
			<td>Room</td>
		</tr>
	<%
		ArrayList<HashMap<String, String>> courses = Teaches.getCourses();
	for (int i = 0; i < courses.size(); i++) {
		HashMap<String, String> course = courses.get(i);
	%>
		 
			<tr>
				<td><%=course.get("term")%></td>
				<td><%=course.get("year")%></td>
				<td><%=course.get("department")%></td>
				<td><%=course.get("number")%></td>
				<td><%=course.get("firstname")%></td>
				<td><%=course.get("lastname")%></td>
				<td><%=course.get("days")%></td>
				<td><%=course.get("time")%></td>
				<td>
					<form action="/CMS/addCourse" method="post">
					<input type="submit" value="Add">
					<input type="hidden" id="studentID" name="studentID" value=<%=studentID%>>
					<input type="hidden" id="configID" name="configID" value=<%=course.get("configID")%>>
					<input type="hidden" id="department" name="department" value=<%=course.get("department")%>>
					<input type="hidden" id="number" name="number" value=<%=course.get("number")%>>
					</form>
				</td>
			</tr>
	<%
		}
	%>
	</table>


</body>
</html>