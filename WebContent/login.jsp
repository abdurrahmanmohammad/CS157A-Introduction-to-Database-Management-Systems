<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Login</title>
<link href="style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<div class="topnav">
		<a class="active" href="index.jsp">Course Management System</a> <a
			href="login.jsp">Login</a> <a href="register.jsp">Register</a> <a
			href="#about">About</a>
	</div>
	<div class="login-box">
		<h1>Login</h1>
		<form action="LoginAction" method="post">
			<div class="textbox">
				<i class="fas fa-user"></i> <input type="text"
					placeholder="Username" name="username">
			</div>
			<div class="textbox">
				<i class="fas fa-lock"></i> <input type="password"
					placeholder="password" name="password">
			</div>
			<input type="submit" class="btn" value="Login">
		</form>
	</div>
</body>
</html>
