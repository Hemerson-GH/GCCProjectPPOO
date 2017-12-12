package br.ufla.gcc.ppoo.Control;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ufla.gcc.ppoo.BancoDeDados.BancoDeDados;
import br.ufla.gcc.ppoo.Dados.DadosLogin;
import br.ufla.gcc.ppoo.Exceptions.BancoDadosException;
import br.ufla.gcc.ppoo.Exceptions.ConverteSenhaException;
import br.ufla.gcc.ppoo.Exceptions.UsuarioException;
import br.ufla.gcc.ppoo.Exceptions.UsuarioExistenteException;

public class ControleDadosUsuarios {
	
	private static BancoDeDados bancoDados = new BancoDeDados();
	
	public static boolean CadastrarUsuario(DadosLogin dados) throws UsuarioExistenteException, BancoDadosException, UsuarioException{
		
		if (ConfereEmail(dados.getEmail())) {
			throw new UsuarioExistenteException(dados.getEmail(), "Usu�rio Existente");
		}
		
		bancoDados.Conecta();
		
		boolean ok = false;
		
		try {
			PreparedStatement pst = bancoDados.getConnection().prepareStatement("insert into dados_user(nome,email,senha) values(?,?,?)");
			pst.setString(1, dados.getNome());		
			pst.setString(2, dados.getEmail());
			pst.setString(3, dados.getSenha());	
			pst.execute();
			
			ok = true;
		} catch (SQLException sqle) {
			throw new UsuarioException("N�o foi poss�vel cadastrar seu usu�rio\n" + sqle.getMessage(), "Erro ao cadastrar usu�rio");
		} finally {
			bancoDados.Desconecta();
		}
		return ok;
	}
	
	public static DadosLogin BuscarDados(String email) throws BancoDadosException, UsuarioException {
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
		} catch (SQLException sqle) {
			throw new UsuarioException("N�o foi poss�vel buscar os dados desse usu�rio" + sqle.getMessage(), "Erro ao buscar dados do usu�rio");
		} finally {
			bancoDados.Desconecta();
		}
		
		return dadosLogin;
	}
	
	public static String BuscaNomeUser(Long id) throws BancoDadosException, UsuarioException {
		bancoDados.Conecta();	
		
		String nomeUser = null;
		
		try {
			PreparedStatement pst = bancoDados.getConnection().prepareStatement("SELECT * FROM dados_user Where id_user = ?");
			pst.setLong(1, id);
			ResultSet rs = pst.executeQuery();	
			
			if (rs.next()) {
				nomeUser = (rs.getString("nome"));	
			}
		} catch (SQLException sqle) {
			throw new UsuarioException("N�o foi poss�vel buscar os nome desse usu�rio" + sqle.getMessage(), "Erro ao buscar nome do usu�rio");
		} finally {
			bancoDados.Desconecta();
		}
		
		return nomeUser;
	}
	
	public static Long BuscaIdUser(String nome) throws BancoDadosException, UsuarioException {
		bancoDados.Conecta();	
		
		Long id = null;
		
		try {
			PreparedStatement pst = bancoDados.getConnection().prepareStatement("SELECT * FROM dados_user Where nome = ?");
			pst.setString(1, nome);
			ResultSet rs = pst.executeQuery();	
			
			if (rs.next()) {
				id = (rs.getLong("id_user"));	
			}
		} catch (SQLException sqle) {
			throw new UsuarioException("N�o foi poss�vel buscar o nome desse usu�rio" +  sqle.getMessage(), "Erro ao buscar ID do usu�rio");
		} finally {
			bancoDados.Desconecta();
		}
		
		return id;
	}
	
	public static boolean ConfereEmail(String email) throws BancoDadosException, UsuarioException {
		bancoDados.Conecta();	
		
		boolean encontrei = false;
		
		try {
			PreparedStatement pst = bancoDados.getConnection().prepareStatement("SELECT * FROM dados_user Where email = ?");
			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();		
			
			if (rs.next()) {	
				encontrei = true;
			}
		} catch (SQLException sqle) {
			throw new UsuarioException("N�o foi poss�vel buscar o email desse usu�rio" + sqle.getMessage(), "Erro ao conferir email");
		} finally {
			bancoDados.Desconecta();
		}
		
		return encontrei;
	}
	
	public static String ConvertMD5(String wordConvert) throws ConverteSenhaException{
		MessageDigest messageDigest = null;
		
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException nsae) {
			throw new ConverteSenhaException(nsae.getMessage(), "Erro ao converter senha");
		} 
		
		messageDigest.update(wordConvert.getBytes(),0,wordConvert.length()); 
		
		return new BigInteger(1, messageDigest.digest()).toString(16);
	}
}
