package br.ufla.gcc.ppoo.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import br.ufla.gcc.ppoo.BancoDeDados.BancoDeDados;
import br.ufla.gcc.ppoo.Control.ControleDadosFilmes;
import br.ufla.gcc.ppoo.Control.ControleDadosUsuarios;
import br.ufla.gcc.ppoo.Dados.DadosLogin;
import br.ufla.gcc.ppoo.Dados.Filme;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class TelaBuscarFilme {

	JFrame viewBuscarFilme;
	static boolean status = false;
	
//	public TelaBuscarFilme() { }
	
	public boolean getStatus() { 
		return status;
	}
	
	Filme filme = new Filme();
	BancoDeDados bancoDDados = new BancoDeDados();
	ControleDadosFilmes controlFilmes = new ControleDadosFilmes();
	ControleDadosUsuarios controlUser = new ControleDadosUsuarios();
	private JTextField textFieldBusca;
	
	public TelaBuscarFilme(DadosLogin dadosLogin){
		viewTelaBuscarFilme(dadosLogin);
	}
	
	public void viewTelaBuscarFilme(DadosLogin dadosLogin){
		
		viewBuscarFilme = new JFrame();
		viewBuscarFilme.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				status = false;
			}
		});
		viewBuscarFilme.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		viewBuscarFilme.setBackground(new Color(0, 0, 255));
		viewBuscarFilme.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		viewBuscarFilme.setVisible(false);
		viewBuscarFilme.getContentPane().setLayout(null);
		viewBuscarFilme.getContentPane().setBackground(new Color(51, 102, 153));
		viewBuscarFilme.getContentPane().setForeground(new Color(255, 255, 255));
		viewBuscarFilme.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		viewBuscarFilme.setTitle("Buscar Filmes");
		viewBuscarFilme.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		
		status = true;
		
		JLabel lblCadastro = new JLabel("Buscar Filmes");
		lblCadastro.setForeground(new Color(255, 255, 255));
		lblCadastro.setBounds(340, 15, 250, 55);
		lblCadastro.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastro.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 40));
		viewBuscarFilme.getContentPane().add(lblCadastro);
		
		JLabel lblNewItem = new JLabel("");
		lblNewItem.setIcon(new ImageIcon(TelaBuscarFilme.class.getResource("/br/ufla/gcc/ppoo/Imagens/icon-procurar-grande.png")));
		lblNewItem.setBackground(new Color(51, 51, 255));
		lblNewItem.setBounds(295, 20, 40, 40);
		lblNewItem.setVerticalAlignment(SwingConstants.TOP);
		
		viewBuscarFilme.getContentPane().add(lblNewItem);
		
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(TelaBuscarFilme.class.getResource("/br/ufla/gcc/ppoo/Imagens/btn-cancelar.png")));
		btnCancelar.setBounds(620, 520, 135, 30);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				viewBuscarFilme.dispose();
			}
		});
		btnCancelar.setForeground(new Color(0, 0, 0));
		btnCancelar.setToolTipText("Cancelar");
		btnCancelar.setBackground(new Color(255, 255, 255));
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 14));
		viewBuscarFilme.getContentPane().add(btnCancelar);
		
		JButton btnVisualizar = new JButton("Visualizar");
		btnVisualizar.setIcon(new ImageIcon(TelaBuscarFilme.class.getResource("/br/ufla/gcc/ppoo/Imagens/btn-ok.png")));
		btnVisualizar.setBounds(135, 520, 135, 30);
		btnVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DadosLogin dl = controlUser.buscarDados(dadosLogin.getEmail());
				
				
				
			}
		});
		btnVisualizar.setForeground(new Color(0, 0, 0));
		btnVisualizar.setToolTipText("Visualizar filme");
		btnVisualizar.setBackground(new Color(255, 255, 255));
		btnVisualizar.setFont(new Font("Arial", Font.PLAIN, 14));
		viewBuscarFilme.getContentPane().add(btnVisualizar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 175, 875, 320);
		viewBuscarFilme.getContentPane().add(scrollPane);
		
		JLabel label = new JLabel("Selecione um filme para realizar alguma a\u00E7\u00E3o:");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 20));
		label.setBounds(250, 150, 405, 25);
		viewBuscarFilme.getContentPane().add(label);
		
		textFieldBusca = new JTextField();
		textFieldBusca.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		textFieldBusca.setBounds(10, 85, 500, 45);
		viewBuscarFilme.getContentPane().add(textFieldBusca);
		textFieldBusca.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(545, 85, 115, 45);
		btnBuscar.setBackground(new Color(255, 255, 255));
		btnBuscar.setFont(new Font("Arial", Font.PLAIN, 14));
		viewBuscarFilme.getContentPane().add(btnBuscar);
		
		viewBuscarFilme.setResizable(false);
		viewBuscarFilme.setSize(900, 600);
		viewBuscarFilme.setVisible(true);		
	}
}

