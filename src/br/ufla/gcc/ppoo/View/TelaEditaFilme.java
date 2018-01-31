package br.ufla.gcc.ppoo.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
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
import br.ufla.gcc.ppoo.Imagens.GerenciadorDeImagens;

public class TelaEditaFilme {
	
	private JFrame viewEditaFilme;
	private JTextField textFieldNome;
	private JTextField textFieldData;
	private JTextField textFieldDuracao;
	private JTextField textFieldGenero;
	private JTextField textFieldWorKeys;
	private JEditorPane editorPaneDescricao;
	private JTextField textFieldDiretor;
	private DadosLogin dl;
	
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
	
	public TelaEditaFilme(DadosLogin dadosLogin, Filme filme){
		viewListagemDeFilmes(dadosLogin, filme);
	}
	
	public void viewListagemDeFilmes(DadosLogin dadosLogin, final Filme filme) {
		
		viewEditaFilme = new JFrame();
		viewEditaFilme.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		viewEditaFilme.setBackground(new Color(0, 0, 255));
		viewEditaFilme.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		viewEditaFilme.getContentPane().setBackground(new Color(51, 102, 153));
		viewEditaFilme.getContentPane().setForeground(new Color(255, 255, 255));
		viewEditaFilme.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		viewEditaFilme.setTitle("Editar Filme");
		viewEditaFilme.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		viewEditaFilme.getContentPane().setLayout(null);
		
		try {
			dl = ControleDadosUsuarios.buscarDados(dadosLogin.getEmail());
		} catch (BancoDadosException bdex){
			JOptionPane.showMessageDialog(null, bdex.getMessage(), bdex.getTitulo(), JOptionPane.ERROR_MESSAGE);
		} catch (UsuarioException ee) {
			JOptionPane.showMessageDialog(null, ee.getMessage(), ee.getTitulo(), JOptionPane.ERROR_MESSAGE);
		} 
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(15, 100, 45, 25);
		lblNome.setForeground(new Color(255, 255, 255));
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblNome.setBackground(Color.WHITE);
		viewEditaFilme.getContentPane().add(lblNome);
		
		textFieldNome = new JTextField();
		textFieldNome.setText(filme.getNome());
		textFieldNome.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		textFieldNome.setBounds(15, 125, 575, 30);
		viewEditaFilme.getContentPane().add(textFieldNome);
		textFieldNome.setColumns(10);
		
		final String guardarFilme = filme.getNome();
		
		JLabel lblData = new JLabel("Data de Lançamento");
		lblData.setBounds(155, 220, 140, 25);
		lblData.setHorizontalAlignment(SwingConstants.CENTER);
		lblData.setForeground(new Color(255, 255, 255));
		lblData.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblData.setBackground(Color.WHITE);
		viewEditaFilme.getContentPane().add(lblData);
		
		textFieldData = new JTextField();
		textFieldData.setText(filme.getData());
		textFieldData.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		textFieldData.setBounds(155, 245, 140, 30);
		textFieldData.setToolTipText("Preencha esse campo da seguinte forma, 01/11/2017");
		textFieldData.setColumns(10);
		viewEditaFilme.getContentPane().add(textFieldData);
		
		JLabel lblDuraoDoFilme = new JLabel("Duração");
		lblDuraoDoFilme.setBounds(325, 220, 58, 25);
		lblDuraoDoFilme.setHorizontalAlignment(SwingConstants.CENTER);
		lblDuraoDoFilme.setForeground(new Color(255, 255, 255));
		lblDuraoDoFilme.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblDuraoDoFilme.setBackground(Color.WHITE);
		viewEditaFilme.getContentPane().add(lblDuraoDoFilme);
		
		textFieldDuracao = new JTextField();
		textFieldDuracao.setText(filme.getDuracaoFilme());
		textFieldDuracao.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		textFieldDuracao.setBounds(325, 245, 100, 30);
		textFieldDuracao.setToolTipText("Preencha esse campo da seguinte forma, 2h15m");
		textFieldDuracao.setColumns(10);
		viewEditaFilme.getContentPane().add(textFieldDuracao);
		
		JLabel lblGnero = new JLabel("Gênero");
		lblGnero.setBounds(15, 220, 55, 25);
		lblGnero.setHorizontalAlignment(SwingConstants.CENTER);
		lblGnero.setForeground(new Color(255, 255, 255));
		lblGnero.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblGnero.setBackground(Color.WHITE);
		viewEditaFilme.getContentPane().add(lblGnero);
		
		textFieldGenero = new JTextField();
		textFieldGenero.setText(filme.getGenero());
		textFieldGenero.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		textFieldGenero.setBounds(15, 245, 115, 30);
		textFieldGenero.setToolTipText("");
		textFieldGenero.setColumns(10);
		viewEditaFilme.getContentPane().add(textFieldGenero);
		
		JLabel lblPalavras = new JLabel("Palavras-chave(mínimo 2)");
		lblPalavras.setBounds(15, 165, 180, 25);
		lblPalavras.setHorizontalAlignment(SwingConstants.CENTER);
		lblPalavras.setForeground(new Color(255, 255, 255));
		lblPalavras.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblPalavras.setBackground(Color.WHITE);
		viewEditaFilme.getContentPane().add(lblPalavras);
		
		textFieldWorKeys = new JTextField();
		textFieldWorKeys.setText(filme.getWordKeys());
		textFieldWorKeys.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		textFieldWorKeys.setBounds(15, 190, 575, 30);
		textFieldWorKeys.setToolTipText("Preencha esse campo da seguinte forma, PalavraChave1-PalavraChave2...");
		textFieldWorKeys.setColumns(10);
		viewEditaFilme.getContentPane().add(textFieldWorKeys);
		
		JLabel lblDescrio = new JLabel("Descrição");
		lblDescrio.setBounds(15, 281, 72, 25);
		lblDescrio.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescrio.setForeground(new Color(255, 255, 255));
		lblDescrio.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblDescrio.setBackground(Color.WHITE);
		viewEditaFilme.getContentPane().add(lblDescrio);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 305, 575, 130);
		viewEditaFilme.getContentPane().add(scrollPane);
		
		editorPaneDescricao = new JEditorPane();
		scrollPane.setViewportView(editorPaneDescricao);
		editorPaneDescricao.setText(filme.getDescricao());
		editorPaneDescricao.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		editorPaneDescricao.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
		
		JLabel lblDireto = new JLabel("Diretor");
		lblDireto.setHorizontalAlignment(SwingConstants.CENTER);
		lblDireto.setForeground(Color.WHITE);
		lblDireto.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblDireto.setBackground(Color.WHITE);
		lblDireto.setBounds(460, 220, 50, 25);
		viewEditaFilme.getContentPane().add(lblDireto);
		
		textFieldDiretor = new JTextField();
		textFieldDiretor.setText(filme.getDiretor());
		textFieldDiretor.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		textFieldDiretor.setColumns(10);
		textFieldDiretor.setBounds(460, 245, 130, 30);
		viewEditaFilme.getContentPane().add(textFieldDiretor);
		
		JButton btnSalvar = new JButton("Salvar", GerenciadorDeImagens.OK);
		btnSalvar.setBounds(80, 450, 150, 25);
		btnSalvar.setForeground(new Color(0, 0, 0));
		btnSalvar.setToolTipText("Entrar");
		btnSalvar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnSalvar.setBackground(new Color(255, 255, 255));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				filme.setNome(textFieldNome.getText());
				filme.setData(textFieldData.getText());
				filme.setDescricao(editorPaneDescricao.getText());
				filme.setWordKeys(textFieldWorKeys.getText());
				filme.setGenero(textFieldGenero.getText());
				filme.setDuracaoFilme(textFieldDuracao.getText());
				filme.setDiretor(textFieldDiretor.getText());
				
				try {
					confereCampos(textFieldNome, textFieldWorKeys, textFieldData, textFieldDuracao, textFieldGenero, editorPaneDescricao, textFieldDiretor);
					contensHifen(textFieldWorKeys.getText());
					contemPalavrasChave(textFieldWorKeys.getText().trim());
					
					ControleDadosFilmes.alteraFilme(filme, guardarFilme);
					JOptionPane.showMessageDialog(null, "Filme editado com sucesso.", "Filme editado", JOptionPane.WARNING_MESSAGE);
					viewEditaFilme.dispose();
					new TelaListagemFilmes(dl);
				}
				catch (CadastroFilmeException cfe) {
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
		viewEditaFilme.getContentPane().add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar", GerenciadorDeImagens.CANCELAR);
		btnCancelar.setBounds(395, 450, 150, 25);
		btnCancelar.setForeground(new Color(0, 0, 0));
		btnCancelar.setToolTipText("Entrar");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCancelar.setBackground(new Color(255, 255, 255));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				viewEditaFilme.dispose();
				
				try {
					new TelaListagemFilmes(dl);
				} catch (BancoDadosException bdex) {
					JOptionPane.showMessageDialog(null, bdex.getMessage(), bdex.getTitulo(), JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		viewEditaFilme.getContentPane().add(btnCancelar);
		
		JLabel lblEditarFilme = new JLabel("Editar Filme");
		lblEditarFilme.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditarFilme.setForeground(Color.WHITE);
		lblEditarFilme.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 40));
		lblEditarFilme.setBounds(140, 20, 325, 55);
		viewEditaFilme.getContentPane().add(lblEditarFilme);
		
		viewEditaFilme.setSize(630, 530);
		viewEditaFilme.setVisible(true);
		viewEditaFilme.setResizable(true);
	}
}
