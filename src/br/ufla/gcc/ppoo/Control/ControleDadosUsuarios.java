package br.ufla.gcc.ppoo.Control;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.ufla.gcc.ppoo.BancoDeDados.BancoDeDados;
import br.ufla.gcc.ppoo.Dados.DadosLogin;

public class ControleDadosUsuarios {
	
	private static BancoDeDados bancoDados = new BancoDeDados();
	
	public static boolean CadastrarUsuario(DadosLogin dados){
		bancoDados.Conecta();
		boolean ok = false;
		
		try {
			PreparedStatement pst = bancoDados.getConnection().prepareStatement("insert into dados_user(nome,email,senha) values(?,?,?)");
			pst.setString(1, dados.getNome());		
			pst.setString(2, dados.getEmail());
			pst.setString(3, dados.getSenha());	
			pst.execute();
			
			ok = true;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Falha ao cadastrar usuário:\n" + ex.getMessage() + 
					"\nEntre em contato com o administrador do sistema.", "Erro no cadastro", JOptionPane.ERROR_MESSAGE);
		} finally {
			bancoDados.Desconecta();
		}
		return ok;
	}
	
	public static DadosLogin BuscarDados(String email){
		bancoDados.Conecta();	
		DadosLogin dadosLogin = new DadosLogin(null, null, null, null);
		
		try {
			PreparedStatement pst = bancoDados.getConnection().prepareStatement("SELECT * FROM dados_user Where email = ?");
			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();	
			
			if (rs.next()) {	
			    dadosLogin.setEmail(rs.getString("email"));
			    dadosLogin.setSenha(rs.getString("senha"));
			    dadosLogin.setNome(rs.getString("nome"));	
			    dadosLogin.setId(rs.getLong("id_user"));
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Falha ao buscar usuário:\n" + ex.getMessage() + 
					"\nEntre em contato com o administrador do sistema.", "Erro na busca", JOptionPane.ERROR_MESSAGE);
		} finally {
			bancoDados.Desconecta();
		}
		
		return dadosLogin;
	}
	
	public static String BuscaNomeUser(Long id){
		bancoDados.Conecta();	
		String nomeUser = null;
		
		try {
			PreparedStatement pst = bancoDados.getConnection().prepareStatement("SELECT * FROM dados_user Where id_user = ?");
			pst.setLong(1, id);
			ResultSet rs = pst.executeQuery();	
			
			if (rs.next()) {
				nomeUser = (rs.getString("nome"));	
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Falha ao buscar nome do usuário:\n" + ex.getMessage() + 
					"\nEntre em contato com o administrador do sistema.", "Erro na busca", JOptionPane.ERROR_MESSAGE);
		} finally {
			bancoDados.Desconecta();
		}
		
		return nomeUser;
	}
	
	public static boolean ConfereEmail(String email){
		bancoDados.Conecta();	
		boolean encontrei = false;
		
		try {
			PreparedStatement pst = bancoDados.getConnection().prepareStatement("SELECT * FROM dados_user Where email = ?");
			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();		
			
			if (rs.next()) {	
				encontrei = true;
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Falha ao conferir email:\n" + ex.getMessage() + 
					"\nEntre em contato com o administrador do sistema.", "Erro ao conferir", JOptionPane.ERROR_MESSAGE);
		} finally {
			bancoDados.Desconecta();
		}
		
		return encontrei;
	}
	
	public static String ConvertMD5(String wordConvert){
		MessageDigest md = null;
		
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException ex) {
			JOptionPane.showMessageDialog(null, "Falha ao converte senha:\n" + ex.getMessage() + 
				"\nEntre em contato com o administrador do sistema.", "Erro na conversão", JOptionPane.ERROR_MESSAGE);
		} 
	    
		md.update(wordConvert.getBytes(),0,wordConvert.length()); 
		
//	    System.out.println("MD5: "+ new BigInteger(1, m.digest()).toString(16));
		return new BigInteger(1, md.digest()).toString(16);
	}
}
