<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login de Usuário</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/login" method="post">
<table>
	<tr>
		<td>Login</td>
		<td><input type="text" name="login" /></td>
	</tr>
	<tr>
		<td>Senha</td>
		<td><input type="password" name="password" /></td>
	</tr>
</table>
<input type="submit" value="Enviar" />
</form>
</body>
</html>