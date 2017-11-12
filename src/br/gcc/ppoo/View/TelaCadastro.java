package br.gcc.ppoo.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.gcc.ppoo.BancoDeDados.BancoDeDados;
import br.gcc.ppoo.Control.ControleDados;
import br.gcc.ppoo.Dados.DadosLogin;

public class TelaCadastro {
//	public class SecWind {

//	Create I created the JFrame variable as a global variable so I did not have to create every instant I was going to use
	JFrame myViewCadastro;

	private JPasswordField passwordField;
	private JPasswordField passwordFieldConfir;
	private JTextField textFieldNome;
	private JTextField textFieldEmail;
	DadosLogin dl = new DadosLogin();
	BancoDeDados bd = new BancoDeDados();
	ControleDados cd = new ControleDados();
	
//	Class calling my class that creates my JFrame
	public TelaCadastro() {
		bd.conexao();
		ViewMain();
	}
	
	public void ViewMain(){
		
//		Create my JFrame and I set it up
		JComponent.setDefaultLocale(Locale.ENGLISH);
		myViewCadastro = new JFrame();
		myViewCadastro.getContentPane().setBackground(new Color(51, 102, 153));
		myViewCadastro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myViewCadastro.setResizable(false);
		myViewCadastro.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		myViewCadastro.setTitle("Cadastro de novo usuário");
		myViewCadastro.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		myViewCadastro.getContentPane().setLayout(null);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmail.setBounds(25, 107, 50, 25);
		myViewCadastro.getContentPane().add(lblEmail);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setForeground(new Color(255, 255, 255));
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNome.setBounds(25, 60, 50, 25);
		myViewCadastro.getContentPane().add(lblNome);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setForeground(new Color(255, 255, 255));
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSenha.setBounds(25, 165, 60, 15);
		myViewCadastro.getContentPane().add(lblSenha);
		
		JLabel lblConfirSenha = new JLabel("Confirmar Senha:");
		lblConfirSenha.setForeground(new Color(255, 255, 255));
		lblConfirSenha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblConfirSenha.setBounds(220, 165, 125, 15);
		myViewCadastro.getContentPane().add(lblConfirSenha);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Digite sua senha...");
		passwordField.setBackground(new Color(255, 255, 255));
		passwordField.setBounds(80, 160, 125, 25);
		myViewCadastro.getContentPane().add(passwordField);
		
		passwordFieldConfir = new JPasswordField();
		passwordFieldConfir.setToolTipText("Digite a mesma senha digitada anteriormente...");
		passwordFieldConfir.setBackground(new Color(255, 255, 255));
		passwordFieldConfir.setBounds(345, 160, 125, 25);
		myViewCadastro.getContentPane().add(passwordFieldConfir);
		
		JLabel txtrCadastrarUsurio = new JLabel();
		txtrCadastrarUsurio.setToolTipText("Cadastro de novo usuário...");
		txtrCadastrarUsurio.setForeground(new Color(255, 255, 255));
		txtrCadastrarUsurio.setBackground(new Color(255, 255, 255));
		txtrCadastrarUsurio.setOpaque(false);
		txtrCadastrarUsurio.setFont(new Font("Arial", Font.BOLD, 18));
		txtrCadastrarUsurio.setText("Cadastrar usuário");
		txtrCadastrarUsurio.setBounds(165, 11, 176, 38);
		myViewCadastro.getContentPane().add(txtrCadastrarUsurio);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setToolTipText("Salvar usuário...");
		btnSalvar.setBackground(new Color(255, 255, 255));
		btnSalvar.setFont(new Font("Arial", Font.PLAIN, 14));
		
		btnSalvar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				
				dl.setNome(textFieldNome.getText());
				dl.setEmail(textFieldEmail.getText());
				
				if (passwordField.getText().equals(passwordFieldConfir.getText())) {
					dl.setSenha(passwordField.getText());
					System.out.println("senhas confere");
				}
				
				cd.salvar(dl);
				
				myViewCadastro.dispose();
				new TelaLogin();
				
			}
		});
		btnSalvar.setBounds(80, 210, 105, 35);
		myViewCadastro.getContentPane().add(btnSalvar);
		
		JButton btnCancelar = new JButton("Sair");
		btnCancelar.setToolTipText("Voltar para Tela de Login...");
		btnCancelar.setBackground(new Color(255, 255, 255));
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myViewCadastro.dispose();
				bd.desconecta();
				new TelaLogin();
			}
		});
		btnCancelar.setBounds(345, 210, 105, 35);
		myViewCadastro.getContentPane().add(btnCancelar);
		
		textFieldNome = new JTextField();
		textFieldNome.setToolTipText("Digite o seu nome...");
		textFieldNome.setBackground(new Color(255, 255, 255));
		textFieldNome.setColumns(10);
		textFieldNome.setBounds(80, 60, 390, 25);
		myViewCadastro.getContentPane().add(textFieldNome);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setToolTipText("Digite seu email...");
		textFieldEmail.setBackground(new Color(255, 255, 255));
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(80, 110, 390, 25);
		myViewCadastro.getContentPane().add(textFieldEmail);
		
		JLabel fundoCadastro = new JLabel("");
		fundoCadastro.setIcon(new ImageIcon(TelaCadastro.class.getResource("/br/gcc/ppoo/Imagens/xfce-teal.jpg")));
		fundoCadastro.setBounds(0, 421, 116, 84);
//		myViewCadastro.getContentPane().add(fundoCadastro);
		
//		More configuration of JFrame
		myViewCadastro.setSize(500, 300);
		myViewCadastro.setVisible(true);
		myViewCadastro.setResizable(false);
	}
}	

