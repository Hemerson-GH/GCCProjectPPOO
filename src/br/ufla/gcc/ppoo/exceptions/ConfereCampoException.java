package br.ufla.gcc.ppoo.exceptions;

@SuppressWarnings("serial")
public class ConfereCampoException extends Exception {
	
	private String titulo;
	
	public ConfereCampoException(String msg, String titulo) {
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
