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
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import br.ufla.gcc.ppoo.Control.ControleDadosFilmes;
import br.ufla.gcc.ppoo.Control.ControleDadosUsuarios;
import br.ufla.gcc.ppoo.Dados.DadosLogin;
import br.ufla.gcc.ppoo.Dados.Filme;

public class TelaBuscarFilme {

	private JFrame viewBuscarFilme;
	private JTable tableFilmes;
	private JScrollPane scrollPaneList;
	private JTextField textFieldBusca;
	
	private static boolean status = false;
	
	private ArrayList<Filme> listFilms = new ArrayList<>();

	public static boolean getStatus() { 
		return status;
	}
	
	public static void setStatus(boolean bool) {
		status = bool;
	}
	
	public ArrayList<Filme> atualizaLista(DadosLogin dl){
		return ControleDadosFilmes.BuscarFilmesTodosUsuarios(dl.getId());
	}
	
	public int atulizaQuantidadeFilmes(ArrayList<Filme> listFilms){
		return listFilms.size();
	}
	
	@SuppressWarnings("serial")
	public void constroiTabela(ArrayList<Filme> listFilms, DadosLogin dadosLogin){
		
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
	public String confereNomeFilme(Filme filme, DadosLogin dadosLogin) {
		
		String nome = ControleDadosUsuarios.BuscaNomeUser(filme.getId_user());
		
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
	
	public static void  quickSort(ArrayList<Filme> listFilmes, int esquerda, int direita){
		int esq = esquerda;
		int dir = direita;
		Filme pivo = listFilmes.get((esq + dir) /2);
		Filme troca;
		
		while (esq <= dir) {
			while ( listFilmes.get(esq).getPontos() > pivo.getPontos()) {
				esq = esq + 1;
			}
			while ( listFilmes.get(dir).getPontos() < pivo.getPontos()) {
				dir = dir - 1;
			}
			if (esq <= dir) {
				troca = listFilmes.get(esq);
				listFilmes.set(esq, listFilmes.get(dir));
				listFilmes.set(dir, troca);
				esq = esq + 1;
				dir = dir - 1;
			}
		}
		if (dir > esquerda) {
			quickSort(listFilmes, esquerda, dir);
		}
		if(esq < direita) {
			quickSort(listFilmes, esq, direita);
		}
	}
	
	public TelaBuscarFilme(DadosLogin dadosLogin){
		viewTelaBuscarFilme(dadosLogin);
	}
	
	public void viewTelaBuscarFilme(DadosLogin dadosLogin){
		
		DadosLogin dl = ControleDadosUsuarios.BuscarDados(dadosLogin.getEmail());
		
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
		viewBuscarFilme.setVisible(false);
		viewBuscarFilme.getContentPane().setLayout(null);
		viewBuscarFilme.getContentPane().setBackground(new Color(51, 102, 153));
		viewBuscarFilme.getContentPane().setForeground(new Color(255, 255, 255));
		viewBuscarFilme.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		viewBuscarFilme.setTitle("Buscar Filmes");
		viewBuscarFilme.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		
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
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if ((textFieldBusca.getText().equals(""))) {
					iniciarTabela();
					JOptionPane.showMessageDialog(null, "Digite uma palavra para que possa ser feita a busca.", "Campo Busca Vazio", JOptionPane.INFORMATION_MESSAGE);
				} else { 
					
					iniciarTabela();
					listFilms = atualizaLista(dl);
					listFilms = Filme.pesquisaFilme(listFilms, textFieldBusca.getText());
					
					if (!listFilms.isEmpty()) {
						quickSort(listFilms, 0, listFilms.size()-1);
						constroiTabela(listFilms, dadosLogin);
					} else {
						JOptionPane.showMessageDialog(null, "Nenhum filme com essa palavra foi encontrado", "Filme não encontrado", JOptionPane.INFORMATION_MESSAGE);
					}	
				}
	
			}
		});
		btnBuscar.setBounds(545, 85, 115, 45);
		btnBuscar.setBackground(new Color(255, 255, 255));
		btnBuscar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnBuscar.setToolTipText("Clique aqui para listar os filmes a partir de sua busca");
		viewBuscarFilme.getContentPane().add(btnBuscar);
		
		JButton btnBuscarTodos = new JButton("Buscar Todos");
		btnBuscarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				listFilms = atualizaLista(dl);

				if (!listFilms.isEmpty()) {
					quickSort(listFilms, 0, listFilms.size()-1);
					constroiTabela(listFilms, dadosLogin);
				} else {
					JOptionPane.showMessageDialog(null, "Nenhum filme foi encontrado", "Filmes não encontrados", JOptionPane.INFORMATION_MESSAGE);
				}				
			}
		});
		btnBuscarTodos.setBounds(720, 85, 125, 45);
		btnBuscarTodos.setBackground(new Color(255, 255, 255));
		btnBuscarTodos.setToolTipText("Clique aqui para listar todos os filmes de todos os usuários");
		btnBuscarTodos.setFont(new Font("Arial", Font.PLAIN, 14));
		viewBuscarFilme.getContentPane().add(btnBuscarTodos);
		
		JLabel lblBuscar = new JLabel("Buscar Filmes");
		lblBuscar.setForeground(new Color(255, 255, 255));
		lblBuscar.setBounds(340, 15, 250, 55);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 40));
		viewBuscarFilme.getContentPane().add(lblBuscar);
		
		JLabel lblIconSearch = new JLabel("");
		lblIconSearch.setIcon(new ImageIcon(TelaBuscarFilme.class.getResource("/br/ufla/gcc/ppoo/Imagens/icon-procurar-grande.png")));
		lblIconSearch.setBackground(new Color(51, 51, 255));
		lblIconSearch.setBounds(295, 20, 40, 40);
		lblIconSearch.setVerticalAlignment(SwingConstants.TOP);
		viewBuscarFilme.getContentPane().add(lblIconSearch);
		
		JButton btnVisualizar = new JButton("Visualizar");
		btnVisualizar.setIcon(new ImageIcon(TelaBuscarFilme.class.getResource("/br/ufla/gcc/ppoo/Imagens/filmes.png")));
		btnVisualizar.setBounds(135, 520, 135, 30);
		btnVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (tableFilmes.getSelectedRow() != -1) {
					String filmeSelect = (String) tableFilmes.getModel().getValueAt(tableFilmes.getSelectedRow() , 1);					
					try {
						Filme filme = new Filme (Filme.comparaFilme(listFilms, filmeSelect));
						setStatus(false);
						viewBuscarFilme.dispose();
						new TelaVisualizaFilme(dl, filme);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Filme seleciona é " + e.getCause()+ "\nEntre em contato com o administrador do sistema.", "Filme não encontrado", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Para visuzalizar um filme selecione a linha dele.", "Seleção inválida", 
							JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnVisualizar.setForeground(new Color(0, 0, 0));
		btnVisualizar.setToolTipText("Visualizar filme selecionado");
		btnVisualizar.setBackground(new Color(255, 255, 255));
		btnVisualizar.setFont(new Font("Arial", Font.PLAIN, 14));
		viewBuscarFilme.getContentPane().add(btnVisualizar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(TelaBuscarFilme.class.getResource("/br/ufla/gcc/ppoo/Imagens/btn-cancelar.png")));
		btnCancelar.setBounds(620, 520, 135, 30);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setStatus(false);
				viewBuscarFilme.dispose();
			}
		});
		btnCancelar.setForeground(new Color(0, 0, 0));
		btnCancelar.setToolTipText("Cancelar");
		btnCancelar.setBackground(new Color(255, 255, 255));
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 14));
		viewBuscarFilme.getContentPane().add(btnCancelar);
		
		setStatus(true);
		
		viewBuscarFilme.setResizable(false);
		viewBuscarFilme.setSize(900, 600);
		viewBuscarFilme.setVisible(true);		
	}
}

