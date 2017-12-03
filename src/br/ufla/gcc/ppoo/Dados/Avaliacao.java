package br.ufla.gcc.ppoo.Dados;

import java.util.ArrayList;

public class Avaliacao {
	
	private Long id_user;
	private Long id_filme;
	private boolean avaliacao = false;
	
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

	public boolean getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(boolean avaliacao) {
		this.avaliacao = avaliacao;
	}
	
	public Avaliacao(Long id_user, Long id_filme, boolean avaliacao) {
		this.id_user = id_user;
		this.id_filme = id_filme;
		this.avaliacao = avaliacao;
	}
	
	public static boolean confereAvaliacao(ArrayList<Avaliacao> listAvaliacao, Filme filme, DadosLogin dadosLogin) {
		
		for (Avaliacao avaliacao : listAvaliacao) {
			if (avaliacao.getId_filme().equals(filme.getId_filme()) && avaliacao.getId_user().equals(dadosLogin.getId()) && avaliacao.getAvaliacao() == true) {
				return true;
			}
		}

		return false;
	}

}
