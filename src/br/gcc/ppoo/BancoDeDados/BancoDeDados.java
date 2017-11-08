package br.gcc.ppoo.BancoDeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class BancoDeDados {
	
	public Statement stm;
	public ResultSet rs;
	public Connection con;
	private String driver = "org.postgresql.Driver";
	private String caminho = "jdbc:postgresql://localhost:5433/ProjetoFilmes";
	private String usuario = "postgres";
	private String senha = "seagate01";

	public void conexao(){

		try {
			System.setProperty("jdbc.Drivers", driver);
			con = DriverManager.getConnection(caminho, usuario, senha);

			JOptionPane.showMessageDialog(null, "Conexão efetuada com Sucesso");

		} catch (SQLException ex){
			JOptionPane.showMessageDialog(null, "Falha na conexão ao Banco De Dados:\n " + ex.getMessage());
		}
	}

	public void desconecta(){
		try {
			con.close();

			JOptionPane.showMessageDialog(null, "Desconectado com Sucesso");

		} catch (SQLException ex){
			JOptionPane.showMessageDialog(null, "Falha na desconexão ao Banco De Dados:\n " + ex.getMessage());
		}
	}

	
}
