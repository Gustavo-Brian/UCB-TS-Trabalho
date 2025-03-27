package com.tf12.trabalho_teste_software.gestao_funcionarios.funcionario.dto.request;

import java.time.LocalDate;

import com.tf12.trabalho_teste_software.gestao_funcionarios.funcionario.entity.Funcionario;

public record FuncionarioRequest(Long id,
	String nome,
	int cpf,
	LocalDate dataNascimento,
	String localidade,
	boolean ativo,
	LocalDate dataAdmissao ) {
			
	public static Funcionario converter(FuncionarioRequest dto) {
		return (dto == null) ? null : 
		
		new Funcionario(dto.id(),dto.nome(), dto.cpf(), dto.dataNascimento(), dto.localidade(), dto.ativo(), dto.dataAdmissao());
	}

}
