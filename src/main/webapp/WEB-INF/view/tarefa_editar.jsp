<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Tarefa</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/TaskEditServlet" method="post">
	<table>
		<tr>
			<td>Título</td>
			<td><input type="text" name="titulo" value=<%= request.getAttribute("titulo") %> /></td>
		</tr>
		<tr>
			<td>Descrição</td>
			<td><input type="text" name="descricao" value=<%= request.getAttribute("descricao") %> /></td>
		</tr>
		<tr>
			<td>Data de Criação</td>
			<td><input type="date" name="data_criacao" value=<%= request.getAttribute("data_criacao") %> /></td>
		</tr>
		<tr>
			<td>Data de Conclusão</td>
			<td><input type="date" name="data_conclusao" value=<%= request.getAttribute("data_conclusao") %> /></td>
		</tr>
		<tr>
			<td>Status</td>
			<td><input type="text" name="status" value=<%= request.getAttribute("status") %>  /></td>
		</tr>
	</table>
	<input type="submit" value="Enviar" />
</form>
</body>
</html>