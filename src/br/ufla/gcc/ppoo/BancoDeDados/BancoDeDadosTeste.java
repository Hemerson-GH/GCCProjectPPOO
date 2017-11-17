package br.ufla.gcc.ppoo.BancoDeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class BancoDeDadosTeste {
	
	private Connection connection = null;
	private Statement statement = null;	
	private ResultSet resultSet = null;
	
	public void conectar(){
		
		String servidor = "jdbc:mysql://localhost:3306/agenda";
		String usuario = "root";
		String senha = "dcc";
		String driver = "com.mysql.jdbc.Driver";
		
		try {
			Class.forName(driver);
			this.connection = DriverManager.getConnection(servidor, usuario, senha);
			this.statement = this.connection.createStatement();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
	
	public boolean estaConectado(){
		
		if (this.connection != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public void listarContatos(){
		try {
			String query = "SELECT * FROM contato ORDER BY nome";
			this.resultSet = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();
			
			while (this.resultSet.next()) {
				System.out.println("ID:" + this.resultSet.getString("id") + " - Nome: " + this.resultSet.getString("nome") 
								+ " - Telefone: " + this.resultSet.getString("telefone")); 
				
			}
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
	
	public void inserirContato(String nome, String telefone){
		try {
			String query = "INSERT INTO contato (nome, telefone) VALUES ('" + nome + "', '" + telefone + "')";
			this.statement.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
	
	public void editarContato(String id, String nome, String telefone){
		try {
			String query = "UPDATE contato set nome = " + nome + ", telefone = " + telefone + "' WHERE id = " + id + ";";
			this.statement.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
	
	public void apagarContato(String id){
		try {
			String query = "DELETE FROM contato	WHERE id = " + id + ";";
			this.statement.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
	
	public void desconectar(){
		try {
			this.connection.close();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}	
	}
}
