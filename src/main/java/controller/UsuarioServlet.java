package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.UsuarioDAO;
import model.Criptografia;
import model.Usuario;

/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet("/UserServlet")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UsuarioDAO udao = new UsuarioDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_cadastro.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		
		if((nome.isEmpty() || nome.equals("") || nome.equals(" ") || nome.isBlank())){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_cadastro_falha_nome.jsp");
			dispatcher.forward(request, response);
		} else if((login.isEmpty() || login.equals("") || login.equals(" ") || login.isBlank())) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_cadastro_falha_login.jsp");
			dispatcher.forward(request, response);
		} else if((password.isEmpty() || password.equals("") || password.equals(" ") || password.isBlank())){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_cadastro_falha_senha.jsp");
			dispatcher.forward(request, response);
		} else {
			password = Criptografia.criptografar(password);
			Usuario u = null;
			
			try {
				if(udao.verificarLogin(login)) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_cadastro_login_existente.jsp");
					dispatcher.forward(request, response);
				} else {
					u = new Usuario(login, password, nome, email);
					try {
						udao.cadastrarUsuario(u);
					}catch(ClassNotFoundException e) {
						e.printStackTrace();
					}
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_cadastro_sucesso.jsp");
					dispatcher.forward(request, response);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
