<%@ page import="registers.studentRegisters,java.util.ArrayList,java.util.HashMap" language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Drop Courses</title>
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
<% String studentID = request.getParameter("studentID"); // get ID from previous page %>
<div class="topnav">
	<a class="active" href=<%="/CMS/studentPortal.jsp?studentID="+studentID%>>Student Portal</a>
	<a href=<%="add.jsp?studentID="+studentID%>>Add Courses</a>
	<a href=<%="drop.jsp?studentID="+studentID%>>Drop Courses</a>
	<a href=<%="viewCourses.jsp?studentID="+studentID%>>View Courses</a>
	<a href=<%="transcript.jsp?studentID="+studentID%>>Transcript</a>
	<a href=<%="transactions.jsp?studentID="+studentID%>>Transactions</a>
	<a href="/CMS/index.jsp">Logout</a>
</div>
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
		ArrayList<HashMap<String, String>> regCourses = studentRegisters.viewRegisteredCourses(studentID);
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