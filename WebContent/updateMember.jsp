<%@ page import="administrators.Administrators,members.Members,users.Users,java.util.HashMap" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Member</title>
<link href="portal.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<% String ID = request.getParameter("ID");
	String type = request.getParameter("type");
	HashMap<String, String> member = Members.search(ID);
	HashMap<String, String> user = Users.search(ID);
	String adminID = request.getParameter("adminID");
	int clearance = Administrators.getClearance(adminID);
	%>
	<div class="topnav">
		<a class="active" href=<%="administratorPortal.jsp?adminID="+adminID%>>Administrator Portal</a> 
		<% if(clearance == 1 || clearance == 3) { // Clearance 1 = manage courses and configs %>
		<a href=<%="manageCourses.jsp?adminID="+adminID%>>Manage Courses</a>
		<a href=<%="manageConfigurations.jsp?adminID="+adminID%>>Manage Configurations</a>
		<% } %>
		<% if(clearance == 2 || clearance == 3) { // Clearance 2 = manage accounts %>
		<a href=<%="manageMembers.jsp?adminID="+adminID%>>Manage Members</a>
		<a href=<%="manageStudents.jsp?adminID="+adminID%>>Manage Students</a>
		<a href=<%="manageInstructors.jsp?adminID="+adminID%>>Manage Instructors</a>
		<a href=<%="manageAdministrators.jsp?adminID="+adminID%>>Manage Administrators</a>
		<% } %>
		<a href="index.jsp">Logout</a>
	</div>
<h1 style="text-align:center;">New Values</h1>
<form action="updateMember" method="post">
<table class="content-table">
	<tr>
		<td><input type="hidden" id="oldID" name="oldID" value=<%=member.get("ID")%>></td>
		<td><input type="hidden" id="type" name="type" value=<%=member.get("type")%>>
			<input type="hidden" id="adminID" name="adminID" value=<%=adminID%>>
		</td>
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
	<tr>
		<td></td>
		<td></td>
		<td><input type="submit" value="Submit"></td>
	</tr>
</table>
</form> 
</body>
</html>