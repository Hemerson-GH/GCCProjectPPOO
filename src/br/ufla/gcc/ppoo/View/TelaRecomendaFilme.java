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

import br.ufla.gcc.ppoo.Control.ControleDadosFilmes;
import br.ufla.gcc.ppoo.Control.ControleDadosUsuarios;
import br.ufla.gcc.ppoo.Dados.DadosLogin;
import br.ufla.gcc.ppoo.Dados.Filme;
import br.ufla.gcc.ppoo.Exceptions.BancoDadosException;
import br.ufla.gcc.ppoo.Exceptions.BuscasException;
import br.ufla.gcc.ppoo.Exceptions.FilmeExistenteException;
import br.ufla.gcc.ppoo.Exceptions.FilmesException;
import br.ufla.gcc.ppoo.Exceptions.UsuarioException;

public class TelaRecomendaFilme {
	
	private JFrame viewTelaRecomenda;
	private JTable tableFilmes;
	private JScrollPane scrollPaneList;
	private static boolean status = false;
	private ArrayList<Filme> listTodosFilmes = new ArrayList<>();
	private ArrayList<Filme> listFilmesUsuario = new ArrayList<>();
	private DadosLogin dl;

	public static boolean getStatus() { 
		return status;
	}
	
	public static void setStatus(boolean bool) {
		status = bool;
	}
	
	public ArrayList<Filme> atualizaListaUsuarios(DadosLogin dl) throws BancoDadosException, FilmesException{
		return ControleDadosFilmes.buscarFilmesOutrosUsuarios(dl.getId());
	}
	
	public ArrayList<Filme> atualizaListaUsuario(DadosLogin dl) throws BancoDadosException, FilmesException{
		return ControleDadosFilmes.buscarFilmesUmUsuario(dl.getId());
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
	public String confereNomeFilme(Filme filme, DadosLogin dadosLogin) throws BancoDadosException, UsuarioException {
		
		String nome = ControleDadosUsuarios.buscaNomeUser(filme.getId_user());
		
		if (nome.equals(dadosLogin.getNome())) {
			nome = "Eu";
		} 
		
		return nome;
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
	
	private void confereLista(ArrayList<Filme> listFilmes) throws BuscasException {
		if(listFilmes.isEmpty()) {
			throw new BuscasException("Sinto muito mas não temos nenhuma recomendação de filme para você.", "Filmes não encontrados");
		}
	}
	
	public void ConfereTabelaAdicionar(JTable tableFilmes) throws BuscasException{
		if (tableFilmes.getSelectedRow() == -1) {
			throw new BuscasException("Para adicionar um filme selecione a linha dele.", "Seleção inválida");
		}
	}
	
	public TelaRecomendaFilme(DadosLogin dadosLogin){
		viewTelaRecomendaFilme(dadosLogin);
	}
	
	public void viewTelaRecomendaFilme(DadosLogin dadosLogin){
		
		viewTelaRecomenda = new JFrame();	
		viewTelaRecomenda.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				setStatus(false);
			}
		});
		viewTelaRecomenda.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		viewTelaRecomenda.setBackground(new Color(0, 0, 255));
		viewTelaRecomenda.setFont(new Font("Times New Roman", Font.PLAIN, 14));
//		viewTelaRecomenda.setVisible(false);
		viewTelaRecomenda.getContentPane().setBackground(new Color(51, 102, 153));
		viewTelaRecomenda.getContentPane().setForeground(new Color(255, 255, 255));
		viewTelaRecomenda.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		viewTelaRecomenda.setTitle("Buscar Filmes");
		viewTelaRecomenda.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		viewTelaRecomenda.getContentPane().setLayout(null);
		
		setStatus(true);
		
		try {
			dl = ControleDadosUsuarios.buscarDados(dadosLogin.getEmail());
		} catch (BancoDadosException bdex){
			JOptionPane.showMessageDialog(null, bdex.getMessage(), bdex.getTitulo(), JOptionPane.ERROR_MESSAGE);
		} catch (UsuarioException ee) {
			JOptionPane.showMessageDialog(null, ee.getMessage(), ee.getTitulo(), JOptionPane.ERROR_MESSAGE);
		} 
		
		scrollPaneList = new JScrollPane();
		scrollPaneList.setBounds(10, 125, 875, 375);
		viewTelaRecomenda.getContentPane().add(scrollPaneList);
		
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
		lblSelecionar.setBounds(265, 100, 405, 25);
		viewTelaRecomenda.getContentPane().add(lblSelecionar);
		
		JButton btnRecomendar = new JButton("Gerar Recomendações");
		btnRecomendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					listTodosFilmes = atualizaListaUsuarios(dl);
					listFilmesUsuario = atualizaListaUsuario(dl);	
					listTodosFilmes = Filme.pesquisaRecomendacao(listTodosFilmes, listFilmesUsuario);
					confereLista(listTodosFilmes);
					constroiTabela(listTodosFilmes, dadosLogin);
				} catch (BancoDadosException dbe) {
					JOptionPane.showMessageDialog(null, dbe.getMessage(), dbe.getTitulo(), JOptionPane.ERROR_MESSAGE);
				} catch (FilmesException fe) {
					JOptionPane.showMessageDialog(null, fe.getMessage(), fe.getTitulo(), JOptionPane.ERROR_MESSAGE);
				} catch (UsuarioException ue) {
					JOptionPane.showMessageDialog(null, ue.getMessage(), ue.getTitulo(), JOptionPane.ERROR_MESSAGE);
				} catch (BuscasException be) {
					JOptionPane.showMessageDialog(null, be.getMessage(), be.getTitulo(), JOptionPane.ERROR_MESSAGE);
				} 		
			}
		});
		btnRecomendar.setBounds(365, 520, 190, 30);
		btnRecomendar.setBackground(new Color(255, 255, 255));
		btnRecomendar.setToolTipText("Clique aqui para listar todos os filmes de todos os usuários");
		btnRecomendar.setFont(new Font("Arial", Font.PLAIN, 14));
		viewTelaRecomenda.getContentPane().add(btnRecomendar);
		
		JLabel lblRecomendacoes = new JLabel("Filmes Recomendados");
		lblRecomendacoes.setForeground(new Color(255, 255, 255));
		lblRecomendacoes.setBounds(270, 15, 400, 55);
		lblRecomendacoes.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecomendacoes.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 40));
		viewTelaRecomenda.getContentPane().add(lblRecomendacoes);
		
		JButton btnAdicionar = new JButton("Adicionar Filme");
		btnAdicionar.setIcon(new ImageIcon(TelaBuscarFilme.class.getResource("/br/ufla/gcc/ppoo/Imagens/filmes.png")));
		btnAdicionar.setBounds(10, 520, 165, 30);
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ConfereTabelaAdicionar(tableFilmes);
					confereLista(listTodosFilmes);
					
					String filmeSelect = (String) tableFilmes.getModel().getValueAt(tableFilmes.getSelectedRow() , 1);		
					String donoFilme = (String) tableFilmes.getModel().getValueAt(tableFilmes.getSelectedRow() , 0);
					Filme filme = new Filme (ControleDadosFilmes.confereFilme(donoFilme, filmeSelect));
					
					filme.setId_user(dl.getId());
					ControleDadosFilmes.cadastrarFilme(filme, dl.getId());
					
					listTodosFilmes = atualizaListaUsuarios(dl);
					listFilmesUsuario = atualizaListaUsuario(dl);				
					listTodosFilmes = Filme.pesquisaRecomendacao(listTodosFilmes, listFilmesUsuario);
						
					constroiTabela(listTodosFilmes, dadosLogin);							
					JOptionPane.showMessageDialog(null, "Filme cadastrado com sucesso", "Filme cadastrado", JOptionPane.INFORMATION_MESSAGE);					
				} catch (BuscasException be) {
					JOptionPane.showMessageDialog(null, be.getMessage(), be.getTitulo(), JOptionPane.ERROR_MESSAGE);
				} catch (BancoDadosException dbe) {
					JOptionPane.showMessageDialog(null, dbe.getMessage(), dbe.getTitulo(), JOptionPane.ERROR_MESSAGE);
				} catch (UsuarioException ee) {
					JOptionPane.showMessageDialog(null, ee.getMessage(), ee.getTitulo(), JOptionPane.ERROR_MESSAGE);
				} catch (FilmesException fe) {
					JOptionPane.showMessageDialog(null, fe.getMessage(), fe.getTitulo(), JOptionPane.ERROR_MESSAGE);
				} catch (FilmeExistenteException fex) {
					JOptionPane.showMessageDialog(null, fex.getMessage(), fex.getTitulo(), JOptionPane.ERROR_MESSAGE);
				}				
			}
		});
		btnAdicionar.setForeground(new Color(0, 0, 0));
		btnAdicionar.setToolTipText("Adicionar filme recomendado");
		btnAdicionar.setBackground(new Color(255, 255, 255));
		btnAdicionar.setFont(new Font("Arial", Font.PLAIN, 14));
		viewTelaRecomenda.getContentPane().add(btnAdicionar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(TelaBuscarFilme.class.getResource("/br/ufla/gcc/ppoo/Imagens/btn-cancelar.png")));
		btnCancelar.setBounds(750, 520, 135, 30);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setStatus(false);
				viewTelaRecomenda.dispose();
			}
		});
		btnCancelar.setForeground(new Color(0, 0, 0));
		btnCancelar.setToolTipText("Cancelar");
		btnCancelar.setBackground(new Color(255, 255, 255));
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 14));
		viewTelaRecomenda.getContentPane().add(btnCancelar);
				
		viewTelaRecomenda.setResizable(false);
		viewTelaRecomenda.setSize(915, 600);
		viewTelaRecomenda.setVisible(true);		
	}
}
