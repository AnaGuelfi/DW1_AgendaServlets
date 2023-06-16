<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de Tarefa</title>
<style><%@include file="/WEB-INF/view/estilos.css"%></style>
</head>
<body>

<div>
    <nav>
        <ul class="menu">
            <li class="borda_right">
                <a href = "/AgendaServlet/UserTask">Tarefas Cadastradas</a>
            </li>
            <li class="borda_right">
                <a href="/AgendaServlet/LogoutServlet">Sair</a>
            </li>
        </ul>
    </nav>
</div>

	<fieldset>
		<legend>Cadastrar Tarefa</legend>
		<form action="<%=request.getContextPath()%>/TaskServlet" method="post">
			<p>
				Título: <input type="text" name="titulo" required="required" />
			</p>
			<p>
				Descrição: <input type="text" name="descricao" />
			</p>
			<p>
				Data de Início: <input type="date" name="data_criacao" />
			</p>
			<p>
				Data de Conclusão: <input type="date" name="data_conclusao" required="required" />
			</p>
			<p>
				Status: <input type="text" name="status" />
			</p>
			<p>
				<input class = "botao" type="submit" value="Enviar" />
			</p>
		</form>
	</fieldset>
</body>
</html>