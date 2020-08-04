<%@ page import="users.Users,members.Members,java.util.HashMap" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Portal</title>
<link href="portal.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<div class="topnav">
	<% 	String username = request.getParameter("username");
		String studentID = request.getParameter("studentID");
	 	if(username == null) username = Users.search(studentID).get("username");
	 	if(studentID == null)  studentID = Users.getID(username);
	 	HashMap<String, String> student = Members.search(studentID);%>	
		<a class="active" href=<%="studentPortal.jsp?studentID="+studentID%>>Student Portal</a> 
		<a href=<%="add.jsp?studentID="+studentID%>>Add Courses</a>
		<a href=<%="drop.jsp?studentID="+studentID%>>Drop Courses</a>
		<a href=<%="viewCourses.jsp?studentID="+studentID%>>View Courses</a>
		<a href=<%="transcript.jsp?studentID="+studentID%>>Transcript</a>
		<a href=<%="transactions.jsp?studentID="+studentID%>>Transactions</a>
		<a href="index.jsp">Logout</a>
	</div>
	<h1 style="text-align:center;">Student Portal</h1>
	<table class="content-table">
		<tr>
		<td><h2>Welcome Student <%out.println(student.get("firstname") + " " + student.get("lastname"));out.println("(" + studentID + ")");%></h2></td>
		</tr>
	</table>
</body>
