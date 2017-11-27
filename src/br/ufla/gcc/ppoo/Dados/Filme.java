package br.ufla.gcc.ppoo.Dados;

import java.util.ArrayList;

public class Filme {
	
	private String nome;
	private String data;
	private String descricao;
	private String wordKeys;
	private String genero;
	private String duracaoFilme;
	private String diretor;
	private Long pontos;
	private Long id_user;
	private Long id_filme;
	
	public Filme(String nome, String data, String descricao, String wordKeys, String genero, String duracaoFilme, String diretor, Long pontos, Long id_user, Long id_filme){
		this.nome = nome;
		this.data = data;
		this.descricao = descricao;
		this.wordKeys = wordKeys;
		this.genero = genero;
		this.duracaoFilme = duracaoFilme;
		this.diretor = diretor;
		this.pontos = pontos;
		this.id_filme = id_user;
		this.id_filme = id_filme;
	}
	
	public Filme(Filme filme){
		this.setNome(filme.getNome());
		this.setData(filme.getData());
		this.setDescricao(filme.getDescricao());
		this.setWordKeys(filme.getWordKeys());
		this.setGenero(filme.getGenero());
		this.setDuracaoFilme(filme.getDuracaoFilme());
		this.setDiretor(filme.getDiretor());
		this.setPontos(filme.getPontos());
		this.setId_filme(filme.getId_filme());
		this.setId_user(filme.getId_user());
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

	public Long getId_user() {
		return id_user;
	}

	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}

	public Long getId_filme() {
		return id_filme;
	}

	public void setId_filme(Long id_filme) {
		this.id_filme = id_filme;
	}
	
	public Filme comparaFilme(ArrayList<Filme> listFilmes, String nomeFilme){
		Filme itemFilme = new Filme();
		
		for (Filme filme : listFilmes) {
			if (filme.getNome().equals(nomeFilme)) {
				itemFilme = filme;
			}
		}
		return itemFilme;
	}
	
	public boolean comparaFilme(ArrayList<Filme> listFilmes, String nomeFilme, String filmeContido){		
		for (Filme filme : listFilmes) {
			if (filme.getNome().equals(nomeFilme) && !filme.getNome().equals(filmeContido)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean comparaFilmeBoolean(ArrayList<Filme> listFilmes, String nomeFilme){
		
		for (Filme filme : listFilmes) {
			if (filme.getNome().equals(nomeFilme)) {
				return true;
			}
		}
		return false;
	}
}
