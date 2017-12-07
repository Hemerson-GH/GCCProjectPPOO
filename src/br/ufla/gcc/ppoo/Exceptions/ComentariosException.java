package br.ufla.gcc.ppoo.Exceptions;

@SuppressWarnings("serial")
public class ComentariosException extends Exception{
	private String titulo;
	
	public ComentariosException(String msg, String titulo) {
		super(msg + "\nEntre Em Contato Com O Administrador Do Sistema.");	
		this.titulo = titulo;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}
