package com.tf12.trabalho_teste_software.gestao_funcionarios.funcionario.dto.response;

import java.util.List;

import com.tf12.trabalho_teste_software.gestao_funcionarios.funcionario.entity.Funcionario;

public record FuncionariosResponse(List<FuncionarioResponse> funcionarios) {
	
	public static FuncionariosResponse fromEntity(List<Funcionario> funcionarios) {
		if (funcionarios == null){
			return null;
		}
		return new FuncionariosResponse(funcionarios.stream().map(FuncionarioResponse::fromEntity).toList());
	}
}
