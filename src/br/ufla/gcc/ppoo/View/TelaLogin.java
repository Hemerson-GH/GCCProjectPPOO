package br.ufla.gcc.ppoo.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
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
import br.ufla.gcc.ppoo.Exceptions.BancoDadosException;
import br.ufla.gcc.ppoo.Exceptions.BuscasException;
import br.ufla.gcc.ppoo.Exceptions.ConverteSenhaException;
import br.ufla.gcc.ppoo.Exceptions.UsuarioException;

public class TelaLogin {
	
	private JFrame myViewLogin;
	private JTextField txtNovoUsuario;
	private JTextField textAreaUser;
	private JPasswordField passwordField;
	private BancoDeDados bancoDDados = new BancoDeDados();

	public TelaLogin() {
		try {
			bancoDDados.conecta();
		} catch (BancoDadosException dbe) {
			JOptionPane.showMessageDialog(null, dbe.getMessage(), dbe.getTitulo(), JOptionPane.ERROR_MESSAGE);
		}
		
		View();
	}
	
	public void confereSenhaUsuario(String senhaInserida, String senhaUsuario) throws BuscasException, ConverteSenhaException {
		if (!ControleDadosUsuarios.convertMD5(senhaInserida).equals(senhaUsuario)) {
			throw new BuscasException(" Usuário e/ou senha inválidos...", "Usuário inválidos");
		}
	}

	public void View(){

		myViewLogin = new JFrame();
		myViewLogin.getContentPane().setBackground(new Color(51, 102, 153));
		myViewLogin.getContentPane().setForeground(new Color(255, 255, 255));
		myViewLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myViewLogin.setResizable(true);
		myViewLogin.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		myViewLogin.setTitle("Login");
		myViewLogin.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		
		textAreaUser = new JTextField();
		textAreaUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textAreaUser.setToolTipText("Digite seu email...");
		textAreaUser.setBackground(new Color(255, 255, 255));
		textAreaUser.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setToolTipText("Digite sua senha...");
		passwordField.setBackground(new Color(255, 255, 255));
		passwordField.setBounds(90, 111, 220, 25);
		myViewLogin.getContentPane().add(passwordField);
		
		JButton btnEnter = new JButton("Entrar");
		btnEnter.setForeground(new Color(0, 0, 0));
		btnEnter.setToolTipText("Entrar");
		btnEnter.setFont(new Font("Arial", Font.PLAIN, 15));
		btnEnter.setBackground(new Color(255, 255, 255));
		btnEnter.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				
				DadosLogin dadosLogin;
				
				try {
					dadosLogin = new DadosLogin(ControleDadosUsuarios.buscarDados(textAreaUser.getText()));
					confereSenhaUsuario(passwordField.getText().trim(), dadosLogin.getSenha());					
					
					myViewLogin.dispose();
					new TelaPrincipal(dadosLogin);
				}  catch (BancoDadosException dbe) {
					JOptionPane.showMessageDialog(null, dbe.getMessage(), dbe.getTitulo(), JOptionPane.ERROR_MESSAGE);
				} catch (UsuarioException ee) {
					JOptionPane.showMessageDialog(null, ee.getMessage(), ee.getTitulo(), JOptionPane.ERROR_MESSAGE);
				} catch (ConverteSenhaException cse) {
					JOptionPane.showMessageDialog(null, cse.getMessage(), cse.getTitulo(), JOptionPane.ERROR_MESSAGE);
				} catch (BuscasException be) {
					JOptionPane.showMessageDialog(null, be.getMessage(), be.getTitulo(), JOptionPane.ERROR_MESSAGE);
				} 					
			}
		});
		btnEnter.setBounds(90, 160, 90, 25);
		myViewLogin.getContentPane().add(btnEnter);
		
		JButton btnCancel = new JButton("Sair");
		btnCancel.setToolTipText("Sair");
		btnCancel.setFont(new Font("Arial", Font.PLAIN, 15));
		btnCancel.setBackground(new Color(255, 255, 255));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				myViewLogin.dispose();
				
				try {
					bancoDDados.desconecta();
				}  catch (BancoDadosException dbe) {
					JOptionPane.showMessageDialog(null, dbe.getMessage(), dbe.getTitulo(), JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCancel.setBounds(320, 160, 90, 25);
		myViewLogin.getContentPane().add(btnCancel);
		
		txtNovoUsuario = new JTextField();
		txtNovoUsuario.setForeground(new Color(255, 255, 255));
		txtNovoUsuario.setFont(new Font("Arial", Font.ITALIC, 15));
		txtNovoUsuario.setBackground(new Color(0, 128, 128));
		txtNovoUsuario.setToolTipText("Clique aqui para cadastrar um novo usuário");
		txtNovoUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				myViewLogin.dispose();
				new TelaCadastroUsuario();
			}
		});
		txtNovoUsuario.setEditable(false);
		txtNovoUsuario.setText("Novo usuário");
		txtNovoUsuario.setBounds(335, 115, 95, 20);
		myViewLogin.getContentPane().add(txtNovoUsuario);
		txtNovoUsuario.setOpaque(false);
		txtNovoUsuario.setBorder(null);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setForeground(new Color(255, 255, 255));
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSenha.setBounds(40, 115, 50, 20);
		myViewLogin.getContentPane().add(lblSenha);
		
		JLabel lblLogin = new JLabel("Email:");
		lblLogin.setForeground(new Color(255, 255, 255));
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblAutenticao = new JLabel("Autenticar usuário ");
		lblAutenticao.setBackground(new Color(51, 204, 102));
		lblAutenticao.setFont(new Font("Arial", Font.BOLD, 19));
		lblAutenticao.setForeground(new Color(255, 255, 255));
		lblAutenticao.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutenticao.setBounds(125, 10, 225, 35);
		myViewLogin.getContentPane().add(lblAutenticao);
		
		GroupLayout groupLayout = new GroupLayout(myViewLogin.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(125)
					.addComponent(lblAutenticao, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
					.addGap(119))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(48)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblLogin, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
							.addGap(337))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(42)
							.addComponent(textAreaUser, GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)))
					.addGap(39))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(40)
					.addComponent(lblSenha, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
					.addGap(25)
					.addComponent(txtNovoUsuario, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addGap(39))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(90)
					.addComponent(btnEnter, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
					.addGap(140)
					.addComponent(btnCancel, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
					.addGap(59))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(lblAutenticao, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLogin, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(textAreaUser, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)))
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSenha, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addComponent(txtNovoUsuario, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
							.addGap(3)))
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnEnter, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnCancel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(16))
		);
		
		myViewLogin.getContentPane().setLayout(groupLayout);
		
		myViewLogin.setSize(485, 240);
		myViewLogin.setMinimumSize(new Dimension(485, 240));
		myViewLogin.setVisible(true);
		myViewLogin.setResizable(true);
	}
}	

