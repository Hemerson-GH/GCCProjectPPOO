package br.ufla.gcc.ppoo.Control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.ufla.gcc.ppoo.BancoDeDados.BancoDeDados;
import br.ufla.gcc.ppoo.Dados.Avaliacao;

public class ControleDadosAvaliacao {
	
private static BancoDeDados bancoDados = new BancoDeDados();
	
	public static void CadastrarAvaliacao(Avaliacao avaliacao){
		bancoDados.Conecta();
		
		try {
			PreparedStatement pst = bancoDados.getConnection().prepareStatement("insert into avaliation(id_user_avaliador, id_filme_avaliado, bool_avaliacao) values(?,?,?)");
			
			pst.setLong(1, avaliacao.getId_user());
			pst.setLong(2, avaliacao.getId_filme());		
			pst.setBoolean(3, avaliacao.getAvaliacao());
			pst.execute();
			
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Falha ao salvar avaliação:\n" + ex.getMessage() + 
					"\nEntre em contato com o administrador do sistema.", "Falha na avaliação", JOptionPane.ERROR_MESSAGE);
		} finally {
			bancoDados.Desconecta();
		}
	}	
	
	public static ArrayList<Avaliacao> BuscarAvaliacao(Long id){
		bancoDados.Conecta();
		ArrayList<Avaliacao> listAvaliacao = new ArrayList<>();
		Long idReceived, id_filmeReceived;
		boolean avaliacaoBool;
		
		try {
			PreparedStatement pst = bancoDados.getConnection().prepareStatement("SELECT * FROM avaliation WHERE id_user_avaliador = ?");
			pst.setLong(1, id);
			ResultSet rs = pst.executeQuery();	
			
			while (rs.next()) {	
				
				idReceived = rs.getLong("id_user_avaliador");
				id_filmeReceived = rs.getLong("id_filme_avaliado");
				avaliacaoBool = rs.getBoolean("bool_avaliacao");
				
				Avaliacao avaliacao = new Avaliacao(idReceived, id_filmeReceived, avaliacaoBool);
				
				listAvaliacao.add(avaliacao);
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Falha ao buscar avaliação do filme:\n" + ex.getMessage() + 
					"\nEntre em contato com o administrador do sistema.",  "Falha na avaliação", JOptionPane.ERROR_MESSAGE);
		} finally {
			bancoDados.Desconecta();
		}
				
		return listAvaliacao;
	}
	
	public static boolean DeletaFilme(Long id_filme){
		boolean encontrou = false;
		
		bancoDados.Conecta();
		
		try {
			PreparedStatement pst = bancoDados.getConnection().prepareStatement("DELETE from avaliation where id_filme_avaliado = ?");
			
			pst.setLong(1, id_filme);
			pst.execute();
			pst.close();	
			
			encontrou = true;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao deletar avaliação selecionado:\n" + ex.getMessage() + 
					"\nEntre em contato com o administrador do sistema.", "Falha ao deletar avaliações", JOptionPane.ERROR_MESSAGE);
		} finally {
			bancoDados.Desconecta();
		}
		
		return encontrou;
	}

}
