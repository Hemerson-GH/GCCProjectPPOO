package br.ufla.gcc.ppoo.exceptions;

@SuppressWarnings("serial")
public class UsuarioException extends Exception {
	
	private String titulo;
	
	public UsuarioException(String msg, String titulo) {
		super("Erro: " + msg + "\nEntre em contato com o Administrador do Sistema");	
		this.titulo = titulo;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}
