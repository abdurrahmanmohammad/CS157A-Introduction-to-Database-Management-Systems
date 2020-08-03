<%@ page import="administrators.Administrators,java.util.ArrayList,java.util.HashMap"
	language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Administrators</title>
</head>
<body>
<% String adminID = request.getParameter("adminID");%>
<% int clearance = Integer.parseInt(request.getParameter("clearance"));%>
<h1 style="text-align:center;">New Values</h1>
<form action="updateAdministrator" method="post">
<table>
	<tr>
		<td><%="Name: " + request.getParameter("firstname") + " " + request.getParameter("lastname")%></td>
		<td><input type="hidden" id="oldAdminID" name="oldAdminID" value=<%=adminID%>></td>
	</tr>
	<tr>
		<td>Administrator ID</td>
		<td><input type="text" id="newAdminID" name="newAdminID" value=<%=adminID%>><td>
	</tr>
	<tr>
		<td>Clearance</td>
		<td><input type="text" id="clearance" name="clearance" value=<%=clearance%>><td>
	</tr>
	<tr><td><input type="submit" value="Submit"></td></tr>
</table>
</form>
</body>
</html>