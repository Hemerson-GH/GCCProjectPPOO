package br.ufla.gcc.ppoo.View;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;

import br.ufla.gcc.ppoo.Dados.DadosLogin;

public class TelaVisualizarFilmes {
	
	JFrame viewListagem;
	
	public TelaVisualizarFilmes(DadosLogin dadosLogin){
		viewListagemDeFilmes(dadosLogin);
	}
	
	public void viewListagemDeFilmes(DadosLogin dadosLogin) {
		
		viewListagem = new JFrame();
		viewListagem.setBackground(new Color(0, 0, 255));
		viewListagem.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		viewListagem.setVisible(false);
		viewListagem.getContentPane().setLayout(null);
		viewListagem.getContentPane().setBackground(new Color(51, 102, 153));
		viewListagem.getContentPane().setForeground(new Color(255, 255, 255));
		viewListagem.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		viewListagem.setTitle("Cadastrar Filme");
		viewListagem.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
	}
}
