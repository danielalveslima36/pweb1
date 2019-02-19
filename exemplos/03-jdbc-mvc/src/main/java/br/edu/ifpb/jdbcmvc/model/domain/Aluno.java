package br.edu.ifpb.jdbcmvc.model.domain;

public class Aluno {
	
	private String matricula;
	private String nome;
	private Integer idade;
	
	public Aluno() {}
	
	public Aluno(String matricula, String nome, Integer idade) {
		this.matricula = matricula;
		this.nome = nome;
		this.idade = idade;
	}
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	
}
