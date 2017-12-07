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
			throw new AvaliacaoException(sqle.getMessage(), "Erro Ao Cadastrar Avaliação");
		} finally {
			bancoDados.Desconecta();
		}
		return ok;
	}	
	
	public static boolean BuscarAvaliacao(Long id, Long id_filme) throws AvaliacaoException, BancoDadosException{
//		public static ArrayList<Avaliacao> BuscarAvaliacao(Long id) throws AvaliacaoException, BancoDadosException{
		bancoDados.Conecta();
//		ArrayList<Avaliacao> listAvaliacao = new ArrayList<>();
//		Long idReceived, id_filmeReceived;
		boolean ok = false;
		
		try {
			PreparedStatement pst = bancoDados.getConnection().prepareStatement("SELECT * FROM avaliation WHERE id_user_avaliador = ? and id_filme_avaliado");
			pst.setLong(1, id);
			pst.setLong(2, id_filme);
			ResultSet rs = pst.executeQuery();	
			
			if (rs.next()) {	
				
//				idReceived = rs.getLong("id_user_avaliador");
//				id_filmeReceived = rs.getLong("id_filme_avaliado");
//				avaliacaoBool = rs.getBoolean("bool_avaliacao");
//				
//				Avaliacao avaliacao = new Avaliacao(idReceived, id_filmeReceived, avaliacaoBool);
//				
//				listAvaliacao.add(avaliacao);
				ok = true;
			}
		} catch (SQLException sqle) {
			throw new AvaliacaoException(sqle.getMessage(), "Erro Ao Buscar Avaliação");
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
			throw new AvaliacaoException(sqle.getMessage(), "Erro Ao Deletar Avaliação");
		} finally {
			bancoDados.Desconecta();
		}
		
		return encontrou;
	}

}
