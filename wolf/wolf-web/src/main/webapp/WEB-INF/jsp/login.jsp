<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>wolf-login</title>
</head>
<body>
	<form action="/login" method="post">
		用户名<input type="text" name="username"/><br>
		密  码<input type="password" id="password" name="password"/>	<br>
		<input type="hidden"  name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<button type="submit" class="btn">Log in</button>
	</form>
</body>
</html>