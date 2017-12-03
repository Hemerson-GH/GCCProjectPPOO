package br.ufla.gcc.ppoo.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

import br.ufla.gcc.ppoo.BancoDeDados.BancoDeDados;
import br.ufla.gcc.ppoo.Control.ControleDadosFilmes;
import br.ufla.gcc.ppoo.Control.ControleDadosUsuarios;
import br.ufla.gcc.ppoo.Dados.DadosLogin;
import br.ufla.gcc.ppoo.Dados.Filme;

public class TelaEditaFilme {
	
	private JFrame viewEditaFilme;
	
	private JTextField textFieldNome;
	private JTextField textFieldData;
	private JTextField textFieldDuracao;
	private JTextField textFieldGenero;
	private JTextField textFieldWorKeys;
	private JEditorPane editorPaneDescricao;
	private JTextField textFieldDiretor;
	
	BancoDeDados bancoDDados = new BancoDeDados();
	
	public TelaEditaFilme(DadosLogin dadosLogin, Filme filme){
		bancoDDados.Conecta();
		viewListagemDeFilmes(dadosLogin, filme);
	}
	
	public ArrayList<Filme> atualizaLista(DadosLogin dl){
		return ControleDadosFilmes.BuscarFilmesUmUsuario(dl.getId());
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
	
	public void viewListagemDeFilmes(DadosLogin dadosLogin, Filme filme) {
		
		viewEditaFilme = new JFrame();
		viewEditaFilme.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		viewEditaFilme.setBackground(new Color(0, 0, 255));
		viewEditaFilme.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		viewEditaFilme.setVisible(false);
		viewEditaFilme.getContentPane().setLayout(null);
		viewEditaFilme.getContentPane().setBackground(new Color(51, 102, 153));
		viewEditaFilme.getContentPane().setForeground(new Color(255, 255, 255));
		viewEditaFilme.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		viewEditaFilme.setTitle("Editar Filme");
		viewEditaFilme.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 100, 45, 25);
		lblNome.setForeground(new Color(255, 255, 255));
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblNome.setBackground(Color.WHITE);
		viewEditaFilme.getContentPane().add(lblNome);
		
		textFieldNome = new JTextField();
		textFieldNome.setText(filme.getNome());
		textFieldNome.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 14));
		textFieldNome.setBounds(10, 125, 575, 30);
		viewEditaFilme.getContentPane().add(textFieldNome);
		textFieldNome.setColumns(10);
		
		String guardarFilme = filme.getNome();
		
		JLabel lblData = new JLabel("Data de Lançamento");
		lblData.setBounds(155, 220, 140, 25);
		lblData.setHorizontalAlignment(SwingConstants.CENTER);
		lblData.setForeground(new Color(255, 255, 255));
		lblData.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblData.setBackground(Color.WHITE);
		viewEditaFilme.getContentPane().add(lblData);
		
		textFieldData = new JTextField();
		textFieldData.setText(filme.getData());
		textFieldData.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 14));
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
		textFieldDuracao.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 14));
		textFieldDuracao.setBounds(325, 245, 100, 30);
		textFieldDuracao.setToolTipText("Preencha esse campo da seguinte forma, 2h15m");
		textFieldDuracao.setColumns(10);
		viewEditaFilme.getContentPane().add(textFieldDuracao);
		
		JLabel lblGnero = new JLabel("Gênero");
		lblGnero.setBounds(10, 220, 55, 25);
		lblGnero.setHorizontalAlignment(SwingConstants.CENTER);
		lblGnero.setForeground(new Color(255, 255, 255));
		lblGnero.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblGnero.setBackground(Color.WHITE);
		viewEditaFilme.getContentPane().add(lblGnero);
		
		textFieldGenero = new JTextField();
		textFieldGenero.setText(filme.getGenero());
		textFieldGenero.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 14));
		textFieldGenero.setBounds(10, 245, 115, 30);
		textFieldGenero.setToolTipText("");
		textFieldGenero.setColumns(10);
		viewEditaFilme.getContentPane().add(textFieldGenero);
		
		JLabel lblPalavras = new JLabel("Palavras-chaves(mínimo 2)");
		lblPalavras.setBounds(10, 165, 185, 25);
		lblPalavras.setHorizontalAlignment(SwingConstants.CENTER);
		lblPalavras.setForeground(new Color(255, 255, 255));
		lblPalavras.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblPalavras.setBackground(Color.WHITE);
		viewEditaFilme.getContentPane().add(lblPalavras);
		
		textFieldWorKeys = new JTextField();
		textFieldWorKeys.setText(filme.getWordKeys());
		textFieldWorKeys.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 14));
		textFieldWorKeys.setBounds(10, 190, 575, 30);
		textFieldWorKeys.setToolTipText("Preencha esse campo da seguinte forma, 01/11/2017");
		textFieldWorKeys.setColumns(10);
		viewEditaFilme.getContentPane().add(textFieldWorKeys);
		
		JLabel lblDescrio = new JLabel("Descrição");
		lblDescrio.setBounds(10, 281, 72, 25);
		lblDescrio.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescrio.setForeground(new Color(255, 255, 255));
		lblDescrio.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblDescrio.setBackground(Color.WHITE);
		viewEditaFilme.getContentPane().add(lblDescrio);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 305, 575, 130);
		viewEditaFilme.getContentPane().add(scrollPane);
		
		editorPaneDescricao = new JEditorPane();
		scrollPane.setViewportView(editorPaneDescricao);
		editorPaneDescricao.setText(filme.getDescricao());
		editorPaneDescricao.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 14));
		editorPaneDescricao.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
		
		JLabel lblDireto = new JLabel("Diretor");
		lblDireto.setHorizontalAlignment(SwingConstants.CENTER);
		lblDireto.setForeground(Color.WHITE);
		lblDireto.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblDireto.setBackground(Color.WHITE);
		lblDireto.setBounds(455, 220, 50, 25);
		viewEditaFilme.getContentPane().add(lblDireto);
		
		textFieldDiretor = new JTextField();
		textFieldDiretor.setText(filme.getDiretor());
		textFieldDiretor.setToolTipText("Preencha esse campo da seguinte forma, 2h15m");
		textFieldDiretor.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 14));
		textFieldDiretor.setColumns(10);
		textFieldDiretor.setBounds(455, 245, 130, 30);
		viewEditaFilme.getContentPane().add(textFieldDiretor);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(TelaCadastroFilme.class.getResource("/br/ufla/gcc/ppoo/Imagens/btn-ok.png")));
		btnSalvar.setBounds(60, 445, 150, 25);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DadosLogin dl = ControleDadosUsuarios.BuscarDados(dadosLogin.getEmail());
				
				filme.setNome(textFieldNome.getText());
				filme.setData(textFieldData.getText());
				filme.setDescricao(editorPaneDescricao.getText());
				filme.setWordKeys(textFieldWorKeys.getText());
				filme.setGenero(textFieldGenero.getText());
				filme.setDuracaoFilme(textFieldDuracao.getText());
				filme.setDiretor(textFieldDiretor.getText());
				
				ArrayList<Filme> listFilmes =  atualizaLista(dl);
				
				if (!filme.comparaFilme(listFilmes, filme.getNome(), guardarFilme)) {
					if (ControleDadosFilmes.AlteraFilme(filme)) {
						JOptionPane.showMessageDialog(null, "Filme Editado no banco de dados com sucesso.", "Filme Editado Com Sucesso", JOptionPane.WARNING_MESSAGE);
						viewEditaFilme.dispose();
						new TelaListagemFilmes(dl);
					} else {
						JOptionPane.showMessageDialog(null, "Erro ao editar filme no banco de dados.", "Erro Ao Editar Filme", JOptionPane.ERROR_MESSAGE);
					}	
				} else {
					JOptionPane.showMessageDialog(null, "Um filme com esse nome já está cadastrado", "Filme Existente", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSalvar.setForeground(new Color(0, 0, 0));
		btnSalvar.setToolTipText("Entrar");
		btnSalvar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnSalvar.setBackground(new Color(255, 255, 255));
		viewEditaFilme.getContentPane().add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(TelaCadastroFilme.class.getResource("/br/ufla/gcc/ppoo/Imagens/btn-cancelar.png")));
		btnCancelar.setBounds(390, 445, 150, 25);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				viewEditaFilme.dispose();
			}
		});
		btnCancelar.setForeground(new Color(0, 0, 0));
		btnCancelar.setToolTipText("Entrar");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCancelar.setBackground(new Color(255, 255, 255));
		viewEditaFilme.getContentPane().add(btnCancelar);
		
		JLabel lblEditarFilme = new JLabel("Editar Filme");
		lblEditarFilme.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditarFilme.setForeground(Color.WHITE);
		lblEditarFilme.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 40));
		lblEditarFilme.setBounds(120, 35, 325, 55);
		viewEditaFilme.getContentPane().add(lblEditarFilme);
		
		viewEditaFilme.setResizable(false);
		viewEditaFilme.setSize(600, 520);
		viewEditaFilme.setVisible(true);		
	}
}
