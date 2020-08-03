<%@ page
	import="administrators.Administrators,java.util.ArrayList,java.util.HashMap"
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Administrators</title>
</head>
<body>
	<h1 style="text-align: center;">Manage Administrators</h1>
	<table style="width: 100%">
		<tr>
			<td>ID</td>
			<td>First Name</td>
			<td>Last Name</td>
			<td>Clearance</td>
		</tr>
		<%
			ArrayList<HashMap<String, String>> administrators = Administrators.getAll();
		for (int i = 0; i < administrators.size(); i++) {
			HashMap<String, String> administrator = administrators.get(i);
		%>
		<tr>
			<td><%=administrator.get("adminID")%></td>
			<td><%=administrator.get("firstname")%></td>
			<td><%=administrator.get("lastname")%></td>
			<td><%=administrator.get("clearance")%></td>
			<td>
				<form action="updateAdministrator.jsp" method="post">
					<input type="hidden" id="adminID" name="adminID" value=<%=administrator.get("adminID")%>> 
					<input type="hidden" id="clearance" name="clearance" value=<%=administrator.get("clearance")%>> 
					<input type="hidden" id="firstname" name="firstname" value=<%=administrator.get("firstname")%>> 
					<input type="hidden" id="lastname" name="lastname" value=<%=administrator.get("lastname")%>> 
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