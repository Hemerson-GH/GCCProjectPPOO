package br.ufla.gcc.ppoo.Control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ufla.gcc.ppoo.BancoDeDados.BancoDeDados;
import br.ufla.gcc.ppoo.Dados.DadosLogin;

public class ControleDadosFilmes {
	BancoDeDados bancoDados = new BancoDeDados();
	
	public void CadastrarFilme(DadosLogin dados){
		bancoDados.Conecta();
		
		try {
			PreparedStatement pst = bancoDados.connection.prepareStatement("insert into dados_user(nome,email,senha) values(?,?,?)");
			pst.setString(1, dados.getNome());		
			pst.setString(2, dados.getEmail());
			pst.setString(3, dados.getSenha());	
			pst.execute();
//			JOptionPane.showMessageDialog(null, "Salvo Com Sucesso");
			
		} catch (SQLException ex) {
//			JOptionPane.showMessageDialog(null, "Erro Ao Salvar Dado: \n " + ex);
			ex.printStackTrace();
		}
		
		bancoDados.Desconecta();
	}
	
	DadosLogin dadosLogin = new DadosLogin(null, null, null);
	
	public DadosLogin buscarDados(String email){
		bancoDados.Conecta();	
		
		try {
			PreparedStatement pst = bancoDados.connection.prepareStatement("SELECT * FROM dados_user Where email = '" + email +"'");
			ResultSet rs = pst.executeQuery();	
			
			while (rs.next()) {
								
			    dadosLogin.setEmail(rs.getString("email"));
			    dadosLogin.setSenha(rs.getString("senha"));
			    dadosLogin.setNome(rs.getString("nome"));		    
//			    JOptionPane.showMessageDialog(null, dadosLogin.getEmail() + "\n " + dadosLogin.getNome() + "\n" + dadosLogin.getSenha());
			}
			
		} catch (SQLException ex) {
//			JOptionPane.showMessageDialog(null, "Erro Ao Buscar email: \n " + ex);
			ex.printStackTrace();
		}
		
		bancoDados.Desconecta();
		return dadosLogin;
	}
	
	public boolean confereEmail(String email){
		bancoDados.Conecta();	
//		String emailBancoDDados = null;
		boolean encontrei = false;
		
		try {
			PreparedStatement pst = bancoDados.connection.prepareStatement("SELECT * FROM dados_user Where email = '" + email +"'");
			ResultSet rs = pst.executeQuery();	
			
			while (rs.next()) {
//				emailBancoDDados = rs.getString("email");	
				encontrei = true;
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		bancoDados.Desconecta();
		
		return encontrei;
	}
}
