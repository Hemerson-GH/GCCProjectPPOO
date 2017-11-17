package br.ufla.gcc.ppoo.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Locale;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class TelaPrincipalTeste {
	
	@SuppressWarnings("rawtypes")
	public JComboBox comboBoxProjects;
	public JRadioButton rdbtnRna;
	public JLabel lblPackageNameOriginal;
	JList<String> listPackets, listBlockPackets, listMovimentLog;
	DefaultListModel<String> modelListPackets, modelListBlockPackets, modelLogMovements;

	JFrame frmRestructuring;
	boolean measuredProject = false, restructuredProject = false, firstRrestructuring = true, firstRrestructuring2 = true;
	JPanel panelViewOriginalStructure;
	JScrollPane scrollPaneVisualization;
	JButton btnZoomMinusOriginal, btnZoomPlusOriginal, btnBackOriginalStructure;

	public TelaPrincipalTeste() {
		
		initialize();
	}

	private void initialize() {

		JComponent.setDefaultLocale(Locale.ENGLISH);
		frmRestructuring = new JFrame();
		frmRestructuring.setResizable(false);
		frmRestructuring.setTitle("Software Restructuring Tool \r\n");
		frmRestructuring.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		frmRestructuring.setMinimumSize(new Dimension(1130, 680));
		frmRestructuring.setVisible(true);
		frmRestructuring.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1124, 651);
		frmRestructuring.getContentPane().add(tabbedPane);		
		comboBoxProjects.setToolTipText("Select the software ");

		comboBoxProjects.setSelectedItem(null);
		GridBagConstraints gbc_comboBoxProjects = new GridBagConstraints();
		gbc_comboBoxProjects.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxProjects.insets = new Insets(0, 0, 0, 5);
		gbc_comboBoxProjects.gridx = 0;
		gbc_comboBoxProjects.gridy = 0;
		comboBoxProjects.setFont(new Font("Tahoma", Font.PLAIN, 11));
		@SuppressWarnings("unused")
		ImageIcon minus1 = new ImageIcon(getClass().getResource("/icons/minus.png"));

		ImageIcon plus = new ImageIcon(getClass().getResource("/icons/plus.png"));
		ImageIcon buttonback2 = new ImageIcon(getClass().getResource("/icons/button back 2 new.png"));
		
		ImageIcon minus2 = new ImageIcon(getClass().getResource("/icons/minus.png"));
		
		JPanel panelGeral = new JPanel();
//		panelGeral.setBorder(new CompoundBorder());
		tabbedPane.addTab("Execute Restructuring", null, panelGeral, null);
		panelGeral.setVisible(true);
		panelGeral.setLayout(null);
							
		JLabel lblSelectAProject = new JLabel("Select software:");
		lblSelectAProject.setBounds(10, 8, 106, 23);
		lblSelectAProject.setForeground(SystemColor.desktop);
		lblSelectAProject.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelGeral.add(lblSelectAProject);
									
		JButton btnRefactor = new JButton("Restructuring");
		btnRefactor.setBounds(186, 593, 178, 23);
		btnRefactor.setFont(new Font("Tahoma", Font.PLAIN, 11));	
		panelGeral.add(btnRefactor);

		JPanel panelOriginalStructure = new JPanel();
		panelOriginalStructure.setBounds(10, 42, 1079, 550);
		panelOriginalStructure.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				
			}
		});
		panelOriginalStructure.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Original Structure", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelOriginalStructure.setLayout(null);
		panelOriginalStructure.setVisible(true);
			panelGeral.add(panelOriginalStructure);
					
		panelViewOriginalStructure = new JPanel();
		panelViewOriginalStructure.setToolTipText("");
		panelViewOriginalStructure.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelViewOriginalStructure.setBackground(Color.WHITE);
		panelViewOriginalStructure.setLayout(null);
		panelViewOriginalStructure.setBounds(10, 39, 1037, 490);
		panelOriginalStructure.add(panelViewOriginalStructure);
							
		btnZoomPlusOriginal = new JButton("");
		btnZoomPlusOriginal.setToolTipText("Zoom in ");
		btnZoomPlusOriginal.setBounds(456, 527, 36, 23);
		panelOriginalStructure.add(btnZoomPlusOriginal);
		btnZoomPlusOriginal.setIcon(plus);
		btnZoomPlusOriginal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		btnZoomPlusOriginal.setOpaque(false);
		btnZoomPlusOriginal.setContentAreaFilled(false);
		btnZoomPlusOriginal.setBorderPainted(false);
		btnZoomPlusOriginal.setVisible(false);

		btnZoomMinusOriginal = new JButton("");
		btnZoomMinusOriginal.setToolTipText("Zoom out");
		btnZoomMinusOriginal.setBounds(484, 527, 36, 23);
		panelOriginalStructure.add(btnZoomMinusOriginal);
		btnZoomMinusOriginal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnZoomMinusOriginal.setIcon(minus2);
		
		btnZoomMinusOriginal.setOpaque(false);
		btnZoomMinusOriginal.setContentAreaFilled(false);
		btnZoomMinusOriginal.setBorderPainted(false);
		btnZoomMinusOriginal.setVisible(false);
		
		btnBackOriginalStructure = new JButton("");
		btnBackOriginalStructure.setToolTipText("Back to package dependence view");
		
		btnZoomMinusOriginal.setIcon(minus2);
		
			btnBackOriginalStructure.setIcon(buttonback2);
		btnBackOriginalStructure.setOpaque(false);
		btnBackOriginalStructure.setContentAreaFilled(false);
		btnBackOriginalStructure.setBorderPainted(false);
		
		btnBackOriginalStructure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				btnBackOriginalStructure.setVisible(false);
				lblPackageNameOriginal.setVisible(false);
			}
		});
		btnBackOriginalStructure.setBounds(429, 527, 31, 23);
		panelOriginalStructure.add(btnBackOriginalStructure);
		
		lblPackageNameOriginal = new JLabel("");
		lblPackageNameOriginal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPackageNameOriginal.setBounds(242, 527, 189, 23);
		panelOriginalStructure.add(lblPackageNameOriginal);
		//		panelGeral
			
		JPanel panelSelectProjects = new JPanel();
		panelSelectProjects.setBounds(169, 11, 1009, 41);
		panelOriginalStructure.add(panelSelectProjects);
		panelSelectProjects.setBorder(new CompoundBorder());
		GridBagLayout gbl_panelSelectProjects = new GridBagLayout();
		gbl_panelSelectProjects.columnWidths = new int[] { 648, 418, 0 };
		gbl_panelSelectProjects.rowHeights = new int[] { 14, 0 };
		gbl_panelSelectProjects.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panelSelectProjects.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panelSelectProjects.setLayout(gbl_panelSelectProjects);
		panelSelectProjects.setVisible(true);
		panelSelectProjects.add(comboBoxProjects, gbc_comboBoxProjects);
		btnBackOriginalStructure.setVisible(false);
		
				JButton button = new JButton("Apply Restructuring");
				button.setBounds(755, 593, 178, 23);
				button.setToolTipText("Click here to impact the suggested structure about the current structure");
				button.setFont(new Font("Tahoma", Font.PLAIN, 11));
				panelGeral.add(button);
																										
		JButton btnOK = new JButton("OK");
		btnOK.setBounds(755, 11, 361, 23);
		panelGeral.add(btnOK);
		btnOK.setToolTipText("Click here to start software measurement");
		btnOK.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnOK.setForeground(new Color(0, 0, 0));
		
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxProjects.getSelectedItem() != null) {
		measuredProject = true;
		firstRrestructuring = true;
		firstRrestructuring2 = true;
		restructuredProject = false;
				} else {
		
				}
			}
		});

		JPanel panelRestrictPackets = new JPanel();
		tabbedPane.addTab("Restrict Packages", null, panelRestrictPackets, null);
		panelRestrictPackets.setLayout(null);

		JButton btnInsert = new JButton("Insert >>");
		btnInsert.setToolTipText("Insert packages in the list of blocked packages");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(measuredProject == true || restructuredProject == true){
					modelListBlockPackets.addElement(listPackets.getSelectedValue());
					
					modelListPackets.remove(listPackets.getSelectedIndex());
					measuredProject = true;
					restructuredProject = false;
				}
				else{
					
				}
			}
		});
		btnInsert.setBounds(495, 271, 99, 23);
		panelRestrictPackets.add(btnInsert);

		JLabel lblAllPackets = new JLabel("Packages");
		lblAllPackets.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAllPackets.setHorizontalAlignment(SwingConstants.CENTER);
		lblAllPackets.setBounds(188, 11, 119, 14);
		panelRestrictPackets.add(lblAllPackets);

		JLabel lblNotAnalysedPackages = new JLabel("Blocked Packages");
		lblNotAnalysedPackages.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotAnalysedPackages.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNotAnalysedPackages.setBounds(787, 11, 119, 14);
		panelRestrictPackets.add(lblNotAnalysedPackages);

		JButton btnRemove = new JButton("<< Remove");
		btnRemove.setToolTipText("Remove packages in the list of blocked packages");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(measuredProject == true || restructuredProject == true){
				
					modelListPackets.add(modelListPackets.getSize(), listBlockPackets.getSelectedValue());
					modelListBlockPackets.remove(listBlockPackets.getSelectedIndex());
					measuredProject = true;
					restructuredProject = false;
				}
				else{
		
					
				}
			}
		});
		btnRemove.setBounds(495, 305, 99, 23);
		panelRestrictPackets.add(btnRemove);

		JScrollPane scrollPanePackets = new JScrollPane();
		scrollPanePackets.setBounds(10, 26, 475, 545);
		panelRestrictPackets.add(scrollPanePackets);
		listPackets = new JList<String>(modelListPackets);
		listPackets.setLocation(0, 19);
		scrollPanePackets.setViewportView(listPackets);
		listPackets.setFont(new Font("Tahoma", Font.PLAIN, 11));
		listPackets.setForeground(Color.BLACK);

		JScrollPane scrollPaneBlockPackets = new JScrollPane();
		scrollPaneBlockPackets.setBounds(609, 26, 475, 545);
		panelRestrictPackets.add(scrollPaneBlockPackets);
		listBlockPackets = new JList<String>(modelListBlockPackets);
		listBlockPackets.setFont(new Font("Tahoma", Font.PLAIN, 11));
		listBlockPackets.setForeground(Color.BLACK);
		scrollPaneBlockPackets.setViewportView(listBlockPackets);

		JLabel LblInformation = new JLabel(
				"   After restricting the packages just request the restructuring is not necessary to remeasure the software");
		ImageIcon warning = new ImageIcon(getClass().getResource("/icons/warning.png"));
		LblInformation.setIcon(warning);
		LblInformation.setHorizontalAlignment(SwingConstants.CENTER);
		LblInformation.setFont(new Font("Tahoma", Font.BOLD, 11));
		LblInformation.setBounds(10, 598, 1074, 14);
		panelRestrictPackets.add(LblInformation);

		JPanel panelMovimentLog = new JPanel();
		tabbedPane.addTab("View Movement Log", null, panelMovimentLog, null);
		modelLogMovements = new DefaultListModel<String>();
		GridBagLayout gbl_panelMovimentLog = new GridBagLayout();
		gbl_panelMovimentLog.columnWidths = new int[] { 1100 };
		gbl_panelMovimentLog.rowHeights = new int[] { 568, 49 };
		gbl_panelMovimentLog.columnWeights = new double[] { 0.0 };
		gbl_panelMovimentLog.rowWeights = new double[] { 0.0, 0.0 };
		panelMovimentLog.setLayout(gbl_panelMovimentLog);

		JScrollPane scrollPaneLogMoviments = new JScrollPane();
		GridBagConstraints gbc_scrollPaneLogMoviments = new GridBagConstraints();
		gbc_scrollPaneLogMoviments.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneLogMoviments.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPaneLogMoviments.gridx = 0;
		gbc_scrollPaneLogMoviments.gridy = 0;
		panelMovimentLog.add(scrollPaneLogMoviments, gbc_scrollPaneLogMoviments);
		scrollPaneLogMoviments.setBounds(417, 26, 290, 545);
		scrollPaneLogMoviments.setViewportBorder(null);

		listMovimentLog = new JList<String>();
		scrollPaneLogMoviments.setViewportView(listMovimentLog);
		listMovimentLog.setVisibleRowCount(36);
		listMovimentLog.setForeground(Color.BLACK);
		listMovimentLog.setModel(modelLogMovements);
		listMovimentLog.setBackground(Color.WHITE);

		JButton btnNewButton = new JButton("Save Log");
		btnNewButton.setToolTipText("Save the restructuring movements log");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 1;
		panelMovimentLog.add(btnNewButton, gbc_btnNewButton);

		JPanel panelBackgroundLogMoviment = new JPanel();
		panelBackgroundLogMoviment.setBackground(Color.WHITE);
		GridBagConstraints gbc_panelBackgroundLogMoviment = new GridBagConstraints();
		gbc_panelBackgroundLogMoviment.fill = GridBagConstraints.BOTH;
		gbc_panelBackgroundLogMoviment.gridx = 0;
		gbc_panelBackgroundLogMoviment.gridy = 0;
		panelMovimentLog.add(panelBackgroundLogMoviment, gbc_panelBackgroundLogMoviment);
		GridBagLayout gbl_panelBackgroundLogMoviment = new GridBagLayout();
		gbl_panelBackgroundLogMoviment.columnWidths = new int[] { 0, 0 };
		gbl_panelBackgroundLogMoviment.rowHeights = new int[] { 0, 0 };
		gbl_panelBackgroundLogMoviment.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panelBackgroundLogMoviment.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panelBackgroundLogMoviment.setLayout(gbl_panelBackgroundLogMoviment);

	}
	
	public static void main(String[] args){
		new TelaPrincipalTeste();
	}

}