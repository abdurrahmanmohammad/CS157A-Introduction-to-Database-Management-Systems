<%@ page
	import="teaches.Teaches,registers.Registers,java.util.ArrayList,java.util.HashMap"
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Roster</title>
<link href="portal.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<%
		String instructorID = request.getParameter("instructorID");
	String department = request.getParameter("department");
	String number = request.getParameter("number");
	int configID = Integer.parseInt(request.getParameter("configID"));
	ArrayList<HashMap<String, String>> registered = Registers.getRegisteredStudents(department, number, configID);
	ArrayList<HashMap<String, String>> waitlisted = Registers.getWaitlistedStudents(department, number, configID);
	%>
	<div class="topnav">
		<a class="active"
			href=<%="InstructorPortal.jsp?instructorID=" + instructorID%>>Instructor
			Portal</a>
		<a href=<%="roster.jsp?instructorID=" + instructorID%>>Manage Courses</a> 
		<a href=<%="teachCourse.jsp?instructorID=" + instructorID%>>Teach Course</a> 
		<a href="index.jsp">Logout</a>
	</div>
	
	
	<h1 style="text-align: center;">Registered Students</h1>
	<table class="content-table">
		<tr>
			<td>Student ID</td>
			<td>First Name</td>
			<td>Last Name</td>
			<td></td>
		</tr>
		<%
			for (int i = 0; i < registered.size(); i++) {
			HashMap<String, String> registrant = registered.get(i);
		%>

		<tr>
			<td><%=registrant.get("studentID")%></td>
			<td><%=registrant.get("firstname")%></td>
			<td><%=registrant.get("lastname")%></td>
			<td>
				<form action="dropFromRoster" method="post">
					<input type="hidden" id="instructorID" name="instructorID" value=<%=instructorID%>> 
					<input type="hidden" id="studentID" name="studentID" value=<%=registrant.get("studentID")%>>
					<input type="hidden" id="department" name="department" value='<%=department%>'>
					<input type="hidden" id="number" name="number" value='<%=number%>'>
					<input type="hidden" id="configID" name="configID" value=<%=configID%>>
					<input type="submit" value="Drop"> 
				</form>
			</td>
		</tr>
		<%
			}
		%>
		<tr>
			<td>Student ID</td>
			<td>
				<form action="manualAdd" method="post">
					<input type="text" id="studentID" name="studentID">
					<input type="submit" value="Add"> 
					<input type="hidden" id="instructorID" name="instructorID" value=<%=instructorID%>> 
					<input type="hidden" id="department" name="department" value='<%=department%>'>
					<input type="hidden" id="number" name="number" value='<%=number%>'>
					<input type="hidden" id="configID" name="configID" value=<%=configID%>>
				</form>
			</td>
			<td></td>
			<td></td>
		</tr>
	</table>
	
		<h1 style="text-align: center;">Waitlisted Students</h1>
	<table class="content-table">
		<tr>
			<td>Student ID</td>
			<td>First Name</td>
			<td>Last Name</td>
			<td></td>
			<td></td>
		</tr>
		<%
			for (int i = 0; i < waitlisted.size(); i++) {
			HashMap<String, String> waitlist = waitlisted.get(i);
		%>

		<tr>
			<td><%=waitlist.get("studentID")%></td>
			<td><%=waitlist.get("firstname")%></td>
			<td><%=waitlist.get("lastname")%></td>
			<td>
				<form action="dropFromRoster" method="post">
					<input type="hidden" id="instructorID" name="instructorID" value=<%=instructorID%>> 
					<input type="hidden" id="studentID" name="studentID" value=<%=waitlist.get("studentID")%>>
					<input type="hidden" id="department" name="department" value='<%=department%>'>
					<input type="hidden" id="number" name="number" value='<%=number%>'>
					<input type="hidden" id="configID" name="configID" value=<%=configID%>>
					<input type="submit" value="Drop"> 
				</form>
			<td>
				<form action="manualAdd" method="post">
					<input type="hidden" id="instructorID" name="instructorID" value=<%=instructorID%>> 
					<input type="hidden" id="studentID" name="studentID" value=<%=waitlist.get("studentID")%>>
					<input type="hidden" id="department" name="department" value='<%=department%>'>
					<input type="hidden" id="number" name="number" value='<%=number%>'>
					<input type="hidden" id="configID" name="configID" value=<%=configID%>>
					<input type="submit" value="Add"> 
				</form>
			</td>
		</tr>

		<%
			}
		%>
	</table>

</body>
</html>