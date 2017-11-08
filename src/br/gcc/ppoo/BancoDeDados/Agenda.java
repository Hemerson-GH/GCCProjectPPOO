package br.gcc.ppoo.BancoDeDados;

public class Agenda {
	
	public static void main (String[] args){
		
		BancoDeDadosTeste bD = new BancoDeDadosTeste();
		
		bD.conectar();
		
		if (bD.estaConectado()) {
			bD.listarContatos();
//			bD.inserirContato("Paulo", "1234-5678");
//			bD.editarContato("1", "Paulo", "9999-8888");
//			bD.apagarContato("1");
			bD.desconectar();
		} else {
			System.out.println("Não foi possível conectar ao Banco de Dados!");
		}
	}

}
