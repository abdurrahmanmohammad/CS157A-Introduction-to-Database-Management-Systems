<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
<link href="portal.css" rel="stylesheet" type="text/css"/>
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
			<input type="text" class="contact-form-text" placeholder="Type" name="type">
			<input type="submit" class="contact-form-btn" value="submit">
		</form>
	</div>
</body>
</html>

