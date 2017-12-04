package br.ufla.gcc.ppoo.Dados;

public class Comentarios {
	
	private String commit;
	private Long id_user_commit;
	private Long id_filme_commit;
	
	public String getCommit() {
		return commit;
	}
	
	public void setCommit(String commit) {
		this.commit = commit;
	}
	
	public Long getId_user_commit() {
		return id_user_commit;
	}
	
	public void setId_user_commit(Long id_user_commit) {
		this.id_user_commit = id_user_commit;
	}
	
	public Long getId_filme_commit() {
		return id_filme_commit;
	}
	
	public void setId_filme_commit(Long id_filme_commit) {
		this.id_filme_commit = id_filme_commit;
	}
	
	public Comentarios(String commit, Long id_user_commit, Long id_filme_commit) {
		this.commit = commit;
		this.id_user_commit = id_user_commit;
		this.id_filme_commit = id_filme_commit;
	}
}
