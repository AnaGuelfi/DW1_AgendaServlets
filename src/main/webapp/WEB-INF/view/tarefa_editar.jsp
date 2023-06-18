<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style><%@include file="/WEB-INF/view/estilos.css"%></style>
<title>Editar Tarefa</title>
</head>
<body>
<header>
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
</header>
<fieldset>
		<legend>Editar Tarefa</legend>
		<form action="<%=request.getContextPath()%>/TaskEditServlet" method="post">
			<p>
				<span>Título:</span> <input type="text" name="titulo" value=<%= request.getAttribute("titulo") %> />
			</p>
			<p>
				<span>Descrição:</span> <input type="text" name="descricao" value=<%= request.getAttribute("descricao") %> />
			</p>
			<p>
				<span class="span_left">Início:</span> <input type="date" name="data_criacao" value=<%= request.getAttribute("data_criacao") %> />
			</p>
			<p>
				<span class="span_left">Conclusão:</span> <input type="date" name="data_conclusao" value=<%= request.getAttribute("data_conclusao") %> />
			</p>
			<% String status = (String) request.getAttribute("status"); %>
			<p>
				<span class="span_left">Status:</span>
				<select name = "status">
					<% if(status.equals("nao_iniciada")){ %>
						<option value="nao_iniciada" selected>Não Iniciada</option>
					<% } else { %>
						<option value="nao_iniciada">Não Iniciada</option>
					<% } %>
					
					<% if(status.equals("em_andamento")){ %>
						<option value="em_andamento" selected>Em Andamento</option>
					<% } else { %>
						<option value="em_andamento">Em Andamento</option>
					<% } %>
					
					<% if(status.equals("concluida")){ %>
						<option value="concluida" selected>Concluída</option>
					<% } else { %>
						<option value="concluida">Concluída</option>
					<% } %>
				</select>
			</p>
			<!--<p>
				Status:
				<select name = "status">
					<option value="nao_iniciada">Não Iniciada</option>
					<option value="em_andamento">Em Andamento</option>
					<option value="finalizada">Finalizada</option>
				</select>
			</p>-->
			<!-- 
			<p>
				Status: <input type="text" name="status" value=<%= request.getAttribute("status") %>  />
			</p>
			 -->
			
			<p>
				<input class = "botao" type="submit" value="Enviar" />
			</p>
		</form>
</fieldset>
</body>
</html>