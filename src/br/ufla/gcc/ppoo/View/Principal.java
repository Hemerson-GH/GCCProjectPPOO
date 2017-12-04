package br.ufla.gcc.ppoo.View;

import br.ufla.gcc.ppoo.Dados.DadosLogin;

public class Principal {

	public static void main(String[] args) {
//		new TelaLogin();
		DadosLogin dl = new DadosLogin("hemerson", "hemerson@gmail.com", "seagate01");
		new TelaPrincipal(dl);
	}

}
