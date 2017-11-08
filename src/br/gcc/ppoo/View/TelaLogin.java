package br.gcc.ppoo.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.gcc.ppoo.BancoDeDados.BancoDeDados;
import br.gcc.ppoo.Control.ControleDados;

public class TelaLogin {
//	public class SecWind {
	
//	5432 local

//	Create I created the JFrame variable as a global variable so I did not have to create every instant I was going to use
	JFrame myViewLogin;
	
	private JTextField textAreaUser;
	private JPasswordField passwordField;
	private JTextField txtNovoUsurio;
	BancoDeDados bd = new BancoDeDados();
	ControleDados cd = new ControleDados();
//	DadosLogin dl = new DadosLogin("Hemerson", "hemersonel@gmail.com","12345");
//	DadosLogin dl = new DadosLogin();

//	Class calling my class that creates my JFrame
	public TelaLogin() {
		
		View();
	}

	//Class create my JFrame
	public void View(){
		
//		Create my JFrame and I set it up
		JComponent.setDefaultLocale(Locale.ENGLISH);
		myViewLogin = new JFrame();
		myViewLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myViewLogin.setResizable(false);
		myViewLogin.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		myViewLogin.setTitle("Tela Login");
		myViewLogin.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		myViewLogin.getContentPane().setLayout(null);
		
		textAreaUser = new JTextField();
		textAreaUser.setBackground(Color.LIGHT_GRAY);
		textAreaUser.setBounds(127, 151, 340, 25);
		myViewLogin.getContentPane().add(textAreaUser);
		textAreaUser.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(Color.LIGHT_GRAY);
		passwordField.setBounds(127, 212, 218, 25);
		myViewLogin.getContentPane().add(passwordField);
		
		JButton btnEnter = new JButton("Sing in");
		btnEnter.setBackground(new Color(0, 128, 128));
		btnEnter.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				
				if (textAreaUser.getText().equals("Hemerson") && passwordField.getText().equals("123")) {
					myViewLogin.dispose();
					
					
					
					JOptionPane.showMessageDialog(null,
						    "User " + textAreaUser.getText() + " logado com sucesseo.",
						    "Logado Com Sucesso",
						    JOptionPane.INFORMATION_MESSAGE);
				} else {
					
					System.out.println(textAreaUser.getText());
					
					JOptionPane.showMessageDialog(null,
						    "User " + textAreaUser.getText() + " Not found",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnEnter.setBounds(99, 305, 90, 25);
		myViewLogin.getContentPane().add(btnEnter);
		
		JButton btnCancel = new JButton("Exit");
		btnCancel.setBackground(new Color(0, 128, 128));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myViewLogin.dispose();
				bd.desconecta();
			}
		});
		btnCancel.setBounds(377, 305, 90, 25);
		myViewLogin.getContentPane().add(btnCancel);
		
		txtNovoUsurio = new JTextField();
		txtNovoUsurio.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtNovoUsurio.setBackground(new Color(0, 128, 128));
		txtNovoUsurio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				@SuppressWarnings("unused")
				TelaCadastro telaCadas = new TelaCadastro();
//				myViewLogin.setVisible(false);
				myViewLogin.dispose();
//				telaCadas.myViewLogin.setVisible(true);
			}
		});
		txtNovoUsurio.setEditable(false);
		txtNovoUsurio.setText("Novo usuário");
		txtNovoUsurio.setBounds(390, 214, 85, 20);
		myViewLogin.getContentPane().add(txtNovoUsurio);
//		txtNovoUsurio.setColumns(10);
		txtNovoUsurio.setOpaque(false);
		txtNovoUsurio.setBorder(null);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSenha.setBounds(27, 202, 90, 40);
		myViewLogin.getContentPane().add(lblSenha);
		
		JLabel lblLogin = new JLabel("Email:");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLogin.setBounds(27, 141, 90, 40);
		myViewLogin.getContentPane().add(lblLogin);
		
		JLabel lblAutenticao = new JLabel("Autenticar usuário ");
		lblAutenticao.setFont(new Font("Arial", Font.BOLD, 14));
		lblAutenticao.setForeground(Color.BLACK);
		lblAutenticao.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutenticao.setBounds(141, 53, 280, 40);
		myViewLogin.getContentPane().add(lblAutenticao);
		
		JLabel llbFundo = new JLabel("");
		llbFundo.setIcon(new ImageIcon(TelaLogin.class.getResource("/br/gcc/ppoo/Imagens/xfce-blue.jpg")));
		llbFundo.setBounds(0, 0, 569, 421);
		myViewLogin.getContentPane().add(llbFundo);
		
		
//		More configuration of JFrame
		myViewLogin.setSize(575, 450);
		myViewLogin.setVisible(true);
		myViewLogin.setResizable(false);
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		TelaLogin t = new TelaLogin();
	}
}	

