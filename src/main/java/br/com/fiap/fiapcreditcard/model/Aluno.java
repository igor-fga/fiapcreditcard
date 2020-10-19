package br.com.fiap.fiapcreditcard.model;

import br.com.fiap.fiapcreditcard.dto.AlunoCreateUpdateDTO;

import javax.persistence.*;

@Entity
@Table(name = "TB_ALUNO")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private String numeroCartao;

    @Column
    private Boolean ativo;

    public Aluno() {}

    public Aluno(AlunoCreateUpdateDTO alunoCreateUpdateDTO) {
        this.id = 0L;
        this.nome = alunoCreateUpdateDTO.getNome();
        this.numeroCartao = alunoCreateUpdateDTO.getNumeroCartao();
        this.ativo = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
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
