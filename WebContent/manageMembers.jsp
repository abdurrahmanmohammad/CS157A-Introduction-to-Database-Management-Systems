<%@ page import="members.Members,users.Users,java.util.ArrayList,java.util.HashMap" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Members</title>
</head>
<body>
<h1 style="text-align:center;">Manage Members</h1>
	<table style="width: 100%">
		<tr>
			<td>ID</td>
			<td>First Name</td>
			<td>Last Name</td>
			<td>Type</td>
			<td>Phone</td>
			<td>Email</td>
			<td>Address</td>
			<td>Username</td>
			<td>Password</td>
		</tr>
		<%
			ArrayList<HashMap<String, String>> members = Members.getAll();
		for (int i = 0; i < members.size(); i++) {
			HashMap<String, String> member = members.get(i);
			int typeInt = Users.getType(member.get("username"));
			String type = Users.mapType(typeInt);
		%>
		<tr>
			<td><%=member.get("ID")%></td>
			<td><%=member.get("firstname")%></td>
			<td><%=member.get("lastname")%></td>
			<td><%=type%></td>
			<td><%=member.get("phone")%></td>
			<td><%=member.get("email")%></td>
			<td><%=member.get("address")%></td>
			<td><%=member.get("username")%></td>
			<td><%=member.get("password")%></td>
			<td>
				<form action="updateMember.jsp" method="post">
					<input type="hidden" id="ID" name="ID" value=<%=member.get("ID")%>>
					<input type="hidden" id="type" name="type" value=<%=type%>>
					<input type="submit" value="Update">
				</form>
			</td>
			<td>
				<form action="deleteMember" method="post">
					<input type="hidden" id="ID" name="ID" value=<%=member.get("ID")%>>
					<input type="submit" value="Delete">
				</form>
			</td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>