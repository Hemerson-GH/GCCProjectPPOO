package br.ufla.gcc.ppoo.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import br.ufla.gcc.ppoo.Control.ControleDadosFilmes;
import br.ufla.gcc.ppoo.Control.ControleDadosUsuarios;
import br.ufla.gcc.ppoo.Dados.DadosLogin;
import br.ufla.gcc.ppoo.Dados.Filme;
import br.ufla.gcc.ppoo.Exceptions.BancoDadosException;
import br.ufla.gcc.ppoo.Exceptions.CadastroFilmeException;
import br.ufla.gcc.ppoo.Exceptions.FilmeExistenteException;
import br.ufla.gcc.ppoo.Exceptions.FilmesException;
import br.ufla.gcc.ppoo.Exceptions.UsuarioException;
import br.ufla.gcc.ppoo.Imagens.GerenciadorDeImagens;

public class TelaCadastroFilme {

	private JFrame viewCadastroFilme;
	private static boolean status = false;
	private DadosLogin dl;	
	private JTextField textFieldNome;
	private JTextField textFieldData;
	private JTextField textFieldDuracao;
	private JTextField textFieldGenero;
	private JTextField textFieldWorKeys;
	private JEditorPane editorPaneDescricao;
	private JTextField textFieldDiretor;
	
	public static boolean getStatus() { 
		return status;
	}
	
	public static void setStatus(boolean bool) {
		status = bool;
	}
	
	public void limpaCampos(){
		textFieldNome.setText(null);
		textFieldData.setText(null);
		textFieldDuracao.setText(null);
		textFieldGenero.setText(null);
		textFieldWorKeys.setText(null);
		textFieldDiretor.setText(null);
		editorPaneDescricao.setText(null);
	}
	
	public void confereCampos(JTextField textFieldNome, JTextField textFieldWorKeys, JTextField textFieldData, JTextField textFieldDuracao, 
			JTextField textFieldGenero, JEditorPane editorPaneDescricao, JTextField textFieldDiretor) throws CadastroFilmeException{
		if (textFieldNome.getText().trim().isEmpty() || textFieldWorKeys.getText().trim().isEmpty() || textFieldData.getText().trim().isEmpty() || 
			textFieldDiretor.getText().trim().isEmpty() || textFieldDuracao.getText().trim().isEmpty() || 
			textFieldGenero.getText().trim().isEmpty() || editorPaneDescricao.getText().trim().isEmpty()) {
			throw new CadastroFilmeException("Preencha todos os campos para que seja possível salvar o filme.", "Erro ao salvar");
		}
	}
	
	public void contensHifen(String textFieldWorKeys) throws CadastroFilmeException{
		if(!textFieldWorKeys.contains("-")) {
			throw new CadastroFilmeException("Campo 'Palavras-chave' não está preenchido corretamente.\n"
					+ "Para salvar o filme as palavras-chave precisa ser separadas por '-'.", "Campo Palavras-Chave incorreto");
		}
	}
	
	public void contemPalavrasChave(String textFieldWorKeys) throws CadastroFilmeException {
		String[] wordKeyHifen = textFieldWorKeys.split("-");
		boolean ok = false;
		
		for (int i = 0; i < wordKeyHifen.length && ok == false; i++) {
			if (wordKeyHifen[i].isEmpty()) {
				ok = true;
			}
		}
		
		if (wordKeyHifen.length < 2 || ok == true) {
			throw new CadastroFilmeException("Insira mais de uma palavra-chave", "Campo Palavras-Chave incorreto");
		}
	}
	
	public TelaCadastroFilme(DadosLogin dadosLogin){
		viewTelaCadastroFilme(dadosLogin);
	}
	
	public void viewTelaCadastroFilme(DadosLogin dadosLogin){
		
		viewCadastroFilme = new JFrame();
		viewCadastroFilme.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				status = false;
			}
		});
		viewCadastroFilme.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		viewCadastroFilme.setBackground(new Color(0, 0, 255));
		viewCadastroFilme.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		viewCadastroFilme.getContentPane().setBackground(new Color(51, 102, 153));
		viewCadastroFilme.getContentPane().setForeground(new Color(255, 255, 255));
		viewCadastroFilme.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		viewCadastroFilme.setTitle("Cadastrar Filme");
		viewCadastroFilme.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		
		setStatus(true);
		
		try {
			dl = ControleDadosUsuarios.buscarDados(dadosLogin.getEmail());
		} catch (BancoDadosException bdex){
			JOptionPane.showMessageDialog(null, bdex.getMessage(), bdex.getTitulo(), JOptionPane.ERROR_MESSAGE);
		} catch (UsuarioException ee) {
			JOptionPane.showMessageDialog(null, ee.getMessage(), ee.getTitulo(), JOptionPane.ERROR_MESSAGE);
		} 
		
		JLabel lblCadastroFilme = new JLabel("Cadastrar Filme");
		lblCadastroFilme.setIcon(GerenciadorDeImagens.NOVO_ITEM);
		lblCadastroFilme.setForeground(new Color(255, 255, 255));
		lblCadastroFilme.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastroFilme.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 40));
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setForeground(new Color(255, 255, 255));
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblNome.setBackground(Color.GRAY);
		
		textFieldNome = new JTextField();
		textFieldNome.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 14));
		textFieldNome.setColumns(10);
		
		JLabel lblData = new JLabel("Data de Lançamento");
		lblData.setHorizontalAlignment(SwingConstants.CENTER);
		lblData.setForeground(new Color(255, 255, 255));
		lblData.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblData.setBackground(Color.GRAY);
		
		textFieldData = new JTextField();
		textFieldData.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 14));
		textFieldData.setToolTipText("Preencha esse campo da seguinte forma, 01/11/2017");
		textFieldData.setColumns(10);
		
		JLabel lblDuraoDoFilme = new JLabel("Duração");
		lblDuraoDoFilme.setHorizontalAlignment(SwingConstants.CENTER);
		lblDuraoDoFilme.setForeground(new Color(255, 255, 255));
		lblDuraoDoFilme.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblDuraoDoFilme.setBackground(Color.GRAY);
		
		textFieldDuracao = new JTextField();
		textFieldDuracao.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 14));
		textFieldDuracao.setToolTipText("Preencha esse campo da seguinte forma, 2h15m");
		textFieldDuracao.setColumns(10);
		
		textFieldGenero = new JTextField();
		textFieldGenero.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 14));
		textFieldGenero.setToolTipText("Caso você for adicionar mais de um gênero acrescentar barra no"
													+ " próximo gênero, exemplo: Gênero 1/Gênero 2...");
		textFieldGenero.setColumns(10);
		
		JLabel lblPalavras = new JLabel("Palavras-chaves(mínimo 2)");
		lblPalavras.setHorizontalAlignment(SwingConstants.CENTER);
		lblPalavras.setForeground(new Color(255, 255, 255));
		lblPalavras.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblPalavras.setBackground(Color.GRAY);
		
		textFieldWorKeys = new JTextField();
		textFieldWorKeys.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 14));
		textFieldWorKeys.setToolTipText("Preencha esse campo da seguinte forma, PalavraChave1-PalavraChave2...");
		textFieldWorKeys.setColumns(10);
		
		JLabel lblDescricao = new JLabel("Descrição");
		lblDescricao.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescricao.setForeground(new Color(255, 255, 255));
		lblDescricao.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblDescricao.setBackground(Color.GRAY);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(GerenciadorDeImagens.CANCELAR);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaCampos();
				setStatus(false);
				viewCadastroFilme.dispose();
			}
		});
		btnCancelar.setForeground(new Color(0, 0, 0));
		btnCancelar.setToolTipText("Cancelar");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCancelar.setBackground(new Color(255, 255, 255));
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(GerenciadorDeImagens.OK);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String nome = textFieldNome.getText();
				String data = textFieldData.getText();
				String descricao = editorPaneDescricao.getText();
				String wordsKeys = textFieldWorKeys.getText();
				String genero = textFieldGenero.getText();
				String duracao = textFieldDuracao.getText();
				String diretor = textFieldDiretor.getText();
				
				Filme filme = new Filme(nome, data, descricao, wordsKeys, genero, duracao, diretor);
				
				try {
					confereCampos(textFieldNome, textFieldWorKeys, textFieldData, textFieldDuracao, textFieldGenero, editorPaneDescricao, textFieldDiretor);
					contensHifen(textFieldWorKeys.getText().trim());
					contemPalavrasChave(textFieldWorKeys.getText().trim());
					ControleDadosFilmes.cadastrarFilme(filme, dl.getId());
					
					JOptionPane.showMessageDialog(null, "Filme cadastrado com sucesso", "Filme cadastrado", JOptionPane.INFORMATION_MESSAGE);
					limpaCampos();				
				} catch (CadastroFilmeException cfe) {
					JOptionPane.showMessageDialog(null, cfe.getMessage(), cfe.getTitulo(), JOptionPane.ERROR_MESSAGE);
				} catch (BancoDadosException bdex){
					JOptionPane.showMessageDialog(null, bdex.getMessage(), bdex.getTitulo(), JOptionPane.ERROR_MESSAGE);
				} catch (FilmeExistenteException fex) {
					JOptionPane.showMessageDialog(null, fex.getMessage(), fex.getTitulo(), JOptionPane.ERROR_MESSAGE);
				} catch (FilmesException fe) {
					JOptionPane.showMessageDialog(null, fe.getMessage(), fe.getTitulo(), JOptionPane.ERROR_MESSAGE);
				}  
			}
		});
		btnSalvar.setForeground(new Color(0, 0, 0));
		btnSalvar.setToolTipText("Salvar filme");
		btnSalvar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnSalvar.setBackground(new Color(255, 255, 255));
		
		JScrollPane scrollPaneDescricao = new JScrollPane();
		
		editorPaneDescricao = new JEditorPane();
		editorPaneDescricao.setToolTipText("Digite aqui a descrição do filme");
		scrollPaneDescricao.setViewportView(editorPaneDescricao);
		editorPaneDescricao.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 14));
		editorPaneDescricao.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
		
		textFieldDiretor = new JTextField();
		textFieldDiretor.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 14));
		textFieldDiretor.setColumns(10);
		
		JLabel lblGenero = new JLabel("GêEAnero");
		lblGenero.setHorizontalAlignment(SwingConstants.CENTER);
		lblGenero.setForeground(Color.WHITE);
		lblGenero.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblGenero.setBackground(Color.GRAY);
		
		JLabel lblDiretor = new JLabel("Diretor");
		lblDiretor.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiretor.setForeground(Color.WHITE);
		lblDiretor.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblDiretor.setBackground(Color.GRAY);
		GroupLayout groupLayout = new GroupLayout(viewCadastroFilme.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(115)
					.addComponent(lblCadastroFilme, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
					.addGap(129))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(textFieldNome, GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
					.addGap(9))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(60)
					.addComponent(btnSalvar, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
					.addGap(180)
					.addComponent(btnCancelar, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
					.addGap(54))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(scrollPaneDescricao, GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(textFieldGenero, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
							.addGap(30)
							.addComponent(textFieldData, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
							.addGap(30)
							.addComponent(textFieldDuracao, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
							.addGap(30))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblGenero, GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
							.addGap(86)
							.addComponent(lblData, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
							.addGap(30)
							.addComponent(lblDuraoDoFilme, GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
							.addGap(72)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textFieldDiretor, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
							.addGap(10))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDiretor, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(539, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPalavras)
					.addGap(399))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(textFieldWorKeys, GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
					.addGap(9))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDescricao, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(512, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addComponent(lblCadastroFilme, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
					.addGap(17)
					.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldNome, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblPalavras)
					.addGap(3)
					.addComponent(textFieldWorKeys, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE, false)
							.addComponent(lblData, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblGenero, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE, false)
							.addComponent(lblDuraoDoFilme, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblDiretor, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldGenero, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
						.addComponent(textFieldData, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
						.addComponent(textFieldDuracao, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
						.addComponent(textFieldDiretor, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblDescricao, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(scrollPaneDescricao, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSalvar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnCancelar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(11))
		);
		viewCadastroFilme.getContentPane().setLayout(groupLayout);
		
		viewCadastroFilme.setMinimumSize(new Dimension(610, 515));
		viewCadastroFilme.setResizable(true);
		viewCadastroFilme.setSize(610, 520);
		viewCadastroFilme.setVisible(true);		
	}
}
