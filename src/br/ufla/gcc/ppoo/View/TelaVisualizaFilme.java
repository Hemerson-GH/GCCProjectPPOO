package br.ufla.gcc.ppoo.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.ufla.gcc.ppoo.control.ControleDadosAvaliacao;
import br.ufla.gcc.ppoo.control.ControleDadosComentarios;
import br.ufla.gcc.ppoo.control.ControleDadosFilmes;
import br.ufla.gcc.ppoo.control.ControleDadosUsuarios;
import br.ufla.gcc.ppoo.dados.Avaliacao;
import br.ufla.gcc.ppoo.dados.Comentarios;
import br.ufla.gcc.ppoo.dados.DadosLogin;
import br.ufla.gcc.ppoo.dados.Filme;
import br.ufla.gcc.ppoo.exceptions.AvaliacaoException;
import br.ufla.gcc.ppoo.exceptions.AvaliacaoExistenteException;
import br.ufla.gcc.ppoo.exceptions.BancoDadosException;
import br.ufla.gcc.ppoo.exceptions.ComentariosException;
import br.ufla.gcc.ppoo.exceptions.ConfereCampoException;
import br.ufla.gcc.ppoo.exceptions.FilmesException;
import br.ufla.gcc.ppoo.exceptions.UsuarioException;
import br.ufla.gcc.ppoo.imagens.GerenciadorDeImagens;

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
	
	public void confereCampoComentario(String commit) throws ConfereCampoException {
		if (commit.equals("") || commit.equals("Escreva um comentário sobre o filme...")) {
			throw new ConfereCampoException("Não é permitido enviar um comentário vazio, digite seu comentário antes de clicar aqui.",
					"Comentário inválido");
		}
	}
	
	public void confereTamanhoCampoComentario(String commit) throws ConfereCampoException {
		if (commit.length() > 144) {
			throw new ConfereCampoException("Seu comentário não pode ter mais que 144 caracteres.", "Comentário excede limite de caracteres");
		}
	}
	
	public TelaVisualizaFilme(DadosLogin dadosLogin, Filme filme, String confSaida){
		viewVisualizaFilme(dadosLogin, filme, confSaida);
	}
	
	@SuppressWarnings("unchecked")
	public void viewVisualizaFilme(DadosLogin dadosLogin, final Filme filme, final String confSaida) {
		
		viewVisualizaFilme = new JFrame();
		viewVisualizaFilme.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		viewVisualizaFilme.setBackground(new Color(0, 0, 255));
		viewVisualizaFilme.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		viewVisualizaFilme.getContentPane().setBackground(new Color(51, 102, 153));
		viewVisualizaFilme.getContentPane().setForeground(new Color(255, 255, 255));
		viewVisualizaFilme.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		viewVisualizaFilme.setTitle("Visualiza Filme");
		viewVisualizaFilme.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblVisualizarFilme = new JLabel("Visualizar Filme");
		lblVisualizarFilme.setFont(new Font("Microsoft JhengHei", Font.BOLD, 24));
		lblVisualizarFilme.setHorizontalAlignment(SwingConstants.LEFT);
		lblVisualizarFilme.setForeground(Color.WHITE);
		
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
			
			final JSlider sliderAvaliacao = new JSlider(JSlider.VERTICAL, 0, 5, 0);
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
			labelTable.put( new Integer(5), new JLabel(GerenciadorDeImagens.ESTRELA_5));
			labelTable.put( new Integer(4), new JLabel(GerenciadorDeImagens.ESTRELA_4));
			labelTable.put( new Integer(3), new JLabel(GerenciadorDeImagens.ESTRELA_3));
			labelTable.put( new Integer(2), new JLabel(GerenciadorDeImagens.ESTRELA_2));
			labelTable.put( new Integer(1), new JLabel(GerenciadorDeImagens.ESTRELA_1));
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
		
		JLabel lblNome = new JLabel(filme.getNome());
		lblNome.setToolTipText(filme.getNome());
		lblNome.setForeground(new Color(255, 255, 255));
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome.setFont(new Font("Microsoft JhengHei", Font.BOLD, 20));
		lblNome.setBackground(Color.WHITE);
		
		JLabel lblPalavras = new JLabel("Palavras-chave:");
		lblPalavras.setHorizontalAlignment(SwingConstants.CENTER);
		lblPalavras.setForeground(new Color(0, 0, 0));
		lblPalavras.setFont(new Font("Microsoft JhengHei", Font.BOLD, 18));
		lblPalavras.setBackground(Color.WHITE);
		
		JLabel lblFieldWorKeys = new JLabel(Filme.converteTexto(filme.getWordKeys()));
		lblFieldWorKeys.setForeground(Color.WHITE);
		lblFieldWorKeys.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 16));
		
		JLabel lblGenero = new JLabel("Gênero:");
		lblGenero.setHorizontalAlignment(SwingConstants.LEFT);
		lblGenero.setForeground(new Color(0, 0, 0));
		lblGenero.setFont(new Font("Microsoft JhengHei", Font.BOLD, 17));
		lblGenero.setBackground(Color.WHITE);
		
		JLabel lblTGenero = new JLabel(filme.getGenero());
		lblTGenero.setHorizontalAlignment(SwingConstants.LEFT);
		lblTGenero.setForeground(Color.WHITE);
		lblTGenero.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		lblTGenero.setToolTipText(filme.getGenero());
		
		JLabel lblData = new JLabel("Lançamento:");
		lblData.setHorizontalAlignment(SwingConstants.LEFT);
		lblData.setForeground(new Color(0, 0, 0));
		lblData.setFont(new Font("Microsoft JhengHei", Font.BOLD, 17));
		lblData.setBackground(Color.WHITE);
		
		JLabel lblFieldData = new JLabel(filme.getData());
		lblFieldData.setHorizontalAlignment(SwingConstants.LEFT);
		lblFieldData.setForeground(Color.WHITE);
		lblFieldData.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		
		JLabel lblDuraoDoFilme = new JLabel("Duração:");
		lblDuraoDoFilme.setHorizontalAlignment(SwingConstants.LEFT);
		lblDuraoDoFilme.setForeground(new Color(0, 0, 0));
		lblDuraoDoFilme.setFont(new Font("Microsoft JhengHei", Font.BOLD, 17));
		lblDuraoDoFilme.setBackground(Color.WHITE);
		
		JLabel lblTFieldDuracao = new JLabel(filme.getDuracaoFilme());
		lblTFieldDuracao.setHorizontalAlignment(SwingConstants.LEFT);
		lblTFieldDuracao.setForeground(Color.WHITE);
		lblTFieldDuracao.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		
		JLabel lblDireto = new JLabel("Diretor:");
		lblDireto.setHorizontalAlignment(SwingConstants.LEFT);
		lblDireto.setForeground(new Color(0, 0, 0));
		lblDireto.setFont(new Font("Microsoft JhengHei", Font.BOLD, 17));
		lblDireto.setBackground(Color.WHITE);
		
		JLabel lblTDiretor = new JLabel(filme.getDiretor());
		lblTDiretor.setHorizontalAlignment(SwingConstants.LEFT);
		lblTDiretor.setToolTipText(filme.getDiretor());
		lblTDiretor.setForeground(Color.WHITE);
		lblTDiretor.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));

		JLabel lblDescrio = new JLabel("Descrição:");
		lblDescrio.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescrio.setForeground(new Color(0, 0, 0));
		lblDescrio.setFont(new Font("Microsoft JhengHei", Font.BOLD, 17));
		lblDescrio.setBackground(Color.WHITE);
		
		JScrollPane scrollPaneDescricao = new JScrollPane();
		
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
		
		JScrollPane scrollPaneCommit = new JScrollPane();
		
		final JEditorPane editorPaneCommit = new JEditorPane();
		editorPaneCommit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				editorPaneCommit.setText("");
			}
		});
		editorPaneCommit.setText("Escreva um comentário sobre o filme...");
		editorPaneCommit.setFont(new Font("Microsoft JhengHei", Font.ITALIC, 13));
		scrollPaneCommit.setViewportView(editorPaneCommit);
		
		JScrollPane scrollPaneCommits = new JScrollPane();
		
		final JEditorPane editorPaneCommits = new JEditorPane();
		
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
		
		JButton btnComentar = new JButton("Comentar", GerenciadorDeImagens.COMENTARIO);
		btnComentar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					try {
						confereCampoComentario(editorPaneCommit.getText());
						confereTamanhoCampoComentario(editorPaneCommit.getText());
						
						Comentarios commit = new Comentarios(editorPaneCommit.getText(), dl.getId(), filme.getId_filme());
						ControleDadosComentarios.cadastrarComentario(commit);
						
						JOptionPane.showMessageDialog(null, "Seu comentário foi enviado.", "Comentário enviado", JOptionPane.INFORMATION_MESSAGE);
						
						editorPaneCommit.setText("Escreva um comentário sobre o filme...");
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
		
		JButton btnCancelar = new JButton("Sair", GerenciadorDeImagens.CANCELAR);
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
		
		GroupLayout groupLayout = new GroupLayout(viewVisualizaFilme.getContentPane());
		
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(lblTitulo, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(lblPalavras, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(lblComentarios, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(90)
					.addComponent(btnComentar, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
					.addGap(145)
					.addComponent(btnCancelar, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
					.addGap(79))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPaneCommit, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
						.addComponent(scrollPaneDescricao, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
						.addComponent(scrollPaneCommits, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE))
					.addGap(14))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDescrio, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblGenero, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTGenero, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTFieldDuracao, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
							.addGap(55))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDuraoDoFilme, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblData, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
						.addComponent(lblFieldData, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
					.addGap(42)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lblDireto, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
						.addComponent(lblTDiretor, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 495, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(99, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addComponent(lblFieldWorKeys, GroupLayout.PREFERRED_SIZE, 547, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(45, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(209)
					.addComponent(lblVisualizarFilme, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(214))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblVisualizarFilme, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
					.addGap(14)
					.addComponent(lblTitulo)
					.addGap(5)
					.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(lblPalavras)
					.addGap(5)
					.addComponent(lblFieldWorKeys, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDireto, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblGenero, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
							.addComponent(lblDuraoDoFilme, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
							.addComponent(lblData, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTFieldDuracao, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
								.addComponent(lblTDiretor, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblFieldData, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
							.addGap(10))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTGenero, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addComponent(lblDescrio, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(scrollPaneDescricao, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
					.addGap(5)
					.addComponent(lblComentarios, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(scrollPaneCommits, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(scrollPaneCommit, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnComentar, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(11))
		);
		viewVisualizaFilme.getContentPane().setLayout(groupLayout);
		
		viewVisualizaFilme.setResizable(true);
		viewVisualizaFilme.setSize(620, 695);
		viewVisualizaFilme.setMinimumSize(new Dimension(620, 570));
		viewVisualizaFilme.setVisible(true);		
	}
}
