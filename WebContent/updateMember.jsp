<%@ page import="members.Members,users.Users,java.util.HashMap" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Member</title>
</head>
<body>
<% String ID = request.getParameter("ID");%>
<% String type = request.getParameter("type");%>
<% HashMap<String, String> member = Members.search(ID);%>
<% HashMap<String, String> user = Users.search(ID);%>

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