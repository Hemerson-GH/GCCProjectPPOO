package br.ufla.gcc.ppoo.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

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

import br.ufla.gcc.ppoo.Control.ControleDadosFilmes;
import br.ufla.gcc.ppoo.Control.ControleDadosUsuarios;
import br.ufla.gcc.ppoo.Dados.DadosLogin;
import br.ufla.gcc.ppoo.Dados.Filme;
import br.ufla.gcc.ppoo.Exceptions.BancoDadosException;
import br.ufla.gcc.ppoo.Exceptions.BuscasException;
import br.ufla.gcc.ppoo.Exceptions.FilmesException;
import br.ufla.gcc.ppoo.Exceptions.UsuarioException;
import br.ufla.gcc.ppoo.Imagens.GerenciadorDeImagens;

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
	
	public ArrayList<Filme> AtualizaLista(DadosLogin dl) throws BancoDadosException, FilmesException{
		return ControleDadosFilmes.BuscarFilmesTodosUsuariosPontos();
	}
	
	public String ConfereNomeFilme(Filme filme, DadosLogin dadosLogin) throws BancoDadosException, UsuarioException {
		String nome = ControleDadosUsuarios.BuscaNomeUser(filme.getId_user());
		
		if (nome.equals(dadosLogin.getNome())) { 
			nome = "Eu";
		} 
		
		return nome;
	}
	
	public void ConfereCampoBusca(JTextField textFieldBusca) throws BuscasException{
		if (textFieldBusca.getText().equals("")) {
			throw new BuscasException("Digite uma palavra para que possa ser feita a busca.", "Campo busca vazio");
		}
	}
	
	public void ConfereLista(ArrayList<Filme> listFilms) throws BuscasException{
		if (listFilms.isEmpty()) {
			iniciarTabela();
			throw new BuscasException( "Nenhum filme com essa palavra foi encontrado", "Filme não encontrado");
		}
	}
	
	public void ConfereTabela(JTable tableFilmes) throws BuscasException{
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
			filmes[i][0] = ConfereNomeFilme(filme, dadosLogin);
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
	
	public void viewTelaBuscarFilme(DadosLogin dadosLogin) {
		
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
		viewBuscarFilme.getContentPane().setLayout(null);
		
		setStatus(true);
		
		try {
			dl = ControleDadosUsuarios.BuscarDados(dadosLogin.getEmail());
		} catch (BancoDadosException bdex){
			JOptionPane.showMessageDialog(null, bdex.getMessage(), bdex.getTitulo(), JOptionPane.ERROR_MESSAGE);
		} catch (UsuarioException ee) {
			JOptionPane.showMessageDialog(null, ee.getMessage(), ee.getTitulo(), JOptionPane.ERROR_MESSAGE);
		} 
		
		scrollPaneList = new JScrollPane();
		scrollPaneList.setBounds(10, 175, 875, 320);
		viewBuscarFilme.getContentPane().add(scrollPaneList);
		
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
		lblSelecionar.setBounds(250, 150, 405, 25);
		viewBuscarFilme.getContentPane().add(lblSelecionar);
		
		textFieldBusca = new JTextField();
		textFieldBusca.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		textFieldBusca.setBounds(10, 85, 500, 45);
		viewBuscarFilme.getContentPane().add(textFieldBusca);
		textFieldBusca.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnBuscar.setBackground(new Color(255, 255, 255));
		btnBuscar.setToolTipText("Clique aqui para listar os filmes a partir de sua busca");
		btnBuscar.setBounds(545, 85, 115, 45);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ConfereCampoBusca(textFieldBusca);
					listFilms = AtualizaLista(dl);
					listFilms = Filme.pesquisaFilme(listFilms, textFieldBusca.getText());
					ConfereLista(listFilms);					
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
		viewBuscarFilme.getContentPane().add(btnBuscar);
		
		JButton btnBuscarTodos = new JButton("Buscar Todos");
		btnBuscarTodos.setFont(new Font("Arial", Font.PLAIN, 14));
		btnBuscarTodos.setBackground(new Color(255, 255, 255));
		btnBuscarTodos.setToolTipText("Clique aqui para listar todos os filmes de todos os usuários");
		btnBuscarTodos.setBounds(720, 85, 125, 45);
		btnBuscarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					listFilms = AtualizaLista(dl);
					ConfereLista(listFilms);
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
		viewBuscarFilme.getContentPane().add(btnBuscarTodos);
		
		JLabel lblBuscar = new JLabel("Buscar Filmes");
		lblBuscar.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 40));
		lblBuscar.setForeground(new Color(255, 255, 255));
		lblBuscar.setBounds(340, 15, 250, 55);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		viewBuscarFilme.getContentPane().add(lblBuscar);
		
		JLabel lblIconSearch = new JLabel(GerenciadorDeImagens.PROCURAR_GRANDE);
		lblIconSearch.setBackground(new Color(51, 51, 255));
		lblIconSearch.setBounds(295, 20, 40, 40);
		lblIconSearch.setVerticalAlignment(SwingConstants.TOP);
		viewBuscarFilme.getContentPane().add(lblIconSearch);
		
		JButton btnVisualizar = new JButton("Visualizar", GerenciadorDeImagens.FILME);
		btnVisualizar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnVisualizar.setBackground(new Color(255, 255, 255));
		btnVisualizar.setForeground(new Color(0, 0, 0));
		btnVisualizar.setToolTipText("Visualizar filme selecionado");
		btnVisualizar.setBounds(135, 520, 135, 30);
		btnVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ConfereTabela(tableFilmes);
					String filmeSelect = (String) tableFilmes.getModel().getValueAt(tableFilmes.getSelectedRow() , 1);		
					String donoFilme = (String) tableFilmes.getModel().getValueAt(tableFilmes.getSelectedRow() , 0);
					
					if (donoFilme.equals("Eu")) { donoFilme = dl.getNome(); }
					
					Filme filme = new Filme (ControleDadosFilmes.ConfereFilme(donoFilme, filmeSelect));
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
		viewBuscarFilme.getContentPane().add(btnVisualizar);
		
		JButton btnCancelar = new JButton("Cancelar", GerenciadorDeImagens.CANCELAR);
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCancelar.setBackground(new Color(255, 255, 255));
		btnCancelar.setForeground(new Color(0, 0, 0));
		btnCancelar.setToolTipText("Cancelar");
		btnCancelar.setBounds(620, 520, 135, 30);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setStatus(false);
				viewBuscarFilme.dispose();
			}
		});
		viewBuscarFilme.getContentPane().add(btnCancelar);
				
		viewBuscarFilme.setResizable(false);
		viewBuscarFilme.setSize(915, 600);
		viewBuscarFilme.setVisible(true);		
	}
}

