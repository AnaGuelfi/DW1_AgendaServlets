<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login de Usuário</title>
<style><%@include file="/WEB-INF/view/estilos.css"%></style>
</head>
<body>
<header>
<nav>
    <ul class="menu">
        <li class="borda_right">
            <a href = "/AgendaServlet/UserServlet">Cadastrar-se</a>
        </li>
    </ul>
</nav>
</header>
<fieldset>
	<legend>Autenticar-se</legend>
	<form action="<%=request.getContextPath()%>/login" method="post">
	<p>
		<span>Username:</span> <input type="text" name="login" />
	</p>
	<p>
		<span>Senha:</span> <input type="password" name="password" />
	</p>
	<p>
		<input class = "botao" type="submit" value="Enviar" />
	</p>
	</form>
</fieldset>

</body>
</html>