package br.ufla.gcc.ppoo.Dados;

public class DadosLogin {

	private String nome;
	private String email;
	private String senha;
	private Long id;
	
	public DadosLogin(String nome, String email, String senha, Long id) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.id = id;
	}
	
	public DadosLogin(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}
	
	public DadosLogin(DadosLogin dadosLogin) {
		this.setEmail(dadosLogin.getEmail());
		this.setSenha(dadosLogin.getSenha());
		this.setNome(dadosLogin.getNome());
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
