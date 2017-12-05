package br.ufla.gcc.ppoo.Exceptions;

@SuppressWarnings("serial")
public class FilmeExistenteException extends Exception {
	
	private String titulo;
	
	public FilmeExistenteException(String nomeFilme, String titulo) {
		super("Ops.. O Filme " + nomeFilme + " Já Foi Cadastrado");
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}
