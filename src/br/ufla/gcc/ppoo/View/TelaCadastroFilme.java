package br.ufla.gcc.ppoo.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.ufla.gcc.ppoo.BancoDeDados.BancoDeDados;
import br.ufla.gcc.ppoo.Control.ControleDadosFilmes;
import br.ufla.gcc.ppoo.Control.ControleDadosUsuarios;
import br.ufla.gcc.ppoo.Dados.DadosLogin;
import br.ufla.gcc.ppoo.Dados.Filme;

public class TelaCadastroFilme {

	JFrame viewCadastroFilme;
	
	Filme filme = new Filme();
	BancoDeDados bancoDDados = new BancoDeDados();
	ControleDadosFilmes controlFilmes = new ControleDadosFilmes();
	ControleDadosUsuarios controlUser = new ControleDadosUsuarios();
	
	private JTextField textFieldNome;
	private JTextField textFieldData;
	private JTextField textFieldDuracao;
	private JTextField textFieldGenero;
	private JTextField textFieldWorKeys;
	private JEditorPane editorPaneDescricao;
	
	public void limpaCampos(){
		textFieldNome.setText(null);
		textFieldData.setText(null);
		textFieldDuracao.setText(null);
		textFieldGenero.setText(null);
		textFieldWorKeys.setText(null);
		editorPaneDescricao.setText(null);
	}
	
	public boolean confereCampos(JTextField textFieldNome, JTextField textFieldWorKeys, JTextField textFieldData,
								JTextField textFieldDuracao, JTextField textFieldGenero, JEditorPane editorPaneDescricao){
		boolean ok = false;
		
		if (textFieldNome.getText().length() > 0 && textFieldWorKeys.getText().length() > 0 && textFieldData.getText().length() > 0 
				&& textFieldDuracao.getText().length() > 0 && textFieldGenero.getText().length() > 0 && editorPaneDescricao.getText().length() > 0) {
			ok = true;
		}
		
		return ok;
	}
	
	public boolean contens(JTextField textFieldWorKeys){
		boolean bool = false;
		
		if(textFieldWorKeys.getText().contains("-")) {
			bool = true;
		}
		
		return bool;
	}
	
	public TelaCadastroFilme(DadosLogin dadosLogin){
		viewTelaCadastroFilme(dadosLogin);
	}
	
	public void viewTelaCadastroFilme(DadosLogin dadosLogin){
		
		viewCadastroFilme = new JFrame();
		viewCadastroFilme.setBackground(new Color(0, 0, 255));
		viewCadastroFilme.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		viewCadastroFilme.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		viewCadastroFilme.setVisible(false);
		viewCadastroFilme.getContentPane().setLayout(null);
		viewCadastroFilme.getContentPane().setBackground(new Color(255, 255, 255));
		viewCadastroFilme.getContentPane().setForeground(new Color(255, 255, 255));
		viewCadastroFilme.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		viewCadastroFilme.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		viewCadastroFilme.setTitle("Cadastrar Filme");
		viewCadastroFilme.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		viewCadastroFilme.setSize(618, 440);
		
		JLabel lblCadastro = new JLabel("Cadastrar Filme");
		lblCadastro.setBounds(130, 20, 240, 45);
		lblCadastro.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastro.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 30));
		viewCadastroFilme.getContentPane().add(lblCadastro);
		
		JLabel lblNewItem = new JLabel("");
		lblNewItem.setBounds(74, 22, 40, 40);
		lblNewItem.setVerticalAlignment(SwingConstants.TOP);
		lblNewItem.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/ufla/gcc/ppoo/Imagens/Novo_Item.png")));
		viewCadastroFilme.getContentPane().add(lblNewItem);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(6, 90, 45, 25);
		lblNome.setForeground(new Color(0, 0, 0));
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBackground(Color.GRAY);
		viewCadastroFilme.getContentPane().add(lblNome);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(50, 90, 423, 25);
		viewCadastroFilme.getContentPane().add(textFieldNome);
		textFieldNome.setColumns(10);
		
		JLabel lblData = new JLabel("Data de Lançamento");
		lblData.setBounds(8, 160, 130, 25);
		lblData.setHorizontalAlignment(SwingConstants.CENTER);
		lblData.setForeground(Color.BLACK);
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblData.setBackground(Color.GRAY);
		viewCadastroFilme.getContentPane().add(lblData);
		
		textFieldData = new JTextField();
		textFieldData.setBounds(142, 160, 70, 25);
//		textFieldData.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyTyped(KeyEvent arg0) {
//				if(textFieldData.getText().length() == 2) {
//		        	textFieldData.setText(textFieldData.getText() + '/');
//		        }				
//		        if(textFieldData.getText().length() == 5) {
//					textFieldData.setText(textFieldData.getText() + '/');
//				}
//		        if(textFieldData.getText().length() == 10) {
//		        	textFieldData.setEditable(false);
//		        }
//			}
//		});
		textFieldData.setToolTipText("Preencha esse campo da seguinte forma, 01/11/2017");
		textFieldData.setColumns(10);
		viewCadastroFilme.getContentPane().add(textFieldData);
		
		JLabel lblDuraoDoFilme = new JLabel("Duração");
		lblDuraoDoFilme.setBounds(218, 160, 55, 25);
		lblDuraoDoFilme.setHorizontalAlignment(SwingConstants.CENTER);
		lblDuraoDoFilme.setForeground(Color.BLACK);
		lblDuraoDoFilme.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDuraoDoFilme.setBackground(Color.GRAY);
		viewCadastroFilme.getContentPane().add(lblDuraoDoFilme);
		
		textFieldDuracao = new JTextField();
		textFieldDuracao.setBounds(275, 160, 70, 25);
		textFieldDuracao.setToolTipText("Preencha esse campo da seguinte forma, 2h15m");
		textFieldDuracao.setColumns(10);
		viewCadastroFilme.getContentPane().add(textFieldDuracao);
		
		JLabel lblGnero = new JLabel("Gênero");
		lblGnero.setBounds(350, 160, 50, 25);
		lblGnero.setHorizontalAlignment(SwingConstants.CENTER);
		lblGnero.setForeground(Color.BLACK);
		lblGnero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGnero.setBackground(Color.GRAY);
		viewCadastroFilme.getContentPane().add(lblGnero);
		
		textFieldGenero = new JTextField();
		textFieldGenero.setBounds(400, 160, 75, 25);
		textFieldGenero.setToolTipText("");
		textFieldGenero.setColumns(10);
		viewCadastroFilme.getContentPane().add(textFieldGenero);
		
		JLabel lblPalavras = new JLabel("Palavras-chaves(mínimo 2)");
		lblPalavras.setBounds(6, 125, 174, 25);
		lblPalavras.setHorizontalAlignment(SwingConstants.CENTER);
		lblPalavras.setForeground(Color.BLACK);
		lblPalavras.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPalavras.setBackground(Color.GRAY);
		viewCadastroFilme.getContentPane().add(lblPalavras);
		
		textFieldWorKeys = new JTextField();
		textFieldWorKeys.setBounds(180, 125, 295, 25);
		textFieldWorKeys.setToolTipText("Preencha esse campo da seguinte forma, 01/11/2017");
		textFieldWorKeys.setColumns(10);
		viewCadastroFilme.getContentPane().add(textFieldWorKeys);
		
		JLabel lblDescrio = new JLabel("Descrição");
		lblDescrio.setBounds(7, 190, 65, 25);
		lblDescrio.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescrio.setForeground(Color.BLACK);
		lblDescrio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescrio.setBackground(Color.GRAY);
		viewCadastroFilme.getContentPane().add(lblDescrio);
		
		editorPaneDescricao = new JEditorPane();
		editorPaneDescricao.setBounds(10, 215, 465, 155);
		editorPaneDescricao.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		editorPaneDescricao.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
		viewCadastroFilme.getContentPane().add(editorPaneDescricao);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(285, 380, 150, 25);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaCampos();
			}
		});
		btnCancelar.setForeground(new Color(0, 0, 0));
		btnCancelar.setToolTipText("Entrar");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCancelar.setBackground(new Color(255, 255, 255));
		viewCadastroFilme.getContentPane().add(btnCancelar);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(55, 380, 150, 25);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DadosLogin dl = controlUser.buscarDados(dadosLogin.getEmail());
				
				filme.setNome(textFieldNome.getText());
				filme.setData(textFieldData.getText());
				filme.setDescricao(editorPaneDescricao.getText());
				filme.setWordKeys(textFieldWorKeys.getText());
				filme.setGenero(textFieldGenero.getText());
				filme.setDuracaoFilme(textFieldDuracao.getText());
				
				if ( confereCampos(textFieldNome, textFieldWorKeys, textFieldData, textFieldDuracao, textFieldGenero, editorPaneDescricao) ) {
					if (contens(textFieldWorKeys)) {
						controlFilmes.CadastrarFilme(filme, dl.getId());
						JOptionPane.showMessageDialog(null, "Filme cadastrado com sucesso", "Filme cadastrado", JOptionPane.INFORMATION_MESSAGE);
						limpaCampos();
						
//						JOptionPane.showMessageDialog(null, slider.getValue());
						
					} else {
						JOptionPane.showMessageDialog(null, "Campo 'Palavras-chave' não está preenchido corretamente." 
								+ "\n" + " Para salvar o filme as palavras-chave precisa ser separadas por '-'.",
								"Campo Palavras-chave não corresponde ao padrão", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos para que seja possível salvar o filme.",
							"Erro Ao Salvar", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		btnSalvar.setForeground(new Color(0, 0, 0));
		btnSalvar.setToolTipText("Entrar");
		btnSalvar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnSalvar.setBackground(new Color(255, 255, 255));
		viewCadastroFilme.getContentPane().add(btnSalvar);
		
		viewCadastroFilme.setSize(500, 500);
		viewCadastroFilme.setVisible(true);		
	}
}
