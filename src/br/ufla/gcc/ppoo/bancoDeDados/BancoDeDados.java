package br.ufla.gcc.ppoo.bancoDeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.ufla.gcc.ppoo.exceptions.BancoDadosException;

public class BancoDeDados {
	
	private Connection connection;
	private String driver = "org.postgresql.Driver";
	private String caminho = "jdbc:postgresql://localhost:5432/ProjetoFilmes";
	private String usuario = "postgres";
	private String senha = "root";
	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}	

	public void conecta() throws BancoDadosException{
		try {
			System.setProperty("jdbc.Drivers", driver);
			setConnection(DriverManager.getConnection(caminho, usuario, senha));
		} catch (SQLException sqlex){
			throw new BancoDadosException("Não foi possível conectar com o banco de dados\n" + sqlex.getMessage(), "Erro na Conexão");
		}
	}

	public void desconecta() throws BancoDadosException{
		try {
			getConnection().close();
		} catch (SQLException sqlex){
			throw new BancoDadosException("Não foi possível desconectar com o banco de dados\n" + sqlex.getMessage(), "Erro ao Desconectar");
		}
	}
}
