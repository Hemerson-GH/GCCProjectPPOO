package br.ufla.gcc.ppoo.View;

import br.ufla.gcc.ppoo.Dados.DadosLogin;

public class Principal {

	public static void main(String[] args) {
		DadosLogin dl = new DadosLogin("teste", "teste@teste", "12345");
		new TelaPrincipal(dl);
	}

}
