<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de Tarefa</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/TaskServlet" method="post">
	<table>
		<tr>
			<td>Título</td>
			<td><input type="text" name="titulo" /></td>
		</tr>
		<tr>
			<td>Descrição</td>
			<td><input type="text" name="descricao" /></td>
		</tr>
		<tr>
			<td>Data de Criação</td>
			<td><input type="date" name="data_criacao" /></td>
		</tr>
		<tr>
			<td>Data de Conclusão</td>
			<td><input type="date" name="data_conclusao" /></td>
		</tr>
		<tr>
			<td>Status</td>
			<td><input type="text" name="status" /></td>
		</tr>
	</table>
	<input type="submit" value="Enviar" />
</form>
</body>
</html>