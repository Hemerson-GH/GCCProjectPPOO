package br.ufla.gcc.ppoo.Control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.ufla.gcc.ppoo.BancoDeDados.BancoDeDados;
import br.ufla.gcc.ppoo.Dados.Comentarios;
import br.ufla.gcc.ppoo.Exceptions.BancoDadosException;
import br.ufla.gcc.ppoo.Exceptions.ComentariosException;

public class ControleDadosComentarios {
	
	private static BancoDeDados bancoDados = new BancoDeDados();
	
	public static boolean CadastrarComentario(Comentarios comentario) throws BancoDadosException, ComentariosException{
		bancoDados.Conecta();
		
		boolean status = false;
		
		try {
			PreparedStatement pst = bancoDados.getConnection().prepareStatement("insert into comentarios (coment, id_filme_commit, id_user_commit) values(?,?,?)");
			pst.setString(1, comentario.getCommit());
			pst.setLong(2, comentario.getId_filme_commit());		
			pst.setLong(3, comentario.getId_user_commit());
			pst.execute();
			
			status = true;
		} catch (SQLException sqle) {
			throw new ComentariosException("N�o foi poss�vel cadastrar seu coment�rio\n" + sqle.getMessage(), "Erro ao cadastrar coment�rio");
		} finally {
			bancoDados.Desconecta();
		}
		
		return status;
	}	
	
	public static ArrayList<Comentarios> BuscarAvaliacao(Long id_filme) throws BancoDadosException, ComentariosException{
		bancoDados.Conecta();
		
		ArrayList<Comentarios> listCommits = new ArrayList<>();
		String commit;
		Long id_user_commit, id_filme_commit;
		
		try {
			PreparedStatement pst = bancoDados.getConnection().prepareStatement("SELECT * FROM comentarios WHERE id_filme_commit = ?");
			pst.setLong(1, id_filme);
			ResultSet rs = pst.executeQuery();	
			
			while (rs.next()) {	
				commit = rs.getString("coment");
				id_filme_commit = rs.getLong("id_filme_commit");
				id_user_commit = rs.getLong("id_user_commit");
				
				Comentarios comentario = new Comentarios(commit, id_user_commit, id_filme_commit);
				listCommits.add(comentario);
			}
		} catch (SQLException sqle) {
			throw new ComentariosException("N�o foi poss�vel buscar os coment�rios desse filme\n" + sqle.getMessage(), "Falha ao buscar coment�rios");
		} finally {
			bancoDados.Desconecta();
		}
				
		return listCommits;
	}
	
	public static boolean DeletaComentariosFilme(Long id_filme) throws BancoDadosException, ComentariosException{
		bancoDados.Conecta();
		
		boolean status = false;
		
		try {
			PreparedStatement pst = bancoDados.getConnection().prepareStatement("DELETE from comentarios where id_filme_commit = ?");
			pst.setLong(1, id_filme);
			pst.execute();
			pst.close();	
			
			status = true;
		} catch (SQLException sqle) {
			throw new ComentariosException("N�o foi poss�vel deletar os coment�rios desse filme\n" + sqle.getMessage(), "Falha ao deletar coment�rios");
		} finally {
			bancoDados.Desconecta();
		}
		
		return status;
	}

}
