package br.ufla.gcc.ppoo.Control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.ufla.gcc.ppoo.BancoDeDados.BancoDeDados;
import br.ufla.gcc.ppoo.Dados.Filme;
import br.ufla.gcc.ppoo.Exceptions.AvaliacaoException;
import br.ufla.gcc.ppoo.Exceptions.AvaliacaoExistenteException;
import br.ufla.gcc.ppoo.Exceptions.BancoDadosException;
import br.ufla.gcc.ppoo.Exceptions.FilmeExistenteException;
import br.ufla.gcc.ppoo.Exceptions.FilmesException;
import br.ufla.gcc.ppoo.Exceptions.UsuarioException;

public class ControleDadosFilmes {
	
	private static BancoDeDados bancoDeDados = new BancoDeDados();
	
	public static boolean cadastrarFilme(Filme filme, Long id_user) throws FilmeExistenteException, BancoDadosException, FilmesException {
		
		if (confereFilme(filme.getNome(), id_user)) {
			throw new FilmeExistenteException(filme.getNome(), "Filme já cadastrado");
		}
		
		bancoDeDados.conecta();
		
		boolean ok = false;
		
		try {
			PreparedStatement pst = bancoDeDados.getConnection().prepareStatement("insert into filmes(id_user, nome_filme, ano_lancamento, descricao, palavras_chaves, genero, duracao_filme, diretor) values(?,?,?,?,?,?,?,?)");
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
		} catch (SQLException sqle) {
			throw new FilmesException("Não foi possível cadastrar esse filme\n" + sqle.getMessage(), "Erro ao cadastrar filme");
		} finally {
			bancoDeDados.desconecta();
		}
		
		return ok;
	}	
	
	public static ArrayList<Filme> buscarFilmesUmUsuario(Long id) throws BancoDadosException, FilmesException{
		bancoDeDados.conecta();
		
		ArrayList<Filme> listFilm = new ArrayList<>();
		String nome, data, descricao, wordsKeys, genero, duracao, diretor;
		Long pontos, id_user, id_filme;
		
		try {
			PreparedStatement pst = bancoDeDados.getConnection().prepareStatement("SELECT * FROM filmes WHERE id_user = ? ORDER BY nome_filme ASC");
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
		} catch (SQLException sqle) {
			throw new FilmesException("Não foi possível buscar seus filmes\n" + sqle.getMessage(), "Erro ao buscar filmes");
		} finally {
			bancoDeDados.desconecta();
		}
				
		return listFilm;
	}
	
	public static ArrayList<Filme> buscarFilmesTodosUsuariosPontos() throws BancoDadosException, FilmesException{
		bancoDeDados.conecta();
		
		ArrayList<Filme> listFilm = new ArrayList<>();
		String nome, data, descricao, wordsKeys, genero, duracao, diretor;
		Long pontos, id_user, id_filme;
		
		try {
			PreparedStatement pst = bancoDeDados.getConnection().prepareStatement("SELECT * FROM filmes ORDER BY pontos_filme DESC");
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
		} catch (SQLException sqle) {
			throw new FilmesException("Não foi possível buscar todos os filmes\n" + sqle.getMessage(), "Erro ao buscar filmes");
		} finally {
			bancoDeDados.desconecta();
		}
		
		return listFilm;
	}
	
	public static ArrayList<Filme> buscarFilmesOutrosUsuarios(Long id_usuario) throws BancoDadosException, FilmesException{
		bancoDeDados.conecta();
		
		ArrayList<Filme> listFilm = new ArrayList<>();
		String nome, data, descricao, wordsKeys, genero, duracao, diretor;
		Long pontos, id_user, id_filme;
		
		try {
			PreparedStatement pst = bancoDeDados.getConnection().prepareStatement("SELECT * FROM filmes WHERE id_user != ? ORDER BY nome_filme ASC");
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
		} catch (SQLException sqle) {
			throw new FilmesException("Não foi possível buscar os filmes de outros usuários\n" + sqle.getMessage(), "Erro ao buscar filmes");
		} finally {
			bancoDeDados.desconecta();
		}
		
		return listFilm;
	}
	
	public static boolean confereFilme(String filme, Long id_user) throws BancoDadosException, FilmesException{
		bancoDeDados.conecta();	
		
		boolean encontrei = false;
		
		try {
			PreparedStatement pst = bancoDeDados.getConnection().prepareStatement("SELECT * FROM filmes Where nome_filme = ? and id_user = ?");
			pst.setString(1, filme);
			pst.setLong(2, id_user);
			ResultSet rs = pst.executeQuery();	
			
			if (rs.next()) {	
				encontrei = true;
			}
		} catch (SQLException sqle) {
			throw new FilmesException("Não foi possível encontrar esse filme\n" + sqle.getMessage(),  "Erro ao buscar filme");
		} finally {
			bancoDeDados.desconecta();
		}
		
		return encontrei;
	}
	
	public static Filme confereFilme(String donoFilme, String filme) throws BancoDadosException, UsuarioException, FilmesException{
		bancoDeDados.conecta();	
		
		Filme filmeEncontrado = null;
		String nome, data, descricao, wordsKeys, genero, duracao, diretor;
		Long pontos, id_user, id_filme;
		Long id = ControleDadosUsuarios.buscaIdUser(donoFilme);
		
		try {
			PreparedStatement pst = bancoDeDados.getConnection().prepareStatement("SELECT * FROM filmes Where nome_filme = ? and id_user = ?");
			pst.setString(1, filme);
			pst.setLong(2, id);
			ResultSet rs = pst.executeQuery();	
			
			if (rs.next()) {	
				
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
				
				filmeEncontrado = new Filme(nome, data, descricao, wordsKeys, genero, duracao, diretor, pontos, id_user, id_filme);
			}
		} catch (SQLException sqle) {
			throw new FilmesException("Não foi possível encontrar esse filme\n" + sqle.getMessage(), "Erro ao buscar filme");
		} finally {
			bancoDeDados.desconecta();
		}
		
		return filmeEncontrado;
	}
	
	public static Filme confereFilme(Long id, String filme) throws BancoDadosException, FilmesException{
		bancoDeDados.conecta();	
		
		Filme filmeEncontrado = null;
		String nome, data, descricao, wordsKeys, genero, duracao, diretor;
		Long pontos, id_user, id_filme;
		
		try {
			PreparedStatement pst = bancoDeDados.getConnection().prepareStatement("SELECT * FROM filmes Where nome_filme = ? and id_user = ?");
			pst.setString(1, filme);
			pst.setLong(2, id);
			ResultSet rs = pst.executeQuery();	
			
			if (rs.next()) {	
				
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
				
				filmeEncontrado = new Filme(nome, data, descricao, wordsKeys, genero, duracao, diretor, pontos, id_user, id_filme);
			}
		} catch (SQLException sqle) {
			throw new FilmesException("Não foi possível encontrar esse filme\n" + sqle.getMessage(), "Erro ao buscar filme");
		} finally {
			bancoDeDados.desconecta();
		}
		
		return filmeEncontrado;
	}
	
	public static boolean confereFilme(String filmeEditado, String filme, Long id_user) throws BancoDadosException, FilmesException{
		bancoDeDados.conecta();	
		
		boolean encontrei = false;
		
		try {
			PreparedStatement pst = bancoDeDados.getConnection().prepareStatement("SELECT * FROM filmes Where nome_filme = ? and nome_filme != ? and id_user = ?");
			pst.setString(1, filmeEditado);
			pst.setString(2, filme);
			pst.setLong(3, id_user);
			ResultSet rs = pst.executeQuery();	
			
			if (rs.next()) {	
				encontrei = true;
			}
		} catch (SQLException sqle) {
			throw new FilmesException("Não foi possível encontrar esse filme\n" + sqle.getMessage(), "Erro ao buscar filme");
		} finally {
			bancoDeDados.desconecta();
		}
		
		return encontrei;
	}
	
	public static boolean deletaFilme(Filme filme) throws BancoDadosException{
		bancoDeDados.conecta();
		
		boolean encontrou = false;
		
		try {
			PreparedStatement pst = bancoDeDados.getConnection().prepareStatement("DELETE from filmes where id_filme = ?");
			pst.setLong(1, filme.getId_filme());
			pst.execute();
			pst.close();	
			
			encontrou = true;
		} catch (SQLException sqle) {
			throw new BancoDadosException("Não foi possível deletar esse filme\n" + sqle.getMessage(), "Erro ao deletar filme");
		} finally {
			bancoDeDados.desconecta();
		}
		
		return encontrou;
	}
	
	public static boolean alteraFilme(Filme filme, String nomeOriginal) throws BancoDadosException, FilmeExistenteException, FilmesException{
		boolean encontrou = false;
		
		if (confereFilme(filme.getNome(), nomeOriginal, filme.getId_user())) {
			throw new FilmeExistenteException(filme.getNome(), "filme já cadastrado");
		}
		
		bancoDeDados.conecta();
		
		try {
			 PreparedStatement pst = bancoDeDados.getConnection().prepareStatement("UPDATE filmes set nome_filme = ?, ano_lancamento = ?, descricao = ?, palavras_chaves = ?, genero = ?, duracao_filme = ?, diretor = ? WHERE id_filme = ?");
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
	    } catch (SQLException sqle) {
	    	throw new FilmesException("Não foi possível alterar dados desse filme\n" + sqle.getMessage(), "Erro ao alterar dados do filme");
		} finally {
			bancoDeDados.desconecta();
		}
		
		return encontrou;
	}
	
	public static boolean avaliaFilme(Long id, Filme filme) throws BancoDadosException, AvaliacaoException, AvaliacaoExistenteException, FilmesException{
		
		if (ControleDadosAvaliacao.buscarAvaliacao(id, filme.getId_filme())) {
			throw new AvaliacaoExistenteException(filme.getNome(), "você já avaliou esse filme");
		}
		
		bancoDeDados.conecta();
		
		boolean encontrou = false;
		
		try {
			 PreparedStatement pst = bancoDeDados.getConnection().prepareStatement("UPDATE filmes set pontos_filme = ? WHERE id_filme = ?");
			 pst.setLong(1, filme.getPontos());
			 pst.setLong(2, filme.getId_filme());
	         pst.execute();
	         
	         encontrou = true;
	    } catch (SQLException sqle) {
	    	throw new FilmesException("Não foi possível avaliar esse filme\n" + sqle.getMessage(), "Erro ao avaliar filme");
		} finally {
			bancoDeDados.desconecta();
		}
		
		return encontrou;
	}
}
