<%@ page import="registers.Registers,java.util.ArrayList,java.util.HashMap"
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transcript</title>
</head>
<body>
	<h1>Transcript</h1>
	<table style="width: 100%">
		<tr>
			<td>Term</td>
			<td>Year</td>
			<td>Department</td>
			<td>Number</td>
		</tr>
	<%
		String studentID = request.getParameter("studentID"); // get ID from previous page
	ArrayList<HashMap<String, String>> courses = Registers.viewRegisteredCourses(studentID);
	for (int i = 0; i < courses.size(); i++) {
		HashMap<String, String> course = courses.get(i);
	%>
			<tr>
				<td><%=course.get("term")%></td>
				<td><%=course.get("year")%></td>
				<td><%=course.get("department")%></td>
				<td><%=course.get("number")%></td>
			</tr>
	<%
		}
	%>
	</table>

</body>
</html>