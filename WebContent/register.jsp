<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
<style>* {
	margin: 0;
	padding: 0;
	font-family: "montserrat", sans-serif;
}

.contact-section {
	background: url(pictures/registerBG.jpg) no-repeat top center;
	background-size: cover;
	height: 100vh;
	padding: 40px 0;
}

.contact-section h1 {
	text-align: center;
	color: #ddd;
}

.border {
	width: 100px;
	height: 10px;
	background: #34495e;
	margin: 40px auto;
}

.contact-form {
	max-width: 600px;
	margin: auto;
	padding: 0 10px;
	overflow: hidden;
}

.contact-form-text {
	display: block;
	width: 100%;
	box-sizing: border-box;
	margin: 16px 0;
	border: 0;
	background: #29292b;
	padding: 20px 40px;
	outline: none;
	color: #ddd;
	transition: 0.5s;
}

.contact-form-text:focus {
	box-shadow: 0 0 10px 4px #34495e;
}

textarea.contact-form-text {
	resize: none;
	height: 120px;
}

.contact-form-btn {
	float: right;
	border: 0;
	background: #34495e;
	color: #fff;
	padding: 12px 50px;
	border-radius: 20px;
	cursor: pointer;
	transition: 0.5s;
}

.contact-form-btn:hover {
	background: #2980b9;
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
	<div class="contact-section">
		<h1>Register</h1>
		<div class="border"></div>
		<form class="contact-form" action="registerAction" method="post">
			<input type="text" class="contact-form-text" placeholder="First Name" name="firstname">
			<input type="text" class="contact-form-text" placeholder="Last Name" name="lastname">
			<input type="text" class="contact-form-text" placeholder="Phone" name="phone">
			<input type="text" class="contact-form-text" placeholder="Email" name="email">
			<input type="text" class="contact-form-text" placeholder="Address" name="address">
			<input type="text" class="contact-form-text" placeholder="Type (student, instructor, administrator)" name="type">
			<input type="submit" class="contact-form-btn" value="submit">
		</form>
	</div>
</body>
</html>

