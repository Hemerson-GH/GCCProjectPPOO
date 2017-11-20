package br.ufla.gcc.ppoo.Dados;

public class Filme {
	
	private String nome;
	private String data;
	private String descricao;
	private String wordKeys;
	private String genero;
	private String duracaoFilme;

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

}
