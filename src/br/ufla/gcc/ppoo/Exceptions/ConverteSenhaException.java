package br.ufla.gcc.ppoo.Exceptions;

@SuppressWarnings("serial")
public class ConverteSenhaException extends Exception{
	private String titulo;
	
	public ConverteSenhaException(String msg, String titulo) {
		super("Ops... N�o Foi Poss�vel Converte A Sua Senha.\nErro: " + msg);
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}
