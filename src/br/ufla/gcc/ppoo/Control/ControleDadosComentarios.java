package br.ufla.gcc.ppoo.control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.ufla.gcc.ppoo.bancoDeDados.BancoDeDados;
import br.ufla.gcc.ppoo.dados.Comentarios;
import br.ufla.gcc.ppoo.exceptions.BancoDadosException;
import br.ufla.gcc.ppoo.exceptions.ComentariosException;

public class ControleDadosComentarios {
	
	private static BancoDeDados bancoDeDados = new BancoDeDados();
	
	public static boolean cadastrarComentario(Comentarios comentario) throws BancoDadosException, ComentariosException{
		bancoDeDados.conecta();
		
		boolean status = false;
		
		try {
			PreparedStatement pst = bancoDeDados.getConnection().prepareStatement("insert into comentarios (coment, id_filme_commit, id_user_commit) values(?,?,?)");
			pst.setString(1, comentario.getCommit());
			pst.setLong(2, comentario.getId_filme_commit());		
			pst.setLong(3, comentario.getId_user_commit());
			pst.execute();
			
			status = true;
		} catch (SQLException sqle) {
			throw new ComentariosException("Não foi possível cadastrar seu comentário\n" + sqle.getMessage(), "Erro ao cadastrar comentário");
		} finally {
			bancoDeDados.desconecta();
		}
		
		return status;
	}	
	
	public static ArrayList<Comentarios> buscarAvaliacao(Long id_filme) throws BancoDadosException, ComentariosException{
		bancoDeDados.conecta();
		
		ArrayList<Comentarios> listCommits = new ArrayList<>();
		String commit;
		Long id_user_commit, id_filme_commit;
		
		try {
			PreparedStatement pst = bancoDeDados.getConnection().prepareStatement("SELECT * FROM comentarios WHERE id_filme_commit = ?");
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
			throw new ComentariosException("Não foi possível buscar os comentários desse filme\n" + sqle.getMessage(), "Falha ao buscar comentários");
		} finally {
			bancoDeDados.desconecta();
		}
				
		return listCommits;
	}
	
	public static boolean deletaComentariosFilme(Long id_filme) throws BancoDadosException, ComentariosException{
		bancoDeDados.conecta();
		
		boolean status = false;
		
		try {
			PreparedStatement pst = bancoDeDados.getConnection().prepareStatement("DELETE from comentarios where id_filme_commit = ?");
			pst.setLong(1, id_filme);
			pst.execute();
			pst.close();	
			
			status = true;
		} catch (SQLException sqle) {
			throw new ComentariosException("Não foi possível deletar os comentários desse filme\n" + sqle.getMessage(), "Falha ao deletar comentários");
		} finally {
			bancoDeDados.desconecta();
		}
		
		return status;
	}

}
