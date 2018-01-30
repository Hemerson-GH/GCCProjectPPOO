package br.ufla.gcc.ppoo.Control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ufla.gcc.ppoo.BancoDeDados.BancoDeDados;
import br.ufla.gcc.ppoo.Dados.Avaliacao;
import br.ufla.gcc.ppoo.Exceptions.AvaliacaoException;
import br.ufla.gcc.ppoo.Exceptions.BancoDadosException;

public class ControleDadosAvaliacao {
	
	private static BancoDeDados bancoDeDados = new BancoDeDados();
	
	public static boolean cadastrarAvaliacao(Avaliacao avaliacao) throws BancoDadosException, AvaliacaoException{
		bancoDeDados.conecta();
		
		boolean ok = false;
		
		try {
			PreparedStatement pst = bancoDeDados.getConnection().prepareStatement("insert into avaliation(id_user_avaliador, id_filme_avaliado, bool_avaliacao) values(?,?,?)");
			pst.setLong(1, avaliacao.getId_user());
			pst.setLong(2, avaliacao.getId_filme());		
			pst.setBoolean(3, avaliacao.getAvaliacao());
			pst.execute();
			
			ok = true;
		} catch (SQLException sqle) {
			throw new AvaliacaoException("Não foi possível cadastrar a sua avaliação\n" + sqle.getMessage(), "Erro ao cadastrar avaliação");
		} finally {
			bancoDeDados.desconecta();
		}
		return ok;
	}	
	
	public static boolean buscarAvaliacao(Long id, Long id_filme) throws AvaliacaoException, BancoDadosException{
		bancoDeDados.conecta();
		
		boolean ok = false;
		
		try {
			PreparedStatement pst = bancoDeDados.getConnection().prepareStatement("SELECT * FROM avaliation WHERE id_user_avaliador = ? and id_filme_avaliado = ?");
			pst.setLong(1, id);
			pst.setLong(2, id_filme);
			ResultSet rs = pst.executeQuery();	
			
			if (rs.next()) {	
				ok = true;
			}
		} catch (SQLException sqle) {
			throw new AvaliacaoException("Não foi possível buscar a avaliação desse usuário\n" + sqle.getMessage(), "Erro ao buscar avaliação");
		} finally {
			bancoDeDados.desconecta();
		}
				
		return ok;
	}
	
	public static boolean deletaAvaliacaoDoFllme(Long id_filme) throws BancoDadosException, AvaliacaoException{
		boolean encontrou = false;
		
		bancoDeDados.conecta();
		
		try {
			PreparedStatement pst = bancoDeDados.getConnection().prepareStatement("DELETE from avaliation where id_filme_avaliado = ?");
			pst.setLong(1, id_filme);
			pst.execute();
			pst.close();	
			
			encontrou = true;
		} catch (SQLException sqle) {
			throw new AvaliacaoException("Não foi possível deletar as avaliações desse filme\n" + sqle.getMessage(), "Erro ao deletar avaliação");
		} finally {
			bancoDeDados.desconecta();
		}
		
		return encontrou;
	}
	
}
