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

<header>
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
</header>

	<fieldset>
		<legend>Cadastrar Tarefa</legend>
		<form action="<%=request.getContextPath()%>/TaskServlet" method="post">
			<p>
				<span>Título:</span> <input type="text" name="titulo" required="required" />
			</p>
			<p>
				<span>Descrição:</span> <input type="text" name="descricao" />
			</p>
			<p>
				<span class="span_left">Início:</span> <input type="date" name="data_criacao" />
			</p>
			<p>
				<span class="span_left">Conclusão:</span> <input type="date" name="data_conclusao" required="required" />
			</p>
			<p>
				<span class="span_left">Status:</span>
				<select name = "status">
					<option value="nao_iniciada">Não Iniciada</option>
					<option value="em_andamento">Em Andamento</option>
					<option value="concluida">Concluída</option>
				</select>
			</p>
			<!--  
			<p>
				Status: <input type="text" name="status" />
			</p>
			-->
			
			<p>
				<input class = "botao" type="submit" value="Enviar" />
			</p>
		</form>
	</fieldset>
</body>
</html>