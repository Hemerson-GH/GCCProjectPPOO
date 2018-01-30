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

import br.ufla.gcc.ppoo.Control.ControleDadosAvaliacao;
import br.ufla.gcc.ppoo.Control.ControleDadosComentarios;
import br.ufla.gcc.ppoo.Control.ControleDadosFilmes;
import br.ufla.gcc.ppoo.Control.ControleDadosUsuarios;
import br.ufla.gcc.ppoo.Dados.Avaliacao;
import br.ufla.gcc.ppoo.Dados.Comentarios;
import br.ufla.gcc.ppoo.Dados.DadosLogin;
import br.ufla.gcc.ppoo.Dados.Filme;
import br.ufla.gcc.ppoo.Exceptions.AvaliacaoException;
import br.ufla.gcc.ppoo.Exceptions.AvaliacaoExistenteException;
import br.ufla.gcc.ppoo.Exceptions.BancoDadosException;
import br.ufla.gcc.ppoo.Exceptions.ComentariosException;
import br.ufla.gcc.ppoo.Exceptions.ConfereCampoException;
import br.ufla.gcc.ppoo.Exceptions.FilmesException;
import br.ufla.gcc.ppoo.Exceptions.UsuarioException;

public class TelaVisualizaFilme {
	
	private JFrame viewVisualizaFilme;
	private DadosLogin dl;
	private String text = "";
	private JLabel lblOnwer = new JLabel();
	
	public String formatCommits(Long id_filme) throws BancoDadosException, UsuarioException, ComentariosException{
		ArrayList<Comentarios> listCommits = ControleDadosComentarios.buscarAvaliacao(id_filme);
		String text = "";
		
		if (!listCommits.isEmpty()) {
			for (Comentarios commit : listCommits) {
				text += ControleDadosUsuarios.buscaNomeUser(commit.getId_user_commit()) + ": " + commit.getCommit() + "\n";
			}
		} 
		
		return text;
	}
	
	
	public ArrayList<Filme> atualizaLista(DadosLogin dl) throws BancoDadosException, FilmesException{
		return ControleDadosFilmes.buscarFilmesUmUsuario(dl.getId());
	}
	
	public boolean contemFilme(Filme filme, DadosLogin dadosLogin) {	
		if (filme.getId_user().equals(dadosLogin.getId())) {
				return true;
		}		
		return false;
	}
	
	public void ConfereCampoComentario(String commit) throws ConfereCampoException {
		if (commit.equals("") || commit.equals("Digite aqui seu comentário sobre esse filme...")) {
			throw new ConfereCampoException("Não é permitido enviar um comentário vazio, digite seu comentário antes de clicar aqui.",
					"Comentário inválido");
		}
	}
	
	public void ConfereTamanhoCampoComentario(String commit) throws ConfereCampoException {
		if (commit.length() > 144) {
			throw new ConfereCampoException("Seu comentário não pode ter mais que 144 caracteres.", "Comentário excede limite de caracteres");
		}
	}
	
	public TelaVisualizaFilme(DadosLogin dadosLogin, Filme filme, String confSaida){
		viewVisualizaFilme(dadosLogin, filme, confSaida);
	}
	
	@SuppressWarnings("unchecked")
	public void viewVisualizaFilme(DadosLogin dadosLogin, Filme filme, String confSaida) {
		
		viewVisualizaFilme = new JFrame();
		viewVisualizaFilme.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		viewVisualizaFilme.setBackground(new Color(0, 0, 255));
		viewVisualizaFilme.setFont(new Font("Times New Roman", Font.PLAIN, 14));
//		viewVisualizaFilme.setVisible(false);
		viewVisualizaFilme.getContentPane().setBackground(new Color(51, 102, 153));
		viewVisualizaFilme.getContentPane().setForeground(new Color(255, 255, 255));
		viewVisualizaFilme.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		viewVisualizaFilme.setTitle("Visualiza Filme");
		viewVisualizaFilme.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		viewVisualizaFilme.getContentPane().setLayout(null);
		
		JLabel lblVisualizarFilme = new JLabel("Visualizar Filme");
		lblVisualizarFilme.setFont(new Font("Microsoft JhengHei", Font.BOLD, 24));
		lblVisualizarFilme.setHorizontalAlignment(SwingConstants.LEFT);
		lblVisualizarFilme.setForeground(Color.WHITE);
		lblVisualizarFilme.setBounds(190, 10, 185, 30);
		viewVisualizaFilme.getContentPane().add(lblVisualizarFilme);
		
		try {
			dl = ControleDadosUsuarios.buscarDados(dadosLogin.getEmail());
		} catch (BancoDadosException bdex){
			JOptionPane.showMessageDialog(null, bdex.getMessage(), bdex.getTitulo(), JOptionPane.ERROR_MESSAGE);
		} catch (UsuarioException ee) {
			JOptionPane.showMessageDialog(null, ee.getMessage(), ee.getTitulo(), JOptionPane.ERROR_MESSAGE);
		} 
		
		boolean avaibleAvaliation = contemFilme(filme, dl);
		
		if (!avaibleAvaliation) {
			
			lblOnwer = new JLabel();
			
			try {
				lblOnwer = new JLabel("(" + ControleDadosUsuarios.buscaNomeUser(filme.getId_user()) + " | " +filme.getPontos()+ ")");
				lblOnwer.setToolTipText("(" + ControleDadosUsuarios.buscaNomeUser(filme.getId_user()) + " | " +filme.getPontos()+ ")");
			} catch (BancoDadosException dbe) {
				JOptionPane.showMessageDialog(null, dbe.getMessage(), dbe.getTitulo(), JOptionPane.ERROR_MESSAGE);
			} catch (UsuarioException ue) {
				JOptionPane.showMessageDialog(null, ue.getMessage(), ue.getTitulo(), JOptionPane.ERROR_MESSAGE);
			} 
			
			lblOnwer.setForeground(Color.WHITE);
			lblOnwer.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
			lblOnwer.setBounds(5, 15, 165, 30);
			viewVisualizaFilme.getContentPane().add(lblOnwer);	
			
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
					
					filme.setPontos((long) sliderAvaliacao.getValue() + filme.getPontos());
					
					try {
						ControleDadosFilmes.avaliaFilme(dl.getId(), filme);
						Avaliacao avaliacao = new Avaliacao(dl.getId(), filme.getId_filme(), true);
						ControleDadosAvaliacao.cadastrarAvaliacao(avaliacao);
						
						JOptionPane.showMessageDialog(null, "Avaliação salva com sucesso", "Avaliação salva", JOptionPane.INFORMATION_MESSAGE);
						
						lblOnwer.setText("(" + ControleDadosUsuarios.buscaNomeUser(filme.getId_user()) + " | " + filme.getPontos()+ ")");
						lblOnwer.setToolTipText("(" + ControleDadosUsuarios.buscaNomeUser(filme.getId_user()) + " | " + filme.getPontos()+ ")");
					} catch (BancoDadosException dbe) {
						JOptionPane.showMessageDialog(null, dbe.getMessage(), dbe.getTitulo(), JOptionPane.ERROR_MESSAGE);
					} catch (AvaliacaoException ae) {
						JOptionPane.showMessageDialog(null, ae.getMessage(), ae.getTitulo(), JOptionPane.ERROR_MESSAGE);
					} catch (AvaliacaoExistenteException aee) {
						JOptionPane.showMessageDialog(null, aee.getMessage(), aee.getTitulo(), JOptionPane.ERROR_MESSAGE);
					} catch (FilmesException fe) {
						JOptionPane.showMessageDialog(null, fe.getMessage(), fe.getTitulo(), JOptionPane.ERROR_MESSAGE);
					} catch (UsuarioException ue) {
						JOptionPane.showMessageDialog(null, ue.getMessage(), ue.getTitulo(), JOptionPane.ERROR_MESSAGE);
					} 
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
		lblDuraoDoFilme.setBounds(155, 190, 75, 25);
		lblDuraoDoFilme.setHorizontalAlignment(SwingConstants.CENTER);
		lblDuraoDoFilme.setForeground(new Color(0, 0, 0));
		lblDuraoDoFilme.setFont(new Font("Microsoft JhengHei", Font.BOLD, 17));
		lblDuraoDoFilme.setBackground(Color.WHITE);
		viewVisualizaFilme.getContentPane().add(lblDuraoDoFilme);
		
		JLabel lblTFieldDuracao = new JLabel(filme.getDuracaoFilme());
		lblTFieldDuracao.setForeground(Color.WHITE);
		lblTFieldDuracao.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		lblTFieldDuracao.setBounds(155, 215, 85, 30);
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
		lblTDiretor.setBounds(470, 215, 110, 30);
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
		editorPaneCommit.setText("Escreva um coment\u00E1rio sobre o filme...");
		editorPaneCommit.setFont(new Font("Microsoft JhengHei", Font.ITALIC, 13));
		scrollPaneCommit.setViewportView(editorPaneCommit);
		
		JScrollPane scrollPaneCommits = new JScrollPane();
		scrollPaneCommits.setBounds(10, 420, 575, 110);
		viewVisualizaFilme.getContentPane().add(scrollPaneCommits);
		
		JEditorPane editorPaneCommits = new JEditorPane();
		
		try {
			text = formatCommits(filme.getId_filme());
		} catch (BancoDadosException bdex){
			JOptionPane.showMessageDialog(null, bdex.getMessage(), bdex.getTitulo(), JOptionPane.ERROR_MESSAGE);
		} catch (UsuarioException ee) {
			JOptionPane.showMessageDialog(null, ee.getMessage(), ee.getTitulo(), JOptionPane.ERROR_MESSAGE);
		} catch (ComentariosException ce) {
			JOptionPane.showMessageDialog(null, ce.getMessage(), ce.getTitulo(), JOptionPane.ERROR_MESSAGE);
		}
		
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
					try {
						ConfereCampoComentario(editorPaneCommit.getText());
						ConfereTamanhoCampoComentario(editorPaneCommit.getText());
						
						Comentarios commit = new Comentarios(editorPaneCommit.getText(), dl.getId(), filme.getId_filme());
						ControleDadosComentarios.cadastrarComentario(commit);
						
						JOptionPane.showMessageDialog(null, "Seu comentário foi enviado.", "Comentário enviado", JOptionPane.INFORMATION_MESSAGE);
						
						editorPaneCommit.setText("Digite aqui seu comentário sobre esse filme...");
						text = formatCommits(filme.getId_filme());
						editorPaneCommits.setText(text);
					} catch (ConfereCampoException cce) {
						JOptionPane.showMessageDialog(null, cce.getMessage(), cce.getTitulo(), JOptionPane.ERROR_MESSAGE);
					} catch (BancoDadosException bdex){
						JOptionPane.showMessageDialog(null, bdex.getMessage(), bdex.getTitulo(), JOptionPane.ERROR_MESSAGE);
					} catch (ComentariosException ce) {
						JOptionPane.showMessageDialog(null, ce.getMessage(), ce.getTitulo(), JOptionPane.ERROR_MESSAGE);
					} catch (UsuarioException ue) {
						JOptionPane.showMessageDialog(null, ue.getMessage(), ue.getTitulo(), JOptionPane.ERROR_MESSAGE);
					} 
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
					try {
						new TelaListagemFilmes(dl);
					} catch (BancoDadosException bdex){
						JOptionPane.showMessageDialog(null, bdex.getMessage(), bdex.getTitulo(), JOptionPane.ERROR_MESSAGE);
					}
				} else if (confSaida.equals("TelaBuscar")) {
					try {
						new TelaBuscarFilme(dl);
					} catch (BancoDadosException bdex){
						JOptionPane.showMessageDialog(null, bdex.getMessage(), bdex.getTitulo(), JOptionPane.ERROR_MESSAGE);
					} catch (UsuarioException ue) {
						JOptionPane.showMessageDialog(null, ue.getMessage(), ue.getTitulo(), JOptionPane.ERROR_MESSAGE);
					} 
				}
			}
		});
		btnCancelar.setForeground(new Color(0, 0, 0));
		btnCancelar.setToolTipText("Clique aqui para sair");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCancelar.setBackground(new Color(255, 255, 255));
		viewVisualizaFilme.getContentPane().add(btnCancelar);
		
		viewVisualizaFilme.setResizable(false);
		viewVisualizaFilme.setSize(620, 695);
		viewVisualizaFilme.setVisible(true);		
	}
}
