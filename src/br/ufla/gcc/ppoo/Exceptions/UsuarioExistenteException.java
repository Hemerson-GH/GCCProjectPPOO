package br.ufla.gcc.ppoo.Exceptions;

@SuppressWarnings("serial")
public class UsuarioExistenteException extends Exception {
	private String titulo;
	
	public UsuarioExistenteException(String email, String titulo) {
		super("Ops... o email " + '"'  + email + '"' + " j� est� sendo utilizado por outro usu�rio.\n Por favor, tente outro email.");
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}
