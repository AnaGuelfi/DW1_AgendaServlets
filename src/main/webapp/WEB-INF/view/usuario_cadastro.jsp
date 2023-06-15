<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de Usuário</title>
<style><%@include file="/WEB-INF/view/estilos.css"%></style>
</head>
<body>
<nav>
    <ul class="menu">
        <li class="borda_right">
            <a href = "/AgendaServlet/LoginServlet">Login</a>
        </li>
    </ul>
</nav>
<fieldset>
	<legend>Cadastrar-se</legend>
	<form action="<%=request.getContextPath()%>/UserServlet" method="post">
	<p>
		Nome: <input type="text" name="nome" />
	</p>
	<p>
		E-mail: <input type="email" name="email" />
	</p>
	<p>
		Username: <input type="text" name="login" />
	</p>
	<p>
		Senha: <input type="password" name="password" />
	</p>
	<p>
		<input class = "botao" type="submit" value="Enviar" />
	</p>
</form>
</fieldset>
</body>
</html>