package com.tf12.trabalho_teste_software.gestao_funcionarios.cadastro.dto.request;

import java.time.LocalDate;

import com.tf12.trabalho_teste_software.gestao_funcionarios.cadastro.entity.Cadastro;

public record CadastroRequest(Long id, String nome, String email, String senha, LocalDate dataCadastrado) {
	
	public static Cadastro converter(CadastroRequest dto) {
		return (dto == null) ? null :
			new Cadastro(dto.id(),
					dto.nome(),
					dto.email(),
					dto.senha(),
					dto.dataCadastrado());
	}
}
