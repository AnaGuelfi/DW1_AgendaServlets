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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
