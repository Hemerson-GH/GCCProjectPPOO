package br.ufla.gcc.ppoo.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

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
	
	private void ConfereCampoNome(String nome) throws CadastroUsuarioException {
		if (nome.trim().isEmpty()) {
			throw new CadastroUsuarioException("Campo nome n�o pode ser vazio, digite novamente.", "Nome inv�lido");
		}
	}
	
	public void ConfereSenhas(String senhaPri, String senhaSec) throws CadastroUsuarioException{
		if (!senhaPri.equals(senhaSec)) {
			throw new CadastroUsuarioException("As senha digitadas n�o conferem, digite novamente.", "Senha inv�lida");
		}
	}
	
	public void ConfereTamanhoSenhas(String senhaPri, String senhaSec) throws CadastroUsuarioException{
		if ( (senhaPri.trim().length() <= 3)  || (senhaSec.trim().length() <= 3) ) {
			throw new CadastroUsuarioException("A senha digitada deve conter no m�nimo quatro d�gitos."
					+ "\nPor favor digite uma nova senha v�lida.", "Senha inv�lida");
		} 
	}
	
	public void ConfereCampoEmail(JTextField textFieldEmail) throws CadastroUsuarioException{
		if (!textFieldEmail.getText().contains("@") || !textFieldEmail.getText().contains(".com")) {
			throw new CadastroUsuarioException("O campo email est� preenchido incorretamente.\nLembre-se de inserir um email v�lido, "
					+ "exemplo nome@dominio.com", "Campo email incorreto");
		} 
	}
	
	public void ViewMain(){
		
		myViewCadastro = new JFrame();
		myViewCadastro.getContentPane().setBackground(new Color(51, 102, 153));
		myViewCadastro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myViewCadastro.setResizable(true);
		myViewCadastro.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		myViewCadastro.setTitle("Cadastro De Usu�rio");
		myViewCadastro.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		myViewCadastro.getContentPane().setLayout(null);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmail.setBounds(25, 110, 50, 25);
		myViewCadastro.getContentPane().add(lblEmail);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setForeground(new Color(255, 255, 255));
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNome.setBounds(25, 60, 50, 25);
		myViewCadastro.getContentPane().add(lblNome);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setForeground(new Color(255, 255, 255));
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSenha.setBounds(25, 165, 60, 15);
		myViewCadastro.getContentPane().add(lblSenha);
		
		JLabel lblConfirSenha = new JLabel("Confirmar Senha:");
		lblConfirSenha.setForeground(new Color(255, 255, 255));
		lblConfirSenha.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblConfirSenha.setBounds(220, 165, 130, 15);
		myViewCadastro.getContentPane().add(lblConfirSenha);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordField.setToolTipText("Digite uma senha com pelo menos 4 caracteres...");
		passwordField.setBackground(new Color(255, 255, 255));
		passwordField.setBounds(80, 160, 125, 25);
		myViewCadastro.getContentPane().add(passwordField);
		
		passwordFieldConfir = new JPasswordField();
		passwordFieldConfir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordFieldConfir.setToolTipText("Digite a mesma senha digitada anteriormente...");
		passwordFieldConfir.setBackground(new Color(255, 255, 255));
		passwordFieldConfir.setBounds(355, 160, 125, 25);
		myViewCadastro.getContentPane().add(passwordFieldConfir);
		
		JLabel txtrCadastrarUsurio = new JLabel();
		txtrCadastrarUsurio.setToolTipText("Cadastro de novo usu�rio...");
		txtrCadastrarUsurio.setForeground(new Color(255, 255, 255));
		txtrCadastrarUsurio.setBackground(new Color(255, 255, 255));
		txtrCadastrarUsurio.setOpaque(false);
		txtrCadastrarUsurio.setFont(new Font("Arial", Font.BOLD, 19));
		txtrCadastrarUsurio.setText("Cadastrar novo usu�rio");
		txtrCadastrarUsurio.setBounds(140, 10, 235, 40);
		myViewCadastro.getContentPane().add(txtrCadastrarUsurio);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setToolTipText("Salvar usu�rio...");
		btnSalvar.setBackground(new Color(255, 255, 255));
		btnSalvar.setFont(new Font("Arial", Font.PLAIN, 15));
		
		btnSalvar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {		
				try {
					ConfereCampoNome(textFieldNome.getText());
					ConfereCampoEmail(textFieldEmail);
					ConfereTamanhoSenhas(passwordField.getText(), passwordFieldConfir.getText());
					ConfereSenhas(passwordField.getText(), passwordFieldConfir.getText());
					
					String nome = textFieldNome.getText().trim();
					String email = textFieldEmail.getText().trim();
					String senha = ControleDadosUsuarios.ConvertMD5(passwordField.getText().trim());
					DadosLogin dadosLogin = new DadosLogin(nome, email, senha);
					
					ControleDadosUsuarios.CadastrarUsuario(dadosLogin);
					myViewCadastro.dispose();
					
					JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso.\nAgora voc� ser� redirecionado para tela de login.", 
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
		btnSalvar.setBounds(85, 215, 105, 35);
		myViewCadastro.getContentPane().add(btnSalvar);
		
		JButton btnCancelar = new JButton("Sair");
		btnCancelar.setToolTipText("Voltar para Tela de Login...");
		btnCancelar.setBackground(new Color(255, 255, 255));
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 15));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myViewCadastro.dispose();
				new TelaLogin();
			}
		});
		btnCancelar.setBounds(350, 215, 105, 35);
		myViewCadastro.getContentPane().add(btnCancelar);
		
		textFieldNome = new JTextField();
		textFieldNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldNome.setToolTipText("Digite aqui o seu nome de usu�rio...");
		textFieldNome.setBackground(new Color(255, 255, 255));
		textFieldNome.setColumns(10);
		textFieldNome.setBounds(80, 60, 400, 25);
		myViewCadastro.getContentPane().add(textFieldNome);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldEmail.setToolTipText("Digite aqui seu email.\n Exemplo nome@dominio.com");
		textFieldEmail.setBackground(new Color(255, 255, 255));
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(80, 110, 400, 25);
		myViewCadastro.getContentPane().add(textFieldEmail);
		
		GroupLayout groupLayout = new GroupLayout(myViewCadastro.getContentPane());
		
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(textFieldNome, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
					.addGap(29))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(textFieldEmail, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
					.addGap(29))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(55)
							.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
						.addComponent(lblSenha, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addComponent(lblConfirSenha)
					.addGap(5)
					.addComponent(passwordFieldConfir, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
					.addGap(29))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(85)
					.addComponent(btnSalvar, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
					.addGap(160)
					.addComponent(btnCancelar, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
					.addGap(54))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(153)
					.addComponent(txtrCadastrarUsurio, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
					.addGap(121))
		);
		
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtrCadastrarUsurio, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldNome))
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(textFieldEmail)))
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(passwordField)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblSenha, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 5, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblConfirSenha, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 5, Short.MAX_VALUE))
						.addComponent(passwordFieldConfir))
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSalvar, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
						.addComponent(btnCancelar, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
					.addGap(16))
		);
		myViewCadastro.getContentPane().setLayout(groupLayout);
		
//		JLabel fundoCadastro = new JLabel("");
//		fundoCadastro.setIcon(new ImageIcon(TelaCadastroUsuario.class.getResource("/br/ufla/gcc/ppoo/Imagens/icone-usuario.png")));
//		fundoCadastro.setBounds(100, 7, 50, 46);
//		myViewCadastro.getContentPane().add(fundoCadastro);
		
		myViewCadastro.setSize(525, 305);
		myViewCadastro.setResizable(true);
		myViewCadastro.setVisible(true);
	}	
}