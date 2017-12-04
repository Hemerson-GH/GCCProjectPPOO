package br.ufla.gcc.ppoo.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

import br.ufla.gcc.ppoo.BancoDeDados.BancoDeDados;
import br.ufla.gcc.ppoo.Dados.DadosLogin;

public class TelaPrincipal {
	
	private JFrame viewMain;
	
	private BancoDeDados bancoDDados = new BancoDeDados();
	
	public TelaPrincipal(DadosLogin dadosLogin) {
		bancoDDados.Conecta();
		View(dadosLogin);
	}
	
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
		viewMain.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		viewMain.setTitle("Menu Principal");
		viewMain.getContentPane().setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		viewMain.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		menuBar.setBounds(0, 0, 1389, 20);
		menuBar.setBackground(new Color(255, 255, 255));
		viewMain.getContentPane().add(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		mnMenu.setFont(new Font("Arial", Font.PLAIN, 13));
		mnMenu.setBackground(new Color(255, 255, 255));
		menuBar.add(mnMenu);
		
		JMenuItem mnItemCadastro = new JMenuItem("Cadastra Novo Filme");
		mnItemCadastro.setFont(new Font("Arial", Font.PLAIN, 13));
		mnItemCadastro.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/ufla/gcc/ppoo/Imagens/novo.png")));
		mnItemCadastro.setBackground(new Color(255, 255, 255));
		mnItemCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (!(TelaCadastroFilme.getStatus())) {
					new TelaCadastroFilme(dadosLogin);
				} else {
					JOptionPane.showMessageDialog(null, "Uma janela já está em execução", "Tela Já Está Em Execução", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		mnMenu.add(mnItemCadastro);
		
		JMenuItem mnItemListagem = new JMenuItem("Listar Meus Filmes");
		mnItemListagem.setFont(new Font("Arial", Font.PLAIN, 13));
		mnItemListagem.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/ufla/gcc/ppoo/Imagens/filmes.png")));
		mnItemListagem.setBackground(new Color(255, 255, 255));
		mnItemListagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (!(TelaListagemFilmes.getStatus())) {
					new TelaListagemFilmes(dadosLogin);
				} else {
					JOptionPane.showMessageDialog(null, "Uma janela já está em execução", "Tela Já Está Em Execução", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		JSeparator separatorMenu = new JSeparator();
		mnMenu.add(separatorMenu);
		mnItemListagem.setBackground(new Color(255, 255, 255));
		mnMenu.add(mnItemListagem);
		
		JMenuItem mnItemBuscarFilmes = new JMenuItem("Buscar Filmes");
		mnItemBuscarFilmes.setFont(new Font("Arial", Font.PLAIN, 13));
		mnItemBuscarFilmes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (!(TelaBuscarFilme.getStatus())) {
					new TelaBuscarFilme(dadosLogin);
				} else {
					JOptionPane.showMessageDialog(null, "Uma janela já está em execução", "Tela Já Está Em Execução", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		JSeparator separatorFilmes = new JSeparator();
		mnMenu.add(separatorFilmes);
		mnItemBuscarFilmes.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/ufla/gcc/ppoo/Imagens/procurar.png")));
		mnItemBuscarFilmes.setBackground(new Color(255, 255, 255));
		mnMenu.add(mnItemBuscarFilmes);
		
		JSeparator separatorBusca = new JSeparator();
		mnMenu.add(separatorBusca);
		
		JMenuItem mnRecomendacoes = new JMenuItem("Recomendações");
		mnRecomendacoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!(TelaRecomendaFilme.getStatus())) {
					new TelaRecomendaFilme(dadosLogin);
				} else {
					JOptionPane.showMessageDialog(null, "Uma janela já está em execução", "Tela Já Está Em Execução", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		mnRecomendacoes.setBackground(new Color(255, 255, 255));
		mnRecomendacoes.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/ufla/gcc/ppoo/Imagens/filmes.png")));
		mnRecomendacoes.setFont(new Font("Arial", Font.PLAIN, 13));
		mnMenu.add(mnRecomendacoes);
		
		JMenu mnSair = new JMenu("Sair");
		mnSair.setFont(new Font("Arial", Font.PLAIN, 13));
		mnSair.setBackground(new Color(255, 255, 255));
		menuBar.add(mnSair);
		
		JMenuItem mnItemLogout = new JMenuItem("Logout");
		mnItemLogout.setFont(new Font("Arial", Font.PLAIN, 13));
		mnItemLogout.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/ufla/gcc/ppoo/Imagens/logout.png")));
		mnItemLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bancoDDados.Desconecta();
				JOptionPane.showMessageDialog(null, "Você será redirecionado para a tela de login.", "Tela de Login", JOptionPane.CLOSED_OPTION);
				viewMain.dispose();
				new TelaLogin();
			}
		});
		mnItemLogout.setBackground(new Color(255, 255, 255));
		mnSair.add(mnItemLogout);
		
		JMenuItem mnItemSair = new JMenuItem("Sair");
		mnItemSair.setFont(new Font("Arial", Font.PLAIN, 13));
		mnItemSair.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/ufla/gcc/ppoo/Imagens/sair.png")));
		mnItemSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				final int confirm = JOptionPane.showConfirmDialog(null, "Deseja realmente sair ?", "Confirmação	 Para Sair", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if (JOptionPane.YES_OPTION == confirm) {	
					bancoDDados.Desconecta();
					viewMain.dispose();
					System.exit(0);
				}
			}
		});
		
		JSeparator separatorSair = new JSeparator();
		mnSair.add(separatorSair);
		mnItemSair.setBackground(new Color(255, 255, 255));
		mnSair.add(mnItemSair);
		
//		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
//		int lar = (int) tela.getWidth();
//		int alt = (int) tela.getHeight();
		
//		viewMain.setSize(lar, alt);
		viewMain.setSize(1366, 768);
		viewMain.setExtendedState(JFrame.MAXIMIZED_BOTH);
		viewMain.setVisible(true);
	}
}	