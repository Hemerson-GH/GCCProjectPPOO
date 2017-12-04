package br.ufla.gcc.ppoo.Control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.ufla.gcc.ppoo.BancoDeDados.BancoDeDados;
import br.ufla.gcc.ppoo.Dados.Filme;

public class ControleDadosFilmes {
	
	private static BancoDeDados bancoDados = new BancoDeDados();
	
	public static boolean CadastrarFilme(Filme filme, Long id_user){
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
			JOptionPane.showMessageDialog(null, "Falha ao cadastrar filme:\n" + ex.getMessage() + 
					"\nEntre em contato com o administrador do sistema.", "Erro no cadastro", JOptionPane.ERROR_MESSAGE);
		} finally {
			bancoDados.Desconecta();
		}
		return ok;
	}	
	
	public static ArrayList<Filme> BuscarFilmesUmUsuario(Long id){
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
			JOptionPane.showMessageDialog(null, "Falha ao buscar todos os seus filmes:\n" + ex.getMessage() + 
					"\nEntre em contato com o administrador do sistema.", "Erro na busca", JOptionPane.ERROR_MESSAGE);
		} finally {
			bancoDados.Desconecta();
		}
				
		return listFilm;
	}
	
	public static ArrayList<Filme> BuscarFilmesTodosUsuarios(Long id){
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
			JOptionPane.showMessageDialog(null, "Falha ao buscar todos filmes do sistemas:\n" + ex.getMessage() + 
					"\nEntre em contato com o administrador do sistema.", "Erro na busca", JOptionPane.ERROR_MESSAGE);
		} finally {
			bancoDados.Desconecta();
		}
		
		return listFilm;
	}
	
	public boolean ConfereFilme(String filme){
		bancoDados.Conecta();	
		boolean encontrei = false;
		
		try {
			PreparedStatement pst = bancoDados.getConnection().prepareStatement("SELECT * FROM filmes Where nome_filme = ?");
			pst.setString(1, filme);
			ResultSet rs = pst.executeQuery();	
			
			if (rs.next()) {	
				encontrei = true;
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Falha ao buscar filme selecionado:\n" + ex.getMessage() + 
					"\nEntre em contato com o administrador do sistema.", "Erro na busca", JOptionPane.ERROR_MESSAGE);
		} finally {
			bancoDados.Desconecta();
		}
		
		return encontrei;
	}
	
	public static boolean DeletaFilme(Filme filme){
		boolean encontrou = false;
		
		bancoDados.Conecta();
		
		try {
			PreparedStatement pst = bancoDados.getConnection().prepareStatement("DELETE from filmes where nome_filme = ?");
			
			pst.setString(1, filme.getNome());
			pst.execute();
			pst.close();	
			
			encontrou = true;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao deletar filme selecionado:\n" + ex.getMessage() + 
					"\nEntre em contato com o administrador do sistema.", "Erro ao deletar", JOptionPane.ERROR_MESSAGE);
		} finally {
			bancoDados.Desconecta();
		}
		
		return encontrou;
	}
	
	public static boolean AlteraFilme(Filme filme){
		boolean encontrou = false;
		
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
			JOptionPane.showMessageDialog(null, "Erro ao alterar dados do filme selecionado:\n" + ex.getMessage() + 
					"\nEntre em contato com o administrador do sistema.", "Erro ao alterar dados", JOptionPane.ERROR_MESSAGE);
		} finally {
			bancoDados.Desconecta();
		}
		
		return encontrou;
	}
	
	public static boolean AvaliaFilme(Filme filme){
		boolean encontrou = false;
		
		bancoDados.Conecta();
		
		try {
			 PreparedStatement pst = bancoDados.getConnection().prepareStatement("UPDATE filmes set pontos_filme = ? WHERE id_filme = ?");

			 pst.setLong(1, filme.getPontos());
			 pst.setLong(2, filme.getId_filme());
	         pst.execute();
	         
	         encontrou = true;
	    } catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao avaliar filme selecionado:\n" + ex.getMessage() + 
					"\nEntre em contato com o administrador do sistema.", "Erro ao avaliar filme", JOptionPane.ERROR_MESSAGE);
		} finally {
			bancoDados.Desconecta();
		}
		
		return encontrou;
	}
}
