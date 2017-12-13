package br.ufla.gcc.ppoo.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.ufla.gcc.ppoo.Control.ControleDadosUsuarios;
import br.ufla.gcc.ppoo.Dados.DadosLogin;
import br.ufla.gcc.ppoo.Exceptions.BancoDadosException;
import br.ufla.gcc.ppoo.Exceptions.CadastroUsuarioException;
import br.ufla.gcc.ppoo.Exceptions.ConverteSenhaException;
import br.ufla.gcc.ppoo.Exceptions.UsuarioException;
import br.ufla.gcc.ppoo.Exceptions.UsuarioExistenteException;
	
public class TelaCadastroUsuario {

	private JFrame myViewCadastro;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldConfir;
	private JTextField textFieldNome;
	private JTextField textFieldEmail;
	
	public TelaCadastroUsuario() {
		ViewMain();
	}
	
	public void ConfereSenhas(String senhaPri, String senhaSec) throws CadastroUsuarioException{
		if (!senhaPri.equals(senhaSec)) {
			throw new CadastroUsuarioException("As senha digitadas não conferem, digite novamente.", "Senha inválida");
		} 
	}
	
	public void ConfereTamanhoSenhas(String senhaPri, String senhaSec) throws CadastroUsuarioException{
		if ( (senhaPri.trim().length() <= 3)  || (senhaSec.trim().length() <= 3) ) {
			throw new CadastroUsuarioException("A senha digitada deve conter no mínimo quatro dígitos."
					+ "\nPor favor digite uma nova senha válida.", "Senha inválida");
		} 
	}
	
	public void ConfereCampoEmail(JTextField textFieldEmail) throws CadastroUsuarioException{
		if (!textFieldEmail.getText().contains("@") || !textFieldEmail.getText().contains(".com")) {
			throw new CadastroUsuarioException("O campo email está preenchido incorretamente.\nLembre-se de inserir um email válido, "
					+ "exemplo nome@dominio.com", "Campo email incorreto");
		} 
	}
	
	public void ViewMain(){
		
		myViewCadastro = new JFrame();
		myViewCadastro.getContentPane().setBackground(new Color(51, 102, 153));
		myViewCadastro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myViewCadastro.setResizable(false);
		myViewCadastro.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		myViewCadastro.setTitle("Cadastro De Usuário");
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
		passwordField.setToolTipText("Digite uma senha com pelo menos 4 caracteres...");
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
		txtrCadastrarUsurio.setText("Cadastrar novo usuário");
		txtrCadastrarUsurio.setBounds(155, 10, 205, 40);
		myViewCadastro.getContentPane().add(txtrCadastrarUsurio);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setToolTipText("Salvar usuário...");
		btnSalvar.setBackground(new Color(255, 255, 255));
		btnSalvar.setFont(new Font("Arial", Font.PLAIN, 14));
		
		btnSalvar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {		
				try {
					ConfereCampoEmail(textFieldEmail);
					ConfereTamanhoSenhas(passwordField.getText(), passwordFieldConfir.getText());
					ConfereSenhas(passwordField.getText(), passwordFieldConfir.getText());
					
					String nome = textFieldNome.getText().trim();
					String email = textFieldEmail.getText().trim();
					String senha = ControleDadosUsuarios.ConvertMD5(passwordField.getText().trim());
					DadosLogin dadosLogin = new DadosLogin(nome, email, senha);
					
					ControleDadosUsuarios.CadastrarUsuario(dadosLogin);
					myViewCadastro.dispose();
					
					JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso.\nAgora você será redirecionado para tela de login.", 
																						"Cadastro realizado", JOptionPane.INFORMATION_MESSAGE);
					new TelaLogin();
				} catch (CadastroUsuarioException cue) {
					JOptionPane.showMessageDialog(null, cue.getMessage(), cue.getTitulo(), JOptionPane.ERROR_MESSAGE);
				} catch (ConverteSenhaException cse) {
					JOptionPane.showMessageDialog(null, cse.getMessage(), cse.getTitulo(), JOptionPane.ERROR_MESSAGE);
				} catch (BancoDadosException bdex){
					JOptionPane.showMessageDialog(null, bdex.getMessage(), bdex.getTitulo(), JOptionPane.ERROR_MESSAGE);
				} catch (UsuarioExistenteException uee) {
					JOptionPane.showMessageDialog(null, uee.getMessage(), uee.getTitulo(), JOptionPane.ERROR_MESSAGE);
				} catch (UsuarioException ue) {
					JOptionPane.showMessageDialog(null, ue.getMessage(), ue.getTitulo(), JOptionPane.ERROR_MESSAGE);
				} 
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
				new TelaLogin();
			}
		});
		btnCancelar.setBounds(345, 210, 105, 35);
		myViewCadastro.getContentPane().add(btnCancelar);
		
		textFieldNome = new JTextField();
		textFieldNome.setToolTipText("Digite aqui o seu nome de usuário...");
		textFieldNome.setBackground(new Color(255, 255, 255));
		textFieldNome.setColumns(10);
		textFieldNome.setBounds(80, 60, 390, 25);
		myViewCadastro.getContentPane().add(textFieldNome);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setToolTipText("Digite aqui seu email.\n Exemplo nome@dominio.com");
		textFieldEmail.setBackground(new Color(255, 255, 255));
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(80, 110, 390, 25);
		myViewCadastro.getContentPane().add(textFieldEmail);
		
		JLabel fundoCadastro = new JLabel("");
		fundoCadastro.setIcon(new ImageIcon(TelaCadastroUsuario.class.getResource("/br/ufla/gcc/ppoo/Imagens/icone-usuario.png")));
		fundoCadastro.setBounds(100, 7, 50, 46);
		myViewCadastro.getContentPane().add(fundoCadastro);
		
		myViewCadastro.setSize(500, 300);
		myViewCadastro.setResizable(false);
		myViewCadastro.setVisible(true);
	}	
}	

