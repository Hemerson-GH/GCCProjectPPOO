package br.ufla.gcc.ppoo.exceptions;

@SuppressWarnings("serial")
public class BancoDadosException extends Exception {
	
	private String titulo;
	
	public BancoDadosException(String msg, String titulo) {
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
