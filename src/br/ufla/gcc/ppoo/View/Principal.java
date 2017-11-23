package br.ufla.gcc.ppoo.View;

import br.ufla.gcc.ppoo.Dados.DadosLogin;

public class Principal {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
//		TelaLogin tl = new TelaLogin();
		DadosLogin dl = new DadosLogin("teste", "teste@teste", "12345");
		TelaPrincipal tl = new TelaPrincipal(dl);
	}

}
