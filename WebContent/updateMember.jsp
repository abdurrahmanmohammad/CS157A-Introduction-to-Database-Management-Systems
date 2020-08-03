<%@ page import="members.Members,users.Users,java.util.HashMap" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Member</title>
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
<% String ID = request.getParameter("ID");%>
<% String type = request.getParameter("type");%>
<% HashMap<String, String> member = Members.search(ID);%>
<% HashMap<String, String> user = Users.search(ID);%>
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
<h1 style="text-align:center;">New Values</h1>
<form action="updateMember" method="post">
<table>
	<tr>
		<td><input type="hidden" id="oldID" name="oldID" value=<%=member.get("ID")%>></td>
		<td><input type="hidden" id="type" name="type" value=<%=member.get("type")%>></td>
	</tr>
	<tr>
		<td>ID</td>
		<td><input type="text" id="newID" name="newID" value=<%=member.get("ID")%>><td>
	</tr>
	<tr>
		<td>First Name</td>
		<td><input type="text" id="firstname" name="firstname" value=<%=member.get("firstname")%>><td>
	</tr>
	<tr>
		<td>Last Name</td>
		<td><input type="text" id="lastname" name="lastname" value=<%=member.get("lastname")%>><td>
	</tr>
	<tr>
		<td>Phone</td>
		<td><input type="text" id="phone" name="phone" value='<%=member.get("phone")%>'><td>
	</tr>
	<tr>
		<td>Email</td>
		<td><input type="text" id="email" name="email" value=<%=member.get("email")%>><td>
	</tr>
	<tr>
		<td>Address</td>
		<td><input type="text" id="address" name="address" value='<%=member.get("address")%>'><td>
	</tr>
	<tr>
		<td>Username</td>
		<td><input type="text" id="username" name="username" value=<%=user.get("username")%>><td>
	</tr>
	<tr>
		<td>Password</td>
		<td><input type="text" id="password" name="password" value=<%=user.get("password")%>><td>
	</tr>
	<tr><td><input type="submit" value="Submit"></td></tr>
</table>
</form> 
</body>
</html>