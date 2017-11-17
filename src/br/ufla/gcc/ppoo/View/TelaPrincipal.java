package br.ufla.gcc.ppoo.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

public class TelaPrincipal {
//	public class SecWind {
	
//	5432 local

//	Create I created the JFrame variable as a global variable so I did not have to create every instant I was going to use
	JFrame myViewPrinc;
	JTabbedPane tabbedPane;

//	Class calling my class that creates my JFrame
	public TelaPrincipal() {
		View();
	}

	//Class create my JFrame
	public void View(){
		
//		Create my JFrame and I set it up
		JComponent.setDefaultLocale(Locale.ENGLISH);
		myViewPrinc = new JFrame();
		myViewPrinc.setBackground(new Color(255, 255, 255));
		myViewPrinc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myViewPrinc.setResizable(false);
		myViewPrinc.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		myViewPrinc.setTitle("Tela Login");
		myViewPrinc.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		myViewPrinc.getContentPane().setLayout(null);
//		myViewPrinc.setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 255, 255));
		menuBar.setBounds(0, 0, 72, 21);
//		menuBar.setBorder(true);
		myViewPrinc.getContentPane().add(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);
		mnMenu.setOpaque(true);
		
		JMenuItem mntmTipos = new JMenuItem("Tipos");
		mntmTipos.setBackground(new Color(255, 255, 255));
		mntmTipos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Menu OK");
			}
		});
		mnMenu.add(mntmTipos);
		
		JMenuItem mnDados = new JMenuItem("Dados");
		mnDados.setBackground(new Color(255, 255, 255));
		mnMenu.add(mnDados);
		
		JMenu mnSair = new JMenu("Sair");
		mnSair.setOpaque(true);
		menuBar.add(mnSair);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.setBackground(new Color(255, 255, 255));
		mnSair.add(mntmSair);
		
		JMenuItem mntmTela = new JMenuItem("Tela De Login");
		mntmTela.setBackground(new Color(255, 255, 255));
		mnSair.add(mntmTela);
		
		JLabel llbFundo = new JLabel("");
		llbFundo.setBounds(0, -26, 894, 647);
//		llbFundo.setIcon(new ImageIcon(TelaPrincipal.class.getResource()));
		myViewPrinc.getContentPane().add(llbFundo);
		
		
//		More configuration of JFrame
		myViewPrinc.setSize(900, 650);
		myViewPrinc.setVisible(true);
		myViewPrinc.setResizable(false);
	}
}	

