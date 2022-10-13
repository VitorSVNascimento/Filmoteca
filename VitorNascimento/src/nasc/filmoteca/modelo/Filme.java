package nasc.filmoteca.modelo;

import java.util.ArrayList;
import java.util.List;

public class Filme {
private boolean blueray;
private boolean dvd;
private	String classificacao;
private	float avaliacaoIMDB;
private	int avaliacaoUsuario;
private	int ano;


private	String titulo,data,duracao,sinopse,imagem;
private	List<String> generos;
private	List<Pessoa> pessoas;
	
public Filme() {
	generos = new ArrayList<String>();
	pessoas = new ArrayList<Pessoa>();
	avaliacaoUsuario=0;
	dvd =false;
	blueray = false;
}

public String getClassificacao() {
	return classificacao;
}

public void setClassificacao(String classificacao) {
	this.classificacao = classificacao;
}

public float getAvaliacaoIMDB() {
	return avaliacaoIMDB;
}

public void setAvaliacaoIMDB(float avaliacaoIMDB) {
	this.avaliacaoIMDB = avaliacaoIMDB;
}

public int getAvaliacaoUsuario() {
	return avaliacaoUsuario;
}

public void setAvaliacaoUsuario(int avaliacaoUsuario) {
	this.avaliacaoUsuario = avaliacaoUsuario;
}

public String getTitulo() {
	return titulo;
}

public void setTitulo(String titulo) {
	this.titulo = titulo;
}

public String getData() {
	return data;
}

public void setData(String data) {
	this.data = data;
}

public String getDuracao() {
	return duracao;
}

public void setDuracao(String duracao) {
	this.duracao = duracao;
}

public String getSinopse() {
	return sinopse;
}

public void setSinopse(String sinopse) {
	this.sinopse = sinopse;
}

public String getImagem() {
	return imagem;
}

public void setImagem(String imagem) {
	this.imagem = imagem;
}

public List<String> getGeneros() {
	return generos;
}

public void setGeneros(List<String> generos) {
	this.generos = generos;
}

public List<Pessoa> getPessoas() {
	return pessoas;
}

public void setPessoas(List<Pessoa> pessoas) {
	this.pessoas = pessoas;
}


public int getAno() {
	return ano;
}

public void setAno(int ano) {
	this.ano = ano;
}

public boolean isBlueray() {
	return blueray;
}

public void setBlueray(boolean blueray) {
	this.blueray = blueray;
}

public boolean isDvd() {
	return dvd;
}

public void setDvd(boolean dvd) {
	this.dvd = dvd;
}

}