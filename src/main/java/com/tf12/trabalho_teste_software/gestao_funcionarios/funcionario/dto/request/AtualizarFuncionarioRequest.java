package com.tf12.trabalho_teste_software.gestao_funcionarios.funcionario.dto.request;

import java.time.LocalDate;

public record AtualizarFuncionarioRequest(Long id,
	String nome,
	int cpf,
	LocalDate dataNascimento,// em caso da pessoa digitar errado a data
	String localidade) {

}
