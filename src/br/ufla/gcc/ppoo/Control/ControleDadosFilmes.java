package br.ufla.gcc.ppoo.Control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.ufla.gcc.ppoo.BancoDeDados.BancoDeDados;
import br.ufla.gcc.ppoo.Dados.Filme;

public class ControleDadosFilmes {
	
	BancoDeDados bancoDados = new BancoDeDados();
	
	public void CadastrarFilme(Filme filme, int id_user){
		bancoDados.Conecta();
		
		try {
			PreparedStatement pst = bancoDados.connection.prepareStatement("insert into filmes(id_user,nome_filme,ano_lancamento,"
					+ "descricao,palavras_chaves,genero,duracao_filme, diretor) values(?,?,?,?,?,?,?,?)");
			
			pst.setInt(1, id_user);
			pst.setString(2, filme.getNome());		
			pst.setString(3, filme.getData());
			pst.setString(4, filme.getDescricao());	
			pst.setString(5, filme.getWordKeys());	
			pst.setString(6, filme.getGenero());	
			pst.setString(7, filme.getDuracaoFilme());	
			pst.setString(9, filme.getDiretor());	
			pst.execute();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		bancoDados.Desconecta();
	}
	
	Filme findFilme = new Filme(null, null, null, null, null, null, null);
	
	public Filme buscarDados(String email){
		bancoDados.Conecta();	
		
		try {
			PreparedStatement pst = bancoDados.connection.prepareStatement("SELECT * FROM filmes Where email = '" + email +"'");
			ResultSet rs = pst.executeQuery();	
			
			while (rs.next()) {				
				findFilme.setNome(rs.getString("nome_filme"));
				findFilme.setData(rs.getString("ano_lancamento"));
				findFilme.setDescricao("descricao");
				findFilme.setWordKeys("palavras_chaves");
				findFilme.setGenero("genero");
				findFilme.setDuracaoFilme("duracao_filme");
				findFilme.setDiretor("diretor");
			    JOptionPane.showMessageDialog(null, "procura ok");
			}
			
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro Ao Buscar email: \n " + ex);
			ex.printStackTrace();
		}
		
		bancoDados.Desconecta();
		return findFilme;
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
