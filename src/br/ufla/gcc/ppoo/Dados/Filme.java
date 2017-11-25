package br.ufla.gcc.ppoo.Dados;

public class Filme {
	
	private String nome;
	private String data;
	private String descricao;
	private String wordKeys;
	private String genero;
	private String duracaoFilme;
	private String diretor;
	private Long pontos;
	
	public Filme(String nome, String data, String descricao, String wordKeys, String genero, String duracaoFilme, String diretor){
		this.nome = nome;
		this.data = data;
		this.descricao = descricao;
		this.wordKeys = wordKeys;
		this.genero = genero;
		this.duracaoFilme = duracaoFilme;
		this.diretor = diretor;
	}
	
	public Filme(Filme filme){
		this.setNome(filme.getNome());
		this.setData(filme.getData());
		this.setDescricao(filme.getDescricao());
		this.setWordKeys(filme.getWordKeys());
		this.setGenero(filme.getGenero());
		this.setDuracaoFilme(filme.getDuracaoFilme());
		this.setDiretor(filme.getDiretor());
	}
	
	public Filme(){
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getWordKeys() {
		return wordKeys;
	}

	public void setWordKeys(String wordKeys) {
		this.wordKeys = wordKeys;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getDuracaoFilme() {
		return duracaoFilme;
	}

	public void setDuracaoFilme(String duracaoFilme) {
		this.duracaoFilme = duracaoFilme;
	}

	public Long getPontos() {
		return pontos;
	}

	public void setPontos(Long pontos) {
		this.pontos = pontos;
	}

	public String getDiretor() {
		return diretor;
	}

	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}

}
