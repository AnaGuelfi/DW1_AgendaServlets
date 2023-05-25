package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Tarefa;

import java.io.IOException;
import java.text.ParseException;

import dao.TarefaDAO;

/**
 * Servlet implementation class TarefaServlet
 */
@WebServlet("/TaskServlet")
public class TarefaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    TarefaDAO tdao = new TarefaDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TarefaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/tarefa_cadastro.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String titulo = request.getParameter("titulo");
		String descricao = request.getParameter("descricao");
		String data_criacao = request.getParameter("data_criacao");
		String data_conclusao = request.getParameter("data_conclusao");
		String status = request.getParameter("status");
		
		Tarefa t = new Tarefa();
		t.setTitulo(titulo);
		t.setDescricao(descricao);
		t.setStatus(status);
		
		java.text.DateFormat fmt = new java.text.SimpleDateFormat("dd/MM/yyyy");
		java.sql.Date data_criacaoSQL;
		try {
			data_criacaoSQL = new java.sql.Date(fmt.parse(data_criacao).getTime());
			t.setData_criacao(data_criacaoSQL);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		java.sql.Date data_conclusaoSQL;
		try {
			data_conclusaoSQL = new java.sql.Date(fmt.parse(data_conclusao).getTime());
			t.setData_conclusao(data_conclusaoSQL);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		try {
			tdao.cadastrarTarefa(t);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_tarefas.jsp");
		dispatcher.forward(request, response);
	}

}
