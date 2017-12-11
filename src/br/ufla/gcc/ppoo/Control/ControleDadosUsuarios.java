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
			throw new UsuarioExistenteException(dados.getEmail(), "Usuário Existente");
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
			throw new UsuarioException("Não foi possível cadastrar seu usuário\n" + sqle.getMessage(), "Erro Ao Cadastrar Usuário");
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
			throw new UsuarioException("Não foi possível buscar os dados desse usuário" + sqle.getMessage(), "Erro Ao Buscar Dados Do Usuário");
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
			throw new UsuarioException("Não foi possível buscar os nome desse usuário" + sqle.getMessage(), "Erro Ao Nome Do Usuário");
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
			throw new UsuarioException("Não foi possível buscar o nome desse usuário" +  sqle.getMessage(), "Erro Ao Buscar Id Do Usuário");
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
			throw new UsuarioException("Não foi possível buscar o email desse usuário" + sqle.getMessage(), "Erro Ao Conferir Email");
		} finally {
			bancoDados.Desconecta();
		}
		
		return encontrei;
	}
	
	public static String ConvertMD5(String wordConvert) throws ConverteSenhaException{
		MessageDigest md = null;
		
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException nsae) {
			throw new ConverteSenhaException(nsae.getMessage(), "Erro Ao Converte Senha");
			
		} 
		md.update(wordConvert.getBytes(),0,wordConvert.length()); 
//	    System.out.println("MD5: "+ new BigInteger(1, m.digest()).toString(16));
		return new BigInteger(1, md.digest()).toString(16);
	}
}
