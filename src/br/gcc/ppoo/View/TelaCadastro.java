package br.gcc.ppoo.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

import javax.swing.DefaultListModel;
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
	
	@SuppressWarnings("rawtypes")
	static DefaultListModel listmetodosSec;

	@SuppressWarnings("rawtypes")
	DefaultListModel listMethodsCalled;
	int contMethods = 0;
	@SuppressWarnings("rawtypes")
	static Map<Integer, DefaultListModel> methodsCalleds;
	ArrayList<Integer> listQtdMet = new ArrayList<Integer> ();
	int qtdMetCall = 0, qtdMet = 0;
	int qtdMethodsJTA;
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

	//Class create my JFrame
	@SuppressWarnings({ "rawtypes"})
	public void ViewMain(){
		
//		Create my JFrame and I set it up
		JComponent.setDefaultLocale(Locale.ENGLISH);
		myViewCadastro = new JFrame();
		myViewCadastro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myViewCadastro.setResizable(false);
		myViewCadastro.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		myViewCadastro.setTitle("Cadastro de usuário");
		myViewCadastro.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		myViewCadastro.getContentPane().setLayout(null);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmail.setBounds(26, 206, 46, 23);
		myViewCadastro.getContentPane().add(lblEmail);
		
		JLabel mais = new JLabel("");
		mais.setIcon(new ImageIcon(TelaCadastro.class.getResource("/br/gcc/ppoo/Imagens/images.jpg")));
		mais.setBounds(61, 11, 78, 80);
		myViewCadastro.getContentPane().add(mais);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNome.setBounds(26, 151, 46, 23);
		myViewCadastro.getContentPane().add(lblNome);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSenha.setBounds(26, 270, 46, 14);
		myViewCadastro.getContentPane().add(lblSenha);
		
		JLabel lblConfirSenha = new JLabel("Confirmar Senha");
		lblConfirSenha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblConfirSenha.setBounds(225, 270, 116, 14);
		myViewCadastro.getContentPane().add(lblConfirSenha);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(230, 230, 250));
		passwordField.setBounds(82, 267, 125, 25);
		myViewCadastro.getContentPane().add(passwordField);
		
		passwordFieldConfir = new JPasswordField();
		passwordFieldConfir.setBackground(new Color(230, 230, 250));
		passwordFieldConfir.setBounds(351, 267, 125, 25);
		myViewCadastro.getContentPane().add(passwordFieldConfir);
		
		JLabel txtrCadastrarUsurio = new JLabel();
		txtrCadastrarUsurio.setBackground(Color.BLACK);
		txtrCadastrarUsurio.setOpaque(false);
//		txtrCadastrarUsurio.  HorizontalAlignment(SwingConstants.CENTER);
		txtrCadastrarUsurio.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 16));
		txtrCadastrarUsurio.setText("Cadastrar usuário");
		txtrCadastrarUsurio.setBounds(172, 55, 153, 23);
		myViewCadastro.getContentPane().add(txtrCadastrarUsurio);
		
		JButton btnSalvar = new JButton("Salvar");
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
				
				
				
			}
		});
		btnSalvar.setBounds(61, 329, 138, 40);
		myViewCadastro.getContentPane().add(btnSalvar);
		
		JButton btnCancelar = new JButton("Sair");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				myViewCadastro.dispose();
				bd.desconecta();
			}
		});
		btnCancelar.setBounds(377, 329, 125, 40);
		myViewCadastro.getContentPane().add(btnCancelar);
		
		textFieldNome = new JTextField();
		textFieldNome.setBackground(new Color(230, 230, 250));
		textFieldNome.setColumns(10);
		textFieldNome.setBounds(82, 152, 390, 25);
		myViewCadastro.getContentPane().add(textFieldNome);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBackground(new Color(230, 230, 250));
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(82, 207, 390, 25);
		myViewCadastro.getContentPane().add(textFieldEmail);
		
		JLabel fundoCadastro = new JLabel("");
		fundoCadastro.setIcon(new ImageIcon(TelaCadastro.class.getResource("/br/gcc/ppoo/Imagens/xfce-teal.jpg")));
		fundoCadastro.setBounds(0, 0, 554, 421);
		myViewCadastro.getContentPane().add(fundoCadastro);

//		Creating DefaultListModel to receive the methods and attributes the of JList Methods and Attributes
		listmetodosSec = new DefaultListModel();
		
//		More configuration of JFrame
		myViewCadastro.setSize(560, 450);
		myViewCadastro.setVisible(true);
		myViewCadastro.setResizable(false);
	}
}	

