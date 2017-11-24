package br.ufla.gcc.ppoo.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;

import br.ufla.gcc.ppoo.BancoDeDados.BancoDeDados;
import br.ufla.gcc.ppoo.Control.ControleDadosFilmes;
import br.ufla.gcc.ppoo.Control.ControleDadosUsuarios;
import br.ufla.gcc.ppoo.Dados.DadosLogin;
import br.ufla.gcc.ppoo.Dados.Filme;
import javax.swing.table.DefaultTableModel;

public class TelaPrincipal {
	
	JFrame myViewMain;
	JPanel myPanelCadastro, myPanelListagem;
	BancoDeDados bancoDDados = new BancoDeDados();
	ControleDadosFilmes controlFilmes = new ControleDadosFilmes();
	ControleDadosUsuarios controlUser = new ControleDadosUsuarios();
	
	Filme filme = new Filme();
	private JTextField textFieldNome;
	private JTextField textFieldData;
	private JTextField textFieldDuracao;
	private JTextField textFieldGenero;
	private JTextField textFieldWorKeys;
	private JEditorPane editorPaneDescricao;
	private JTable table;
	
	public void limpaCampos(){
		textFieldNome.setText(null);
		textFieldData.setText(null);
		textFieldDuracao.setText(null);
		textFieldGenero.setText(null);
		textFieldWorKeys.setText(null);
		editorPaneDescricao.setText(null);
	}
	
	public boolean confereCampos(JTextField textFieldNome, JTextField textFieldWorKeys, JTextField textFieldData,
								JTextField textFieldDuracao, JTextField textFieldGenero, JEditorPane editorPaneDescricao){
		boolean ok = false;
		
		if (textFieldNome.getText().length() > 0 && textFieldWorKeys.getText().length() > 0 && textFieldData.getText().length() > 0 
				&& textFieldDuracao.getText().length() > 0 && textFieldGenero.getText().length() > 0 && editorPaneDescricao.getText().length() > 0) {
			ok = true;
		}
		
		return ok;
	}
	
	public boolean contens(JTextField textFieldWorKeys){
		boolean bool = false;
		
		if(textFieldWorKeys.getText().contains("-")) {
			bool = true;
		}
		
		return bool;
	}
	
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
		myViewMain.setResizable(false);
		myViewMain.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		myViewMain.setTitle("Menu Principal");
		myViewMain.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		myViewMain.getContentPane().setLayout(null);
		
//		myViewMain.setLocationRelativeTo(null);
		
		myPanelCadastro = new JPanel();
		myPanelCadastro.setBackground(new Color(255, 255, 255));
		myPanelCadastro.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
//		myPanelCadastro.setLocation(90, 55);
		myPanelCadastro.setLocation(1111, 511115);
		myPanelCadastro.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		myPanelCadastro.setVisible(false);
//		myPanelCadastro.setSize(485, 415);
		myPanelCadastro.setSize(485, 415);
		myPanelCadastro.setLayout(null);
		myViewMain.getContentPane().add(myPanelCadastro);
		
		myPanelCadastro.setVisible(false);
		
		myPanelListagem = new JPanel();
		myPanelListagem.setBackground(new Color(255, 255, 255));
		myPanelListagem.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		myPanelListagem.setLocation(90, 55);
		myPanelListagem.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		myPanelListagem.setVisible(true);
		myPanelListagem.setSize(485, 415);
		myPanelListagem.setLayout(null);
		myViewMain.getContentPane().add(myPanelListagem);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/ufla/gcc/ppoo/Imagens/iconeExit.jpg")));
		button.setBounds(442, 22, 18, 15);
		myPanelListagem.add(button);
		
		JLabel lblTituloListagem = new JLabel("Meus Filmes");
		lblTituloListagem.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloListagem.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 30));
		lblTituloListagem.setBounds(125, 30, 240, 45);
		myPanelListagem.add(lblTituloListagem);
		
		JLabel lblIconLista = new JLabel("");
		lblIconLista.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/ufla/gcc/ppoo/Imagens/Lista.jpg")));
		lblIconLista.setVerticalAlignment(SwingConstants.TOP);
		lblIconLista.setBounds(110, 35, 40, 40);
		myPanelListagem.add(lblIconLista);
		
//		table = new JTable();
		
		
		Object[] titulosColunas = {"TESTE1", "TESTE2"};

        Object[][] dados = {
            {"Like a Stone", "Audioslave"},
            {"Alive", "Pearl Jam"},
            {"Scorpions", "sdfsfsdfdsf "},
            {"Bom Jovi", "fdsfdsfdsf"}
        };
        
//        DefaultTableModel modelo = new DefaultTableModel(dados, titulosColunas);

        table = new JTable(dados, titulosColunas);
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        		{"Like a Stone", null, "Audioslave"},
        		{"Alive", null, "Pearl Jam"},
        		{null, null, null},
        		{null, null, null},
        	},
        	new String[] {
        		"Musica", "Teste", "Cantor"
        	}
        ));
        table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
	    table.setPreferredScrollableViewportSize(new Dimension(500, 70));
	    table.setFillsViewportHeight(true);
	    table.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 12));
	    table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	    table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setBounds(10, 100, 465, 305);
		table.clearSelection();
		
		myPanelListagem.add(table);
		
		JSlider slider = new JSlider(JSlider.VERTICAL, 0, 5, 1);
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
//		slider.addChangeListener((ChangeListener) this);
		
		slider.setBounds(617, 442, 75, 92);
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
		
		JButton btnExit = new JButton("");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myPanelCadastro.setVisible(false);
			}
		});
		btnExit.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/ufla/gcc/ppoo/Imagens/iconeExit.jpg")));
		btnExit.setBounds(455, 10, 18, 15);
		myPanelCadastro.add(btnExit);
		
		JLabel lblCadastro = new JLabel("Cadastrar Filme");
		lblCadastro.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastro.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 30));
		lblCadastro.setBounds(130, 20, 240, 45);
		myPanelCadastro.add(lblCadastro);
		
		JLabel lblNewItem = new JLabel("");
		lblNewItem.setVerticalAlignment(SwingConstants.TOP);
		lblNewItem.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/ufla/gcc/ppoo/Imagens/Novo_Item.png")));
		lblNewItem.setBounds(74, 22, 40, 40);
		myPanelCadastro.add(lblNewItem);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setForeground(new Color(0, 0, 0));
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(6, 90, 45, 25);
		lblNome.setBackground(Color.GRAY);
		myPanelCadastro.add(lblNome);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(50, 90, 423, 25);
		myPanelCadastro.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		JLabel lblData = new JLabel("Data de Lançamento");
		lblData.setHorizontalAlignment(SwingConstants.CENTER);
		lblData.setForeground(Color.BLACK);
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblData.setBackground(Color.GRAY);
		lblData.setBounds(8, 160, 130, 25);
		myPanelCadastro.add(lblData);
		
		textFieldData = new JTextField();
//		textFieldData.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyTyped(KeyEvent arg0) {
//				if(textFieldData.getText().length() == 2) {
//		        	textFieldData.setText(textFieldData.getText() + '/');
//		        }				
//		        if(textFieldData.getText().length() == 5) {
//					textFieldData.setText(textFieldData.getText() + '/');
//				}
//		        if(textFieldData.getText().length() == 10) {
//		        	textFieldData.setEditable(false);
//		        }
//			}
//		});
		textFieldData.setToolTipText("Preencha esse campo da seguinte forma, 01/11/2017");
		textFieldData.setColumns(10);
		textFieldData.setBounds(142, 160, 70, 25);
		myPanelCadastro.add(textFieldData);
		
		JLabel lblDuraoDoFilme = new JLabel("Duração");
		lblDuraoDoFilme.setHorizontalAlignment(SwingConstants.CENTER);
		lblDuraoDoFilme.setForeground(Color.BLACK);
		lblDuraoDoFilme.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDuraoDoFilme.setBackground(Color.GRAY);
		lblDuraoDoFilme.setBounds(218, 160, 55, 25);
		myPanelCadastro.add(lblDuraoDoFilme);
		
		textFieldDuracao = new JTextField();
		textFieldDuracao.setToolTipText("Preencha esse campo da seguinte forma, 2h15m");
		textFieldDuracao.setColumns(10);
		textFieldDuracao.setBounds(275, 160, 70, 25);
		myPanelCadastro.add(textFieldDuracao);
		
		JLabel lblGnero = new JLabel("Gênero");
		lblGnero.setHorizontalAlignment(SwingConstants.CENTER);
		lblGnero.setForeground(Color.BLACK);
		lblGnero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGnero.setBackground(Color.GRAY);
		lblGnero.setBounds(350, 160, 50, 25);
		myPanelCadastro.add(lblGnero);
		
		textFieldGenero = new JTextField();
		textFieldGenero.setToolTipText("");
		textFieldGenero.setColumns(10);
		textFieldGenero.setBounds(400, 160, 75, 25);
		myPanelCadastro.add(textFieldGenero);
		
		JLabel lblPalavras = new JLabel("Palavras-chaves(mínimo 2)");
		lblPalavras.setHorizontalAlignment(SwingConstants.CENTER);
		lblPalavras.setForeground(Color.BLACK);
		lblPalavras.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPalavras.setBackground(Color.GRAY);
		lblPalavras.setBounds(6, 125, 174, 25);
		myPanelCadastro.add(lblPalavras);
		
		textFieldWorKeys = new JTextField();
		textFieldWorKeys.setToolTipText("Preencha esse campo da seguinte forma, 01/11/2017");
		textFieldWorKeys.setColumns(10);
		textFieldWorKeys.setBounds(180, 125, 295, 25);
		myPanelCadastro.add(textFieldWorKeys);
		
		JLabel lblDescrio = new JLabel("Descrição");
		lblDescrio.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescrio.setForeground(Color.BLACK);
		lblDescrio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescrio.setBackground(Color.GRAY);
		lblDescrio.setBounds(7, 190, 65, 25);
		myPanelCadastro.add(lblDescrio);
		
		editorPaneDescricao = new JEditorPane();
		editorPaneDescricao.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		editorPaneDescricao.setBounds(10, 215, 465, 155);
		editorPaneDescricao.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
		myPanelCadastro.add(editorPaneDescricao);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaCampos();
			}
		});
		btnCancelar.setForeground(new Color(0, 0, 0));
		btnCancelar.setToolTipText("Entrar");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCancelar.setBackground(new Color(255, 255, 255));
		btnCancelar.setBounds(285, 380, 150, 25);
		myPanelCadastro.add(btnCancelar);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DadosLogin dl = controlUser.buscarDados(dadosLogin.getEmail());
				
				filme.setNome(textFieldNome.getText());
				filme.setData(textFieldData.getText());
				filme.setDescricao(editorPaneDescricao.getText());
				filme.setWordKeys(textFieldWorKeys.getText());
				filme.setGenero(textFieldGenero.getText());
				filme.setDuracaoFilme(textFieldDuracao.getText());
				
				if ( confereCampos(textFieldNome, textFieldWorKeys, textFieldData, textFieldDuracao, textFieldGenero, editorPaneDescricao) ) {
					if (contens(textFieldWorKeys)) {
						controlFilmes.CadastrarFilme(filme, dl.getId());
						JOptionPane.showMessageDialog(null, "Filme cadastrado com sucesso", "Filme cadastrado", JOptionPane.INFORMATION_MESSAGE);
						limpaCampos();
						
//						JOptionPane.showMessageDialog(null, slider.getValue());
						
					} else {
						JOptionPane.showMessageDialog(null, "Campo 'Palavras-chave' não está preenchido corretamente." 
								+ "\n" + " Para salvar o filme as palavras-chave precisa ser separadas por '-'.",
								"Campo Palavras-chave não corresponde ao padrão", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos para que seja possível salvar o filme.",
							"Erro Ao Salvar", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		btnSalvar.setForeground(new Color(0, 0, 0));
		btnSalvar.setToolTipText("Entrar");
		btnSalvar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnSalvar.setBackground(new Color(255, 255, 255));
		btnSalvar.setBounds(55, 380, 150, 25);
		myPanelCadastro.add(btnSalvar);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 255, 255));
		menuBar.setBounds(0, 0, 75, 20);
//		menuBar.setBorder(true);
		myViewMain.getContentPane().add(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		mnMenu.setBackground(new Color(255, 255, 255));
		menuBar.add(mnMenu);
		
		JMenuItem mnCadastro = new JMenuItem("Cadastra Novo Filme");
		mnCadastro.setBackground(new Color(255, 255, 255));
		mnCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myPanelCadastro.setVisible(true);
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
				JOptionPane.showMessageDialog(null, "Até Mais...", "Sair", JOptionPane.CLOSED_OPTION);
				myViewMain.dispose();
			}
		});
		mntmSair.setBackground(new Color(255, 255, 255));
		mnSair.add(mntmSair);
		
		JMenuItem mntmTela = new JMenuItem("Logout");
		mntmTela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bancoDDados.Desconecta();
				JOptionPane.showMessageDialog(null, "Você será redirecionado para a tela de login.", "Tela de Login", JOptionPane.CLOSED_OPTION);
				myViewMain.dispose();
				new TelaLogin();
			}
		});
		mntmTela.setBackground(new Color(255, 255, 255));
		mnSair.add(mntmTela);
		
//		myViewMain.setSize(675, 550);
		myViewMain.setSize(708, 574);
		myViewMain.setVisible(true);
	}
}	

