package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;

import java.io.IOException;
import java.text.ParseException;

import dao.TarefaDAO;

/**
 * Servlet implementation class UsuarioTarefa
 */
@WebServlet("/UserTask")
public class UsuarioTarefa extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TarefaDAO tdao = new TarefaDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioTarefa() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuario = (String) request.getSession().getAttribute("usuario");
		if(usuario != null) {
			ServletContext sc = getServletContext();
			Usuario u = (Usuario) sc.getAttribute("usuario");
			try {
				tdao.buscarTarefas(u.getId());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.setAttribute("lista_tarefas", tdao.getTarefasUsuario());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_tarefas.jsp");
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
			ServletContext sc = getServletContext();
			Usuario u = (Usuario) sc.getAttribute("usuario");
			
			String tituloBuscar = (String) request.getParameter("titulo");
			String data_criacao = request.getParameter("data_criacao");
			String data_conclusao = request.getParameter("data_conclusao");
			java.text.DateFormat fmt = new java.text.SimpleDateFormat("yyyy-MM-dd");
			java.sql.Date data_criacaoSQL;
			java.sql.Date data_conclusaoSQL;
			
			try {
				if(tituloBuscar.equals("") && data_criacao.equals("") && data_conclusao.equals("")) {
					try {
						tdao.buscarTarefas(u.getId());
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					request.setAttribute("lista_tarefas", tdao.getTarefasUsuario());
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_tarefas.jsp");
					dispatcher.forward(request, response);
				} else if(!tituloBuscar.isEmpty() && !data_criacao.isEmpty() && !data_conclusao.isEmpty()) {
					try {
						data_criacaoSQL = new java.sql.Date(fmt.parse(data_criacao).getTime());
						data_conclusaoSQL = new java.sql.Date(fmt.parse(data_conclusao).getTime());
						request.setAttribute("lista_tarefas", tdao.pesquisarTarefa(tituloBuscar, data_criacaoSQL, data_conclusaoSQL, u.getId()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_tarefas.jsp");
					dispatcher.forward(request, response);
				} else if (!tituloBuscar.isEmpty() && !data_criacao.isEmpty()){
					try {
						data_criacaoSQL = new java.sql.Date(fmt.parse(data_criacao).getTime());
						request.setAttribute("lista_tarefas", tdao.pesquisarTarefa(tituloBuscar, data_criacaoSQL, u.getId()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_tarefas.jsp");
					dispatcher.forward(request, response);
				} else if (!data_conclusao.isEmpty() && !data_criacao.isEmpty()) {
					try {
						data_criacaoSQL = new java.sql.Date(fmt.parse(data_criacao).getTime());
						data_conclusaoSQL = new java.sql.Date(fmt.parse(data_conclusao).getTime());
						request.setAttribute("lista_tarefas", tdao.pesquisarTarefa(data_criacaoSQL, data_conclusaoSQL, u.getId()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_tarefas.jsp");
					dispatcher.forward(request, response);
				} else if(!tituloBuscar.isEmpty()) {
					request.setAttribute("lista_tarefas", tdao.pesquisarTarefa(tituloBuscar, u.getId()));
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_tarefas.jsp");
					dispatcher.forward(request, response);
				} else if (!data_criacao.isEmpty()) {
					try {
						data_criacaoSQL = new java.sql.Date(fmt.parse(data_criacao).getTime());
						request.setAttribute("lista_tarefas", tdao.pesquisarTarefa(data_criacaoSQL, "criacao", u.getId()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_tarefas.jsp");
					dispatcher.forward(request, response);
				} else if (!data_conclusao.isEmpty()) {
					try {
						data_conclusaoSQL = new java.sql.Date(fmt.parse(data_conclusao).getTime());
						request.setAttribute("lista_tarefas", tdao.pesquisarTarefa(data_conclusaoSQL, "conclusao", u.getId()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_tarefas.jsp");
					dispatcher.forward(request, response);
					
				}
				
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_login.jsp");
			dispatcher.forward(request, response);
		}
	}

}
