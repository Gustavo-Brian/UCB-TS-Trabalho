package com.tf12.trabalho_teste_software.gestao_funcionarios.funcionario.dto.response;

import java.time.LocalDate;

import com.tf12.trabalho_teste_software.gestao_funcionarios.funcionario.entity.Funcionario;

public record FuncionarioResponse(Long id,
	String nome,
	int cpf,
	LocalDate dataNascimento,
	String localidade,
	Boolean ativo,
	LocalDate dataAdmissao) {
		
	public static FuncionarioResponse converter(Funcionario funcionario){
		return (funcionario == null) ? null :
			new FuncionarioResponse(funcionario.getId(),
			 funcionario.getNome(),
			 funcionario.getCpf(), 
			 funcionario.getDataNascimento(), 
			 funcionario.getLocalidade(), 
			 funcionario.getAtivo(), 
			 funcionario.getDataAdmissao());
	}
}
