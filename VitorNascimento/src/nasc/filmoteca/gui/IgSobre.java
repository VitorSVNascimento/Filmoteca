package nasc.filmoteca.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;


public class IgSobre extends JDialog {

	/**
	 * Create the dialog.
	 */
	public IgSobre(IgFilmoteca igFilmoteca) {
		getContentPane().setLayout(new BorderLayout());
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JPanel sobrePanel = new JPanel(), creditosPanel = new JPanel();
		
		// Cria um painel com abas na parte superior do painel. 
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		// Adiciona um painel para cada aba do JTabbedPane, incluindo o nome e a dica da aba.  
		tabbedPane.addTab("Sobre", null, sobrePanel, "Versão e Ano do programa");
		tabbedPane.addTab("Créditos", null, creditosPanel, "Autoria do programa" );
		
		sobrePanel.setLayout(new MigLayout("", "[][]", "[][][]"));
		creditosPanel.setLayout(new MigLayout("", "[][]", "[][][]"));
		
		
		JLabel nomeProgramaLabel = new JLabel("Filmoteca");
		nomeProgramaLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		sobrePanel.add(nomeProgramaLabel, "cell 1 0");
		
		JLabel versaoLabel = new JLabel("Versão 0.1 Ano 2021");
		versaoLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		sobrePanel.add(versaoLabel, "cell 1 1");
		
		JLabel autorLabel = new JLabel("Vitor Nascimento");
		autorLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		creditosPanel.add(autorLabel, "cell 0 1");
		
		// Adiciona o painel com abas na janela. 
		contentPanel.add(tabbedPane);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		JButton okButton = new JButton("OK");
		buttonPane.add(okButton);
		
		// Fecha a caixa de diÃ¡logo e libera a memÃ³ria alocada pra ela.
		okButton.addActionListener( (e) ->  IgSobre.this.dispose() );

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Sobre Mensagem de Texto");
		
		// Define o tamanho da caixa de diÃ¡logo.
		setSize(414, 212);
		
		// Define a posiÃ§Ã£o da caixa de diÃ¡logo em relaÃ§Ã£o a janela principal do programa. 
		setLocationRelativeTo(igFilmoteca);
		
		setResizable(false);
		setVisible(true);

	}

}
