package com.tf12.trabalho_teste_software.gestao_funcionarios.cadastro.dto.response;

import java.time.LocalDate;

import com.tf12.trabalho_teste_software.gestao_funcionarios.cadastro.entity.Cadastro;

public record CadastroResponse(Long id,String nome, String email, LocalDate dataCadastrado) {
	
	public static CadastroResponse converter(Cadastro cadastro) {
		return (cadastro == null) ? null :
			new CadastroResponse(cadastro.getId(), 
					cadastro.getNome(), 
					cadastro.getEmail(), 
					cadastro.getDataCadastrado());
	}
}
