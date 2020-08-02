<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Confirmation</title>
<style>
body {
	background: url(pictures/college.jpg) no-repeat;
	background-size: cover;
	margin: 0;
	padding: 0;
	font-family: Arial, Helvetica, sans-serif;
}

h1 {
	padding: 40px 0;
	text-align: center;
	color: white
}

h2 {
	text-align: center;
	color: white
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
<div class="topnav">
		<a class="active" href="index.jsp">Course Management System</a> <a
			href="login.jsp">Login</a> <a href="register.jsp">Register</a> <a href="#about">About</a>
	</div>
<h1>Registration successfully submitted!</h1>
<h2>An administrator will be in touch with you shortly</h2>
<h2>You will receive your username and password in your email</h2>
<h2 href="index.jsp">Please return to the homepage</h2>
</body>
</html>