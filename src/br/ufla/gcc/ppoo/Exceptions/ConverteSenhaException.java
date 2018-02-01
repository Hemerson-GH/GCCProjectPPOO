package br.ufla.gcc.ppoo.exceptions;

@SuppressWarnings("serial")
public class ConverteSenhaException extends Exception{
	
	private String titulo;
	
	public ConverteSenhaException(String msg, String titulo) {
		super("Ops... não foi possível converte a sua senha.\nErro: " + msg);
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}
