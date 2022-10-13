package nasc.filmoteca.arquivo;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Arquivo {
	
	/**
	 * Abre o arquivo texto, lê e retorna o seu conteúdo.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static String abrir(String nomeArquivo) throws FileNotFoundException, IOException {
		try (TextFile textFile = new TextFile(nomeArquivo)) {
			return textFile.read();
		}
	} 
	
	/**
	 * Obtém a extensão do arquivo. Se o arquivo não possui extensão ou ocorrer algum erro retorna null.
	 */
	public static String obterExtensao(String nomeArquivo)  {
		try  {  return nomeArquivo.substring(nomeArquivo.lastIndexOf('.'));
		} catch (Exception e) {
				return null;
		}
	}
	
	
}
