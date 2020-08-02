<%@ page import="teaches.Teaches,registers.Registers,java.util.ArrayList,java.util.HashMap" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Offered Courses</h1>
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
				<td><%=course.get("room")%></td>
			</tr>
	<%
		}
	%>
	</table>

</body>
</html>