package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Usuario;

public class UsuarioDAO {
	
	String serverName="localhost";
	String dataBasePort="3306";
	String mydatabase="agenda";
	String url="jdbc:mysql://" + serverName + ":" + dataBasePort + "/" + mydatabase;
	String usernameb="root";
	String passwordb="";
	
	public int cadastrarUsuario(Usuario u) throws ClassNotFoundException{
        String INSERT_USERS_SQL = "INSERT INTO usuarios" + 
                "(login, password, nome, email) VALUES " +
                "(?,?,?,?);";
        
        int result = 0;
        
        Class.forName("com.mysql.jdbc.Driver");
        
        try (Connection connection = DriverManager.getConnection(url,usernameb,passwordb);
        	PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)){
        	preparedStatement.setString(1, u.getLogin());
        	preparedStatement.setString(2, u.getSenha());
        	preparedStatement.setString(3, u.getNome());
        	preparedStatement.setString(4, u.getEmail());
        	System.out.println("Senha, UsuarioDAO.cadastrarUsuario: " + u.getSenha());
        	System.out.println(preparedStatement);
        	
        	result = preparedStatement.executeUpdate();
        	
        	ResultSet rs = preparedStatement.getGeneratedKeys();
 
        	if (rs.next()) {
        	    u.setId(rs.getInt(1));
        	}
        	
        }catch(SQLException e) {
        	e.printStackTrace();
        }
        
        return result;
	}
	
	public boolean verificarLogin(String login) throws ClassNotFoundException {
		String SELECT_USERS_SQL = "SELECT id, login, password, nome, email FROM usuarios WHERE login like ?;";
        
        Class.forName("com.mysql.jdbc.Driver");
        
        try (Connection connection = DriverManager.getConnection(url,usernameb,passwordb);
        	PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS_SQL)){
        	preparedStatement.setString(1, login);
        	System.out.println(preparedStatement);
        	
        	ResultSet rs = preparedStatement.executeQuery();
        	if(rs.next()) {
        		return true;
        	}
        }catch(SQLException ex) {
        	ex.printStackTrace();
        }
        
        return false;
	}
	
	public Usuario buscarUsuario(String login, String password) throws ClassNotFoundException {
		String SELECT_USERS_SQL = "SELECT id, login, password, nome, email FROM usuarios WHERE login like ? and password like ?";
        
        Class.forName("com.mysql.jdbc.Driver");
        
        Usuario u = null;
        
        try (Connection connection = DriverManager.getConnection(url,usernameb,passwordb);
        	PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS_SQL)){
        	preparedStatement.setString(1, login);
        	preparedStatement.setString(2, password);
        	System.out.println("Senha, UsuarioDAO.buscarUsuario: " + password);
        	System.out.println(preparedStatement);
        	
        	ResultSet rs = preparedStatement.executeQuery();
        	if(rs.next()) {
        		u = new Usuario(rs.getInt("id"), rs.getString("login"), rs.getString("password"), rs.getString("nome"), rs.getString("email"));
        		System.out.println("Senha, UsuarioDAO.buscarUsuario - ResultSet: " + rs.getString("password"));
        	}
        }catch(SQLException ex) {
        	ex.printStackTrace();
        }
        
        return u;
	}

}