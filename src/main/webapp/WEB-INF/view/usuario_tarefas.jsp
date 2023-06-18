<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tarefas Cadastradas</title>
<style><%@include file="/WEB-INF/view/estilos.css"%></style>
</head>
<body>

<header>
    <nav>
        <ul class="menu">
            <li class="borda_right">
                <a href = "/AgendaServlet/TaskServlet">Cadastrar Nova Tarefa</a>
            </li>
            <li class="borda_right">
                <a href="/AgendaServlet/LogoutServlet">Sair</a>
            </li>
        </ul>
    </nav>
</header>

<div class = "tabela">

<fieldset>
	<legend>Pesquisar Tarefa</legend>
	<form action="<%=request.getContextPath()%>/UserTask" method="post">
		<p>
			<span>Título:</span> <input type="text" name="titulo" />
		</p>
		<p>
			<span class="span_left">Início:</span> <input type="date" name="data_criacao" />
		</p>
		<p>
			<span class="span_left">Conclusão:</span> <input type="date" name="data_conclusao" />
		</p>
		<p>
			<input name = "buscar" class = "botao" type="submit" value="Buscar Tarefa" />
		</p>
	</form>
</fieldset>

<h1>Tarefas</h1>

<br  /><br  />

<form action="<%=request.getContextPath()%>/UserTask" method="post">
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Tarefa" %>
<table>
	<tr>
		<th>Título</th>
		<th>Descrição</th>
		<th>Data de Início</th>
		<th>Data de Conclusão</th>
		<th>Status</th>
		<th>Alterar Tarefa</th>
		<th>Excluir Tarefa</th>
	</tr>
	<c:forEach items="${requestScope.lista_tarefas}" var="c">
		<tr>
			<td>
				${c.titulo}
			</td>
			<td>
				${c.descricao}
			</td>
			<td>
				${c.data_criacao}
			</td>
			<td>
				${c.data_conclusao}
			</td>
			<td>
				<c:choose>
				   <c:when test="${c.status=='nao_iniciada'}">Não Iniciada</c:when> 
				   <c:when test="${c.status=='em_andamento'}">Em Andamento</c:when> 
				   <c:when test="${c.status=='concluida'}">Concluída</c:when>
				</c:choose>
			</td>
			<!--
			<td>
				${c.status}
			</td>
			-->
			<td>
				<a class = "botao_tabela" href="/AgendaServlet/TaskEditServlet?id_tarefa=${c.id}">Editar</a>
			</td>
			<td>
				<input type="hidden" name="id_excluir" value="${c.id}" />
				<input class = "botao_tabela" type="submit" value="Excluir" />
			</td>
		</tr>
	</c:forEach>
</table>
</form>
</div>
</body>
</html>