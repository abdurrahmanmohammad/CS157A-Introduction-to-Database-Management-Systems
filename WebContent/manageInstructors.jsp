<%@ page
	import="instructors.Instructors,java.util.ArrayList,java.util.HashMap"
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Students</title>
<link href="portal.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<% String adminID = request.getParameter("adminID");%>	
	<div class="topnav">
		<a class="active" href=<%="administratorPortal.jsp?adminID="+adminID%>>Administrator Portal</a> 
		<a href=<%="manageCourses.jsp?adminID="+adminID%>>Manage Courses</a>
		<a href=<%="manageConfigurations.jsp?adminID="+adminID%>>Manage Configurations</a>
		<a href=<%="manageMembers.jsp?adminID="+adminID%>>Manage Members</a>
		<a href=<%="manageStudents.jsp?adminID="+adminID%>>Manage Students</a>
		<a href=<%="manageInstructors.jsp?adminID="+adminID%>>Manage Instructors</a>
		<a href=<%="manageAdministrators.jsp?adminID="+adminID%>>Manage Administrators</a>
		<a href="index.jsp">Logout</a>
	</div>
	<h1 style="text-align: center;">Manage Students</h1>
	<table class="content-table">
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