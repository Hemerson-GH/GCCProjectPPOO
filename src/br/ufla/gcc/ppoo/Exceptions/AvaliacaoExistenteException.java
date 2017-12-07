package br.ufla.gcc.ppoo.Exceptions;

@SuppressWarnings("serial")
public class AvaliacaoExistenteException extends Exception{
	private String titulo;
	
	public AvaliacaoExistenteException(String nomeFilme, String titulo) {
		super("Ops... O Filme " + nomeFilme + " Já Foi Avaliado");
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}
