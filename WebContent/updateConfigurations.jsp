<%@ page import="configurations.Configurations,java.util.HashMap" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Configurations</title>
</head>
<body>
<% int configID = Integer.parseInt(request.getParameter("configID"));%>
<% HashMap<String, String> course = Configurations.search(configID);%>

<h1 style="text-align:center;">New Values</h1>
<form action="updateConfiguration" method="post">
<table>
	<tr>
		<td><input type="hidden" id="oldConfigID" name="oldConfigID" value=<%=course.get("configID")%>></td>
	</tr>
	<tr>
		<td>ConfigID</td>
		<td><input type="text" id="newConfigID" name="newConfigID" value=<%=course.get("configID")%>><td>
	</tr>
	<tr>
		<td>Term</td>
		<td><input type="text" id="term" name="term" value=<%=course.get("term")%>><td>
	</tr>
	<tr>
		<td>Year</td>
		<td><input type="text" id="year" name="year" value=<%=course.get("year")%>><td>
	</tr>
	<tr>
		<td>Days</td>
		<td><input type="text" id="days" name="days" value=<%=course.get("days")%>><td>
	</tr>
	<tr>
		<td>Time</td>
		<td><input type="text" id="time" name="time" value=<%=course.get("time")%>><td>
	</tr>
	<tr>
		<td>Room</td>
		<td><input type="text" id="room" name="room" value=<%=course.get("room")%>><td>
	</tr>
	<tr>
		<td>Seats</td>
		<td><input type="text" id="seats" name="seats" value=<%=course.get("seats")%>><td>
	</tr>
	<tr><td><input type="submit" value="Submit"></td></tr>
</table>
</form> 

</body>
</html>