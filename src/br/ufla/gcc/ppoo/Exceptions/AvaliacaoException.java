package br.ufla.gcc.ppoo.Exceptions;

@SuppressWarnings("serial")
public class AvaliacaoException extends Exception {
	
	private String titulo;
	
	public AvaliacaoException(String msg, String titulo) {
		super(msg);	
		this.titulo = titulo;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
