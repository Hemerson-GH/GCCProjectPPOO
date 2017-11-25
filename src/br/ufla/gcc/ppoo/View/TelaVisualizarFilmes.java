package br.ufla.gcc.ppoo.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import br.ufla.gcc.ppoo.Dados.DadosLogin;

public class TelaVisualizarFilmes {
	
	JFrame viewListagem;
	static boolean status = false;
	
	public boolean getStatus() { 
		return status;
	}
	
	public TelaVisualizarFilmes(DadosLogin dadosLogin){
		viewListagemDeFilmes(dadosLogin);
	}
	
//	public TelaVisualizarFilmes() { }
	
	public void viewListagemDeFilmes(DadosLogin dadosLogin) {
		
		viewListagem = new JFrame();
		viewListagem.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		viewListagem.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				status = false;
			}
		});
		viewListagem.setBackground(new Color(0, 0, 255));
		viewListagem.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		viewListagem.setVisible(true);
		viewListagem.getContentPane().setLayout(null);
		viewListagem.getContentPane().setBackground(new Color(51, 102, 153));
		viewListagem.getContentPane().setForeground(new Color(255, 255, 255));
		viewListagem.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		viewListagem.setTitle("Cadastrar Filme");
		viewListagem.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		
		status = true;
		
		viewListagem.setResizable(false);
		viewListagem.setSize(600, 520);
		viewListagem.setVisible(true);		
	}
}
