package com.tf12.trabalho_teste_software.gestao_funcionarios.cadastro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tf12.trabalho_teste_software.gestao_funcionarios.cadastro.entity.Cadastro;

public interface CadastroRepository extends JpaRepository<Cadastro, Long>{
	
	public Optional<Cadastro> findByEmail(String email);
}
