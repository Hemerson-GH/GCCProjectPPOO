package br.ufla.gcc.ppoo.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import br.ufla.gcc.ppoo.Dados.DadosLogin;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class TelaVisualizarFilmes {
	
	JFrame viewListagem;
	static boolean status = false;
	private JTable table;
	
	public boolean getStatus() { 
		return status;
	}
	
	public TelaVisualizarFilmes(DadosLogin dadosLogin){
		viewListagemDeFilmes(dadosLogin);
	}
	
	public TelaVisualizarFilmes() { }
	
	public void viewListagemDeFilmes(DadosLogin dadosLogin) {
		
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 125, 874, 362);
		viewListagem.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setSurrendersFocusOnKeystroke(true);
		table.setFont(new Font("Microsoft JhengHei", Font.BOLD, 12));
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"sasaas", "124", "21"},
				{"saa", "412", "241"},
			},
			new String[] {
				"Filme", "#Pontos", "Teste 3"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		scrollPane.setViewportView(table);
		
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
		lblSelecione.setBounds(170, 100, 575, 26);
		viewListagem.getContentPane().add(lblSelecione);
		
		JButton btnVisualizar = new JButton("Visualizar");
		btnVisualizar.setBounds(10, 510, 131, 41);
		viewListagem.getContentPane().add(btnVisualizar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(271, 510, 114, 41);
		viewListagem.getContentPane().add(btnEditar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.setBounds(538, 510, 121, 41);
		viewListagem.getContentPane().add(btnRemover);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(758, 510, 126, 41);
		viewListagem.getContentPane().add(btnCancelar);
		
		status = true;
		
		viewListagem.setResizable(false);
		viewListagem.setSize(900, 600);
		viewListagem.setVisible(true);		
	}
}
