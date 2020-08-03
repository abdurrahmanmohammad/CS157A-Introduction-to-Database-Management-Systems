<%@ page
	import="instructors.Instructors,java.util.ArrayList,java.util.HashMap"
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
			ArrayList<HashMap<String, String>> instructors = Instructors.getAll();
		for (int i = 0; i < instructors.size(); i++) {
			HashMap<String, String> instructor = instructors.get(i);
		%>
		<tr>
			<td><%=instructor.get("instructorID")%></td>
			<td><%=instructor.get("firstname")%></td>
			<td><%=instructor.get("lastname")%></td>
			<td><%=instructor.get("status")%></td>
			<td>
				<form action="updateInstructors.jsp" method="post">
					<input type="hidden" id="instructorID" name="instructorID" value=<%=instructor.get("instructorID")%>> 
					<input type="hidden" id="firstname" name="firstname" value=<%=instructor.get("firstname")%>> 
					<input type="hidden" id="lastname" name="lastname" value=<%=instructor.get("lastname")%>> 
					<input type="hidden" id="status" name="status" value=<%=instructor.get("status")%>>  
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