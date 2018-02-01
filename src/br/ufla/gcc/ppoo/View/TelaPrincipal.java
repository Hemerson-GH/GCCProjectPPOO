package br.ufla.gcc.ppoo.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

import br.ufla.gcc.ppoo.dados.DadosLogin;
import br.ufla.gcc.ppoo.exceptions.BancoDadosException;
import br.ufla.gcc.ppoo.exceptions.UsuarioException;
import br.ufla.gcc.ppoo.imagens.GerenciadorDeImagens;

public class TelaPrincipal {
	
	private JFrame viewMain;
	
	public TelaPrincipal(DadosLogin dadosLogin) {
		view(dadosLogin);
	}
	
	public void view(final DadosLogin dadosLogin){

		viewMain = new JFrame();
		
		viewMain.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				final int confirm = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Sair", 
													JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (JOptionPane.YES_OPTION == confirm) {	
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
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		menuBar.setBackground(new Color(255, 255, 255));
		
		JMenu mnMenu = new JMenu("Menu");
		mnMenu.setFont(new Font("Arial", Font.PLAIN, 13));
		mnMenu.setBackground(new Color(255, 255, 255));
		menuBar.add(mnMenu);
		
		JMenuItem mnItemCadastro = new JMenuItem("Cadastrar Novo Filme", GerenciadorDeImagens.NOVO);
		mnItemCadastro.setFont(new Font("Arial", Font.PLAIN, 13));
		mnItemCadastro.setBackground(new Color(255, 255, 255));
		mnItemCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!TelaCadastroFilme.getStatus()) {
					new TelaCadastroFilme(dadosLogin);
				} else {
					JOptionPane.showMessageDialog(null, "Uma janela já está em execução", "Tela em execução", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		mnMenu.add(mnItemCadastro);
		
		JMenuItem mnItemListagem = new JMenuItem("Listar Meus Filmes", GerenciadorDeImagens.FILME);
		mnItemListagem.setFont(new Font("Arial", Font.PLAIN, 13));
		mnItemListagem.setBackground(new Color(255, 255, 255));
		mnItemListagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!TelaListagemFilmes.getStatus()) {
					try {
						new TelaListagemFilmes(dadosLogin);
					} catch (BancoDadosException dbe) {
						JOptionPane.showMessageDialog(null, dbe.getMessage(), dbe.getTitulo(), JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Uma janela já está em execução", "Tela em execução", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		JSeparator separatorMenu = new JSeparator();
		mnMenu.add(separatorMenu);
		mnMenu.add(mnItemListagem);
		
		JMenuItem mnItemBuscarFilmes = new JMenuItem("Buscar Filmes", GerenciadorDeImagens.PROCURAR);
		mnItemBuscarFilmes.setFont(new Font("Arial", Font.PLAIN, 13));
		mnItemBuscarFilmes.setBackground(new Color(255, 255, 255));
		mnItemBuscarFilmes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!TelaBuscarFilme.getStatus()) {
					try {
						new TelaBuscarFilme(dadosLogin);
					} catch (BancoDadosException dbe) {
						JOptionPane.showMessageDialog(null, dbe.getMessage(), dbe.getTitulo(), JOptionPane.ERROR_MESSAGE);
					} catch (UsuarioException ee) {
						JOptionPane.showMessageDialog(null, ee.getMessage(), ee.getTitulo(), JOptionPane.ERROR_MESSAGE);
					} 
				} else {
					JOptionPane.showMessageDialog(null, "Uma janela já está em execução", "Tela em execução", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		JSeparator separatorFilmes = new JSeparator();
		mnMenu.add(separatorFilmes);
		mnMenu.add(mnItemBuscarFilmes);
		
		JSeparator separatorBusca = new JSeparator();
		mnMenu.add(separatorBusca);
		
		JMenuItem mnRecomendacoes = new JMenuItem("Recomendações", GerenciadorDeImagens.FILME);
		mnRecomendacoes.setFont(new Font("Arial", Font.PLAIN, 13));
		mnRecomendacoes.setBackground(new Color(255, 255, 255));
		mnRecomendacoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!TelaRecomendaFilme.getStatus()) {
					new TelaRecomendaFilme(dadosLogin);
				} else {
					JOptionPane.showMessageDialog(null, "Uma janela já está em execução", "Tela em execução", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		mnMenu.add(mnRecomendacoes);
		
		JMenu mnSair = new JMenu("Sair");
		mnSair.setFont(new Font("Arial", Font.PLAIN, 13));
		mnSair.setBackground(new Color(255, 255, 255));
		menuBar.add(mnSair);
		
		JMenuItem mnItemLogout = new JMenuItem("Logout", GerenciadorDeImagens.LOGOUT);
		mnItemLogout.setFont(new Font("Arial", Font.PLAIN, 13));
		mnItemLogout.setBackground(new Color(255, 255, 255));
		mnItemLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Você será redirecionado para a tela de login.", "Tela de Login", JOptionPane.CLOSED_OPTION);
				viewMain.dispose();
				new TelaLogin();
			}
		});
		mnSair.add(mnItemLogout);
		
		JMenuItem mnItemSair = new JMenuItem("Sair", GerenciadorDeImagens.SAIR);
		mnItemSair.setFont(new Font("Arial", Font.PLAIN, 13));
		mnItemSair.setBackground(new Color(255, 255, 255));
		mnItemSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final int confirm = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Confirmação para sair", 
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (JOptionPane.YES_OPTION == confirm) {	
					viewMain.dispose();
					System.exit(0);
				}
			}
		});
		
		JSeparator separatorSair = new JSeparator();
		mnSair.add(separatorSair);
		mnSair.add(mnItemSair);
		
		GroupLayout groupLayout = new GroupLayout(viewMain.getContentPane());
		
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(menuBar, GroupLayout.DEFAULT_SIZE, 1350, Short.MAX_VALUE)
					.addGap(0))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(menuBar, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(709, Short.MAX_VALUE))
		);
		viewMain.getContentPane().setLayout(groupLayout);
		
		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
		int lar = (int) tela.getWidth();
		int alt = (int) tela.getHeight();
		
		viewMain.setSize(lar, alt);
		viewMain.setSize(1366, 768);
		viewMain.setMinimumSize(new Dimension(270, 60));
		viewMain.setExtendedState(JFrame.MAXIMIZED_BOTH);
		viewMain.setVisible(true);
	}
}	