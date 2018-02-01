package br.ufla.gcc.ppoo.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import br.ufla.gcc.ppoo.control.ControleDadosAvaliacao;
import br.ufla.gcc.ppoo.control.ControleDadosComentarios;
import br.ufla.gcc.ppoo.control.ControleDadosFilmes;
import br.ufla.gcc.ppoo.control.ControleDadosUsuarios;
import br.ufla.gcc.ppoo.dados.DadosLogin;
import br.ufla.gcc.ppoo.dados.Filme;
import br.ufla.gcc.ppoo.exceptions.AvaliacaoException;
import br.ufla.gcc.ppoo.exceptions.BancoDadosException;
import br.ufla.gcc.ppoo.exceptions.BuscasException;
import br.ufla.gcc.ppoo.exceptions.ComentariosException;
import br.ufla.gcc.ppoo.exceptions.FilmesException;
import br.ufla.gcc.ppoo.exceptions.UsuarioException;
import br.ufla.gcc.ppoo.imagens.GerenciadorDeImagens;

public class TelaListagemFilmes {
	
	private JFrame viewListagem;
	private JTable tableFilmes;
	private JScrollPane scrollPaneList;
	private DadosLogin dl;
	private static boolean status = false;
	
	private ArrayList<Filme> listFilms = new ArrayList<>();
	
	public static boolean getStatus() { 
		return status;
	}
	
	public static void setStatus(boolean bool) {
		status = bool;
	}
	
	public ArrayList<Filme> atualizaLista(DadosLogin dl) throws BancoDadosException, FilmesException{
		return ControleDadosFilmes.buscarFilmesUmUsuario(dl.getId());
	}
	
	@SuppressWarnings("serial")
	public void constroiTabela(ArrayList<Filme> listFilms){
		
		int i = 0, n = listFilms.size();
		String[] titulosColunas = { "Filme", "Gênero", "Data de Lançamento", "Duração", "Diretor", "#Pontos" };
		Object [][]filmes = new Object[n][6];
		
		for (Filme filme : listFilms) {
			filmes[i][0] = filme.getNome();
			filmes[i][1] = filme.getGenero();
			filmes[i][2] = filme.getData();
			filmes[i][3] = filme.getDuracaoFilme();
			filmes[i][4] = filme.getDiretor();
			filmes[i][5] = filme.getPontos();
			i++;
		}
		
		tableFilmes.setModel(new DefaultTableModel(filmes, titulosColunas) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
	}
	
	public void confereTabelaEditar(JTable tableFilmes) throws BuscasException{
		if (tableFilmes.getSelectedRow() == -1) {
			throw new BuscasException("Para editar um filme selecione a linha dele.", "Seleção inválida");
		}
	}
	
	public void confereTabelaDeletar(JTable tableFilmes) throws BuscasException{
		if (tableFilmes.getSelectedRow() == -1) {
			throw new BuscasException("Para excluir um filme selecione a linha dele.", "Seleção inválida");
		}
	}
	
	public void confereTabelaVisualizar(JTable tableFilmes) throws BuscasException{
		if (tableFilmes.getSelectedRow() == -1) {
			throw new BuscasException("Para visualizar um filme selecione a linha dele.", "Seleção inválida");
		}
	}
	
	public TelaListagemFilmes(DadosLogin dadosLogin) throws BancoDadosException{
		viewListagemDeFilmes(dadosLogin);
	}
	
	public void viewListagemDeFilmes(DadosLogin dadosLogin) throws BancoDadosException {
		
		viewListagem = new JFrame();
		viewListagem.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		viewListagem.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				setStatus(false);
			}
		});
		viewListagem.setBackground(new Color(0, 0, 255));
		viewListagem.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		viewListagem.getContentPane().setBackground(new Color(51, 102, 153));
		viewListagem.getContentPane().setForeground(new Color(255, 255, 255));
		viewListagem.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		viewListagem.setTitle("Meus Filmes");
		viewListagem.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		
		setStatus(true);

		try {
			dl = ControleDadosUsuarios.buscarDados(dadosLogin.getEmail());
		} catch (BancoDadosException bdex){
			JOptionPane.showMessageDialog(null, bdex.getMessage(), bdex.getTitulo(), JOptionPane.ERROR_MESSAGE);
		} catch (UsuarioException ee) {
			JOptionPane.showMessageDialog(null, ee.getMessage(), ee.getTitulo(), JOptionPane.ERROR_MESSAGE);
		} 
		
		scrollPaneList = new JScrollPane();
		
		tableFilmes = new JTable();
		
		try {
			listFilms = atualizaLista(dl);
		} catch (FilmesException fe) {
			JOptionPane.showMessageDialog(null, fe.getMessage(), fe.getTitulo(), JOptionPane.ERROR_MESSAGE);
		}	
		
		constroiTabela(listFilms);
		
		tableFilmes.setFont(new Font("Microsoft JhengHei", Font.BOLD, 13));
		tableFilmes.clearSelection();
		tableFilmes.setFillsViewportHeight(true);
		tableFilmes.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);		
		scrollPaneList.setViewportView(tableFilmes);
		
		JLabel lblMeusFilme = new JLabel("Meus Filmes");
		lblMeusFilme.setIcon(GerenciadorDeImagens.LISTA_OK);
		lblMeusFilme.setHorizontalAlignment(SwingConstants.CENTER);
		lblMeusFilme.setForeground(Color.WHITE);
		lblMeusFilme.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 40));
		
		JLabel lblSelecione = new JLabel("Selecione um filme para realizar alguma ação:");
		lblSelecione.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelecione.setForeground(Color.WHITE);
		lblSelecione.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 20));
		
		JButton btnVisualizar = new JButton("Visualizar", GerenciadorDeImagens.FILME);
		btnVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					confereTabelaVisualizar(tableFilmes);
					String filmeSelect = (String) tableFilmes.getModel().getValueAt(tableFilmes.getSelectedRow() , 0);
					Filme filme = new Filme (ControleDadosFilmes.confereFilme(dl.getId(), filmeSelect));
					setStatus(false);
					viewListagem.dispose();
					
					new TelaVisualizaFilme(dl, filme, "TelaListagem");
				} catch (BuscasException be) {
					JOptionPane.showMessageDialog(null, be.getMessage(), be.getTitulo(), JOptionPane.ERROR_MESSAGE);
				} catch (BancoDadosException dbe) {
					JOptionPane.showMessageDialog(null, dbe.getMessage(), dbe.getTitulo(), JOptionPane.ERROR_MESSAGE);
				} catch (FilmesException fe) {
					JOptionPane.showMessageDialog(null, fe.getMessage(), fe.getTitulo(), JOptionPane.ERROR_MESSAGE);
				}	
			}
		});
		btnVisualizar.setForeground(new Color(0, 0, 0));
		btnVisualizar.setToolTipText("Visualizar item");
		btnVisualizar.setFont(new Font("Arial", Font.PLAIN, 15));
		btnVisualizar.setBackground(new Color(255, 255, 255));
		
		JButton btnEditar = new JButton("Editar", GerenciadorDeImagens.EDITAR);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				try {
					confereTabelaEditar(tableFilmes);
					String filmeSelect = (String) tableFilmes.getModel().getValueAt(tableFilmes.getSelectedRow() , 0);	
					
					Filme filme = new Filme (ControleDadosFilmes.confereFilme(dl.getId(), filmeSelect));
					
					setStatus(false);
					viewListagem.dispose();
					
					new TelaEditaFilme(dl, filme);
				} catch (BuscasException be) {
					JOptionPane.showMessageDialog(null, be.getMessage(), be.getTitulo(), JOptionPane.ERROR_MESSAGE);
				} catch (BancoDadosException dbe) {
					JOptionPane.showMessageDialog(null, dbe.getMessage(), dbe.getTitulo(), JOptionPane.ERROR_MESSAGE);
				} catch (FilmesException fe) {
					JOptionPane.showMessageDialog(null, fe.getMessage(), fe.getTitulo(), JOptionPane.ERROR_MESSAGE);
				}	
			}
		});
		btnEditar.setForeground(new Color(0, 0, 0));
		btnEditar.setToolTipText("Editar item");
		btnEditar.setFont(new Font("Arial", Font.PLAIN, 15));
		btnEditar.setBackground(new Color(255, 255, 255));
		
		JButton btnRemover = new JButton("Remover", GerenciadorDeImagens.DELETAR);
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					confereTabelaDeletar(tableFilmes);
					String filmeSelect = (String) tableFilmes.getModel().getValueAt(tableFilmes.getSelectedRow() , 0);		
					Filme filme = new Filme (ControleDadosFilmes.confereFilme(dl.getId(), filmeSelect));
					final int confirm = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir esse filme?", "Excluir", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					
					if (JOptionPane.YES_OPTION == confirm) {
						ControleDadosComentarios.deletaComentariosFilme(filme.getId_filme());
						ControleDadosAvaliacao.deletaAvaliacaoDoFllme(filme.getId_filme());
						ControleDadosFilmes.deletaFilme(filme);
	
						JOptionPane.showMessageDialog(null, "Filme deletado com sucesso.", "Filme deletado", JOptionPane.INFORMATION_MESSAGE);
						
						listFilms = atualizaLista(dl);
						constroiTabela(listFilms);
					}
				} catch (BuscasException be) {
					JOptionPane.showMessageDialog(null, be.getMessage(), be.getTitulo(), JOptionPane.ERROR_MESSAGE);
				} catch (BancoDadosException dbe) {
					JOptionPane.showMessageDialog(null, dbe.getMessage(), dbe.getTitulo(), JOptionPane.ERROR_MESSAGE);
				} catch (ComentariosException ce) {
					JOptionPane.showMessageDialog(null, ce.getMessage(), ce.getTitulo(), JOptionPane.ERROR_MESSAGE);
				} catch (AvaliacaoException ae) {
					JOptionPane.showMessageDialog(null, ae.getMessage(), ae.getTitulo(), JOptionPane.ERROR_MESSAGE);
				} catch (FilmesException fe) {
					JOptionPane.showMessageDialog(null, fe.getMessage(), fe.getTitulo(), JOptionPane.ERROR_MESSAGE);
				}	 
			}
		});
		btnRemover.setForeground(new Color(0, 0, 0));
		btnRemover.setToolTipText("Remover item");
		btnRemover.setFont(new Font("Arial", Font.PLAIN, 15));
		btnRemover.setBackground(new Color(255, 255, 255));
		
		JButton btnCancelar = new JButton("Cancelar", GerenciadorDeImagens.CANCELAR);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setStatus(false);
				viewListagem.dispose();
			}
		});
		btnCancelar.setForeground(new Color(0, 0, 0));
		btnCancelar.setToolTipText("Cancelar");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 15));
		btnCancelar.setBackground(new Color(255, 255, 255));
		
		GroupLayout groupLayout = new GroupLayout(viewListagem.getContentPane());
		
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(250)
					.addComponent(lblSelecione, GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
					.addGap(244))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(scrollPaneList, GroupLayout.DEFAULT_SIZE, 875, Short.MAX_VALUE)
					.addGap(14))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(btnVisualizar, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
					.addGap(130)
					.addComponent(btnEditar, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
					.addGap(140)
					.addComponent(btnRemover, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
					.addGap(125)
					.addComponent(btnCancelar, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
					.addGap(14))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(303)
					.addComponent(lblMeusFilme, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(332))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(lblMeusFilme, GroupLayout.PREFERRED_SIZE, 40, Short.MAX_VALUE)
					.addGap(40)
					.addComponent(lblSelecione, GroupLayout.PREFERRED_SIZE, 25, Short.MAX_VALUE)
					.addComponent(scrollPaneList, GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnVisualizar, Alignment.TRAILING)
						.addComponent(btnEditar, Alignment.TRAILING)
						.addComponent(btnRemover)
						.addComponent(btnCancelar, Alignment.TRAILING))
					.addGap(16))
		);
		viewListagem.getContentPane().setLayout(groupLayout);
		
		viewListagem.setSize(915, 615);
		viewListagem.setMinimumSize(new Dimension(915, 255));
		viewListagem.setVisible(true);	
		viewListagem.setResizable(true);
	}
}
