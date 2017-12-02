package br.ufla.gcc.ppoo.Control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.ufla.gcc.ppoo.BancoDeDados.BancoDeDados;
import br.ufla.gcc.ppoo.Dados.Filme;

public class ControleDadosFilmes {
	
	private BancoDeDados bancoDados = new BancoDeDados();
	
	public void CadastrarFilme(Filme filme, int id_user){
		bancoDados.Conecta();
		
		try {
			PreparedStatement pst = bancoDados.getConnection().prepareStatement("insert into filmes(id_user, nome_filme, ano_lancamento,"
					+ " descricao, palavras_chaves, genero, duracao_filme, diretor) values(?,?,?,?,?,?,?,?)");
			
			pst.setInt(1, id_user);
			pst.setString(2, filme.getNome());		
			pst.setString(3, filme.getData());
			pst.setString(4, filme.getDescricao());	
			pst.setString(5, filme.getWordKeys());	
			pst.setString(6, filme.getGenero());	
			pst.setString(7, filme.getDuracaoFilme());	
			pst.setString(8, filme.getDiretor());	
			pst.execute();
			
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Falha ao cadastrar filme:\n" + ex.getMessage() + 
					"\nEntre em contato com o administrador do sistema.", "Erro na busca", JOptionPane.ERROR_MESSAGE);
		} finally {
			bancoDados.Desconecta();
		}
	}	
	
	public ArrayList<Filme> BuscarFilmesUmUsuario(int id){
		bancoDados.Conecta();
		ArrayList<Filme> listFilm = new ArrayList<>();
		
		try {
			PreparedStatement pst = bancoDados.getConnection().prepareStatement("SELECT * FROM filmes WHERE id_user = ? ORDER BY nome_filme ASC");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();	
			
			while (rs.next()) {	
				Filme filme = new Filme();
				
				filme.setNome(rs.getString("nome_filme"));
				filme.setData(rs.getString("ano_lancamento"));
				filme.setDescricao(rs.getString("descricao"));
				filme.setWordKeys(rs.getString("palavras_chaves"));
				filme.setGenero(rs.getString("genero"));
				filme.setDuracaoFilme(rs.getString("duracao_filme"));
				filme.setDiretor(rs.getString("diretor"));
				filme.setPontos(rs.getLong("pontos_filme"));
				filme.setId_user(rs.getLong("id_user"));
				filme.setId_filme(rs.getLong("id_filme"));
				
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
	
	public ArrayList<Filme> BuscarFilmesTodosUsuarios(int id){
		bancoDados.Conecta();
		
		ArrayList<Filme> listFilm = new ArrayList<>();
		
		try {
			PreparedStatement pst = bancoDados.getConnection().prepareStatement("SELECT * FROM filmes ORDER BY nome_filme ASC");
			ResultSet rs = pst.executeQuery();	
			
			while (rs.next()) {	
				Filme filme = new Filme();
				
				filme.setNome(rs.getString("nome_filme"));
				filme.setData(rs.getString("ano_lancamento"));
				filme.setDescricao(rs.getString("descricao"));
				filme.setWordKeys(rs.getString("palavras_chaves"));
				filme.setGenero(rs.getString("genero"));
				filme.setDuracaoFilme(rs.getString("duracao_filme"));
				filme.setDiretor(rs.getString("diretor"));
				filme.setPontos(rs.getLong("pontos_filme"));
				filme.setId_user(rs.getLong("id_user"));
				filme.setId_filme(rs.getLong("id_filme"));
				
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
	
	public boolean DeletaFilme(Filme filme){
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
	
	public boolean AlteraFilme(Filme filme){
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
}
