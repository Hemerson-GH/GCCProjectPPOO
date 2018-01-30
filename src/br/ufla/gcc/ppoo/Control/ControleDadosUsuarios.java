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
	
	private static BancoDeDados bancoDeDados = new BancoDeDados();
	
	public static boolean cadastrarUsuario(DadosLogin dados) throws UsuarioExistenteException, BancoDadosException, UsuarioException{
		
		if (confereEmail(dados.getEmail())) {
			throw new UsuarioExistenteException(dados.getEmail(), "Usuário Existente");
		}
		
		bancoDeDados.conecta();
		
		boolean ok = false;
		
		try {
			PreparedStatement pst = bancoDeDados.getConnection().prepareStatement("insert into dados_user(nome,email,senha) values(?,?,?)");
			pst.setString(1, dados.getNome());		
			pst.setString(2, dados.getEmail());
			pst.setString(3, dados.getSenha());	
			pst.execute();
			
			ok = true;
		} catch (SQLException sqle) {
			throw new UsuarioException("Não foi possível cadastrar seu usuário\n" + sqle.getMessage(), "Erro ao cadastrar usuário");
		} finally {
			bancoDeDados.desconecta();
		}
		return ok;
	}
	
	public static DadosLogin buscarDados(String email) throws BancoDadosException, UsuarioException {
		bancoDeDados.conecta();	
		
		DadosLogin dadosLogin = new DadosLogin(null, null, null, null);
		
		try {
			PreparedStatement pst = bancoDeDados.getConnection().prepareStatement("SELECT * FROM dados_user Where email = ?");
			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();	
			
			if (rs.next()) {
			    dadosLogin.setEmail(rs.getString("email"));
			    dadosLogin.setSenha(rs.getString("senha"));
			    dadosLogin.setNome(rs.getString("nome"));
			    dadosLogin.setId(rs.getLong("id_user"));
			} 
		} catch (SQLException sqle) {
			throw new UsuarioException("Não foi possível buscar os dados desse usuário" + sqle.getMessage(), "Erro ao buscar dados do usuário");
		} finally {
			bancoDeDados.desconecta();
		}
		
		return dadosLogin;
	}
	
	public static String buscaNomeUser(Long id) throws BancoDadosException, UsuarioException {
		bancoDeDados.conecta();	
		
		String nomeUser = null;
		
		try {
			PreparedStatement pst = bancoDeDados.getConnection().prepareStatement("SELECT * FROM dados_user Where id_user = ?");
			pst.setLong(1, id);
			ResultSet rs = pst.executeQuery();	
			
			if (rs.next()) {
				nomeUser = (rs.getString("nome"));	
			}
		} catch (SQLException sqle) {
			throw new UsuarioException("Não foi possível buscar os nome desse usuário" + sqle.getMessage(), "Erro ao buscar nome do usuário");
		} finally {
			bancoDeDados.desconecta();
		}
		
		return nomeUser;
	}
	
	public static Long buscaIdUser(String nome) throws BancoDadosException, UsuarioException {
		bancoDeDados.conecta();	
		
		Long id = null;
		
		try {
			PreparedStatement pst = bancoDeDados.getConnection().prepareStatement("SELECT * FROM dados_user Where nome = ?");
			pst.setString(1, nome);
			ResultSet rs = pst.executeQuery();	
			
			if (rs.next()) {
				id = (rs.getLong("id_user"));	
			}
		} catch (SQLException sqle) {
			throw new UsuarioException("Não foi possível buscar o nome desse usuário" +  sqle.getMessage(), "Erro ao buscar ID do usuário");
		} finally {
			bancoDeDados.desconecta();
		}
		
		return id;
	}
	
	public static boolean confereEmail(String email) throws BancoDadosException, UsuarioException {
		bancoDeDados.conecta();	
		
		boolean encontrei = false;
		
		try {
			PreparedStatement pst = bancoDeDados.getConnection().prepareStatement("SELECT * FROM dados_user Where email = ?");
			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();		
			
			if (rs.next()) {	
				encontrei = true;
			}
		} catch (SQLException sqle) {
			throw new UsuarioException("Não foi possível buscar o email desse usuário" + sqle.getMessage(), "Erro ao conferir email");
		} finally {
			bancoDeDados.desconecta();
		}
		
		return encontrei;
	}
	
	public static String convertMD5(String wordConvert) throws ConverteSenhaException{
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
