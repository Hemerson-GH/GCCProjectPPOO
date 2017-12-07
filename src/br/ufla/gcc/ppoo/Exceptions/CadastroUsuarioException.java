package br.ufla.gcc.ppoo.Exceptions;

@SuppressWarnings("serial")
public class CadastroUsuarioException extends Exception {
	private String titulo;
	
	public CadastroUsuarioException(String msg, String titulo) {
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
