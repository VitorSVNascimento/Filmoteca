package nasc.filmoteca.gui;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Fornece serviços de interface gráfica que dão ao usuário acesso aos arquivos do sistema de arquivos.
 * 
 *@author Prof. Márlon Oliveira da Silva 
 *
 *@version 0.3
 *
 */
public class IgArquivo {
	private static JFileChooser fileChooser;
	
	/**
	 * Obtém a referência única do objeto <code>JFileChooser</code>.
	 */
	private static JFileChooser getFileChooser() {
		return (fileChooser == null) ? fileChooser = new JFileChooser() : fileChooser;
	}

	/** 
	 * Exibe uma caixa de diálogo <code>JFileChooser</code> para o usuário navegar pelo sistema de arquivos e selecionar o arquivo que será aberto. 
	 * 
	 * @param janela objeto <code>Component</code> que identifica a janela sobre a qual o <code>JFileChooser</code> será exibido. Use <code>null</code>
	 * se não existir uma janela ou para centralizar o <code>JFileChooser</code> na tela. 
	 *  
	 * @param titulo <code>String</code> com o nome da barra de título da caixa de diálogo.
	 *        
	 * @return um <code>String</code> com o nome do arquivo a ser aberto. Será retornado <code>null</code> se o usuário cancelar a operação (clicar no botão Cancelar ou Fechar) ou
	 * ocorrer algum erro ao tentar acessar o nome do arquivo no sistema de arquivos.
	 *         
	 * @see javax.swing.JFileChooser
	 */
	public static String dialogoAbrirArquivo(Component janela, String titulo) {
		definirPropriedades(titulo, "Abre um arquivo.", KeyEvent.VK_A);
	  
	  // Exibe a caixa de diálogo "Abrir Arquivo".
	  int opcao = fileChooser.showDialog(janela, "Abrir");
	  
	  // Verifica se o usuário cancelou a operação; se não, obtém o nome do arquivo digitado ou selecionado pelo usuário na caixa de  diálogo.
	  try { return (opcao == JFileChooser.APPROVE_OPTION) ? fileChooser.getSelectedFile().getCanonicalPath() : null;
	  } catch (Exception e) {
		  return null;
	  	}
	}

	/** 
	 * Exibe uma caixa de diálogo <code>JFileChooser</code> para o usuário navegar pelo sistema de arquivos e fornecer o nome do arquivo que gravado.
	 * 
	 * @param janela objeto <code>Component</code> que identifica a janela sobre a qual o <code>JFileChooser</code> será exibido. 
	 * Use <code>null</code> se não existir uma janela ou para centralizar o <code>JFileChooser</code> na tela. 
	 *   
	 * @param titulo <code>String</code> com o nome da barra de título da caixa de diálogo.
	 *        
	 * @return um <code>String</code> com o nome do arquivo a ser gravado.  Será retornado <code>null</code> se o usuário cancelar a operação (clicar no botão Cancelar ou Fechar) ou
	 * ocorrer algum erro ao tentar acessar o nome do arquivo no sistema de arquivos.
	 *       
	 *         
	 * @see javax.swing.JFileChooser
	 */
	public static String dialogoGravarArquivo(Component janela, String titulo) {
		definirPropriedades(titulo, "Gravar o texto no arquivo.", KeyEvent.VK_G);
		
	  // Exibe a caixa de diálogo "Gravar Arquivo".
	  int opcao = fileChooser.showDialog(janela, "Gravar");
	
	  // Verifica se o usuário cancelou a operação; se não, obtém o nome do arquivo digitado ou selecionado pelo usuário na caixa de diálogo.
	  try { return (opcao == JFileChooser.APPROVE_OPTION) ? fileChooser.getSelectedFile().getCanonicalPath() : null;
	  } catch (Exception e) {
		  return null;
	  	}
	} 

	/**
	 * Define as propriedades do JFileChooser, incluindo o título, o texto de ajuda (dica) do botão principal, o modo de seleção de arquivo, 
	 * o diretório atual e os filtros de extensão de arquivo.
	 */
	private static void definirPropriedades(String titulo, String dicaBotao, int letraMnemonica) {  
		final String DIRETORIO_ATUAL = ".", ARQUIVOS_TXT = "Arquivos de texto (*.txt)", EXTENSAO_TXT = "txt", 
				                                                     ARQUIVOS_JAVA = "Código-fonte Java (*.java)", EXTENSAO_JAVA = "java";

		// Define as propriedades somente uma vez. 
		if (fileChooser == null) {
			fileChooser = getFileChooser();

			// Indica que o usuário poderá selecionar apenas nomes de arquivos no diretório. 
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

			// Define qual é o diretório default.
			fileChooser.setCurrentDirectory(new File(DIRETORIO_ATUAL));

			// Cria os filtros de extensão de arquivo que serão usados pelo JFileChooser para filtrar os tipos de arquivos que serão exibidos na janela.
			FileNameExtensionFilter txtExtensionFilter = new FileNameExtensionFilter(ARQUIVOS_TXT, EXTENSAO_TXT);
			FileNameExtensionFilter javaExtensionFilter = new FileNameExtensionFilter(ARQUIVOS_JAVA, EXTENSAO_JAVA);
			
			// O último tipo de arquivo acrescentado ao JFileChooser é considerado o filtro (ou tipo) default quando se usa o método abaixo.
			fileChooser.setFileFilter(txtExtensionFilter);
			
			// Acrescenta um novo tipo de arquivo ao JFileChooser sem alterar o filtro default.
			fileChooser.addChoosableFileFilter(javaExtensionFilter);
		}
		// Define o título da caixa de diálogo do JFileChooser. 
		fileChooser.setDialogTitle(titulo);

		// Define um texto de ajuda para o botão principal da caixa de diálogo.
		fileChooser.setApproveButtonToolTipText(dicaBotao);
		
		/* Define uma letra mnemônica texto de ajuda para o botão principal da caixa de diálogo. 
		 * 
		 * ATENÇÃO: O método setApproveButtonMnemonic da classe JFileChooser não funciona com o look-and-feel Metal, ele é dependente do tipo de look-and-feel da GUI,
		 * 				   conforme está registrado na seção de comentários do bug  "JFileChooser.setApproveButtonMnemonic() doesn't work" em 
		 *					   https://bugs.java.com/bugdatabase/view_bug.do?bug_id=6297220.
		 */
		fileChooser.setApproveButtonMnemonic(letraMnemonica);
	}
} // class IgArquivo