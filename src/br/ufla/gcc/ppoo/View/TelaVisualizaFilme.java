package br.ufla.gcc.ppoo.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import br.ufla.gcc.ppoo.Control.ControleDadosComentarios;
import br.ufla.gcc.ppoo.Control.ControleDadosFilmes;
import br.ufla.gcc.ppoo.Control.ControleDadosUsuarios;
import br.ufla.gcc.ppoo.Dados.Avaliacao;
import br.ufla.gcc.ppoo.Dados.Comentarios;
import br.ufla.gcc.ppoo.Dados.DadosLogin;
import br.ufla.gcc.ppoo.Dados.Filme;

public class TelaVisualizaFilme {
	
	private JFrame viewVisualizaFilme;
	
	private BancoDeDados bancoDDados = new BancoDeDados();
//	private ArrayList<String> listaComentarios = new ArrayList<>();
	private String text = "";
	
//	public ArrayList<String> formatCommits(Long id_filme){
	public String formatCommits(Long id_filme){
		ArrayList<Comentarios> listCommits = ControleDadosComentarios.BuscarAvaliacao(id_filme);
//		ArrayList<String> listaComentariosFormatada = new ArrayList<>();
		String text = "";
		
		if (!listCommits.isEmpty()) {
			for (Comentarios commit : listCommits) {
//				listaComentariosFormatada.add(ControleDadosUsuarios.BuscaNomeUser(commit.getId_user_commit()) + ": " + commit.getCommit() + "\n");
				text += ControleDadosUsuarios.BuscaNomeUser(commit.getId_user_commit()) + ": " + commit.getCommit() + "\n";
			}
		} 
		
		return text;
	}
	
	
	public ArrayList<Filme> atualizaLista(DadosLogin dl){
		return ControleDadosFilmes.BuscarFilmesUmUsuario(dl.getId());
	}
	
	public boolean contemFilme(Filme filme, DadosLogin dadosLogin) {	
		if (filme.getId_user().equals(dadosLogin.getId())) {
				return true;
		}		
		return false;
	}
	
	public TelaVisualizaFilme(DadosLogin dadosLogin, Filme filme, String confSaida){
		bancoDDados.Conecta();
		viewVisualizaFilme(dadosLogin, filme, confSaida);
	}
	
	@SuppressWarnings("unchecked")
	public void viewVisualizaFilme(DadosLogin dadosLogin, Filme filme, String confSaida) {
		
		DadosLogin dl = ControleDadosUsuarios.BuscarDados(dadosLogin.getEmail());
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
		lblVisualizarFilme.setBounds(190, 10, 185, 30);
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
						if (ControleDadosFilmes.AvaliaFilme(filme)) {
							Avaliacao avaliacao = new Avaliacao(dl.getId(), filme.getId_filme(), true);
							
							try {
								ControleDadosAvaliacao.CadastrarAvaliacao(avaliacao);
								JOptionPane.showMessageDialog(null, "Avaliação salva com sucesso", "Avaliação salva", 
										 												JOptionPane.INFORMATION_MESSAGE);
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "Erro ao salvar avaliação:\n" + e.getCause() + 
										"\nEntre em contato com o administrador do sistema.", "Erro ao salvar", JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null, "Não foi possível avaliar o filme selecionado", "Erro ao avaliar", 
										JOptionPane.INFORMATION_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Você não pode avaliar mais de uma vez um filme", "Erro na avaliação",
								JOptionPane.ERROR_MESSAGE);
					}
					
					pontAnt = (long) 0;
					
				}
			});
			btnAvaliacao.setBounds(460, 135, 125, 25);
			btnAvaliacao.setFont(new Font("Arial", Font.PLAIN, 12));
			btnAvaliacao.setBackground(new Color(255, 255, 255));	
			viewVisualizaFilme.getContentPane().add(btnAvaliacao);			
			
			JLabel lblOnwer = new JLabel("(" + ControleDadosUsuarios.BuscaNomeUser(filme.getId_user()) + " | " +filme.getPontos()+ ")");
			lblOnwer.setToolTipText("(" + ControleDadosUsuarios.BuscaNomeUser(filme.getId_user()) + " | " +filme.getPontos()+ ")");
			lblOnwer.setForeground(Color.WHITE);
			lblOnwer.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
			lblOnwer.setBounds(5, 15, 165, 30);
			viewVisualizaFilme.getContentPane().add(lblOnwer);	
			
		}		
		
		JLabel lblTitulo = new JLabel("Título:");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(Color.BLACK);
		lblTitulo.setFont(new Font("Microsoft JhengHei", Font.BOLD, 18));
		lblTitulo.setBackground(Color.WHITE);
		lblTitulo.setBounds(10, 55, 60, 25);
		viewVisualizaFilme.getContentPane().add(lblTitulo);
		
		JLabel lblNome = new JLabel(filme.getNome());
		lblNome.setToolTipText(filme.getNome());
		lblNome.setBounds(10, 85, 495, 25);
		lblNome.setForeground(new Color(255, 255, 255));
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome.setFont(new Font("Microsoft JhengHei", Font.BOLD, 20));
		lblNome.setBackground(Color.WHITE);
		viewVisualizaFilme.getContentPane().add(lblNome);	
		
		JLabel lblPalavras = new JLabel("Palavras-chave:");
		lblPalavras.setBounds(10, 120, 140, 25);
		lblPalavras.setHorizontalAlignment(SwingConstants.CENTER);
		lblPalavras.setForeground(new Color(0, 0, 0));
		lblPalavras.setFont(new Font("Microsoft JhengHei", Font.BOLD, 18));
		lblPalavras.setBackground(Color.WHITE);
		viewVisualizaFilme.getContentPane().add(lblPalavras);
		
		JLabel lblFieldWorKeys = new JLabel(Filme.converteTexto(filme.getWordKeys()));
		lblFieldWorKeys.setForeground(Color.WHITE);
		lblFieldWorKeys.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 16));
		lblFieldWorKeys.setBounds(12, 150, 475, 30);
		viewVisualizaFilme.getContentPane().add(lblFieldWorKeys);
		
		JLabel lblGenero = new JLabel("Gênero:");
		lblGenero.setBounds(10, 190, 70, 25);
		lblGenero.setHorizontalAlignment(SwingConstants.CENTER);
		lblGenero.setForeground(new Color(0, 0, 0));
		lblGenero.setFont(new Font("Microsoft JhengHei", Font.BOLD, 17));
		lblGenero.setBackground(Color.WHITE);
		viewVisualizaFilme.getContentPane().add(lblGenero);
		
		JLabel lblTGenero = new JLabel(filme.getGenero());
		lblTGenero.setForeground(Color.WHITE);
		lblTGenero.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		lblTGenero.setBounds(10, 215, 115, 30);
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
		lblDescrio.setBounds(10, 255, 90, 25);
		lblDescrio.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescrio.setForeground(new Color(0, 0, 0));
		lblDescrio.setFont(new Font("Microsoft JhengHei", Font.BOLD, 17));
		lblDescrio.setBackground(Color.WHITE);
		viewVisualizaFilme.getContentPane().add(lblDescrio);
		
		JScrollPane scrollPaneDescricao = new JScrollPane();
		scrollPaneDescricao.setBounds(10, 285, 575, 100);
		viewVisualizaFilme.getContentPane().add(scrollPaneDescricao);
		
		JEditorPane editorPaneDescricao = new JEditorPane();
		editorPaneDescricao.setEditable(false);
		scrollPaneDescricao.setViewportView(editorPaneDescricao);
		editorPaneDescricao.setText(filme.getDescricao());
		editorPaneDescricao.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 14));
		editorPaneDescricao.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
		
		JLabel lblComentarios = new JLabel("Comentários:");
		lblComentarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblComentarios.setForeground(Color.BLACK);
		lblComentarios.setFont(new Font("Microsoft JhengHei", Font.BOLD, 17));
		lblComentarios.setBackground(Color.WHITE);
		lblComentarios.setBounds(10, 390, 115, 25);
		viewVisualizaFilme.getContentPane().add(lblComentarios);
		
		JScrollPane scrollPaneCommit = new JScrollPane();
		scrollPaneCommit.setBounds(10, 540, 575, 70);
		viewVisualizaFilme.getContentPane().add(scrollPaneCommit);
		
		JEditorPane editorPaneCommit = new JEditorPane();
		editorPaneCommit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				editorPaneCommit.setText("");
			}
		});
		editorPaneCommit.setText("Digite aqui seu comentário sobre esse filme...");
		editorPaneCommit.setFont(new Font("Microsoft JhengHei", Font.ITALIC, 13));
		scrollPaneCommit.setViewportView(editorPaneCommit);
		
		JScrollPane scrollPaneCommits = new JScrollPane();
		scrollPaneCommits.setBounds(10, 420, 575, 110);
		viewVisualizaFilme.getContentPane().add(scrollPaneCommits);
		
		JEditorPane editorPaneCommits = new JEditorPane();
//		listaComentarios = formatCommits(filme.getId_filme());
		text = formatCommits(filme.getId_filme());
		
//		if (listaComentarios.isEmpty()) {
		if (text.equals("")) {
			editorPaneCommits.setText("Seja o primeiro a comentar sobre esse filme...");
		} else {
			editorPaneCommits.setText(text);
		}
		
		editorPaneCommits.setEditable(false);
		editorPaneCommits.setFont(new Font("Microsoft JhengHei", Font.BOLD, 13));
		scrollPaneCommits.setViewportView(editorPaneCommits);
		
		JButton btnComentar = new JButton("Comentar");
		btnComentar.setIcon(new ImageIcon(TelaVisualizaFilme.class.getResource("/br/ufla/gcc/ppoo/Imagens/comentario.png")));
		btnComentar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!editorPaneCommit.getText().equals("") && !editorPaneCommit.getText().equals("Digite aqui seu comentário sobre esse filme...") ) {
					if (editorPaneCommit.getText().length() <= 144) {
						Comentarios commit = new Comentarios(editorPaneCommit.getText(), filme.getId_user(), filme.getId_filme());
						
						if (ControleDadosComentarios.CadastrarComentario(commit)) {
							JOptionPane.showMessageDialog(null, "Seu comentário foi enviado.", "Comentário enviado", JOptionPane.INFORMATION_MESSAGE);
							
							editorPaneCommit.setText("Digite aqui seu comentário sobre esse filme...");
							text = formatCommits(filme.getId_filme());
							editorPaneCommits.setText(text);
							
						} else {
							JOptionPane.showMessageDialog(null, "Não conseguimos enviar seu comentário.", "Erro ao enviar comentário", JOptionPane.WARNING_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Seu comentário não pode ter mais que 144 caracteres.", "Comentário excede limite de caracteres", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Não é permitido enviar um comentário vazio, digite seu comentário antes de clicar aqui.",
							"Comentário inválido", JOptionPane.ERROR_MESSAGE);
				}
//				editorPaneCommit.setText("");
//				listaComentarios = formatCommits(filme.getId_filme());
//				editorPaneCommits.setText(listaComentarios.toString());					
//				editorPaneCommits.setText(text);	
			}
		});
		btnComentar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnComentar.setBackground(new Color(255, 255, 255));
		btnComentar.setToolTipText("Clique aqui para salvar seu comentario");
		btnComentar.setBounds(90, 620, 150, 25);
		viewVisualizaFilme.getContentPane().add(btnComentar);
		
		JButton btnCancelar = new JButton("Sair");
		btnCancelar.setIcon(new ImageIcon(TelaCadastroFilme.class.getResource("/br/ufla/gcc/ppoo/Imagens/btn-cancelar.png")));
		btnCancelar.setBounds(385, 620, 150, 25);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				viewVisualizaFilme.dispose();
				
				if (confSaida.equals("TelaListagem")) {
					new TelaListagemFilmes(dl);
				} else if (confSaida.equals("TelaBuscar")) {
					new TelaBuscarFilme(dl);
				}
			}
		});
		btnCancelar.setForeground(new Color(0, 0, 0));
		btnCancelar.setToolTipText("Clique aqui para sair");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCancelar.setBackground(new Color(255, 255, 255));
		viewVisualizaFilme.getContentPane().add(btnCancelar);
		
		viewVisualizaFilme.setResizable(false);
		viewVisualizaFilme.setSize(600, 680);
		viewVisualizaFilme.setVisible(true);		
	}
}
