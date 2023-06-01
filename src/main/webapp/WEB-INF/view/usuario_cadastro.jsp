<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de Usuário</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/UserServlet" method="post">
	<table>
		<tr>
			<td>Login</td>
			<td><input type="text" name="login" /></td>
		</tr>
		<tr>
			<td>Senha</td>
			<td><input type="password" name="password" /></td>
		</tr>
		<tr>
			<td>Nome</td>
			<td><input type="text" name="nome" /></td>
		</tr>
		<tr>
			<td>E-mail</td>
			<td><input type="email" name="email" /></td>
		</tr>
	</table>
	<input type="submit" value="Enviar" />
</form>
</body>
</html>