package br.ufla.gcc.ppoo.Control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.ufla.gcc.ppoo.BancoDeDados.BancoDeDados;
import br.ufla.gcc.ppoo.Dados.Avaliacao;
import br.ufla.gcc.ppoo.Exceptions.BancoDadosException;
import br.ufla.gcc.ppoo.Exceptions.ConexaoBD;

public class ControleDadosAvaliacao {
	
	private static BancoDeDados bancoDados = new BancoDeDados();
	
	public static boolean CadastrarAvaliacao(Avaliacao avaliacao) throws ConexaoBD, BancoDadosException{
		bancoDados.Conecta();
		boolean ok = false;
		try {
			PreparedStatement pst = bancoDados.getConnection().prepareStatement("insert into avaliation(id_user_avaliador, id_filme_avaliado, bool_avaliacao) values(?,?,?)");
			
			pst.setLong(1, avaliacao.getId_user());
			pst.setLong(2, avaliacao.getId_filme());		
			pst.setBoolean(3, avaliacao.getAvaliacao());
			pst.execute();
			
			ok = true;
		} catch (SQLException ex) {
			throw new BancoDadosException("Não foi possível salvar a avaliação, por favor entre em contato com o administrador do sistema", "Erro ao cadastrar");
		} finally {
			bancoDados.Desconecta();
		}
		return ok;
	}	
	
	public static ArrayList<Avaliacao> BuscarAvaliacao(Long id) throws ConexaoBD, BancoDadosException{
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
			throw new BancoDadosException("Não foi possível buscar avaliação desse filme, por favor entre em contato com o administrador do sistema",
																															"Erro ao buscar avaliação");
		} finally {
			bancoDados.Desconecta();
		}
				
		return listAvaliacao;
	}
	
	public static boolean DeletaFilme(Long id_filme) throws ConexaoBD, BancoDadosException{
		boolean encontrou = false;
		
		bancoDados.Conecta();
		
		try {
			PreparedStatement pst = bancoDados.getConnection().prepareStatement("DELETE from avaliation where id_filme_avaliado = ?");
			
			pst.setLong(1, id_filme);
			pst.execute();
			pst.close();	
			
			encontrou = true;
		} catch (SQLException ex) {
			throw new BancoDadosException("Não foi possível deletar a avaliação desse filme, por favor entre em contato com o administrador do sistema",
					"Erro ao deletar avaliação");
		} finally {
			bancoDados.Desconecta();
		}
		
		return encontrou;
	}

}
