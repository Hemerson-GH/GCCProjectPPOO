package br.ufla.gcc.ppoo.Control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ufla.gcc.ppoo.BancoDeDados.BancoDeDados;
import br.ufla.gcc.ppoo.Dados.Avaliacao;
import br.ufla.gcc.ppoo.Exceptions.AvaliacaoException;
import br.ufla.gcc.ppoo.Exceptions.BancoDadosException;

public class ControleDadosAvaliacao {
	
	private static BancoDeDados bancoDados = new BancoDeDados();
	
	public static boolean CadastrarAvaliacao(Avaliacao avaliacao) throws BancoDadosException, AvaliacaoException{
		bancoDados.Conecta();
		
		boolean ok = false;
		
		try {
			PreparedStatement pst = bancoDados.getConnection().prepareStatement("insert into avaliation(id_user_avaliador, id_filme_avaliado, bool_avaliacao) values(?,?,?)");
			pst.setLong(1, avaliacao.getId_user());
			pst.setLong(2, avaliacao.getId_filme());		
			pst.setBoolean(3, avaliacao.getAvaliacao());
			pst.execute();
			
			ok = true;
		} catch (SQLException sqle) {
			throw new AvaliacaoException("N�o foi poss�vel cadastrar a sua avalia��o\n" + sqle.getMessage(), "Erro ao cadastrar avalia��o");
		} finally {
			bancoDados.Desconecta();
		}
		return ok;
	}	
	
	public static boolean BuscarAvaliacao(Long id, Long id_filme) throws AvaliacaoException, BancoDadosException{
		bancoDados.Conecta();
		
		boolean ok = false;
		
		try {
			PreparedStatement pst = bancoDados.getConnection().prepareStatement("SELECT * FROM avaliation WHERE id_user_avaliador = ? and id_filme_avaliado = ?");
			pst.setLong(1, id);
			pst.setLong(2, id_filme);
			ResultSet rs = pst.executeQuery();	
			
			if (rs.next()) {	
				ok = true;
			}
		} catch (SQLException sqle) {
			throw new AvaliacaoException("N�o foi poss�vel buscar a avalia��o desse usu�rio\n" + sqle.getMessage(), "Erro ao buscar avalia��o");
		} finally {
			bancoDados.Desconecta();
		}
				
		return ok;
	}
	
	public static boolean DeletaAvaliacaoDoFllme(Long id_filme) throws BancoDadosException, AvaliacaoException{
		boolean encontrou = false;
		
		bancoDados.Conecta();
		
		try {
			PreparedStatement pst = bancoDados.getConnection().prepareStatement("DELETE from avaliation where id_filme_avaliado = ?");
			pst.setLong(1, id_filme);
			pst.execute();
			pst.close();	
			
			encontrou = true;
		} catch (SQLException sqle) {
			throw new AvaliacaoException("N�o foi poss�vel deletar as avalia��es desse filme\n" + sqle.getMessage(), "Erro ao deletar avalia��o");
		} finally {
			bancoDados.Desconecta();
		}
		
		return encontrou;
	}
	
}
