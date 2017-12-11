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
		
		boolean ok = false;
		
		try {
			PreparedStatement pst = bancoDados.getConnection().prepareStatement("insert into comentarios (coment, id_filme_commit, id_user_commit) values(?,?,?)");
			
			pst.setString(1, comentario.getCommit());
			pst.setLong(2, comentario.getId_filme_commit());		
			pst.setLong(3, comentario.getId_user_commit());
			pst.execute();
			ok = true;
			
		} catch (SQLException sqle) {
			throw new ComentariosException(sqle.getMessage(), "Erro Ao Cadastrar Filme");
		} finally {
			bancoDados.Desconecta();
		}
		
		return ok;
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
			throw new ComentariosException(sqle.getMessage(), "Falha ao buscar comentários");
		} finally {
			bancoDados.Desconecta();
		}
				
		return listCommits;
	}
	
	public static boolean DeletaComentariosFilme(Long id_filme) throws BancoDadosException, ComentariosException{
		boolean encontrou = false;
		
		bancoDados.Conecta();
		
		try {
			PreparedStatement pst = bancoDados.getConnection().prepareStatement("DELETE from comentarios where id_filme_commit = ?");
			
			pst.setLong(1, id_filme);
			pst.execute();
			pst.close();	
			
			encontrou = true;
		} catch (SQLException sqle) {
			throw new ComentariosException(sqle.getMessage(), "Falha ao deletar comentários");
		} finally {
			bancoDados.Desconecta();
		}
		
		return encontrou;
	}

}
