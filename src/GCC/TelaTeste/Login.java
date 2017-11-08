package GCC.TelaTeste;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class Login {

//	Create I created the JFrame variable as a global variable so I did not have to create every instant I was going to use
	JFrame MyScreen;
	
	@SuppressWarnings("rawtypes")
	static JComboBox Classes, ClassesP;
	int contPacotes = 0, contClass = 0;
	JButton classDep;
	@SuppressWarnings("rawtypes")
	DefaultListModel listatributos, listmetodos;
	
	@SuppressWarnings("rawtypes")
	static JComboBox Projetos;
	
//	Class calling my class that creates my JFrame
	public Login() {
		
//		System.getProperty = Gets the system property indicated by the specified key.
//		Returns the directory of the specified class
		System.getProperty("java.classpath");
//		System.out.println (System.getProperty ("java.class.path"));
//		Call
		InitializeAST();
	}
	
//	 @SuppressWarnings to remove warnings
	@SuppressWarnings({ "rawtypes"})
	
//	Class that create my JFrame
	public static JComboBox getClassJ(){
		return ClassesP;
	}
	public static String myProject(){
		return (String) Projetos.getSelectedItem();
	}
	
	@SuppressWarnings({ "rawtypes"})
	public void InitializeAST(){

//		Create my JFrame and I set it up
		JComponent.setDefaultLocale(Locale.ENGLISH);
		MyScreen = new JFrame();
		MyScreen.setResizable(false);
		MyScreen.setFont(new Font("Arial", Font.PLAIN, 14));
		MyScreen.setTitle("Abstract Syntax Tree");
		MyScreen.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		MyScreen.getContentPane().setLayout(null);
		
		JLabel lblProjects = new JLabel("Projects");
        lblProjects.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblProjects.setHorizontalAlignment(SwingConstants.CENTER);
        lblProjects.setToolTipText("Projects");
        lblProjects.setBounds(10, 10, 100, 25);
        MyScreen.getContentPane().add(lblProjects);
        
        JLabel lblPackages = new JLabel("Packages");
        lblPackages.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblPackages.setHorizontalAlignment(SwingConstants.CENTER);
        lblPackages.setBounds(10, 80, 100, 25);
        MyScreen.getContentPane().add(lblPackages);
        
        JLabel lblClass = new JLabel("Class");
        lblClass.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblClass.setToolTipText("Class");
        lblClass.setHorizontalAlignment(SwingConstants.CENTER);
        lblClass.setBounds(10, 150, 100, 25);
        MyScreen.getContentPane().add(lblClass);
		
//		Create my JComboBoxs to receive Strings
		Projetos = new JComboBox();
		JComboBox Pacotes  = new JComboBox();
		Classes  = new JComboBox();	
		ClassesP  = new JComboBox();	
		
		Projetos.setFont(new Font("Arial", Font.PLAIN, 12));		
		Pacotes.setFont(new Font("Arial", Font.PLAIN, 12));		
		Classes.setFont(new Font("Arial", Font.PLAIN, 12));			
		ClassesP.setFont(new Font("Arial", Font.PLAIN, 12));
		
//		Creating JList to receive the methods and attributes
		JList Atributos = new JList();		
		Atributos.setFont(new Font("Arial", Font.PLAIN, 12));
		Atributos.setBackground(Color.WHITE);
		
        JScrollPane attributesScroll  = new JScrollPane();
        attributesScroll.setBounds(20, 290, 415, 300);
        attributesScroll.setViewportView(Atributos);
		
		JList Metodos = new JList();
		Metodos.setFont(new Font("Arial", Font.PLAIN, 12));
		Metodos.setBackground(Color.WHITE);
		
		JScrollPane methodsScroll = new JScrollPane();
        methodsScroll.setBounds(515, 290, 415, 300);
        methodsScroll.setViewportView(Metodos);
        
        MyScreen.getContentPane().add(methodsScroll);		                
        MyScreen.getContentPane().add(attributesScroll);

//		Creating DefaultListModel to receive the methods and attributes the of JList Methods and Attributes
		listmetodos = new DefaultListModel();  
		listatributos = new DefaultListModel();  
		
//		Creating JTextArea to receive the size of Methods, Attributes and SizeClass
		JTextArea qtdatributos = new JTextArea();
		qtdatributos.setFont(new Font("Arial", Font.PLAIN, 16));
		qtdatributos.setBounds(265, 621, 22, 22);
		qtdatributos.setEditable(false);
		MyScreen.getContentPane().add(qtdatributos);
		
		JTextArea qtdmetodos = new JTextArea();
		qtdmetodos.setFont(new Font("Arial", Font.PLAIN, 16));
		qtdmetodos.setBounds(770, 620, 22, 22);
		qtdmetodos.setEditable(false);
		MyScreen.getContentPane().add(qtdmetodos);		
		
		JTextArea SizeClass = new JTextArea();
		SizeClass.setFont(new Font("Arial", Font.PLAIN, 16));
		SizeClass.setBounds(485, 621, 22, 23);
		SizeClass.setEditable(false);
		MyScreen.getContentPane().add(SizeClass);
		
//		Creating JLabes Methods, Methods and SizeClass.
		JLabel lblSc = new JLabel("SizeClass:");
		lblSc.setToolTipText("Size Class");
		lblSc.setFont(new Font("Arial", Font.PLAIN, 16));
		lblSc.setBounds(406, 618, 87, 27);
		MyScreen.getContentPane().add(lblSc);
		
		JLabel lblMtodos = new JLabel("Methods");
		lblMtodos.setFont(new Font("Arial", Font.PLAIN, 16));
		lblMtodos.setBounds(700, 250, 80, 35);
		MyScreen.getContentPane().add(lblMtodos);
		
		JLabel lblAtributos = new JLabel("Attributes");
		lblAtributos.setFont(new Font("Arial", Font.PLAIN, 16));
		lblAtributos.setBounds(195, 250, 80, 35);
		MyScreen.getContentPane().add(lblAtributos);
        
//		Create toolTips of JComboBoxs 
		Projetos.setToolTipText("Select the projects");
		Projetos.setBounds(125, 10, 795, 30);
		MyScreen.getContentPane().add(Projetos);
		
		Pacotes.setToolTipText("Select the package");
		Pacotes.setBounds(125, 80, 795, 30);
		MyScreen.getContentPane().add(Pacotes);
		
//		Pertence a segunda janela
		Classes.setToolTipText("Select the class");
		Classes.setBounds(125, 150, 795, 30);
		MyScreen.getContentPane().add(Classes);
		
		//Inserction Second Windows
		classDep = new JButton("GeneClassDep");
		classDep.setToolTipText("generate class dependecies");
		
		classDep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		classDep.setBounds(815, 600, 120, 60);
		MyScreen.getContentPane().add(classDep);		
		
//		Create JLabels
		JLabel lblTotalDeMtodos = new JLabel("Total of Methods:");
		lblTotalDeMtodos.setToolTipText("Total of methods");
		lblTotalDeMtodos.setFont(new Font("Arial", Font.PLAIN, 16));
		lblTotalDeMtodos.setBounds(645, 620, 145, 27);
		MyScreen.getContentPane().add(lblTotalDeMtodos);
		
		JLabel lblTotalDeAtibutos = new JLabel("Total of Attributes:");
		lblTotalDeAtibutos.setToolTipText("Total of attributes");
		lblTotalDeAtibutos.setFont(new Font("Arial", Font.PLAIN, 16));
		lblTotalDeAtibutos.setBounds(136, 620, 140, 27);
		MyScreen.getContentPane().add(lblTotalDeAtibutos);
		
//		Lines of code Create buttons to remove all items of JList	
//		Lines of code Code of botton to clear Jlist Attributes	
// 		Lines of code Code of botton to clear Jlist Methods
        
//      Taking the selected project and taking its packages
		Projetos.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent arg0) {
				
//				Comparing whether the JComboBox item numbers of projects are the same
				if (true) {
					
				}else{
					JOptionPane.showMessageDialog (null, 
					"It's necessary select a project before start.", 
					"Warning", JOptionPane.WARNING_MESSAGE);
					}
				}			
		});
		
//      Taking the selected package and taking its class
		Pacotes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (true) {  							
				}
			}
    	});
		
//      Taking the selected class and taking its methods, attributes and SizeClass
		Classes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
//				Creating variable SizeClass
//				int sizeclass = 0;
			}
		});

//		More configuration of JFrame	
//		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
//      int lar = (int) tela.getWidth();
//      int alt = (int) tela.getHeight();
//      System.out.println("Sua tela tem resolução " + lar + " x " + alt);
		
		MyScreen.setSize(950, 700);
		MyScreen.setVisible(true);
	}
}	
