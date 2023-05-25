package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Tarefa;

public class TarefaDAO {
	
	String serverName="localhost";
	String dataBasePort="3306";
	String mydatabase="agenda";
	String url="jdbc:mysql://" + serverName + ":" + dataBasePort + "/" + mydatabase;
	String usernameb="root";
	String passwordb="";
	
	static ArrayList<Tarefa>  tarefasUsuario = new ArrayList<Tarefa>();
	
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
        	
        	tarefasUsuario.add(t);
        	
        }catch(SQLException e) {
        	e.printStackTrace();
        }
        
        return result;
	}
	
}
