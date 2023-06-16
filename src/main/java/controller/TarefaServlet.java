package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Tarefa;
import model.Usuario;

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
		String usuario = (String) request.getSession().getAttribute("usuario");
		if(usuario != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/tarefa_cadastro.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_login.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuario = (String) request.getSession().getAttribute("usuario");
		if(usuario != null) {
			String titulo = request.getParameter("titulo");
			String descricao = request.getParameter("descricao");
			String data_criacao = request.getParameter("data_criacao");
			String data_conclusao = request.getParameter("data_conclusao");
			String status = request.getParameter("status");
			if((titulo.isEmpty() || titulo.equals("") || titulo.equals(" ") || titulo.isBlank())){
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/tarefa_cadastro_falha_titulo.jsp");
				dispatcher.forward(request, response);
			} else if ((data_conclusao.isEmpty() || data_conclusao.equals("") || data_conclusao.equals(" ") || data_conclusao.isBlank())) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/tarefa_cadastro_falha_data.jsp");
				dispatcher.forward(request, response);
			} else {
				Tarefa t = new Tarefa();
				t.setTitulo(titulo);
				t.setDescricao(descricao);
				t.setStatus(status);
				
				ServletContext sc = getServletContext();
				Usuario u = (Usuario) sc.getAttribute("usuario");
				t.setU(u);
				
				java.text.DateFormat fmt = new java.text.SimpleDateFormat("yyyy-MM-dd");
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
				
				try {
					tdao.buscarTarefas(u.getId());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("lista_tarefas", tdao.getTarefasUsuario());
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_tarefas.jsp");
				dispatcher.forward(request, response);
			}
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_login.jsp");
			dispatcher.forward(request, response);
		}
	}

}
