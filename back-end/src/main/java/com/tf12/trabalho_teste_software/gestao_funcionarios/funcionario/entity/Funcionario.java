package com.tf12.trabalho_teste_software.gestao_funcionarios.funcionario.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name="Funcionario")
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="funcionario_nome", nullable = false, length = 100)
	private String nome;
	@Column(name="funcionario_cpf", nullable = false, length = 11, unique = true )
	private int cpf;
	@Column(name="funcionario_data_nascimento", nullable = false, length = 100)
	private LocalDate dataNascimento;
	@Column(name="funcionario_localidade", nullable = false, length = 100)
	private String localidade;
	@Column(name="funcionario_ativo", nullable = false, length = 100)
	private Boolean ativo;
	@Column(name="funcionario_data_admissao", nullable = false, length = 100)
	private LocalDate dataAdmissao;
	
	public Funcionario() {}
	
	public Funcionario(Long id, String nome, int cpf, LocalDate dataNascimento, String localidade, Boolean ativo,
			LocalDate dataAdmissao) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.localidade = localidade;
		this.ativo = ativo;
		this.dataAdmissao = dataAdmissao;
	}

	@PrePersist
	private void atribuirValoresIniciais(){
		this.dataAdmissao = LocalDate.now();
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

	public int getCpf() {
		return cpf;
	}

	public void setCpf(int cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public LocalDate getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(LocalDate dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}
}
