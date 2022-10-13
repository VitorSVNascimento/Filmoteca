package nasc.filmoteca.gui;

import static javax.swing.JOptionPane.DEFAULT_OPTION;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.showInternalMessageDialog;
import static javax.swing.JOptionPane.showMessageDialog;
import static javax.swing.JOptionPane.showOptionDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import nasc.filmoteca.arquivo.Arquivo;
import nasc.filmoteca.modelo.Filme;
import nasc.filmoteca.modelo.FilmeList;
import nasc.filmoteca.modelo.Pessoa;
import nasc.filmoteca.validacao.Validacoes;
import net.miginfocom.swing.MigLayout;

public class IgFilmoteca extends JFrame {
	private JTextField pesquisatextField;
	private FilmeList todosFilmes;
	private List<Filme> filmesExibidos;
	private Filme filmeSelecionado;
	private JLabel nomeFilmeLabel;
	private JTextPane sinopsetextPane;
	private JButton anteriorButton;
	private JButton proximoButton;
	private JTextField diretorTextField;
	private JTextField autoresTextField;
	private JTextField imdbTextField;
	private JLabel numeroFilmesLabel;
	private JLabel anoLabel;
	private JLabel classificacaoLabel;
	private JLabel horasLabel;
	private JSpinner avaliacaoUsuarioSpinner;
	private JRadioButton bluRayRadioButton;
	private JRadioButton dvdRadioButton;
	private DefaultTableModel defaultTableModel;
	private JPanel imagemPanel;
	private JLabel imagemLabel;
	private JLabel genero1Label;
	private JLabel genero2Label;
	private JLabel genero3Label;


	/**
	 * Cria e exibe a gui 
	 */
	public IgFilmoteca() {
		setTitle("Filmoteca");
		todosFilmes = new FilmeList();

		final int WIDTH = 800;
		final int HEIGHT = 555;
		
		setBounds(100, 100, WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu filmotecaMenu = new JMenu("Filmoteca");
		filmotecaMenu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(filmotecaMenu);
		
		JMenuItem obterFilmesMenuItem = new JMenuItem("Obter Filmes...");
		obterFilmesMenuItem.setMnemonic(KeyEvent.VK_O);
		filmotecaMenu.add(obterFilmesMenuItem);
		
		
		JMenuItem pesquisarMenuItem = new JMenuItem("Pesquisar");
		pesquisarMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisatextField.requestFocus();
			}
		});
		pesquisarMenuItem.setMnemonic(KeyEvent.VK_P);
		pesquisarMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
		filmotecaMenu.add(pesquisarMenuItem);
		
		JMenuItem melhoresMenuItem = new JMenuItem("Melhores Filmes");
		melhoresMenuItem.setMnemonic(KeyEvent.VK_P);
		melhoresMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK));
		filmotecaMenu.add(melhoresMenuItem);
		
		JSeparator separator = new JSeparator();
		filmotecaMenu.add(separator);
		
		JMenuItem sairMenuItem = new JMenuItem("Sair");
		sairMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		sairMenuItem.setMnemonic(KeyEvent.VK_S);
		filmotecaMenu.add(sairMenuItem); 
		
		JMenu ajudaMenu = new JMenu("Ajuda");
		ajudaMenu.setMnemonic(KeyEvent.VK_U);
		menuBar.add(ajudaMenu);
		
		JMenuItem sobreMenuItem = new JMenuItem("Sobre a Filmoteca");
		sobreMenuItem.setMnemonic(KeyEvent.VK_S);
		ajudaMenu.add(sobreMenuItem);
		
		
		
		JPanel pesquisaPanel = new JPanel();
		getContentPane().add(pesquisaPanel, BorderLayout.NORTH);
		pesquisaPanel.setLayout(new MigLayout("", "[114px,grow]", "[20px]"));
		
		pesquisatextField = new JTextField();
		pesquisatextField.setText("Pesquise por filme, artista, autor, ou diretor");
		pesquisaPanel.add(pesquisatextField, "cell 0 0,grow");
		pesquisatextField.setColumns(10);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		anteriorButton = new JButton("< Anterior");
		anteriorButton.setEnabled(false);
		anteriorButton.setMnemonic(KeyEvent.VK_A);
		panel.add(anteriorButton);
		
		numeroFilmesLabel = new JLabel("1/1");
		panel.add(numeroFilmesLabel);
		
		proximoButton = new JButton("Proximo >");
		proximoButton.setEnabled(false);
		proximoButton.setMnemonic(KeyEvent.VK_P);
		panel.add(proximoButton);
		
		JPanel filmesPanel = new JPanel(),elencoPanel = new JPanel();
		
		JTabbedPane filmesPane = new JTabbedPane(JTabbedPane.TOP);
		filmesPane.addTab("Filme", null, filmesPanel,"Informações Sobre o Filme");
		filmesPanel.setLayout(null);
		
		imagemPanel = new JPanel();
		imagemPanel.setToolTipText("Poster do Filme");
		imagemPanel.setBounds(6, 6, 250, 371);
		filmesPanel.add(imagemPanel);
		imagemPanel.setLayout(new BorderLayout(0, 0));
		
		imagemLabel = new JLabel("Imagem não encontrada");
		imagemPanel.add(imagemLabel, BorderLayout.CENTER);
		
		JPanel dadosPanel = new JPanel();
		dadosPanel.setBounds(285, 6, 438, 81);
		filmesPanel.add(dadosPanel);
		dadosPanel.setLayout(new MigLayout("", "[][][][][]", "[][][]"));
		
		nomeFilmeLabel = new JLabel("Titulo");
		nomeFilmeLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		dadosPanel.add(nomeFilmeLabel, "cell 0 0 5 1");
		
		anoLabel = new JLabel("Ano");
		anoLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		dadosPanel.add(anoLabel, "cell 1 1");
		
		classificacaoLabel = new JLabel("Clasificação");
		classificacaoLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		dadosPanel.add(classificacaoLabel, "cell 2 1");
		
		horasLabel = new JLabel("Horario");
		horasLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		dadosPanel.add(horasLabel, "cell 3 1");
		
		genero1Label = new JLabel("Gêneros");
		dadosPanel.add(genero1Label, "cell 1 2");
		
		genero2Label = new JLabel("");
		dadosPanel.add(genero2Label, "cell 2 2");
		
		genero3Label = new JLabel("");
		dadosPanel.add(genero3Label, "cell 3 2");
		
		JLabel diretorLabel = new JLabel("Diretor:");
		diretorLabel.setBounds(285, 102, 55, 16);
		filmesPanel.add(diretorLabel);
		
		diretorTextField = new JTextField();
		diretorTextField.setEditable(false);
		diretorLabel.setLabelFor(diretorTextField);
		diretorTextField.setBounds(343, 96, 184, 28);
		filmesPanel.add(diretorTextField);
		diretorTextField.setColumns(10);
		
		JLabel autoresLabel = new JLabel("Autores:");
		autoresLabel.setBounds(285, 134, 55, 16);
		filmesPanel.add(autoresLabel);
		
		autoresTextField = new JTextField();
		autoresTextField.setEditable(false);
		autoresLabel.setLabelFor(autoresTextField);
		autoresTextField.setBounds(344, 130, 434, 28);
		filmesPanel.add(autoresTextField);
		autoresTextField.setColumns(10);
		
		JPanel sinopsePanel = new JPanel();
		sinopsePanel.setBorder(new TitledBorder(null, "Sinopse", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sinopsePanel.setBounds(283, 162, 495, 130);
		filmesPanel.add(sinopsePanel);
		sinopsePanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane sinopseScrollPane = new JScrollPane();
		sinopseScrollPane.setBackground(Color.WHITE);
		sinopsePanel.add(sinopseScrollPane, BorderLayout.CENTER);
		
		sinopsetextPane = new JTextPane();
		sinopsetextPane.setEditable(false);
		sinopseScrollPane.setViewportView(sinopsetextPane);
		
		JPanel avaliacaoPanel = new JPanel();
		avaliacaoPanel.setBorder(new TitledBorder(null, "Avaliação", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(59, 59, 59)));
		avaliacaoPanel.setBounds(285, 299, 271, 66);
		filmesPanel.add(avaliacaoPanel);
		avaliacaoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel imdb = new JLabel("IMDB:");
		avaliacaoPanel.add(imdb);
		
		imdbTextField = new JTextField();
		imdbTextField.setEditable(false);
		imdb.setLabelFor(imdbTextField);
		avaliacaoPanel.add(imdbTextField);
		imdbTextField.setColumns(3);
		
		JLabel usuarioAvaliacaoLabel = new JLabel("Sua avaliação");
		usuarioAvaliacaoLabel.setDisplayedMnemonic(KeyEvent.VK_S);
		avaliacaoPanel.add(usuarioAvaliacaoLabel);
		
		avaliacaoUsuarioSpinner = new JSpinner();

		avaliacaoUsuarioSpinner.setModel(new SpinnerNumberModel(0, 0, 10, 1));
		usuarioAvaliacaoLabel.setLabelFor(avaliacaoUsuarioSpinner);
		avaliacaoPanel.add(avaliacaoUsuarioSpinner);
		
		JPanel midiaPanel = new JPanel();
		midiaPanel.setBorder(new TitledBorder(null, "Média", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		midiaPanel.setBounds(568, 299, 203, 66);
		filmesPanel.add(midiaPanel);
		
		bluRayRadioButton = new JRadioButton("Blu-Ray");
		bluRayRadioButton.setMnemonic(KeyEvent.VK_B);
		midiaPanel.add(bluRayRadioButton);
		
		dvdRadioButton = new JRadioButton("DVD");
		dvdRadioButton.setMnemonic(KeyEvent.VK_D);
		midiaPanel.add(dvdRadioButton);
		filmesPane.addTab("Elenco", null, elencoPanel, "Informações Sobre o Elenco");
		elencoPanel.setLayout(new BorderLayout(0, 0));
		
		JTable	elencoTable = new JTable();
		String[] COLUNAS = {"N°","Artista"};
		int linhas = 35;
		defaultTableModel = new DefaultTableModel(COLUNAS, linhas);
        elencoTable.setShowVerticalLines(true);
        elencoTable.setShowHorizontalLines(true);

        elencoTable.setModel(defaultTableModel);
        elencoTable.getColumnModel().getColumn(0).setPreferredWidth(5);
        elencoTable.getColumnModel().getColumn(1).setPreferredWidth(593);

        // Adiciona a tabela ao painel.
        JScrollPane scrollPane = new JScrollPane(elencoTable);
        elencoPanel.add(scrollPane, BorderLayout.CENTER);
		

		getContentPane().add(filmesPane, BorderLayout.CENTER);
		
		obterFilmesMenuItem.addActionListener( (e)  ->{
			String filmeECaminhoString[] = abrirArquivo();
				if(filmeECaminhoString != null) {
					int n_atual = this.todosFilmes.getFilmes().size();
				if(gerarFilme(filmeECaminhoString[0],filmeECaminhoString[1])) {
					if(n_atual==0) {
						filmesExibidos = todosFilmes.getFilmes();
						atualizaGui(filmesExibidos,filmesExibidos.get(0));	
					}
					
				}
				
				
				}
			
		});
		
		anteriorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int posicao = obtemPossicaoFilme(filmesExibidos, filmeSelecionado);
				atualizaGui(filmesExibidos,filmesExibidos.get(posicao-1));	
			}
		});
		
		proximoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int posicao = obtemPossicaoFilme(filmesExibidos, filmeSelecionado);
				atualizaGui(filmesExibidos,filmesExibidos.get(posicao+1));	
			}
		});
		
		pesquisatextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				 int key = e.getKeyCode();
				 if(key == KeyEvent.VK_ENTER) {
					 if(pesquisatextField.getText().isBlank()) {
							filmesExibidos = todosFilmes.getFilmes();
							if(filmesExibidos.size()!=0) {
							atualizaGui(filmesExibidos,filmesExibidos.get(0));	
							}else {
								imdbTextField.requestFocus();
								JOptionPane.showMessageDialog(null, "Nunhum filme cadastrado", "Erro Ao Pesquisar", JOptionPane.ERROR_MESSAGE);	
							}
					 }else {
							filmesExibidos = todosFilmes.pesquisaFilmes(pesquisatextField.getText());
							if(filmesExibidos.size()!=0) {
								atualizaGui(filmesExibidos,filmesExibidos.get(0));		
							}else {
								imdbTextField.requestFocus();
								JOptionPane.showMessageDialog(IgFilmoteca.this, String.format("%s não cadastrado", pesquisatextField.getText()), "Erro Ao Pesquisar", JOptionPane.ERROR_MESSAGE);
							}
							
					 }
				 }
				
			}
		});
		
		avaliacaoUsuarioSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(filmeSelecionado!=null) {
					filmeSelecionado.setAvaliacaoUsuario((int)avaliacaoUsuarioSpinner.getValue());	
				}
				
			}
		});
		
		bluRayRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bluRayRadioButton.isSelected()) {
					filmeSelecionado.setBlueray(true);
				}else {
					filmeSelecionado.setBlueray(false);
				}
			}
		});
		
		dvdRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dvdRadioButton.isSelected()) {
					filmeSelecionado.setDvd(true);
				}else {
					filmeSelecionado.setDvd(false);
				}
			}
		});
		
		sobreMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new IgSobre(IgFilmoteca.this);
			}
		});
		
		melhoresMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(todosFilmes.getFilmes().size()>0) {
					String opcoes[] = {"IMDB", "Avaliação pessoal"};
					
			        //Exibe menu com opções.
			        int ordem = showOptionDialog(IgFilmoteca.this, "Ordenar por:", "Filmoteca", DEFAULT_OPTION,QUESTION_MESSAGE, null, opcoes , opcoes[0]);
			        if(ordem == 0 ) {
			        //ordenar por imdb
			        	ordenarPorIMDB();
			        	atualizaGui(todosFilmes.getFilmes(), todosFilmes.getFilmes().get(0));
			        }else if (ordem == 1) {
			        	ordenarPorAvaliacao();
			        	atualizaGui(todosFilmes.getFilmes(), todosFilmes.getFilmes().get(0));
			        }
				}else {
					JOptionPane.showMessageDialog(null, "Nunhum filme cadastrado", "Erro Ao Pesquisar", JOptionPane.ERROR_MESSAGE);	
				}
				
			}
		});
		
		mudarAparencia("Nimbus");
		setResizable(false);
		setVisible(true);
	}
	
	private String[] abrirArquivo() {
		// Obtém o nome do arquivo com seu caminho absoluto.
		String nomeArquivo = IgArquivo.dialogoAbrirArquivo(this, "Abrir arquivo");

		// Verifica se o usuário cancelou a operação.
		if (nomeArquivo != null) {
			// Obtém a extensão do arquivo.
			String extensao =  Arquivo.obterExtensao(nomeArquivo);
			
			// Se necessário inclui a extensão no nome do arquivo.
			nomeArquivo = String.format("%s%s", nomeArquivo, extensao != null ? "" : ".txt");

			try {  // Solicita a abertura do arquivo.
				     String[] textoECaminho = {Arquivo.abrir(nomeArquivo),nomeArquivo.substring(0,nomeArquivo.lastIndexOf(File.separatorChar))};
				     return textoECaminho;
			
					 // Escrever o texto do arquivo na área de texto.
			} catch (IOException e) {
				showMessageDialog(this, String.format("Erro ao abrir o arquivo.\n\n%s", nomeArquivo), "Abrir arquivo", ERROR_MESSAGE);
				return null;
			}
			
		}
		return null;
	} //abrir arquivo
	
	public boolean gerarFilme(String filmeString, String caminho) {
		final String MARCACAO_FILME = "##Filme";
		int n_filmes,index;
			//numero de filmes do arquivo	
			for( n_filmes=0, index=0;index!=-1;n_filmes++) {
				if(index!=-1) {
					index++;
				}
				index=filmeString.indexOf(MARCACAO_FILME,index);

			}//for que verifica o numero de filmes
			
		try {
			
			if(n_filmes!=0) {
				for(int i=1;i<=n_filmes;i++) {
					Filme filme = new Filme();
					filme.setImagem(String.format("%s%cfilme%d.png",caminho,File.separatorChar,i));
					//coloca o ponto inicial na linha do titulo do filme
					int ponto_inicial = filmeString.indexOf(String.format("%s %d",MARCACAO_FILME, i));
					if(ponto_inicial==-1) {
						System.out.println("F");
					}
					//obtem o titulo
					ponto_inicial = filmeString.indexOf("\n",ponto_inicial)+1;
					int ponto_final = filmeString.indexOf(";",ponto_inicial);
					filme.setTitulo(filmeString.substring(ponto_inicial, ponto_final++).trim());
					
					//obtem o ano
					ponto_inicial=ponto_final;
					ponto_final=filmeString.indexOf(";",ponto_inicial);
					filme.setAno(Integer.parseInt(filmeString.substring(ponto_inicial, ponto_final++).trim()));
					
					//obtem a data
					ponto_inicial=ponto_final;
					ponto_final=filmeString.indexOf(";",ponto_inicial);
					filme.setData(filmeString.substring(ponto_inicial, ponto_final++).trim());
					if(Validacoes.validaData(filme.getData())==0) {
						JOptionPane.showMessageDialog(null, "Erro na data", "Erro Ao importar", JOptionPane.ERROR_MESSAGE);	
						return false;
					}
					
					
					//obtem a classificacao
					ponto_inicial=ponto_final;
					ponto_final=filmeString.indexOf(";",ponto_inicial);
					filme.setClassificacao(filmeString.substring(ponto_inicial, ponto_final++).trim());
					
					
					//obtem a duração
					ponto_inicial=ponto_final;
					ponto_final=filmeString.indexOf(";",ponto_inicial);
					filme.setDuracao(filmeString.substring(ponto_inicial, ponto_final++).trim());
					
					//obtem a Avaliacao
					ponto_inicial=ponto_final;
					ponto_final=filmeString.indexOf("sinopse=",ponto_inicial);
					filme.setAvaliacaoIMDB(Float.parseFloat(filmeString.substring(ponto_inicial, ponto_final++).trim()));
					
					//obtem a sinopse
					ponto_inicial = filmeString.indexOf("=",ponto_final)+1;
					ponto_final = filmeString.indexOf("gêneros=",ponto_inicial)-1;
					filme.setSinopse(filmeString.substring(ponto_inicial, ponto_final));
					
					//obtem os generos
					ponto_inicial = filmeString.indexOf("=",ponto_final)+1;
					ponto_final =  filmeString.indexOf("diretor=",ponto_inicial)-1;
					String aux = filmeString.substring(ponto_inicial, ponto_final);
					int i_aux=0,f_aux=0;
							do{
							 f_aux = aux.indexOf(";",i_aux);

							if(f_aux==-1){
							filme.getGeneros().add(aux.substring(i_aux).trim());
							}else{
							filme.getGeneros().add(aux.substring(i_aux,f_aux).trim());
							i_aux = f_aux+1;

							}
							}while(f_aux != -1);
					
					//obtem diretor
						ponto_inicial = filmeString.indexOf("=",ponto_final)+1;
						ponto_final = filmeString.indexOf("autores=",ponto_inicial)-1;
						Pessoa pessoa = new Pessoa();
						pessoa.setNome(filmeString.substring(ponto_inicial, ponto_final).trim());
						pessoa.setProfissao(pessoa.DIRETOR);
						filme.getPessoas().add(pessoa);
						
						//obtem autores
						ponto_inicial = filmeString.indexOf("=",ponto_final)+1;
						ponto_final = filmeString.indexOf("elenco=",ponto_inicial)-1;
						aux = filmeString.substring(ponto_inicial,ponto_final);
						i_aux=0;
						do{
							 f_aux = aux.indexOf(";",i_aux);

							if(f_aux==-1){
							pessoa = new Pessoa(aux.substring(i_aux).trim(),pessoa.AUTOR);
							filme.getPessoas().add(pessoa);
							}else{
								pessoa = new Pessoa(aux.substring(i_aux,f_aux).trim(),pessoa.AUTOR);
								filme.getPessoas().add(pessoa);
							i_aux = f_aux+1;

							}
							}while(f_aux != -1);
						
						//obtem elenco
						//verefica se é o ultimo filme 
						ponto_inicial = filmeString.indexOf("=",ponto_final)+1;
						if(i==n_filmes) {
							aux = filmeString.substring(ponto_inicial);
						}else {
							ponto_final = filmeString.indexOf("\n",ponto_inicial);
							aux = filmeString.substring(ponto_inicial,ponto_final);
						}
						i_aux=0;
						do{
							 f_aux = aux.indexOf(";",i_aux);

							if(f_aux==-1){
							pessoa = new Pessoa(aux.substring(i_aux).trim(),pessoa.ARTISTA);
							filme.getPessoas().add(pessoa);
							}else{
								pessoa = new Pessoa(aux.substring(i_aux,f_aux).trim(),pessoa.ARTISTA);
								filme.getPessoas().add(pessoa);
							i_aux = f_aux+1;

							}
							}while(f_aux != -1);
						
						todosFilmes.getFilmes().add(filme);
						
					
				}//for que passa em cada filme
				return true;
			}//if numero de filmes 
			return false;
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Importar arquivo", "Erro de importação", JOptionPane.ERROR_MESSAGE);	

			return false;
		}	


	}//fim do método criar String
	
	public void atualizaGui(List<Filme> lista,Filme filme) {
		
		if(lista.size()==0) {
			return;
		}
		
			imagemLabel.setIcon(new ImageIcon( filme.getImagem()));
			nomeFilmeLabel.setText(filme.getTitulo());
			sinopsetextPane.setText(filme.getSinopse());
			imdbTextField.setText(String.valueOf(filme.getAvaliacaoIMDB()));
			anoLabel.setText(String.valueOf(filme.getAno()));
			anoLabel.setToolTipText(formatarData(filme.getData()));
			classificacaoLabel.setText(formatarClassificacao(filme.getClassificacao()));
			horasLabel.setText(filme.getDuracao());
			
			int nGeneros = filme.getGeneros().size();
			if(nGeneros>0) {
				
					genero1Label.setText((filme.getGeneros().get(0))); 
					if(filme.getGeneros().get(1)!=null) {
					genero2Label.setText((filme.getGeneros().get(1))); 
					}
					
					if(filme.getGeneros().get(2)!=null) {
					genero3Label.setText((filme.getGeneros().get(2))); 
					}
				
			}
			
			autoresTextField.setText("");
			
			atualizaElenco(filme);
			
			for(Pessoa p: filme.getPessoas()) {
				if(p.getProfissao()==p.DIRETOR) {
					diretorTextField.setText(p.getNome());
				}
				
				if(p.getProfissao()==p.AUTOR) {
				String	aux = String.format("%s, %s", autoresTextField.getText(),p.getNome());
					 autoresTextField.setText(aux);
				}
			}
			autoresTextField.setText(autoresTextField.getText().replaceFirst(",", "")); 
			
			int pos_filme=obtemPossicaoFilme(lista, filme);
			numeroFilmesLabel.setText(String.format("%d/%d", pos_filme+1,lista.size()));
			//verifica os botões
			if(pos_filme+1==lista.size()) {
				proximoButton.setEnabled(false);
			}else {
				proximoButton.setEnabled(true);
			}
			if(pos_filme==0) {
				anteriorButton.setEnabled(false);
			}else {
				anteriorButton.setEnabled(true);
			}
			
		
		//midia
			if(filme.isDvd()) {
				dvdRadioButton.setSelected(true);
			}else {
				dvdRadioButton.setSelected(false);
			}
			
			if(filme.isBlueray()) {
				bluRayRadioButton.setSelected(true); 
			}else {
				bluRayRadioButton.setSelected(false);
			}
		
		filmeSelecionado = filme;
		
		

		avaliacaoUsuarioSpinner.setValue(filme.getAvaliacaoUsuario());
		
	}
	
	public void atualizaElenco(Filme filme) {
	int index_artista=0;
		for(int i =0;i<filme.getPessoas().size();i++) {
			Pessoa p = filme.getPessoas().get(i);
			if(p.getProfissao().equals(p.ARTISTA)) {
				if(!p.getNome().isBlank()) {
					defaultTableModel.setValueAt(index_artista+1, index_artista, 0);
					defaultTableModel.setValueAt(p.getNome(), index_artista, 1);
					index_artista++;
				}

			}
		}
		
	}
	
	public void ordenarPorIMDB(){
        
        Collections.sort(todosFilmes.getFilmes(), new Comparator<Filme>() {

            @Override
            public int compare(Filme f, Filme outro) {
                float x = f.getAvaliacaoIMDB();
                float y = outro.getAvaliacaoIMDB();
                if(x<y) {
                	return -1;
                }else if(x==y){
                	return 0;
                }else {
                	return 1;
                }
            }

        }.reversed());
        return;
    }
	
	
	public void ordenarPorAvaliacao(){
        
        Collections.sort(todosFilmes.getFilmes(), new Comparator<Filme>() {

            @Override
            public int compare(Filme f, Filme outro) {
                float x = f.getAvaliacaoUsuario();
                float y = outro.getAvaliacaoUsuario();
                if(x<y) {
                	return -1;
                }else if(x==y){
                	return 0;
                }else {
                	return 1;
                }
            }

        }.reversed());
        return;
    }
	
	public int obtemPossicaoFilme(List<Filme> filmes,Filme filme) {
		
		for (int i=0; i<filmes.size();i++) {
			if(filmes.get(i).equals(filme)) {
				return i;
			}
		}
		return -1;
	}
	
	public String formatarData(String data) {
		int mes=Integer.parseInt(data.substring(3,5));
		String dia= data.substring(0,2),ano=data.substring(6);
		String[] meses = {"janeiro","fevereiro","março","abril","maio","junho","julho","agosto","setembro","outubro","novembro","dezembro"};
		String mesExtenso = meses[mes-1];
		
		return String.format("%s de %s de %s", dia,mesExtenso,ano);
	}
	
	public String formatarClassificacao(String classificacao) {
		if(classificacao.equalsIgnoreCase("L")||classificacao.equalsIgnoreCase("Livre")) {
			return "Livre";
		}
		
		return String.format("%s %s",classificacao,"anos");
	}
	
	public boolean mudarAparencia(String lookAndFeelName) {
		UIManager.LookAndFeelInfo lookAndFeelInfo[] = UIManager.getInstalledLookAndFeels();
        for (LookAndFeelInfo lookAndFeel : lookAndFeelInfo) 
            if (lookAndFeelName.equalsIgnoreCase(lookAndFeel.getName())) 
                try {
                    // Carrega o look-and-feel a ser usado pela GUI. 
                    UIManager.setLookAndFeel(lookAndFeel.getClassName());

                    // Ativa a aparência da GUI alterando o seu look-and-feel.
                    SwingUtilities.updateComponentTreeUI(this);

                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                    showInternalMessageDialog(getContentPane(), "Não foi possível alterar a aparência da interface gráfica.", "Mensagem de Texto", ERROR_MESSAGE);
                    return false;
                }
        return true;
    }
}//class