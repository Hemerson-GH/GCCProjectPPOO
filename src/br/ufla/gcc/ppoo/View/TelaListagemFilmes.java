package br.ufla.gcc.ppoo.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import br.ufla.gcc.ppoo.Control.ControleDadosAvaliacao;
import br.ufla.gcc.ppoo.Control.ControleDadosComentarios;
import br.ufla.gcc.ppoo.Control.ControleDadosFilmes;
import br.ufla.gcc.ppoo.Control.ControleDadosUsuarios;
import br.ufla.gcc.ppoo.Dados.DadosLogin;
import br.ufla.gcc.ppoo.Dados.Filme;
import br.ufla.gcc.ppoo.Exceptions.AvaliacaoException;
import br.ufla.gcc.ppoo.Exceptions.BancoDadosException;
import br.ufla.gcc.ppoo.Exceptions.BuscasException;
import br.ufla.gcc.ppoo.Exceptions.ComentariosException;
import br.ufla.gcc.ppoo.Exceptions.FilmesException;
import br.ufla.gcc.ppoo.Exceptions.UsuarioException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

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
		return ControleDadosFilmes.BuscarFilmesUmUsuario(dl.getId());
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
	
	public void ConfereTabelaEditar(JTable tableFilmes) throws BuscasException{
		if (tableFilmes.getSelectedRow() == -1) {
			throw new BuscasException("Para editar um filme selecione a linha dele.", "Seleção inválida");
		}
	}
	
	public void ConfereTabelaDeletar(JTable tableFilmes) throws BuscasException{
		if (tableFilmes.getSelectedRow() == -1) {
			throw new BuscasException("Para excluir um filme selecione a linha dele.", "Seleção inválida");
		}
	}
	
	public void ConfereTabelaVisualizar(JTable tableFilmes) throws BuscasException{
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
			dl = ControleDadosUsuarios.BuscarDados(dadosLogin.getEmail());
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
		lblMeusFilme.setHorizontalAlignment(SwingConstants.CENTER);
		lblMeusFilme.setForeground(Color.WHITE);
		lblMeusFilme.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 40));
		
		JLabel lblLista = new JLabel("");
		lblLista.setIcon(new ImageIcon(TelaListagemFilmes.class.getResource("/br/ufla/gcc/ppoo/Imagens/lista.jpg")));
		lblLista.setVerticalAlignment(SwingConstants.TOP);
		lblLista.setBackground(new Color(51, 51, 255));
		
		JLabel lblSelecione = new JLabel("Selecione um filme para realizar alguma ação:");
		lblSelecione.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelecione.setForeground(Color.WHITE);
		lblSelecione.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 20));
		
		JButton btnVisualizar = new JButton("Visualizar");
		btnVisualizar.setIcon(new ImageIcon(TelaListagemFilmes.class.getResource("/br/ufla/gcc/ppoo/Imagens/filmes.png")));
		btnVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ConfereTabelaVisualizar(tableFilmes);
					String filmeSelect = (String) tableFilmes.getModel().getValueAt(tableFilmes.getSelectedRow() , 0);
					Filme filme = new Filme (ControleDadosFilmes.ConfereFilme(dl.getId(), filmeSelect));
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
		btnVisualizar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnVisualizar.setBackground(new Color(255, 255, 255));
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setIcon(new ImageIcon(TelaListagemFilmes.class.getResource("/br/ufla/gcc/ppoo/Imagens/editar.png")));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				try {
					ConfereTabelaEditar(tableFilmes);
					String filmeSelect = (String) tableFilmes.getModel().getValueAt(tableFilmes.getSelectedRow() , 0);	
					
					Filme filme = new Filme (ControleDadosFilmes.ConfereFilme(dl.getId(), filmeSelect));
					
					setStatus(false);
//					viewListagem.setVisible(false);
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
		btnEditar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnEditar.setBackground(new Color(255, 255, 255));
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.setIcon(new ImageIcon(TelaListagemFilmes.class.getResource("/br/ufla/gcc/ppoo/Imagens/deletar.png")));
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ConfereTabelaDeletar(tableFilmes);
					String filmeSelect = (String) tableFilmes.getModel().getValueAt(tableFilmes.getSelectedRow() , 0);		
					Filme filme = new Filme (ControleDadosFilmes.ConfereFilme(dl.getId(), filmeSelect));
					final int confirm = JOptionPane.showConfirmDialog(null, "Deseja excluir esse filme ?", "Excluir", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					
					if (JOptionPane.YES_OPTION == confirm) {
						ControleDadosComentarios.DeletaComentariosFilme(filme.getId_filme());
						ControleDadosAvaliacao.DeletaAvaliacaoDoFllme(filme.getId_filme());
						ControleDadosFilmes.DeletaFilme(filme);
	
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
		btnRemover.setFont(new Font("Arial", Font.PLAIN, 14));
		btnRemover.setBackground(new Color(255, 255, 255));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setStatus(false);
				viewListagem.dispose();
			}
		});
		btnCancelar.setIcon(new ImageIcon(TelaListagemFilmes.class.getResource("/br/ufla/gcc/ppoo/Imagens/btn-cancelar.png")));
		btnCancelar.setForeground(new Color(0, 0, 0));
		btnCancelar.setToolTipText("Cancelar");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCancelar.setBackground(new Color(255, 255, 255));
		GroupLayout groupLayout = new GroupLayout(viewListagem.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(290)
					.addComponent(lblLista, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(lblMeusFilme, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
					.addGap(332))
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
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblLista)
						.addComponent(lblMeusFilme, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGap(40)
					.addComponent(lblSelecione, GroupLayout.PREFERRED_SIZE, 25, Short.MAX_VALUE)
					.addComponent(scrollPaneList, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnVisualizar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnEditar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnRemover, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnCancelar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(16))
		);
		viewListagem.getContentPane().setLayout(groupLayout);
		
		viewListagem.setSize(915, 615);
		viewListagem.setVisible(true);	
		viewListagem.setResizable(true);
	}
}
