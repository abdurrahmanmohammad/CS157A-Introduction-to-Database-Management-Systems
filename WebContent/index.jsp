<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Course Management System</title>
</head>
<style>
body {
	background: url(pictures/bg1.jpg) no-repeat;
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
<body>
	<div class="topnav">
		<a class="active" href="index.jsp">Course Management System</a> <a
			href="login.jsp">Login</a> <a href="register.jsp">Register</a> <a href="#about">About</a>
	</div>
	<h1 style="color: white; text-align: center; font-size: 200%;">Welcome
		to the Course Management System</h1>

	<h1 style="color: white; text-align: center; font-size: 150%;">Please
		log in or register!</h1>
</body>
</html>
