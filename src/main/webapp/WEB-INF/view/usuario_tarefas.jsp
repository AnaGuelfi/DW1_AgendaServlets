<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tarefas</title>
</head>
<body>
	<a href = "/AgendaServlet/TaskServlet">Cadastrar Nova Tarefa</a>
<form action="<%=request.getContextPath()%>/UserTask" method="post">
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Tarefa" %>
<table>
	<tr>
		<th>Título</th>
		<th>Descrição</th>
		<th>Data de Criação</th>
		<th>Data de Conclusão</th>
		<th>Status</th>
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
				${c.status}
			</td>
		</tr>
	</c:forEach>
</table>
</form>
</body>
</html>