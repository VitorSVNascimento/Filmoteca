package nasc.filmoteca.modelo;

import java.util.ArrayList;
import java.util.List;

public class FilmeList {
	
	private List<Filme> filmes;
	
	public FilmeList() {
		filmes = new ArrayList<Filme>();
	}

	public List<Filme> getFilmes() {
		return filmes;
	}

	public void setFilmes(List<Filme> filmes) {
		this.filmes = filmes; 
	}
	
	public List<Filme> pesquisaFilmes(String pesquisa){
		
	List<Filme> achados = new ArrayList<>();			
		for(Filme f : filmes) {
			int achou = 0;
		//pesquisa pelo titulo do filme	
		if(f.getTitulo().toUpperCase().contains(pesquisa.toUpperCase())) {
			
			achados.add(f);
			achou=1;
		}
		
		
		//pesquisa pelas pessoas
		if(achou==0) {
			for(Pessoa p : f.getPessoas()) {
				
				if(p.getNome().toUpperCase().contains(pesquisa.toUpperCase())) {
					
					achados.add(f);
					achou=1;
					break;
				}
			}
		}

			
		}		
	return achados;	
	}
	

}