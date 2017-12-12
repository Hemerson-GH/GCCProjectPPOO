package br.ufla.gcc.ppoo.BancoDeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.ufla.gcc.ppoo.Exceptions.BancoDadosException;

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

	public void Conecta() throws BancoDadosException{
		try {
			System.setProperty("jdbc.Drivers", driver);
			setConnection(DriverManager.getConnection(caminho, usuario, senha));
		} catch (SQLException sqlex){
			throw new BancoDadosException("Não foi possível conectar com o banco de dados\n" + sqlex.getMessage(), "Erro na Conexão");
		}
	}

	public void Desconecta() throws BancoDadosException{
		try {
			getConnection().close();
		} catch (SQLException sqlex){
			throw new BancoDadosException("Não foi possível desconectar com o banco de dados\n" + sqlex.getMessage(), "Erro ao Desconectar");
		}
	}
}
