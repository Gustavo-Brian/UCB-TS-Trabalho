package com.tf12.trabalho_teste_software.gestao_funcionarios.funcionario.dto.request;

public record DesativarFuncionarioRequest(Long id,
	Boolean ativo) {
	//codigo pra validar se o usuario ja esta desativado
}
