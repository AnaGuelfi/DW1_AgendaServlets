package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Tarefa;
import model.Usuario;

public class TarefaDAO {
	
	String serverName="localhost";
	String dataBasePort="3306";
	String mydatabase="agenda";
	String url="jdbc:mysql://" + serverName + ":" + dataBasePort + "/" + mydatabase;
	String usernameb="root";
	String passwordb="";
	
	ArrayList<Tarefa> tarefasUsuario = new ArrayList<Tarefa>();
	
	public ArrayList<Tarefa> getTarefasUsuario() {
		return tarefasUsuario;
	}

	public int cadastrarTarefa(Tarefa t) throws ClassNotFoundException{
        String INSERT_USERS_SQL = "INSERT INTO tarefas" + 
                "(user_id, titulo, descricao, data_criacao, data_conclusao, status) VALUES " +
                "(?,?,?,?,?,?);";
        
        int result = 0;
        
        Class.forName("com.mysql.jdbc.Driver");
        
        try (Connection connection = DriverManager.getConnection(url,usernameb,passwordb);
        	PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)){
        	preparedStatement.setInt(1, t.getU().getId());
        	preparedStatement.setString(2, t.getTitulo());
        	preparedStatement.setString(3, t.getDescricao());
        	preparedStatement.setDate(4, t.getData_criacao());
        	preparedStatement.setDate(5, t.getData_conclusao());
        	preparedStatement.setString(6, t.getStatus());
        	System.out.println(preparedStatement);
        	
        	result = preparedStatement.executeUpdate();
        	ResultSet rs = preparedStatement.getGeneratedKeys();
        	if (rs.next()) {
        	    t.setId(rs.getInt(1));
        	}
        	
        }catch(SQLException e) {
        	e.printStackTrace();
        }
        
        return result;
	}
	
	public void buscarTarefas(int user_id) throws ClassNotFoundException {
		String SELECT_USERS_SQL = "SELECT * FROM tarefas WHERE user_id = ?";
        
        Class.forName("com.mysql.jdbc.Driver");
        
        Tarefa t = null;
        
        try (Connection connection = DriverManager.getConnection(url,usernameb,passwordb);
        	PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS_SQL)){
        	preparedStatement.setInt(1, user_id);
        	System.out.println(preparedStatement);
        	int flag = 0;
        	ResultSet rs = preparedStatement.executeQuery();
        	while(rs.next()) {
        		t = new Tarefa();
        		t.setId(rs.getInt("id"));
        		t.setTitulo(rs.getString("titulo"));
        		t.setDescricao(rs.getString("descricao"));
        		t.setData_criacao(rs.getDate("data_criacao"));
        		t.setData_conclusao(rs.getDate("data_conclusao"));
        		t.setStatus(rs.getString("status"));
        		for(Tarefa tt : tarefasUsuario) {
        			if(tt.getId() == t.getId()) {
        				flag = 1;
        			}
        		}
        		if(flag == 0) {
        			tarefasUsuario.add(t);
        		}
        	}
        }catch(SQLException ex) {
        	ex.printStackTrace();
        }
	}
	
	public Tarefa buscarTarefaEdicao(int id_tarefa) throws ClassNotFoundException {
		String SELECT_USERS_SQL = "SELECT * FROM tarefas WHERE id = ?";
        
        Class.forName("com.mysql.jdbc.Driver");
        
        Tarefa t = null;
        
        try (Connection connection = DriverManager.getConnection(url,usernameb,passwordb);
        	PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS_SQL)){
        	preparedStatement.setInt(1, id_tarefa);
        	System.out.println(preparedStatement);
        	
        	ResultSet rs = preparedStatement.executeQuery();
        	if(rs.next()) {
        		t = new Tarefa();
        		t.setId(rs.getInt("id"));
        		t.setTitulo(rs.getString("titulo"));
        		t.setDescricao(rs.getString("descricao"));
        		t.setData_criacao(rs.getDate("data_criacao"));
        		t.setData_conclusao(rs.getDate("data_conclusao"));
        		t.setStatus(rs.getString("status"));
        	}
        }catch(SQLException ex) {
        	ex.printStackTrace();
        }
        
        return t;
	}
	
	public void alterarTarefa(Tarefa t) throws ClassNotFoundException {
		 String UPDATE_SQL = "UPDATE tarefas SET titulo = ?, descricao = ?, data_criacao = ?, data_conclusao = ?, status = ? WHERE id = ?";
	        
	        Class.forName("com.mysql.jdbc.Driver");
	        
	        try (Connection connection = DriverManager.getConnection(url,usernameb,passwordb);
	        	PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)){
	        	preparedStatement.setString(1, t.getTitulo());
	        	preparedStatement.setString(2, t.getDescricao());
	        	preparedStatement.setDate(3, t.getData_criacao());
	        	preparedStatement.setDate(4, t.getData_conclusao());
	        	preparedStatement.setString(5, t.getStatus());
	        	preparedStatement.setInt(6, t.getId());
	        	System.out.println(preparedStatement);
	        	
	        	preparedStatement.executeUpdate();
	        	
	        }catch(SQLException e) {
	        	e.printStackTrace();
	        }
	}
	
	public void excluirTarefa(int id) throws ClassNotFoundException{
        String DELETE_SQL = "DELETE FROM tarefas WHERE id = ?";
        
        Class.forName("com.mysql.jdbc.Driver");
        
        try (Connection connection = DriverManager.getConnection(url,usernameb,passwordb);
        	PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)){
        	preparedStatement.setInt(1, id);
        	System.out.println(preparedStatement);
        	
        	preparedStatement.executeUpdate();
        	
        }catch(SQLException e) {
        	e.printStackTrace();
        }
        
	}
	
}
