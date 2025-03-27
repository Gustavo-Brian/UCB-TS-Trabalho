package com.tf12.trabalho_teste_software.gestao_funcionarios.cadastro.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name="Cadastro")
public class Cadastro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "cadastro_nome", nullable = false, length = 100, unique = true)
	private String nome;
	@Column(name = "cadastro_email", nullable = false, length = 100, unique = true)
	private String email;
	@Column(name = "cadastro_senha", nullable = false, length = 100)
	private String senha;
	@Column(name = "cadastro_data_cadastrado")
	private LocalDate dataCadastrado;
	
	public Cadastro() {
		super();
	}

	public Cadastro(Long id, String nome, String email, String senha, LocalDate dataCadastrado) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.dataCadastrado = dataCadastrado;
	}

	@PrePersist
	private void atribuirDataCadastrado() {
		this.dataCadastrado = LocalDate.now();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LocalDate getDataCadastrado() {
		return dataCadastrado;
	}

	public void setDataCadastrado(LocalDate dataCadastrado) {
		this.dataCadastrado = dataCadastrado;
	}
}
