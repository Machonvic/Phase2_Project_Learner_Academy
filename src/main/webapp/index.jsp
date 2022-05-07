<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Admin Login</h1>
		<form action="login" method="post" id="loginForm">
			<label for="username">Username:</label>
			<input name="username" size="30" />
			<br><br>
			<label for="password">Password:</label>
			<input type="password" name="password" size="30" minlength="8" required/>
			<br>${message}
			<br><br>			
			<button type="submit">Login</button>
		</form>
</body>
</html>