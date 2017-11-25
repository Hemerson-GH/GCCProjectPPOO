package br.ufla.gcc.ppoo.View;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;

import br.ufla.gcc.ppoo.Dados.DadosLogin;

public class TelaVisualizarFilmes {
	
	JFrame viewCadastroFilme;
	
	public TelaVisualizarFilmes(DadosLogin dadosLogin){
		viewListagemDeFilmes(dadosLogin);
	}
	
	public void viewListagemDeFilmes(DadosLogin dadosLogin) {
		
		viewCadastroFilme = new JFrame();
		viewCadastroFilme.setBackground(new Color(0, 0, 255));
		viewCadastroFilme.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		viewCadastroFilme.setVisible(false);
		viewCadastroFilme.getContentPane().setLayout(null);
		viewCadastroFilme.getContentPane().setBackground(new Color(51, 102, 153));
		viewCadastroFilme.getContentPane().setForeground(new Color(255, 255, 255));
		viewCadastroFilme.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		viewCadastroFilme.setTitle("Cadastrar Filme");
		viewCadastroFilme.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
	}
}
