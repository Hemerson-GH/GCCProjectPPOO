package br.ufla.gcc.ppoo.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import br.ufla.gcc.ppoo.BancoDeDados.BancoDeDados;
import br.ufla.gcc.ppoo.Control.ControleDadosAvaliacao;
import br.ufla.gcc.ppoo.Control.ControleDadosFilmes;
import br.ufla.gcc.ppoo.Control.ControleDadosUsuarios;
import br.ufla.gcc.ppoo.Dados.Avaliacao;
import br.ufla.gcc.ppoo.Dados.DadosLogin;
import br.ufla.gcc.ppoo.Dados.Filme;

public class TelaVisualizaFilme {
	
	private JFrame viewVisualizaFilme;
	
	private BancoDeDados bancoDDados = new BancoDeDados();
	private ArrayList<Filme> listFilms = new ArrayList<>();
	
	public ArrayList<Filme> atualizaLista(DadosLogin dl){
		return ControleDadosFilmes.BuscarFilmesUmUsuario(dl.getId());
	}
	
	public boolean contemFilme(Filme filme, DadosLogin dadosLogin) {	
		if (filme.getId_user().equals(dadosLogin.getId())) {
				return true;
		}		
		return false;
	}
	
	public TelaVisualizaFilme(DadosLogin dadosLogin, Filme filme){
		bancoDDados.Conecta();
		viewVisualizaFilme(dadosLogin, filme);
	}
	
	@SuppressWarnings("unchecked")
	public void viewVisualizaFilme(DadosLogin dadosLogin, Filme filme) {
		
		DadosLogin dl = ControleDadosUsuarios.BuscarDados(dadosLogin.getEmail());
		listFilms = atualizaLista(dl);	
		boolean avaibleAvaliation = contemFilme(filme, dl);
		
		viewVisualizaFilme = new JFrame();
		viewVisualizaFilme.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		viewVisualizaFilme.setBackground(new Color(0, 0, 255));
		viewVisualizaFilme.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		viewVisualizaFilme.setVisible(false);
		viewVisualizaFilme.getContentPane().setLayout(null);
		viewVisualizaFilme.getContentPane().setBackground(new Color(51, 102, 153));
		viewVisualizaFilme.getContentPane().setForeground(new Color(255, 255, 255));
		viewVisualizaFilme.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		viewVisualizaFilme.setTitle("Visualiza Filme");
		viewVisualizaFilme.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblVisualizarFilme = new JLabel("Visualizar Filme");
		lblVisualizarFilme.setFont(new Font("Microsoft JhengHei", Font.BOLD, 24));
		lblVisualizarFilme.setHorizontalAlignment(SwingConstants.LEFT);
		lblVisualizarFilme.setForeground(Color.WHITE);
		lblVisualizarFilme.setBounds(188, 11, 185, 30);
		viewVisualizaFilme.getContentPane().add(lblVisualizarFilme);
		
		if (!avaibleAvaliation) {
			JSlider sliderAvaliacao = new JSlider(JSlider.VERTICAL, 0, 5, 0);
			sliderAvaliacao.setMinorTickSpacing(1);
			sliderAvaliacao.setBounds(485, 10, 75, 115);
			sliderAvaliacao.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			sliderAvaliacao.setBackground(Color.WHITE);
			sliderAvaliacao.setEnabled(true);
			sliderAvaliacao.setMajorTickSpacing(5);
			sliderAvaliacao.setPaintTicks(true);
			sliderAvaliacao.setPaintLabels(true);
			
			Font font = new Font("Arial", Font.PLAIN, 9);
			sliderAvaliacao.setFont(font);
			
			@SuppressWarnings("rawtypes")
			Hashtable labelTable = new Hashtable();
			labelTable.put( new Integer(5), new JLabel(new ImageIcon(TelaPrincipal.class.getResource("/br/ufla/gcc/ppoo/Imagens/estrela-5.jpg"))) );
			labelTable.put( new Integer(4), new JLabel(new ImageIcon(TelaPrincipal.class.getResource("/br/ufla/gcc/ppoo/Imagens/estrela-4.jpg"))) );
			labelTable.put( new Integer(3), new JLabel(new ImageIcon(TelaPrincipal.class.getResource("/br/ufla/gcc/ppoo/Imagens/estrela-3.jpg"))) );
			labelTable.put( new Integer(2), new JLabel(new ImageIcon(TelaPrincipal.class.getResource("/br/ufla/gcc/ppoo/Imagens/estrela-2.jpg"))) );
			labelTable.put( new Integer(1), new JLabel(new ImageIcon(TelaPrincipal.class.getResource("/br/ufla/gcc/ppoo/Imagens/estrela-1.jpg"))) );
			labelTable.put( new Integer(0), new JLabel( "0" ));
			sliderAvaliacao.setLabelTable(labelTable);
			
			viewVisualizaFilme.getContentPane().add(sliderAvaliacao);	
			
			JButton btnAvaliacao = new JButton("Salvar Avaliação");
			btnAvaliacao.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					Long pontAnt = filme.getPontos();
					
					filme.setPontos((long) sliderAvaliacao.getValue() + pontAnt);
					
					ArrayList<Avaliacao> listAvaliacao = ControleDadosAvaliacao.BuscarAvaliacao(dl.getId());
					
					if (Avaliacao.confereAvaliacao(listAvaliacao, filme, dl) == false) {
						ControleDadosFilmes.AvaliaFilme(filme);
						Avaliacao avaliacao = new Avaliacao(dl.getId(), filme.getId_filme(), true);
						ControleDadosAvaliacao.CadastrarAvaliacao(avaliacao);
						
						JOptionPane.showMessageDialog(null, "Avaliação salva com sucesso", "Avaliação salva", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Você não pode avaliar mais de uma vez um filme", "Falha na avaliação", JOptionPane.ERROR_MESSAGE);
					}
					
					pontAnt = (long) 0;
					
				}
			});
			btnAvaliacao.setBounds(460, 135, 125, 25);
			btnAvaliacao.setFont(new Font("Arial", Font.PLAIN, 12));
			btnAvaliacao.setBackground(new Color(255, 255, 255));	
			viewVisualizaFilme.getContentPane().add(btnAvaliacao);			
		}		
		
		JLabel lblTitulo = new JLabel("Título:");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(Color.BLACK);
		lblTitulo.setFont(new Font("Microsoft JhengHei", Font.BOLD, 18));
		lblTitulo.setBackground(Color.WHITE);
		lblTitulo.setBounds(5, 55, 60, 25);
		viewVisualizaFilme.getContentPane().add(lblTitulo);
		
		JLabel lblNome = new JLabel(filme.getNome());
		lblNome.setBounds(7, 85, 400, 25);
		lblNome.setForeground(new Color(255, 255, 255));
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome.setFont(new Font("Microsoft JhengHei", Font.BOLD, 20));
		lblNome.setBackground(Color.WHITE);
		viewVisualizaFilme.getContentPane().add(lblNome);	
		
		JLabel lblPalavras = new JLabel("Palavras-chave:");
		lblPalavras.setBounds(5, 120, 140, 25);
		lblPalavras.setHorizontalAlignment(SwingConstants.CENTER);
		lblPalavras.setForeground(new Color(0, 0, 0));
		lblPalavras.setFont(new Font("Microsoft JhengHei", Font.BOLD, 18));
		lblPalavras.setBackground(Color.WHITE);
		viewVisualizaFilme.getContentPane().add(lblPalavras);
		
		JLabel lblFieldWorKeys = new JLabel(Filme.converteTexto(filme.getWordKeys()));
		lblFieldWorKeys.setForeground(Color.WHITE);
		lblFieldWorKeys.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 16));
		lblFieldWorKeys.setBounds(8, 150, 475, 30);
		viewVisualizaFilme.getContentPane().add(lblFieldWorKeys);
		
		JLabel lblGenero = new JLabel("Gênero:");
		lblGenero.setBounds(7, 190, 70, 25);
		lblGenero.setHorizontalAlignment(SwingConstants.CENTER);
		lblGenero.setForeground(new Color(0, 0, 0));
		lblGenero.setFont(new Font("Microsoft JhengHei", Font.BOLD, 17));
		lblGenero.setBackground(Color.WHITE);
		viewVisualizaFilme.getContentPane().add(lblGenero);
		
		JLabel lblTGenero = new JLabel(filme.getGenero());
		lblTGenero.setForeground(Color.WHITE);
		lblTGenero.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		lblTGenero.setBounds(9, 215, 115, 30);
		lblTGenero.setToolTipText(filme.getGenero());
		viewVisualizaFilme.getContentPane().add(lblTGenero);
		
		JLabel lblData = new JLabel("Lançamento:");
		lblData.setBounds(297, 190, 110, 25);
		lblData.setHorizontalAlignment(SwingConstants.CENTER);
		lblData.setForeground(new Color(0, 0, 0));
		lblData.setFont(new Font("Microsoft JhengHei", Font.BOLD, 17));
		lblData.setBackground(Color.WHITE);
		viewVisualizaFilme.getContentPane().add(lblData);
		
		JLabel lblFieldData = new JLabel(filme.getData());
		lblFieldData.setForeground(Color.WHITE);
		lblFieldData.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		lblFieldData.setBounds(309, 215, 90, 30);
		viewVisualizaFilme.getContentPane().add(lblFieldData);
		
		JLabel lblDuraoDoFilme = new JLabel("Duração:");
		lblDuraoDoFilme.setBounds(154, 190, 75, 25);
		lblDuraoDoFilme.setHorizontalAlignment(SwingConstants.CENTER);
		lblDuraoDoFilme.setForeground(new Color(0, 0, 0));
		lblDuraoDoFilme.setFont(new Font("Microsoft JhengHei", Font.BOLD, 17));
		lblDuraoDoFilme.setBackground(Color.WHITE);
		viewVisualizaFilme.getContentPane().add(lblDuraoDoFilme);
		
		JLabel lblTFieldDuracao = new JLabel(filme.getDuracaoFilme());
		lblTFieldDuracao.setForeground(Color.WHITE);
		lblTFieldDuracao.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		lblTFieldDuracao.setBounds(154, 215, 85, 30);
		viewVisualizaFilme.getContentPane().add(lblTFieldDuracao);
		
		JLabel lblDireto = new JLabel("Diretor:");
		lblDireto.setHorizontalAlignment(SwingConstants.CENTER);
		lblDireto.setForeground(new Color(0, 0, 0));
		lblDireto.setFont(new Font("Microsoft JhengHei", Font.BOLD, 17));
		lblDireto.setBackground(Color.WHITE);
		lblDireto.setBounds(470, 190, 65, 25);
		viewVisualizaFilme.getContentPane().add(lblDireto);
		
		JLabel lblTDiretor = new JLabel(filme.getDiretor());
		lblTDiretor.setToolTipText(filme.getDiretor());
		lblTDiretor.setForeground(Color.WHITE);
		lblTDiretor.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		lblTDiretor.setBounds(475, 215, 110, 30);
		viewVisualizaFilme.getContentPane().add(lblTDiretor);

		JLabel lblDescrio = new JLabel("Descrição:");
		lblDescrio.setBounds(7, 255, 90, 25);
		lblDescrio.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescrio.setForeground(new Color(0, 0, 0));
		lblDescrio.setFont(new Font("Microsoft JhengHei", Font.BOLD, 17));
		lblDescrio.setBackground(Color.WHITE);
		viewVisualizaFilme.getContentPane().add(lblDescrio);
		
		JScrollPane scrollPaneDescricao = new JScrollPane();
		scrollPaneDescricao.setBounds(7, 285, 575, 100);
		viewVisualizaFilme.getContentPane().add(scrollPaneDescricao);
		
		JEditorPane editorPaneDescricao = new JEditorPane();
		editorPaneDescricao.setEditable(false);
		scrollPaneDescricao.setViewportView(editorPaneDescricao);
		editorPaneDescricao.setText(filme.getDescricao());
		editorPaneDescricao.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 14));
		editorPaneDescricao.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
		
		JButton btnCancelar = new JButton("Sair");
		btnCancelar.setIcon(new ImageIcon(TelaCadastroFilme.class.getResource("/br/ufla/gcc/ppoo/Imagens/btn-cancelar.png")));
		btnCancelar.setBounds(385, 590, 150, 25);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				viewVisualizaFilme.dispose();
				
				if (!TelaBuscarFilme.getStatus()) {
					new TelaBuscarFilme(dl);
				} else if (!TelaListagemFilmes.getStatus()) {
					new TelaListagemFilmes(dl);
				}				
			}
		});
		btnCancelar.setForeground(new Color(0, 0, 0));
		btnCancelar.setToolTipText("Clique aqui para sair");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCancelar.setBackground(new Color(255, 255, 255));
		viewVisualizaFilme.getContentPane().add(btnCancelar);
		
		JButton btnComentar = new JButton("Comentar");
		btnComentar.setFont(new Font("Arial", Font.PLAIN, 13));
		btnComentar.setBackground(new Color(255, 255, 255));
		btnComentar.setToolTipText("Clique aqui para salvar seu comentario");
		btnComentar.setBounds(88, 590, 150, 25);
		viewVisualizaFilme.getContentPane().add(btnComentar);
		
		JLabel lblComentarios = new JLabel("Comentários:");
		lblComentarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblComentarios.setForeground(Color.BLACK);
		lblComentarios.setFont(new Font("Microsoft JhengHei", Font.BOLD, 17));
		lblComentarios.setBackground(Color.WHITE);
		lblComentarios.setBounds(7, 396, 115, 25);
		viewVisualizaFilme.getContentPane().add(lblComentarios);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(7, 425, 575, 155);
		viewVisualizaFilme.getContentPane().add(scrollPane);
		
		JEditorPane editorPaneCommit = new JEditorPane();
		scrollPane.setViewportView(editorPaneCommit);
		
		viewVisualizaFilme.setResizable(false);
		viewVisualizaFilme.setSize(600, 655);
		viewVisualizaFilme.setVisible(true);		
	}
}
