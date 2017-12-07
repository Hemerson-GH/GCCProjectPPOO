package br.ufla.gcc.ppoo.Exceptions;

@SuppressWarnings("serial")
public class BancoDadosException extends Exception {
	
	private String titulo;
	
	public BancoDadosException(String msg, String titulo) {
		super("Erro: " + msg + "\nEntre em contato com o administrador do sistema.");	
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}
