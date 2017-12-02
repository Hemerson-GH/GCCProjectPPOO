package br.ufla.gcc.ppoo.BancoDeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class BancoDeDados {
	
	private Connection connection;
	private String driver = "org.postgresql.Driver";
	private String caminho = "jdbc:postgresql://localhost:5433/ProjetoFilmes";
	private String usuario = "postgres";
	private String senha = "seagate01";
	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}	

	public void Conecta(){
		try {
			System.setProperty("jdbc.Drivers", driver);
			setConnection(DriverManager.getConnection(caminho, usuario, senha));
//			JOptionPane.showMessageDialog(null, "Conexão efetuada com Sucesso");
		} catch (SQLException ex){
			JOptionPane.showMessageDialog(null, "Falha na conexão ao Banco De Dados:\n" + ex.getMessage() + 
					"\nEntre em contato com o administrador do sistema.", "Erro na conexão", JOptionPane.ERROR_MESSAGE);
//			ex.getMessage();
		}
	}

	public void Desconecta(){
		try {
			getConnection().close();
//			JOptionPane.showMessageDialog(null, "Desconectado com Sucesso");
		} catch (SQLException ex){
			JOptionPane.showMessageDialog(null, "Falha na desconexão ao Banco De Dados:\n" + ex.getMessage() + 
					"\nEntre em contato com o administrador do sistema.", "Erro na desconexão", JOptionPane.ERROR_MESSAGE);
//			ex.getMessage();
		}
	}
}
