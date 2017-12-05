package br.ufla.gcc.ppoo.Control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.ufla.gcc.ppoo.BancoDeDados.BancoDeDados;
import br.ufla.gcc.ppoo.Dados.Filme;
import br.ufla.gcc.ppoo.Exceptions.BancoDadosException;
import br.ufla.gcc.ppoo.Exceptions.ConexaoBD;
import br.ufla.gcc.ppoo.Exceptions.FilmeExistenteException;

public class ControleDadosFilmes {
	
	private static BancoDeDados bancoDados = new BancoDeDados();
	
	public static boolean CadastrarFilme(Filme filme, Long id_user) throws FilmeExistenteException, BancoDadosException, ConexaoBD {
		
		if (ConfereFilme(filme.getNome(), id_user)) {
			throw new FilmeExistenteException(filme.getNome(), "Filme existente");
		}
		
		bancoDados.Conecta();
		
		boolean ok = false;
		
		try {
			PreparedStatement pst = bancoDados.getConnection().prepareStatement("insert into filmes(id_user, nome_filme, ano_lancamento,"
					+ " descricao, palavras_chaves, genero, duracao_filme, diretor) values(?,?,?,?,?,?,?,?)");
			
			pst.setLong(1, id_user);
			pst.setString(2, filme.getNome());		
			pst.setString(3, filme.getData());
			pst.setString(4, filme.getDescricao());	
			pst.setString(5, filme.getWordKeys());	
			pst.setString(6, filme.getGenero());	
			pst.setString(7, filme.getDuracaoFilme());	
			pst.setString(8, filme.getDiretor());	
			pst.execute();
			
			ok = true;
		} catch (SQLException ex) {
			throw new BancoDadosException("Não foi possível cadastrar o filme\n" + ex.getMessage() + 
											"\nPor favor entre em contato com o administrador do sistema", "Erro ao cadastrar");
		} finally {
			bancoDados.Desconecta();
		}
		return ok;
	}	
	
	public static ArrayList<Filme> BuscarFilmesUmUsuario(Long id) throws ConexaoBD, BancoDadosException{
		bancoDados.Conecta();
		
		ArrayList<Filme> listFilm = new ArrayList<>();
		String nome, data, descricao, wordsKeys, genero, duracao, diretor;
		Long pontos, id_user, id_filme;
		
		try {
			PreparedStatement pst = bancoDados.getConnection().prepareStatement("SELECT * FROM filmes WHERE id_user = ? ORDER BY nome_filme ASC");
			pst.setLong(1, id);
			ResultSet rs = pst.executeQuery();	
			
			while (rs.next()) {	
				
				nome = (rs.getString("nome_filme"));
				data = (rs.getString("ano_lancamento"));
				descricao = (rs.getString("descricao"));
				wordsKeys = (rs.getString("palavras_chaves"));
				genero = (rs.getString("genero"));
				duracao = (rs.getString("duracao_filme"));
				diretor = (rs.getString("diretor"));
				pontos = (rs.getLong("pontos_filme"));
				id_user = (rs.getLong("id_user"));
				id_filme = (rs.getLong("id_filme"));
				
				Filme filme = new Filme(nome, data, descricao, wordsKeys, genero, duracao, diretor, pontos, id_user, id_filme);
				
				listFilm.add(filme);
			}
		} catch (SQLException ex) {
			throw new BancoDadosException("Não foi possível buscar os seus filmes\n" + ex.getMessage() + 
						"Por favor entre em contato com o administrador do sistema", "Erro ao buscar filmes");
		} finally {
			bancoDados.Desconecta();
		}
				
		return listFilm;
	}
	
	public static ArrayList<Filme> BuscarFilmesTodosUsuarios() throws ConexaoBD, BancoDadosException{
		bancoDados.Conecta();
		
		ArrayList<Filme> listFilm = new ArrayList<>();
		String nome, data, descricao, wordsKeys, genero, duracao, diretor;
		Long pontos, id_user, id_filme;
		
		try {
			PreparedStatement pst = bancoDados.getConnection().prepareStatement("SELECT * FROM filmes ORDER BY nome_filme ASC");
			ResultSet rs = pst.executeQuery();	
			
			while (rs.next()) {	
				
				nome = (rs.getString("nome_filme"));
				data = (rs.getString("ano_lancamento"));
				descricao = (rs.getString("descricao"));
				wordsKeys = (rs.getString("palavras_chaves"));
				genero = (rs.getString("genero"));
				duracao = (rs.getString("duracao_filme"));
				diretor = (rs.getString("diretor"));
				pontos = (rs.getLong("pontos_filme"));
				id_user = (rs.getLong("id_user"));
				id_filme = (rs.getLong("id_filme"));
				
				Filme filme = new Filme(nome, data, descricao, wordsKeys, genero, duracao, diretor, pontos, id_user, id_filme);
				
				listFilm.add(filme);
			}
		} catch (SQLException ex) {
			throw new BancoDadosException("Não foi possível buscar os filmes\n" + ex.getMessage() +
											"Por favor entre em contato com o administrador do sistema", "Erro ao buscar todos os filmes");
		} finally {
			bancoDados.Desconecta();
		}
		
		return listFilm;
	}
	
	public static ArrayList<Filme> BuscarFilmesUsuarios(Long id_usuario) throws ConexaoBD, BancoDadosException{
		bancoDados.Conecta();
		
		ArrayList<Filme> listFilm = new ArrayList<>();
		String nome, data, descricao, wordsKeys, genero, duracao, diretor;
		Long pontos, id_user, id_filme;
		
		try {
			PreparedStatement pst = bancoDados.getConnection().prepareStatement("SELECT * FROM filmes WHERE id_user != ? ORDER BY nome_filme ASC");
			pst.setLong(1, id_usuario);
			ResultSet rs = pst.executeQuery();	
			
			while (rs.next()) {	
				
				nome = (rs.getString("nome_filme"));
				data = (rs.getString("ano_lancamento"));
				descricao = (rs.getString("descricao"));
				wordsKeys = (rs.getString("palavras_chaves"));
				genero = (rs.getString("genero"));
				duracao = (rs.getString("duracao_filme"));
				diretor = (rs.getString("diretor"));
				pontos = (rs.getLong("pontos_filme"));
				id_user = (rs.getLong("id_user"));
				id_filme = (rs.getLong("id_filme"));
				
				Filme filme = new Filme(nome, data, descricao, wordsKeys, genero, duracao, diretor, pontos, id_user, id_filme);
				
				listFilm.add(filme);
			}
		} catch (SQLException ex) {
			throw new BancoDadosException("Não foi possível buscar os filmes\n" + ex.getMessage() + 
								"\nPor favor entre em contato com o administrador do sistema", "Erro ao buscar filmes");
		} finally {
			bancoDados.Desconecta();
		}
		
		return listFilm;
	}
	
	public static boolean ConfereFilme(String filme, Long id_user) throws ConexaoBD, BancoDadosException{
		bancoDados.Conecta();	
		boolean encontrei = false;
		
		try {
			PreparedStatement pst = bancoDados.getConnection().prepareStatement("SELECT * FROM filmes Where nome_filme = ? and id_user = ?");
			pst.setString(1, filme);
			pst.setLong(2, id_user);
			ResultSet rs = pst.executeQuery();	
			
			if (rs.next()) {	
				encontrei = true;
			}
		} catch (SQLException ex) {
			throw new BancoDadosException("Não foi possível buscar o filme\n" + ex.getMessage() +
								"\nPor favor entre em contato com o administrador do sistema", "Erro ao buscar filme");
		} finally {
			bancoDados.Desconecta();
		}
		
		return encontrei;
	}
	
	public static boolean ConfereFilme(String filmeEditado, String filme, Long id_user) throws ConexaoBD, BancoDadosException{
		bancoDados.Conecta();	
		boolean encontrei = false;
		
		try {
			PreparedStatement pst = bancoDados.getConnection().prepareStatement("SELECT * FROM filmes Where nome_filme = ? and nome_filme != ? and id_user = ?");
			pst.setString(1, filmeEditado);
			pst.setString(2, filme);
			pst.setLong(3, id_user);
			ResultSet rs = pst.executeQuery();	
			
			if (rs.next()) {	
				encontrei = true;
			}
		} catch (SQLException ex) {
			throw new BancoDadosException("Não foi possível buscar o filme\n" + ex.getMessage() +
								"\nPor favor entre em contato com o administrador do sistema", "Erro ao buscar filme");
		} finally {
			bancoDados.Desconecta();
		}
		
		return encontrei;
	}
	
	public static boolean DeletaFilme(Filme filme) throws ConexaoBD, BancoDadosException{
		boolean encontrou = false;
		
		bancoDados.Conecta();
		
		try {
			PreparedStatement pst = bancoDados.getConnection().prepareStatement("DELETE from filmes where id_filme = ?");
			
			pst.setLong(1, filme.getId_filme());
			pst.execute();
			pst.close();	
			
			encontrou = true;
		} catch (SQLException ex) {
			throw new BancoDadosException("Não foi possível deletar esse filme\n" + ex.getMessage() +
					"\nPor favor entre em contato com o administrador do sistema", "Erro ao deletar filme");
		} finally {
			bancoDados.Desconecta();
		}
		
		return encontrou;
	}
	
	public static boolean AlteraFilme(Filme filme, String nomeOriginal) throws ConexaoBD, BancoDadosException, FilmeExistenteException{
		boolean encontrou = false;
		
		if (ConfereFilme(filme.getNome(), nomeOriginal, filme.getId_user())) {
			throw new FilmeExistenteException(filme.getNome(), "Filme existente");
		}
		
		bancoDados.Conecta();
		
		try {
			 PreparedStatement pst = bancoDados.getConnection().prepareStatement("UPDATE filmes set nome_filme = ?, ano_lancamento = ?, "
			 		+ "descricao = ?, palavras_chaves = ?, genero = ?, duracao_filme = ?, diretor = ? WHERE id_filme = ?");

			 pst.setString(1, filme.getNome());
			 pst.setString(2, filme.getData());
			 pst.setString(3, filme.getDescricao());
			 pst.setString(4, filme.getWordKeys());
			 pst.setString(5, filme.getGenero());
			 pst.setString(6, filme.getDuracaoFilme());
			 pst.setString(7, filme.getDiretor());
			 pst.setLong(8, filme.getId_filme());
	         pst.execute();
	         
	         encontrou = true;
	    } catch (SQLException ex) {
	    	throw new BancoDadosException("Não foi possível alterar o esse filme\n" + ex.getMessage() +
	    							"\nPor favor entre em contato com o administrador do sistema", "Erro ao alterar filme");
		} finally {
			bancoDados.Desconecta();
		}
		
		return encontrou;
	}
	
	public static boolean AvaliaFilme(Filme filme) throws ConexaoBD, BancoDadosException{
		boolean encontrou = false;
		
		bancoDados.Conecta();
		
		try {
			 PreparedStatement pst = bancoDados.getConnection().prepareStatement("UPDATE filmes set pontos_filme = ? WHERE id_filme = ?");

			 pst.setLong(1, filme.getPontos());
			 pst.setLong(2, filme.getId_filme());
	         pst.execute();
	         
	         encontrou = true;
	    } catch (SQLException ex) {
	    	throw new BancoDadosException("Não foi possível avaliar esse filme\n" + ex.getMessage() +
	    							"\nPor favor entre em contato com o administrador do sistema", "Erro ao avaliar filme");
		} finally {
			bancoDados.Desconecta();
		}
		
		return encontrou;
	}
}
