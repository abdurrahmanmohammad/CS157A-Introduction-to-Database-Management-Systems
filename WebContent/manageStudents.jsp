<%@ page
	import="students.Students,java.util.ArrayList,java.util.HashMap"
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Students</title>
</head>
<body>
	<h1 style="text-align: center;">Manage Students</h1>
	<table style="width: 100%">
		<tr>
			<td>ID</td>
			<td>First Name</td>
			<td>Last Name</td>
			<td>Balance</td>
			<td>Unit Cap</td>
		</tr>
		<%
			ArrayList<HashMap<String, String>> students = Students.getAll();
		for (int i = 0; i < students.size(); i++) {
			HashMap<String, String> student = students.get(i);
		%>
		<tr>
			<td><%=student.get("studentID")%></td>
			<td><%=student.get("firstname")%></td>
			<td><%=student.get("lastname")%></td>
			<td><%=student.get("balance")%></td>
			<td><%=student.get("unit_cap")%></td>
			<td>
				<form action="updateStudents.jsp" method="post">
					<input type="hidden" id="studentID" name="studentID" value=<%=student.get("studentID")%>> 
					<input type="hidden" id="balance" name="balance" value=<%=student.get("balance")%>> 
					<input type="hidden" id="unit_cap" name="unit_cap" value=<%=student.get("unit_cap")%>> 
					<input type="hidden" id="firstname" name="firstname" value=<%=student.get("firstname")%>> 
					<input type="hidden" id="lastname" name="lastname" value=<%=student.get("lastname")%>> 
					<input type="submit" value="Options">
				</form>
			</td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>