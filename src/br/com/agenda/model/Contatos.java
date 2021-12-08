package br.com.agenda.model;

import java.util.Date;

public class Contatos {
	
	private int id;
	private String nome;
	private int idade;
	private Date dataCadastro;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	public String toString(){

		StringBuilder sb = new StringBuilder();
		sb.append("Contato: \n");
		sb.append("ID: " + id + "\n");
		sb.append("Nome: " + nome + "\n");
		sb.append("Idade: " + idade + "\n");
		sb.append("Data de cadastro: " + dataCadastro + "\n");
		
		return sb.toString();
				
	}
}
