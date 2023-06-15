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
			int flag = -1;
			int id_tarefa = flag;
			ServletContext sc = getServletContext();
			Usuario u = (Usuario) sc.getAttribute("usuario");
			
			if(request.getParameter("id_excluir") != null) {
				id_tarefa = Integer.parseInt(request.getParameter("id_excluir"));
			}
			
			String tituloBuscar = (String) request.getParameter("titulo");
			
			try {
				if(id_tarefa != flag) {
					tdao.excluirTarefa(id_tarefa);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/tarefa_excluida.jsp");
					dispatcher.forward(request, response);
				}
				
				if(!tituloBuscar.isEmpty()) {
					request.setAttribute("lista_tarefas", tdao.pesquisarTarefa(tituloBuscar, u.getId()));
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
