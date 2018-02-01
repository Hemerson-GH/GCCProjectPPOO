package br.ufla.gcc.ppoo.view;

import java.awt.Color;
import java.awt.Dimension;
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

import br.ufla.gcc.ppoo.control.ControleDadosFilmes;
import br.ufla.gcc.ppoo.control.ControleDadosUsuarios;
import br.ufla.gcc.ppoo.dados.DadosLogin;
import br.ufla.gcc.ppoo.dados.Filme;
import br.ufla.gcc.ppoo.exceptions.BancoDadosException;
import br.ufla.gcc.ppoo.exceptions.CadastroFilmeException;
import br.ufla.gcc.ppoo.exceptions.FilmeExistenteException;
import br.ufla.gcc.ppoo.exceptions.FilmesException;
import br.ufla.gcc.ppoo.exceptions.UsuarioException;
import br.ufla.gcc.ppoo.imagens.GerenciadorDeImagens;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

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
			throw new CadastroFilmeException("Preencha todos os campos para que seja poss�vel salvar o filme.", "Erro ao salvar");
		}
	}
	
	public void contensHifen(String textFieldWorKeys) throws CadastroFilmeException{
		if(!textFieldWorKeys.contains("-")) {
			throw new CadastroFilmeException("Campo 'Palavras-chave' n�o est� preenchido corretamente.\n"
					+ "Para salvar o filme as palavras-chave precisa ser separadas por '-'.", "Campo Palavras-Chave incorreto");
		}
	}
	
	public void contemPalavrasChave(String textFieldWorKeys) throws CadastroFilmeException {
		String[] wordKeyHifen = textFieldWorKeys.split("-");
		
		if (wordKeyHifen.length < 2) {
			throw new CadastroFilmeException("Insira mais de uma palavra-chave", "Campo Palavras-Chave incorreto");
		}
	}
	
	public void contemPalavrasChaveEmBranco(String textFieldWorKeys) throws CadastroFilmeException {
		String[] wordKeyHifen = textFieldWorKeys.split("-");
		boolean ok = false;
		int contHifens = 0, contPalaInva = 0;
		char caracter;
		
		for (int i = 0; i < wordKeyHifen.length; i++) {
			if (wordKeyHifen[i].isEmpty() || wordKeyHifen[i].trim().equals("")) {
				ok = true;
				contPalaInva++;
			}
		}
		
		for (int i = 0; i < textFieldWorKeys.length(); i++) {
			caracter = textFieldWorKeys.charAt(i);
			if (caracter == '-') {
				contHifens++;
			}
		}
		
		int tamPalaCorreto = wordKeyHifen.length - contPalaInva - 1;
		
		if (ok == true || tamPalaCorreto != contHifens) {
			throw new CadastroFilmeException("Preencha corretamente o campo das palavras-chave.\n", "Campo Palavras-Chave incorreto");
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
		
		try {
			dl = ControleDadosUsuarios.buscarDados(dadosLogin.getEmail());
		} catch (BancoDadosException bdex){
			JOptionPane.showMessageDialog(null, bdex.getMessage(), bdex.getTitulo(), JOptionPane.ERROR_MESSAGE);
		} catch (UsuarioException ee) {
			JOptionPane.showMessageDialog(null, ee.getMessage(), ee.getTitulo(), JOptionPane.ERROR_MESSAGE);
		} 
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setForeground(new Color(255, 255, 255));
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblNome.setBackground(Color.WHITE);
		
		textFieldNome = new JTextField();
		textFieldNome.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldNome.setText(filme.getNome());
		textFieldNome.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		textFieldNome.setColumns(10);
		
		final String guardarFilme = filme.getNome();
		
		JLabel lblData = new JLabel("Data de Lan�amento");
		lblData.setHorizontalAlignment(SwingConstants.LEFT);
		lblData.setForeground(new Color(255, 255, 255));
		lblData.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblData.setBackground(Color.WHITE);
		
		textFieldData = new JTextField();
		textFieldData.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldData.setText(filme.getData());
		textFieldData.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		textFieldData.setToolTipText("Preencha esse campo da seguinte forma, 01/11/2017");
		textFieldData.setColumns(10);
		
		JLabel lblDuraoDoFilme = new JLabel("Dura��o");
		lblDuraoDoFilme.setHorizontalAlignment(SwingConstants.LEFT);
		lblDuraoDoFilme.setForeground(new Color(255, 255, 255));
		lblDuraoDoFilme.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblDuraoDoFilme.setBackground(Color.WHITE);
		
		textFieldDuracao = new JTextField();
		textFieldDuracao.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldDuracao.setText(filme.getDuracaoFilme());
		textFieldDuracao.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		textFieldDuracao.setToolTipText("Preencha esse campo da seguinte forma, 2h15m");
		textFieldDuracao.setColumns(10);
		
		JLabel lblGnero = new JLabel("G�nero");
		lblGnero.setHorizontalAlignment(SwingConstants.LEFT);
		lblGnero.setForeground(new Color(255, 255, 255));
		lblGnero.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblGnero.setBackground(Color.WHITE);
		
		textFieldGenero = new JTextField();
		textFieldGenero.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldGenero.setText(filme.getGenero());
		textFieldGenero.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		textFieldGenero.setToolTipText("");
		textFieldGenero.setColumns(10);
		
		JLabel lblPalavras = new JLabel("Palavras-chave(m�nimo 2)");
		lblPalavras.setHorizontalAlignment(SwingConstants.LEFT);
		lblPalavras.setForeground(new Color(255, 255, 255));
		lblPalavras.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblPalavras.setBackground(Color.WHITE);
		
		textFieldWorKeys = new JTextField();
		textFieldWorKeys.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldWorKeys.setText(filme.getWordKeys());
		textFieldWorKeys.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		textFieldWorKeys.setToolTipText("Preencha esse campo da seguinte forma, PalavraChave1-PalavraChave2...");
		textFieldWorKeys.setColumns(10);
		
		JLabel lblDescrio = new JLabel("Descri��o");
		lblDescrio.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescrio.setForeground(new Color(255, 255, 255));
		lblDescrio.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblDescrio.setBackground(Color.WHITE);
		
		JScrollPane scrollPane = new JScrollPane();
		
		editorPaneDescricao = new JEditorPane();
		scrollPane.setViewportView(editorPaneDescricao);
		editorPaneDescricao.setText(filme.getDescricao());
		editorPaneDescricao.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		editorPaneDescricao.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
		
		JLabel lblDireto = new JLabel("Diretor");
		lblDireto.setHorizontalAlignment(SwingConstants.LEFT);
		lblDireto.setForeground(Color.WHITE);
		lblDireto.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblDireto.setBackground(Color.WHITE);
		
		textFieldDiretor = new JTextField();
		textFieldDiretor.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldDiretor.setText(filme.getDiretor());
		textFieldDiretor.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		textFieldDiretor.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar", GerenciadorDeImagens.OK);
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
					contemPalavrasChaveEmBranco(textFieldWorKeys.getText().trim());
					
					ControleDadosFilmes.alteraFilme(filme, guardarFilme);
					JOptionPane.showMessageDialog(null, "Filme editado com sucesso.", "Filme editado", JOptionPane.INFORMATION_MESSAGE);
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
		
		JButton btnCancelar = new JButton("Cancelar", GerenciadorDeImagens.CANCELAR);
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
		
		JLabel lblEditarFilme = new JLabel("Editar Filme");
		lblEditarFilme.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditarFilme.setForeground(Color.WHITE);
		lblEditarFilme.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 40));
		
		GroupLayout groupLayout = new GroupLayout(viewEditaFilme.getContentPane());
		
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(140)
					.addComponent(lblEditarFilme, GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
					.addGap(139))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(85)
					.addComponent(btnSalvar, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
					.addGap(150)
					.addComponent(btnCancelar, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
					.addGap(69))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldNome, GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
						.addComponent(lblNome))
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
						.addComponent(textFieldWorKeys, GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDescrio, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(textFieldGenero, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
											.addGap(18))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblGnero, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)))
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(textFieldData, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
										.addComponent(lblData, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addGap(24)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblDuraoDoFilme, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
											.addGap(53))
										.addComponent(textFieldDuracao, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
									.addGap(18)))
							.addGap(0)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblDireto, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
								.addComponent(textFieldDiretor, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)))
						.addComponent(lblPalavras, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(lblEditarFilme, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldNome, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(lblPalavras, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldWorKeys, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(31)
							.addComponent(textFieldGenero, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDireto, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldDiretor, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblData, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblGnero, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(31)
							.addComponent(textFieldData, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
						.addComponent(lblDuraoDoFilme, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(31)
							.addComponent(textFieldDuracao, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(24)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
							.addGap(57))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblDescrio, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addGap(111)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnCancelar)
									.addGap(2))
								.addComponent(btnSalvar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addContainerGap())))
		);
		viewEditaFilme.getContentPane().setLayout(groupLayout);
		
		viewEditaFilme.setSize(620, 530);
		viewEditaFilme.setMinimumSize(new Dimension(600, 510));
		viewEditaFilme.setVisible(true);
		viewEditaFilme.setResizable(true);
	}
}
