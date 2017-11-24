package br.ufla.gcc.ppoo.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import br.ufla.gcc.ppoo.BancoDeDados.BancoDeDados;
import br.ufla.gcc.ppoo.Control.ControleDadosFilmes;
import br.ufla.gcc.ppoo.Control.ControleDadosUsuarios;
import br.ufla.gcc.ppoo.Dados.DadosLogin;

public class TelaPrincipal {
	
	JFrame myViewMain;
	JFrame myPanelListagem;
	
	BancoDeDados bancoDDados = new BancoDeDados();
	ControleDadosFilmes controlFilmes = new ControleDadosFilmes();
	ControleDadosUsuarios controlUser = new ControleDadosUsuarios();
	
	
	
	public TelaPrincipal(DadosLogin dadosLogin) {
		bancoDDados.Conecta();
		View(dadosLogin);
	}

	@SuppressWarnings("unchecked")
	public void View(DadosLogin dadosLogin){

		myViewMain = new JFrame();
		myViewMain.getContentPane().setBackground(new Color(51, 102, 153));
		myViewMain.getContentPane().setForeground(new Color(255, 255, 255));
		myViewMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myViewMain.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		myViewMain.setTitle("Menu Principal");
		myViewMain.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		
		myPanelListagem = new JFrame();
		myPanelListagem.setBounds(65, 31, 510, 439);
		myPanelListagem.setBackground(new Color(255, 255, 255));
		myPanelListagem.setFont(new Font("Times New Roman", Font.PLAIN, 14));
//		myPanelListagem.setVisible(true);
		myPanelListagem.getContentPane().setLayout(null);
		
//		myViewMain.getContentPane().add(myPanelListagem);
		
		JLabel lblTituloListagem = new JLabel("Meus Filmes");
		lblTituloListagem.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloListagem.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 30));
		lblTituloListagem.setBounds(125, 30, 240, 45);
		myPanelListagem.getContentPane().add(lblTituloListagem);
		
		JLabel lblIconLista = new JLabel("");
		lblIconLista.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/ufla/gcc/ppoo/Imagens/Lista.jpg")));
		lblIconLista.setVerticalAlignment(SwingConstants.TOP);
		lblIconLista.setBounds(110, 35, 40, 40);
		myPanelListagem.getContentPane().add(lblIconLista);
		myViewMain.getContentPane().setLayout(null);
		
//		myPanelListagem.add(table);
		
		JSlider slider = new JSlider(JSlider.VERTICAL, 0, 5, 1);
		slider.setBounds(666, 155, 90, 130);
		slider.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		slider.setBackground(Color.WHITE);
//		slider.addChangeListener(new ChangeListener() {
//			public void stateChanged(ChangeEvent e) {
//				JSlider source = (JSlider)e.getSource();
//			    
//			    if (!source.getValueIsAdjusting()) {
//			        int fps = (int)slider.getValue();
//			        if (fps == 0) {
//			        	slider.setToolTipText("P�ssimo");
//			        	System.out.println("P�ssimo");
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
		myViewMain.getContentPane().add(slider);
		slider.setMajorTickSpacing(5);
		slider.setMinorTickSpacing(1);
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
//		menuBar.setBorder(true);
		myViewMain.getContentPane().add(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		mnMenu.setBackground(new Color(255, 255, 255));
		menuBar.add(mnMenu);
		
		JMenuItem mnCadastro = new JMenuItem("Cadastra Novo Filme");
		mnCadastro.setBackground(new Color(255, 255, 255));
		mnCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TelaCadastroFilme(dadosLogin);
			}
		});
		mnMenu.add(mnCadastro);
		
		JMenuItem mnListagem = new JMenuItem("Listar Meus Filmes");
		mnListagem.setBackground(new Color(255, 255, 255));
		mnListagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myPanelListagem.setVisible(true);
			}
		});
		mnListagem.setBackground(new Color(255, 255, 255));
		mnMenu.add(mnListagem);
		
		JMenu mnSair = new JMenu("Sair");
		mnSair.setBackground(new Color(255, 255, 255));
		menuBar.add(mnSair);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bancoDDados.Desconecta();
				JOptionPane.showMessageDialog(null, "At� Mais...", "Sair", JOptionPane.CLOSED_OPTION);
				myViewMain.dispose();
			}
		});
		mntmSair.setBackground(new Color(255, 255, 255));
		mnSair.add(mntmSair);
		
		JMenuItem mntmTela = new JMenuItem("Logout");
		mntmTela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bancoDDados.Desconecta();
				JOptionPane.showMessageDialog(null, "Voc� ser� redirecionado para a tela de login.", "Tela de Login", JOptionPane.CLOSED_OPTION);
				myViewMain.dispose();
				new TelaLogin();
			}
		});
		mntmTela.setBackground(new Color(255, 255, 255));
		mnSair.add(mntmTela);
		
//		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
//		int lar = (int) tela.getWidth();
//		int alt = (int) tela.getHeight();
		
//		myViewMain.setSize(lar, alt);
		myViewMain.setSize(1366, 768);
		myViewMain.setVisible(true);
	}
}	

