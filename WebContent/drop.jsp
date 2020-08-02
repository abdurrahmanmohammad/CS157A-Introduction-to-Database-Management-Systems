<%@ page import="registers.Registers,java.util.ArrayList,java.util.HashMap" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Drop Courses</title>
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
			<td>
					<form action="/CMS/dropCourse" method="post">
					<input type="submit" value="Drop">
					<input type="hidden" id="configID" name="configID" value=<%=regCourse.get("configID")%>>
					<input type="hidden" id="studentID" name="studentID" value=<%=studentID%>>
					<input type="hidden" id="department" name="department" value=<%=regCourse.get("department")%>>
					<input type="hidden" id="number" name="number" value=<%=regCourse.get("number")%>>
					</form>
				</td>
		</tr>

	<%
		}
	%>
	
	</table>
</body>
</html>