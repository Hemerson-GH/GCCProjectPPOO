package br.ufla.gcc.ppoo.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.ufla.gcc.ppoo.BancoDeDados.BancoDeDados;
import br.ufla.gcc.ppoo.Control.ControleDadosUsuarios;
import br.ufla.gcc.ppoo.Dados.DadosLogin;

public class TelaLogin {
	
	JFrame myViewLogin;
	
	private JTextField textAreaUser;
	private JPasswordField passwordField;
	private JTextField txtNovoUsurio;
	BancoDeDados bancoDDados = new BancoDeDados();
	ControleDadosUsuarios controlDDados = new ControleDadosUsuarios();

	public TelaLogin() {
		bancoDDados.Conecta();
		View();
	}

	public void View(){

		myViewLogin = new JFrame();
		myViewLogin.getContentPane().setBackground(new Color(51, 102, 153));
		myViewLogin.getContentPane().setForeground(new Color(255, 255, 255));
		myViewLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myViewLogin.setResizable(false);
		myViewLogin.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		myViewLogin.setTitle("Login");
		myViewLogin.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		myViewLogin.getContentPane().setLayout(null);
		
		textAreaUser = new JTextField();
		textAreaUser.setToolTipText("Digite seu email...");
		textAreaUser.setBackground(new Color(255, 255, 255));
		textAreaUser.setBounds(90, 57, 340, 25);
		myViewLogin.getContentPane().add(textAreaUser);
		textAreaUser.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Digite sua senha...");
		passwordField.setBackground(new Color(255, 255, 255));
		passwordField.setBounds(90, 111, 220, 25);
		myViewLogin.getContentPane().add(passwordField);
		
		JButton btnEnter = new JButton("Sing in");
		btnEnter.setForeground(new Color(0, 0, 0));
		btnEnter.setToolTipText("Entrar");
		btnEnter.setFont(new Font("Arial", Font.PLAIN, 14));
		btnEnter.setBackground(new Color(255, 255, 255));
		btnEnter.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				
				DadosLogin dadosLogin = new DadosLogin(controlDDados.BuscarDados(textAreaUser.getText()));
				
				if (passwordField.getText().equals(dadosLogin.getSenha())) {			
					myViewLogin.dispose();
					new TelaPrincipal(dadosLogin);
				} else {					
					JOptionPane.showMessageDialog(null, " Usuário e/ou senha errada ...", "Usuário invalidos", JOptionPane.ERROR_MESSAGE);
				}				
			}
		});
		btnEnter.setBounds(90, 160, 90, 25);
		myViewLogin.getContentPane().add(btnEnter);
		
		JButton btnCancel = new JButton("Exit");
		btnCancel.setToolTipText("Sair");
		btnCancel.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCancel.setBackground(new Color(255, 255, 255));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myViewLogin.dispose();
				bancoDDados.Desconecta();
			}
		});
		btnCancel.setBounds(320, 160, 90, 25);
		myViewLogin.getContentPane().add(btnCancel);
		
		txtNovoUsurio = new JTextField();
		txtNovoUsurio.setForeground(new Color(255, 255, 255));
		txtNovoUsurio.setFont(new Font("Arial", Font.ITALIC, 14));
		txtNovoUsurio.setBackground(new Color(0, 128, 128));
		txtNovoUsurio.setToolTipText("Clique aqui para cadastrar um novo usuário");
		txtNovoUsurio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				@SuppressWarnings("unused")
				TelaCadastroUsuario telaCadas = new TelaCadastroUsuario();
				myViewLogin.dispose();
			}
		});
		txtNovoUsurio.setEditable(false);
		txtNovoUsurio.setText("Novo usuário");
		txtNovoUsurio.setBounds(335, 113, 95, 20);
		myViewLogin.getContentPane().add(txtNovoUsurio);
//		txtNovoUsurio.setColumns(10);
		txtNovoUsurio.setOpaque(false);
		txtNovoUsurio.setBorder(null);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setForeground(new Color(255, 255, 255));
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSenha.setBounds(40, 111, 50, 20);
		myViewLogin.getContentPane().add(lblSenha);
		
		JLabel lblLogin = new JLabel("Email:");
		lblLogin.setForeground(new Color(255, 255, 255));
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLogin.setBounds(48, 56, 45, 25);
		myViewLogin.getContentPane().add(lblLogin);
		
		JLabel lblAutenticao = new JLabel("Autenticar usuário ");
		lblAutenticao.setBackground(new Color(51, 204, 102));
		lblAutenticao.setFont(new Font("Arial", Font.BOLD, 18));
		lblAutenticao.setForeground(new Color(255, 255, 255));
		lblAutenticao.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutenticao.setBounds(125, 10, 225, 35);
//		lblAutenticao.setBorder(true);
		myViewLogin.getContentPane().add(lblAutenticao);
		
//		JLabel llbFundo = new JLabel("");
//		llbFundo.setIcon(new ImageIcon(TelaLogin.class.getResource("/br/gcc/ppoo/Imagens/xfce-blue.jpg")));
//		llbFundo.setBounds(0, 0, 477, 211);
//		myViewLogin.getContentPane().add(llbFundo); // adicionar caso for colocar papel de parede
		
//		myViewLogin.setLocationRelativeTo(null);
		myViewLogin.setSize(485, 240);
		myViewLogin.setVisible(true);
		myViewLogin.setResizable(false);
	}
}	

