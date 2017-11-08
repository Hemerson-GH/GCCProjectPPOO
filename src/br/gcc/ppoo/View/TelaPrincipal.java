package br.gcc.ppoo.View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;

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
		myViewPrinc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myViewPrinc.setResizable(false);
		myViewPrinc.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		myViewPrinc.setTitle("Tela Login");
		myViewPrinc.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		myViewPrinc.getContentPane().setLayout(null);
		
		JLabel lblIcon1 = new JLabel("New label");
		lblIcon1.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/GCC/Package/Imagens/CadMedicos.png")));
		lblIcon1.setBounds(10, 37, 116, 122);
		lblIcon1.setOpaque(true);
		myViewPrinc.getContentPane().add(lblIcon1);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 97, 21);
		myViewPrinc.getContentPane().add(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);
		mnMenu.setOpaque(true);
		
		JMenuItem mntmTipos = new JMenuItem("Tipos");
		mntmTipos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Menu OK");
			}
		});
		mnMenu.add(mntmTipos);
		
		JInternalFrame internalFrame = new JInternalFrame("Bem-Vindo");
		internalFrame.setBounds(0, 170, 894, 451);
		myViewPrinc.getContentPane().add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		
		JPanel panelInternalFr = new JPanel();
		panelInternalFr.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelInternalFr.setBounds(0, 37, 878, 384);
		internalFrame.getContentPane().add(panelInternalFr);
		panelInternalFr.setLayout(null);
		
		JLabel lblLblinternal = new JLabel("lblInternal");
		lblLblinternal.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/GCC/Package/Imagens/internalframe.png")));
		lblLblinternal.setBounds(0, 0, 878, 384);
		panelInternalFr.add(lblLblinternal);
		
		JLabel llbFundo = new JLabel("");
		llbFundo.setBounds(-26, -25, 920, 646);
		llbFundo.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/GCC/Package/Imagens/xfce-blue.jpg")));
		myViewPrinc.getContentPane().add(llbFundo);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(136, 58, 46, 14);
		myViewPrinc.getContentPane().add(lblNewLabel);
		
		
//		More configuration of JFrame
		myViewPrinc.setSize(900, 650);
		myViewPrinc.setVisible(true);
		myViewPrinc.setResizable(false);
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		TelaPrincipal t = new TelaPrincipal();
	}
}	

