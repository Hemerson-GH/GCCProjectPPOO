package br.ufla.gcc.ppoo.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import br.ufla.gcc.ppoo.BancoDeDados.BancoDeDados;
import br.ufla.gcc.ppoo.Control.ControleDadosFilmes;
import br.ufla.gcc.ppoo.Control.ControleDadosUsuarios;
import br.ufla.gcc.ppoo.Dados.DadosLogin;

public class TelaPrincipal {
	
	JFrame viewMain;
	
	BancoDeDados bancoDDados = new BancoDeDados();
	ControleDadosFilmes controlFilmes = new ControleDadosFilmes();
	ControleDadosUsuarios controlUser = new ControleDadosUsuarios();	
	
	public TelaPrincipal(DadosLogin dadosLogin) {
		bancoDDados.Conecta();
		View(dadosLogin);
	}

	@SuppressWarnings("unchecked")
	public void View(DadosLogin dadosLogin){

		viewMain = new JFrame();
		
		viewMain.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				final int confirm = JOptionPane.showConfirmDialog(null, "Deseja realmente sair ?", "Sair", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if (JOptionPane.YES_OPTION == confirm) {	
					bancoDDados.Desconecta();
					viewMain.dispose();
					System.exit(0);
				}
			}
		});		
		
		viewMain.getContentPane().setBackground(new Color(51, 102, 153));
		viewMain.getContentPane().setForeground(new Color(255, 255, 255));
		viewMain.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		viewMain.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		viewMain.setTitle("Menu Principal");
		viewMain.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		
//		JLabel lblTituloListagem = new JLabel("Meus Filmes");
//		lblTituloListagem.setHorizontalAlignment(SwingConstants.CENTER);
//		lblTituloListagem.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 30));
//		lblTituloListagem.setBounds(125, 30, 240, 45);
//		viewListagem.getContentPane().add(lblTituloListagem);
		
		JLabel lblIconLista = new JLabel("");
		lblIconLista.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/ufla/gcc/ppoo/Imagens/Lista.jpg")));
		lblIconLista.setVerticalAlignment(SwingConstants.TOP);
		lblIconLista.setBounds(110, 35, 40, 40);
//		viewListagem.getContentPane().add(lblIconLista);
		viewMain.getContentPane().setLayout(null);
		
		JSlider slider = new JSlider(JSlider.VERTICAL, 0, 5, 0);
		slider.setMinorTickSpacing(1);
		slider.setBounds(667, 166, 90, 130);
		slider.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		slider.setBackground(Color.WHITE);
//		slider.addChangeListener(new ChangeListener() {
//			public void stateChanged(ChangeEvent e) {
//				JSlider source = (JSlider)e.getSource();
//			    
//			    if (!source.getValueIsAdjusting()) {
//			        int fps = (int)slider.getValue();
//			        if (fps == 0) {
//			        	slider.setToolTipText("Péssimo");
//			        	System.out.println("Péssimo");
//			        } else if (fps == 1) {
//			        	slider.setToolTipText("Ruim");
//			        	System.out.println("Ruim");
//			        } else if (fps == 2){
//			        	slider.setToolTipText("Regular");
//			        	System.out.println("Regular");
//			        } else if (fps == 3){
//			        	slider.setToolTipText("Bom");
//			        	System.out.println("Bom");
//			        } else if (fps == 4) {
//			        	slider.setToolTipText("Muito Bom");
//			        	System.out.println("Muito Bom");
//			        } else if (fps == 5) {
//			        	slider.setToolTipText("Excelente");
//			        	System.out.println("Excelente");
//			        }
//			    }
//			}
//		});
		slider.setEnabled(true);
		viewMain.getContentPane().add(slider);
		slider.setMajorTickSpacing(5);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		
		Font font = new Font("Arial", Font.PLAIN, 9);
		slider.setFont(font);
		
		@SuppressWarnings("rawtypes")
		Hashtable labelTable = new Hashtable();
		labelTable.put( new Integer(5), new JLabel(new ImageIcon(TelaPrincipal.class.getResource("/br/ufla/gcc/ppoo/Imagens/estrela-5.jpg"))) );
		labelTable.put( new Integer(4), new JLabel(new ImageIcon(TelaPrincipal.class.getResource("/br/ufla/gcc/ppoo/Imagens/estrela-4.jpg"))) );
		labelTable.put( new Integer(3), new JLabel(new ImageIcon(TelaPrincipal.class.getResource("/br/ufla/gcc/ppoo/Imagens/estrela-3.jpg"))) );
		labelTable.put( new Integer(2), new JLabel(new ImageIcon(TelaPrincipal.class.getResource("/br/ufla/gcc/ppoo/Imagens/estrela-2.jpg"))) );
		labelTable.put( new Integer(1), new JLabel(new ImageIcon(TelaPrincipal.class.getResource("/br/ufla/gcc/ppoo/Imagens/estrela-1.jpg"))) );
		labelTable.put( new Integer(0), new JLabel( "0" ));
		slider.setLabelTable( labelTable );
		
//		JButton btnExit = new JButton("");
//		btnExit.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				myPanelCadastro.setVisible(false);
//			}
//		});
//		btnExit.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/ufla/gcc/ppoo/Imagens/iconeExit.jpg")));
//		btnExit.setBounds(455, 10, 18, 15);
//		myPanelCadastro.add(btnExit);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1389, 20);
		menuBar.setBackground(new Color(255, 255, 255));
		viewMain.getContentPane().add(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		mnMenu.setBackground(new Color(255, 255, 255));
		menuBar.add(mnMenu);
		
		JMenuItem mnCadastro = new JMenuItem("Cadastra Novo Filme");
		mnCadastro.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/ufla/gcc/ppoo/Imagens/novo.png")));
		mnCadastro.setBackground(new Color(255, 255, 255));
		mnCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				TelaCadastroFilme TDF = new TelaCadastroFilme();
				
				if (!(TDF.getStatus())) {
					new TelaCadastroFilme(dadosLogin);
				} else {
					JOptionPane.showMessageDialog(null, "Uma janela já está em execução", "Tela Já Está Em Execução", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		mnMenu.add(mnCadastro);
		
		JMenuItem mnListagem = new JMenuItem("Listar Meus Filmes");
		mnListagem.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/ufla/gcc/ppoo/Imagens/filmes.png")));
		mnListagem.setBackground(new Color(255, 255, 255));
		mnListagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				TelaVisualizarFilmes TVF = new TelaVisualizarFilmes();
				
				if (!(TVF.getStatus())) {
					new TelaVisualizarFilmes(dadosLogin);
				} else {
					JOptionPane.showMessageDialog(null, "Uma janela já está em execução", "Tela Já Está Em Execução", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		JSeparator separatorMenu = new JSeparator();
		mnMenu.add(separatorMenu);
		mnListagem.setBackground(new Color(255, 255, 255));
		mnMenu.add(mnListagem);
		
		JMenu mnSair = new JMenu("Sair");
		mnSair.setBackground(new Color(255, 255, 255));
		menuBar.add(mnSair);
		
		JMenuItem mntmTela = new JMenuItem("Logout");
		mntmTela.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/ufla/gcc/ppoo/Imagens/logout.png")));
		mntmTela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bancoDDados.Desconecta();
				JOptionPane.showMessageDialog(null, "Você será redirecionado para a tela de login.", "Tela de Login", JOptionPane.CLOSED_OPTION);
				viewMain.dispose();
				new TelaLogin();
			}
		});
		mntmTela.setBackground(new Color(255, 255, 255));
		mnSair.add(mntmTela);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/ufla/gcc/ppoo/Imagens/sair.png")));
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				final int confirm = JOptionPane.showConfirmDialog(null, "Deseja realmente sair ?", "Confirmação	Para Sair", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if (JOptionPane.YES_OPTION == confirm) {	
					bancoDDados.Desconecta();
					viewMain.dispose();
					System.exit(0);
				}
			}
		});
		
		JSeparator separatorSair = new JSeparator();
		mnSair.add(separatorSair);
		mntmSair.setBackground(new Color(255, 255, 255));
		mnSair.add(mntmSair);
		
//		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
//		int lar = (int) tela.getWidth();
//		int alt = (int) tela.getHeight();
		
//		viewMain.setSize(lar, alt);
		viewMain.setSize(1366, 768);
		viewMain.setExtendedState(JFrame.MAXIMIZED_BOTH);
		viewMain.setVisible(true);
	}
}	

