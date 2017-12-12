package br.ufla.gcc.ppoo.Exceptions;

@SuppressWarnings("serial")
public class FilmesException extends Exception{
	private String titulo;
	
	public FilmesException(String msg, String titulo) {
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
