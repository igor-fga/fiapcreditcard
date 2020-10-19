package br.com.fiap.fiapcreditcard.dto;

import br.com.fiap.fiapcreditcard.model.Aluno;

public class AlunoDTO {

	private Long id;
	private String nome;
	private String numeroCartao;
	private Boolean ativo;

	public AlunoDTO(){}

	public AlunoDTO(Aluno aluno) {
		this.id = aluno.getId();
		this.nome = aluno.getNome();
		this.numeroCartao = aluno.getNumeroCartao();
		this.ativo = aluno.getAtivo();
	}

	public String getNome() {
		return nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}
