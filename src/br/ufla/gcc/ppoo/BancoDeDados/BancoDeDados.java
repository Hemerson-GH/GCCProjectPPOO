package br.ufla.gcc.ppoo.BancoDeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.ufla.gcc.ppoo.Exceptions.ConexaoBD;

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

	public void Conecta() throws ConexaoBD{
		try {
			System.setProperty("jdbc.Drivers", driver);
			setConnection(DriverManager.getConnection(caminho, usuario, senha));
		} catch (SQLException ex){
			throw new ConexaoBD("Não foi possível conectar ao banco de dados: \n" + ex.getMessage(),"Erro ao conectar");
		}
	}

	public void Desconecta() throws ConexaoBD{
		try {
			getConnection().close();
		} catch (SQLException ex){
			throw new ConexaoBD("Não foi possível desconectar o banco de dados: \n " + ex.getMessage(), "Erro ao desconectar");
		}
	}
}
