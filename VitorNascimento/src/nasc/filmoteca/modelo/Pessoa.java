package nasc.filmoteca.modelo;

public class Pessoa {

public final String DIRETOR="Diretor",ARTISTA="Artista",AUTOR="Autor"; 
	
private String nome;
private String profissao;

public Pessoa() {
}

public Pessoa(String nome, String profissao) {
	this.nome = nome;
	this.profissao = profissao;
}

public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public String getProfissao() {
	return profissao;
}

public void setProfissao(String profissao) {
	this.profissao = profissao;
}




}