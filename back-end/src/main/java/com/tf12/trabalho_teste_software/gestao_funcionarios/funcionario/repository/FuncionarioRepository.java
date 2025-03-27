package com.tf12.trabalho_teste_software.gestao_funcionarios.funcionario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tf12.trabalho_teste_software.gestao_funcionarios.funcionario.entity.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
	List<Funcionario> findByAtivoTrue();
	List<Funcionario> findByAtivoFalse();
}
