package com.tf12.trabalho_teste_software.gestao_funcionarios.cadastro.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tf12.trabalho_teste_software.gestao_funcionarios.cadastro.dto.request.CadastroRequest;
import com.tf12.trabalho_teste_software.gestao_funcionarios.cadastro.dto.response.CadastroResponse;
import com.tf12.trabalho_teste_software.gestao_funcionarios.cadastro.entity.Cadastro;
import com.tf12.trabalho_teste_software.gestao_funcionarios.cadastro.repository.CadastroRepository;

@Service
@Transactional
public class CadastroService {
	
	private final CadastroRepository repository;
	
	public CadastroService(CadastroRepository repository) {
		super();
		this.repository = repository;
	}

	public boolean criarCadastro(CadastroRequest dto) {
		if(dto == null || dto.id() != null || dto.dataCadastrado() != null) {
			return false;
		}
		
		repository.save(CadastroRequest.converter(dto));
		
		return true;
	}
	
	public boolean deletarCadastro(Long id) {
		if(id == null  || id <= 0) {
			return false;
		}
		
		repository.deleteById(id);
		
		return true;
	}
	
	public boolean deletarCadastros() {
		Long count = repository.count();
		
		if(count != null && count > 0) {
			repository.deleteAllInBatch();
			
			return true;
		}
		
		return false;
	}
	
	public CadastroResponse retornarCadastro(Long id) {
		if(id == null || !repository.existsById(id)) {
			return null;
		}
		
		return repository.findById(id).map(CadastroResponse::converter).orElse(null);
	}
	
	public List<CadastroResponse> retornarCadastros() {
		List<Cadastro> cadastros = repository.findAll();
		
		return cadastros.stream().map(CadastroResponse::converter).toList();
	}
	
	public boolean atualizarCadastro(CadastroRequest dto) {
		if(dto == null || dto.dataCadastrado() != null || dto.id() == null) {
			return false;
		}
		
		return repository.findById(dto.id()).map(cadastro -> {
			boolean atualizado = false;
			
			if(dto.nome() != null && !dto.nome().isBlank()) {
				cadastro.setNome(dto.nome());
				
				atualizado = true;
			}
			
			if( dto.email() != null && !dto.email().isBlank()) {
				cadastro.setEmail(dto.email());	
				
				atualizado = true;
			}
			
			if(dto.senha() != null && !dto.senha().isBlank()) {
				cadastro.setSenha(dto.senha());			
				
				atualizado = true;
			}
			
			if(!atualizado) {
				return false;
			}
			
			repository.save(cadastro);
			
			return true;
		}).orElse(false);	
	}
	
	public boolean autenticarCadastro(CadastroRequest dto) {
		if(dto == null ||dto.id() == null) {
			return false;
		}
		
		return repository.findByEmail(dto.email()).map(cadastro -> {
			if(cadastro.getSenha().equals(dto.senha())) {
				
			}
		})
	}
}