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

import br.ufla.gcc.ppoo.BancoDeDados.BancoDeDados;
import br.ufla.gcc.ppoo.Control.ControleDadosFilmes;
import br.ufla.gcc.ppoo.Control.ControleDadosUsuarios;
import br.ufla.gcc.ppoo.Dados.DadosLogin;
import br.ufla.gcc.ppoo.Dados.Filme;
import javax.swing.table.DefaultTableModel;

public class TelaVisualizarFilmes {
	
	JFrame viewListagem;
	static boolean status = false;
	private JTable tableFilmes;
	
	ControleDadosUsuarios controlUser = new ControleDadosUsuarios();
	ControleDadosFilmes controlFilmes = new ControleDadosFilmes();
	BancoDeDados bancoDDados = new BancoDeDados();
	
	public boolean getStatus() { 
		return status;
	}
	
	public TelaVisualizarFilmes(DadosLogin dadosLogin){
		bancoDDados.Conecta();
		viewListagemDeFilmes(dadosLogin);
	}
	
//	public TelaVisualizarFilmes() { }
	
	@SuppressWarnings({ "unused", "serial" })
	public void viewListagemDeFilmes(DadosLogin dadosLogin) {
		
		DadosLogin dl = controlUser.buscarDados(dadosLogin.getEmail());
		
		viewListagem = new JFrame();
		viewListagem.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		viewListagem.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				status = false;
			}
		});
		viewListagem.setBackground(new Color(0, 0, 255));
		viewListagem.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		viewListagem.setVisible(true);
		viewListagem.getContentPane().setLayout(null);
		viewListagem.getContentPane().setBackground(new Color(51, 102, 153));
		viewListagem.getContentPane().setForeground(new Color(255, 255, 255));
		viewListagem.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		viewListagem.setTitle("Meus Filme");
		viewListagem.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		
		JScrollPane scrollPaneList = new JScrollPane();
		scrollPaneList.setBounds(10, 125, 875, 400);
		viewListagem.getContentPane().add(scrollPaneList);
		
		ArrayList<Filme> listFilms = controlFilmes.buscarFilmes(dl.getId());
		
		int n = 0, i = 0;
		for (Filme filme : listFilms) {
			n++;
		}
		
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
		
		tableFilmes = new JTable();
//		table = new JTable(filmes, titulosColunas);
		
		tableFilmes.setModel(new DefaultTableModel(filmes, titulosColunas) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
//		table.getColumnModel().getColumn(0).setResizable(false);
//		table.getColumnModel().getColumn(1).setResizable(false);
//		table.getColumnModel().getColumn(2).setResizable(false);
//		table.getColumnModel().getColumn(3).setResizable(false);
//		table.getColumnModel().getColumn(4).setResizable(false);
//		table.getColumnModel().getColumn(5).setResizable(false);
		tableFilmes.setFont(new Font("Microsoft JhengHei", Font.BOLD, 12));
		tableFilmes.clearSelection();
		tableFilmes.setFillsViewportHeight(true);
		tableFilmes.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		scrollPaneList.setViewportView(tableFilmes);
		
		JLabel lblMeusFilme = new JLabel("Meus Filmes");
		lblMeusFilme.setHorizontalAlignment(SwingConstants.CENTER);
		lblMeusFilme.setForeground(Color.WHITE);
		lblMeusFilme.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 40));
		lblMeusFilme.setBounds(337, 20, 230, 40);
		viewListagem.getContentPane().add(lblMeusFilme);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(TelaVisualizarFilmes.class.getResource("/br/ufla/gcc/ppoo/Imagens/icone-lista.png")));
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setBackground(new Color(51, 51, 255));
		label.setBounds(290, 20, 40, 40);
		viewListagem.getContentPane().add(label);
		
		JLabel lblSelecione = new JLabel("Selecione um filme para realizar alguma a\u00E7\u00E3o:");
		lblSelecione.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelecione.setForeground(Color.WHITE);
		lblSelecione.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 20));
		lblSelecione.setBounds(250, 100, 405, 25);
		viewListagem.getContentPane().add(lblSelecione);
		
		JButton btnVisualizar = new JButton("Visualizar");
		btnVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnVisualizar.setForeground(new Color(0, 0, 0));
		btnVisualizar.setToolTipText("Visualizar item");
		btnVisualizar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnVisualizar.setBackground(new Color(255, 255, 255));
		btnVisualizar.setBounds(10, 535, 120, 25);
		viewListagem.getContentPane().add(btnVisualizar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setForeground(new Color(0, 0, 0));
		btnEditar.setToolTipText("Editar item");
		btnEditar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnEditar.setBackground(new Color(255, 255, 255));
		btnEditar.setBounds(260, 535, 120, 25);
		viewListagem.getContentPane().add(btnEditar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (tableFilmes.getSelectedRow() != -1) {
					int select = tableFilmes.getSelectionModel().getLeadSelectionIndex();
					JOptionPane.showMessageDialog(null, tableFilmes.getModel().getValueAt(tableFilmes.getSelectedRow() , 0));
					tableFilmes.clearSelection();
				} else {
					JOptionPane.showMessageDialog(null, "Seleção inválida...", "Para remover um filme selecione a linha dele.", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnRemover.setForeground(new Color(0, 0, 0));
		btnRemover.setToolTipText("Remover item");
		btnRemover.setFont(new Font("Arial", Font.PLAIN, 14));
		btnRemover.setBackground(new Color(255, 255, 255));
		btnRemover.setBounds(520, 535, 120, 25);
		viewListagem.getContentPane().add(btnRemover);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				viewListagem.dispose();
			}
		});
		btnCancelar.setIcon(new ImageIcon(TelaVisualizarFilmes.class.getResource("/br/ufla/gcc/ppoo/Imagens/btn-cancelar.png")));
		btnCancelar.setForeground(new Color(0, 0, 0));
		btnCancelar.setToolTipText("Cancelar");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCancelar.setBackground(new Color(255, 255, 255));
		btnCancelar.setBounds(765, 535, 120, 25);
		viewListagem.getContentPane().add(btnCancelar);
		
		status = true;
		
		viewListagem.setResizable(false);
		viewListagem.setSize(900, 600);
		viewListagem.setVisible(true);		
	}
}
