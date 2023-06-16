<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style><%@include file="/WEB-INF/view/estilos.css"%></style>
<title>Editar Tarefa</title>
</head>
<body>
<div>
    <nav>
        <ul class="menu">
            <li class="borda_right">
                <a href = "/AgendaServlet/TaskServlet">Cadastrar Nova Tarefa</a>
            </li>
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
		<legend>Editar Tarefa</legend>
		<form action="<%=request.getContextPath()%>/TaskEditServlet" method="post">
			<p>
				Título: <input type="text" name="titulo" value=<%= request.getAttribute("titulo") %> />
			</p>
			<p>
				Descrição: <input type="text" name="descricao" value=<%= request.getAttribute("descricao") %> />
			</p>
			<p>
				Data de Início: <input type="date" name="data_criacao" value=<%= request.getAttribute("data_criacao") %> />
			</p>
			<p>
				Data de Conclusão: <input type="date" name="data_conclusao" value=<%= request.getAttribute("data_conclusao") %> />
			</p>
			<p>
				Status: <input type="text" name="status" value=<%= request.getAttribute("status") %>  />
			</p>
			<p>
				<input class = "botao" type="submit" value="Enviar" />
			</p>
		</form>
</fieldset>
</body>
</html>