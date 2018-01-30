package br.ufla.gcc.ppoo.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
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
		if (wordKeyHifen.length < 2) {
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
		viewCadastroFilme.getContentPane().setLayout(null);
		
		setStatus(true);
		
		try {
			dl = ControleDadosUsuarios.BuscarDados(dadosLogin.getEmail());
		} catch (BancoDadosException bdex){
			JOptionPane.showMessageDialog(null, bdex.getMessage(), bdex.getTitulo(), JOptionPane.ERROR_MESSAGE);
		} catch (UsuarioException ee) {
			JOptionPane.showMessageDialog(null, ee.getMessage(), ee.getTitulo(), JOptionPane.ERROR_MESSAGE);
		} 
		
		JLabel lblCadastro = new JLabel("Cadastrar Filme");
		lblCadastro.setIcon(new ImageIcon(TelaCadastroFilme.class.getResource("/br/ufla/gcc/ppoo/Imagens/novo_item.png")));
		lblCadastro.setForeground(new Color(255, 255, 255));
		lblCadastro.setBounds(105, 30, 350, 55);
		lblCadastro.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastro.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 40));
		viewCadastroFilme.getContentPane().add(lblCadastro);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 100, 45, 25);
		lblNome.setForeground(new Color(255, 255, 255));
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblNome.setBackground(Color.GRAY);
		viewCadastroFilme.getContentPane().add(lblNome);
		
		textFieldNome = new JTextField();
		textFieldNome.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 14));
		textFieldNome.setBounds(10, 125, 575, 30);
		viewCadastroFilme.getContentPane().add(textFieldNome);
		textFieldNome.setColumns(10);
		
		JLabel lblData = new JLabel("Data de Lançamento");
		lblData.setBounds(155, 220, 140, 25);
		lblData.setHorizontalAlignment(SwingConstants.CENTER);
		lblData.setForeground(new Color(255, 255, 255));
		lblData.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblData.setBackground(Color.GRAY);
		viewCadastroFilme.getContentPane().add(lblData);
		
		textFieldData = new JTextField();
		textFieldData.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 14));
		textFieldData.setBounds(155, 245, 140, 30);
		textFieldData.setToolTipText("Preencha esse campo da seguinte forma, 01/11/2017");
		textFieldData.setColumns(10);
		viewCadastroFilme.getContentPane().add(textFieldData);
		
		JLabel lblDuraoDoFilme = new JLabel("Duração");
		lblDuraoDoFilme.setBounds(325, 220, 58, 25);
		lblDuraoDoFilme.setHorizontalAlignment(SwingConstants.CENTER);
		lblDuraoDoFilme.setForeground(new Color(255, 255, 255));
		lblDuraoDoFilme.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblDuraoDoFilme.setBackground(Color.GRAY);
		viewCadastroFilme.getContentPane().add(lblDuraoDoFilme);
		
		textFieldDuracao = new JTextField();
		textFieldDuracao.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 14));
		textFieldDuracao.setBounds(325, 245, 100, 30);
		textFieldDuracao.setToolTipText("Preencha esse campo da seguinte forma, 2h15m");
		textFieldDuracao.setColumns(10);
		viewCadastroFilme.getContentPane().add(textFieldDuracao);
		
		JLabel lblGnero = new JLabel("Gênero");
		lblGnero.setBounds(10, 220, 55, 25);
		lblGnero.setHorizontalAlignment(SwingConstants.CENTER);
		lblGnero.setForeground(new Color(255, 255, 255));
		lblGnero.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblGnero.setBackground(Color.GRAY);
		viewCadastroFilme.getContentPane().add(lblGnero);
		
		textFieldGenero = new JTextField();
		textFieldGenero.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 14));
		textFieldGenero.setBounds(10, 245, 115, 30);
		textFieldGenero.setToolTipText("Caso você for adicionar mais de um gênero acrescentar barra no"
													+ " próximo gênero, exemplo: Gênero 1/Gênero 2...");
		textFieldGenero.setColumns(10);
		viewCadastroFilme.getContentPane().add(textFieldGenero);
		
		JLabel lblPalavras = new JLabel("Palavras-chaves(mínimo 2)");
		lblPalavras.setBounds(10, 165, 185, 25);
		lblPalavras.setHorizontalAlignment(SwingConstants.CENTER);
		lblPalavras.setForeground(new Color(255, 255, 255));
		lblPalavras.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblPalavras.setBackground(Color.GRAY);
		viewCadastroFilme.getContentPane().add(lblPalavras);
		
		textFieldWorKeys = new JTextField();
		textFieldWorKeys.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 14));
		textFieldWorKeys.setBounds(10, 190, 575, 30);
		textFieldWorKeys.setToolTipText("Preencha esse campo da seguinte forma, PalavraChave1-PalavraChave2...");
		textFieldWorKeys.setColumns(10);
		viewCadastroFilme.getContentPane().add(textFieldWorKeys);
		
		JLabel lblDescrio = new JLabel("Descrição");
		lblDescrio.setBounds(10, 281, 72, 25);
		lblDescrio.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescrio.setForeground(new Color(255, 255, 255));
		lblDescrio.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblDescrio.setBackground(Color.GRAY);
		viewCadastroFilme.getContentPane().add(lblDescrio);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(TelaCadastroFilme.class.getResource("/br/ufla/gcc/ppoo/Imagens/btn-cancelar.png")));
		btnCancelar.setBounds(390, 445, 150, 25);
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
		viewCadastroFilme.getContentPane().add(btnCancelar);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(TelaCadastroFilme.class.getResource("/br/ufla/gcc/ppoo/Imagens/btn-ok.png")));
		btnSalvar.setBounds(60, 445, 150, 25);
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
					contensHifen(textFieldWorKeys.getText());
					contemPalavrasChave(textFieldWorKeys.getText());
					ControleDadosFilmes.CadastrarFilme(filme, dl.getId());
					
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
		viewCadastroFilme.getContentPane().add(btnSalvar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 305, 575, 130);
		viewCadastroFilme.getContentPane().add(scrollPane);
		
		editorPaneDescricao = new JEditorPane();
		editorPaneDescricao.setToolTipText("Digite aqui a descrição do filme");
		scrollPane.setViewportView(editorPaneDescricao);
		editorPaneDescricao.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 14));
		editorPaneDescricao.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
		
		textFieldDiretor = new JTextField();
		textFieldDiretor.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 14));
		textFieldDiretor.setColumns(10);
		textFieldDiretor.setBounds(455, 245, 130, 30);
		viewCadastroFilme.getContentPane().add(textFieldDiretor);
		
		JLabel lblDireto = new JLabel("Diretor");
		lblDireto.setHorizontalAlignment(SwingConstants.CENTER);
		lblDireto.setForeground(Color.WHITE);
		lblDireto.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblDireto.setBackground(Color.GRAY);
		lblDireto.setBounds(455, 220, 50, 25);
		viewCadastroFilme.getContentPane().add(lblDireto);
		
		viewCadastroFilme.setResizable(false);
		viewCadastroFilme.setSize(600, 520);
		viewCadastroFilme.setVisible(true);		
	}
}
