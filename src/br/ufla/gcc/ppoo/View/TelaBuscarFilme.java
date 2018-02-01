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
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import br.ufla.gcc.ppoo.control.ControleDadosFilmes;
import br.ufla.gcc.ppoo.control.ControleDadosUsuarios;
import br.ufla.gcc.ppoo.dados.DadosLogin;
import br.ufla.gcc.ppoo.dados.Filme;
import br.ufla.gcc.ppoo.exceptions.BancoDadosException;
import br.ufla.gcc.ppoo.exceptions.BuscasException;
import br.ufla.gcc.ppoo.exceptions.FilmesException;
import br.ufla.gcc.ppoo.exceptions.UsuarioException;
import br.ufla.gcc.ppoo.imagens.GerenciadorDeImagens;

public class TelaBuscarFilme {

	private JFrame viewBuscarFilme;
	private JTable tableFilmes;
	private JScrollPane scrollPaneList;
	private JTextField textFieldBusca;
	private static boolean status = false;
	private ArrayList<Filme> listFilms = new ArrayList<>();
	private DadosLogin dl;

	public static boolean getStatus() { 
		return status;
	}
	
	public static void setStatus(boolean bool) {
		status = bool;
	}
	
	public ArrayList<Filme> atualizaLista(DadosLogin dl) throws BancoDadosException, FilmesException{
		return ControleDadosFilmes.buscarFilmesTodosUsuariosPontos();
	}
	
	public String confereNomeFilme(Filme filme, DadosLogin dadosLogin) throws BancoDadosException, UsuarioException {
		String nome = ControleDadosUsuarios.buscaNomeUser(filme.getId_user());
		
		if (nome.equals(dadosLogin.getNome())) { 
			nome = "Eu";
		} 
		
		return nome;
	}
	
	public void confereCampoBusca(JTextField textFieldBusca) throws BuscasException{
		if (textFieldBusca.getText().equals("")) {
			throw new BuscasException("Digite uma palavra para que possa ser feita a busca.", "Campo busca vazio");
		}
	}
	
	public void confereLista(ArrayList<Filme> listFilms) throws BuscasException{
		if (listFilms.isEmpty()) {
			iniciarTabela();
			throw new BuscasException( "Nenhum filme com essa palavra foi encontrado", "Filme não encontrado");
		}
	}
	
	public void confereTabela(JTable tableFilmes) throws BuscasException{
		if (tableFilmes.getSelectedRow() == -1) {
			throw new BuscasException("Para visuzalizar um filme selecione a linha dele.", "Seleção inválida");
		}
	}
	
	@SuppressWarnings("serial")
	public void constroiTabela(ArrayList<Filme> listFilms, DadosLogin dadosLogin) throws BancoDadosException, UsuarioException{
		
		int n = listFilms.size();
		String[] titulosColunas = { "Usuário", "Filme", "Gênero", "Data de Lançamento", "Duração", "Diretor", "#Pontos" };
		Object [][]filmes = new Object[n][7];
		int i = 0;	
		
		for (Filme filme : listFilms) {
			filmes[i][0] = confereNomeFilme(filme, dadosLogin);
			filmes[i][1] = filme.getNome();
			filmes[i][2] = filme.getGenero();
			filmes[i][3] = filme.getData();
			filmes[i][4] = filme.getDuracaoFilme();
			filmes[i][5] = filme.getDiretor();
			filmes[i][6] = filme.getPontos();
			i++;
		}
		
		tableFilmes.setModel(new DefaultTableModel(filmes, titulosColunas) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false,
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
	}
	
	@SuppressWarnings("serial")
	public void iniciarTabela(){
	
		String[] titulosColunas = { "Usuário", "Filme", "Gênero", "Data de Lançamento", "Duração", "Diretor", "#Pontos" };
		
		tableFilmes.setModel(new DefaultTableModel(	new Object[][] {}, titulosColunas) {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false,
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});	
	}
	
	public TelaBuscarFilme(DadosLogin dadosLogin) throws BancoDadosException, UsuarioException{
		viewTelaBuscarFilme(dadosLogin);
	}
	
	public void viewTelaBuscarFilme(final DadosLogin dadosLogin) {
		
		viewBuscarFilme = new JFrame();	
		viewBuscarFilme.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				setStatus(false);
			}
		});
		viewBuscarFilme.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		viewBuscarFilme.setBackground(new Color(0, 0, 255));
		viewBuscarFilme.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		viewBuscarFilme.getContentPane().setBackground(new Color(51, 102, 153));
		viewBuscarFilme.getContentPane().setForeground(new Color(255, 255, 255));
		viewBuscarFilme.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		viewBuscarFilme.setTitle("Buscar Filmes");
		viewBuscarFilme.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		
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
		iniciarTabela();
		
		tableFilmes.setFont(new Font("Microsoft JhengHei", Font.BOLD, 12));
		tableFilmes.clearSelection();
		tableFilmes.setFillsViewportHeight(true);
		tableFilmes.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);		
		scrollPaneList.setViewportView(tableFilmes);
		
		JLabel lblSelecionar = new JLabel("Selecione um filme para realizar alguma ação:");
		lblSelecionar.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelecionar.setForeground(Color.WHITE);
		lblSelecionar.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 20));
		
		textFieldBusca = new JTextField();
		textFieldBusca.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		textFieldBusca.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnBuscar.setBackground(new Color(255, 255, 255));
		btnBuscar.setToolTipText("Clique aqui para listar os filmes a partir de sua busca");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					confereCampoBusca(textFieldBusca);
					listFilms = atualizaLista(dl);
					listFilms = Filme.pesquisaFilme(listFilms, textFieldBusca.getText());
					confereLista(listFilms);					
					constroiTabela(listFilms, dadosLogin);
				} catch (BuscasException be) {
					JOptionPane.showMessageDialog(null, be.getMessage(), be.getTitulo(), JOptionPane.ERROR_MESSAGE);
				} catch (BancoDadosException dbe) {
					JOptionPane.showMessageDialog(null, dbe.getMessage(), dbe.getTitulo(), JOptionPane.ERROR_MESSAGE);
				} catch (FilmesException fe) {
					JOptionPane.showMessageDialog(null, fe.getMessage(), fe.getTitulo(), JOptionPane.ERROR_MESSAGE);
				} catch (UsuarioException ue) {
					JOptionPane.showMessageDialog(null, ue.getMessage(), ue.getTitulo(), JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton btnBuscarTodos = new JButton("Buscar Todos");
		btnBuscarTodos.setFont(new Font("Arial", Font.PLAIN, 14));
		btnBuscarTodos.setBackground(new Color(255, 255, 255));
		btnBuscarTodos.setToolTipText("Clique aqui para listar todos os filmes de todos os usuários");
		btnBuscarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					listFilms = atualizaLista(dl);
					confereLista(listFilms);
					constroiTabela(listFilms, dadosLogin);
				} catch (BancoDadosException dbe) {
					JOptionPane.showMessageDialog(null, dbe.getMessage(), dbe.getTitulo(), JOptionPane.ERROR_MESSAGE);
				} catch (FilmesException fe) {
					JOptionPane.showMessageDialog(null, fe.getMessage(), fe.getTitulo(), JOptionPane.ERROR_MESSAGE);
				} catch (BuscasException be) {
					JOptionPane.showMessageDialog(null, be.getMessage(), be.getTitulo(), JOptionPane.ERROR_MESSAGE);
				} catch (UsuarioException ue) {
					JOptionPane.showMessageDialog(null, ue.getMessage(), ue.getTitulo(), JOptionPane.ERROR_MESSAGE);
				} 		
			}
		});
		
		JLabel lblBuscar = new JLabel("Buscar Filmes");
		lblBuscar.setIcon(GerenciadorDeImagens.PROCURAR_GRANDE);
		lblBuscar.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 40));
		lblBuscar.setForeground(new Color(255, 255, 255));
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnVisualizar = new JButton("Visualizar", GerenciadorDeImagens.FILME);
		btnVisualizar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnVisualizar.setBackground(new Color(255, 255, 255));
		btnVisualizar.setForeground(new Color(0, 0, 0));
		btnVisualizar.setToolTipText("Visualizar filme selecionado");
		btnVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					confereTabela(tableFilmes);
					String filmeSelect = (String) tableFilmes.getModel().getValueAt(tableFilmes.getSelectedRow() , 1);		
					String donoFilme = (String) tableFilmes.getModel().getValueAt(tableFilmes.getSelectedRow() , 0);
					
					if (donoFilme.equals("Eu")) { donoFilme = dl.getNome(); }
					
					Filme filme = new Filme (ControleDadosFilmes.confereFilme(donoFilme, filmeSelect));
					setStatus(false);
					viewBuscarFilme.dispose();
					
					new TelaVisualizaFilme(dl, filme, "TelaBuscar");
				} catch (BuscasException be) {
					JOptionPane.showMessageDialog(null, be.getMessage(), be.getTitulo(), JOptionPane.ERROR_MESSAGE);
				} catch (BancoDadosException dbe) {
					JOptionPane.showMessageDialog(null, dbe.getMessage(), dbe.getTitulo(), JOptionPane.ERROR_MESSAGE);
				} catch (UsuarioException ue) {
					JOptionPane.showMessageDialog(null, ue.getMessage(), ue.getTitulo(), JOptionPane.ERROR_MESSAGE);
				} catch (FilmesException fe) {
					JOptionPane.showMessageDialog(null, fe.getMessage(), fe.getTitulo(), JOptionPane.ERROR_MESSAGE);
				}			
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar", GerenciadorDeImagens.CANCELAR);
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCancelar.setBackground(new Color(255, 255, 255));
		btnCancelar.setForeground(new Color(0, 0, 0));
		btnCancelar.setToolTipText("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setStatus(false);
				viewBuscarFilme.dispose();
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(viewBuscarFilme.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(250)
					.addComponent(lblSelecionar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(254))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(317)
					.addComponent(lblBuscar, GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
					.addGap(287))
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(textFieldBusca, GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
							.addGap(67)
							.addComponent(btnBuscar, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
							.addGap(68)
							.addComponent(btnBuscarTodos, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnVisualizar, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
							.addGap(605)
							.addComponent(btnCancelar, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(scrollPaneList, GroupLayout.DEFAULT_SIZE, 866, Short.MAX_VALUE)))
					.addGap(24))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(15)
					.addComponent(lblBuscar, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textFieldBusca, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnBuscarTodos, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)))
					.addGap(20)
					.addComponent(lblSelecionar, GroupLayout.PREFERRED_SIZE, 25, Short.MAX_VALUE)
					.addComponent(scrollPaneList, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnVisualizar, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
						.addComponent(btnCancelar, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
					.addGap(21))
		);
		viewBuscarFilme.getContentPane().setLayout(groupLayout);
		
		viewBuscarFilme.setMinimumSize(new Dimension(915, 305));
		viewBuscarFilme.setSize(915, 600);
		viewBuscarFilme.setResizable(true);
		viewBuscarFilme.setVisible(true);		
	}
}

