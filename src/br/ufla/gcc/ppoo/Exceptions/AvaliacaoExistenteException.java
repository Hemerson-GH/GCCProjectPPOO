package br.ufla.gcc.ppoo.exceptions;

@SuppressWarnings("serial")
public class AvaliacaoExistenteException extends Exception{
	
	private String titulo;
	
	public AvaliacaoExistenteException(String nomeFilme, String titulo) {
		super("Ops... você já avaliou o filme " + nomeFilme);
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}
