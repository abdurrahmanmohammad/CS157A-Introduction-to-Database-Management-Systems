<%@ page import="courses.Courses,java.util.HashMap" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Course</title>
</head>
<body>
<% String department = request.getParameter("department");%>
<% String number = request.getParameter("number");%>
<% HashMap<String, String> course = Courses.search(department, number);%>

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