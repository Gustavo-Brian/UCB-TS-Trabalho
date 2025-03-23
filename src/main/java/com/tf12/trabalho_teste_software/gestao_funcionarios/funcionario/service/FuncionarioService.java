package com.tf12.trabalho_teste_software.gestao_funcionarios.funcionario.service;




import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.tf12.trabalho_teste_software.gestao_funcionarios.funcionario.dto.request.FuncionarioRequest;
import com.tf12.trabalho_teste_software.gestao_funcionarios.funcionario.dto.response.FuncionarioResponse;
import com.tf12.trabalho_teste_software.gestao_funcionarios.funcionario.dto.response.FuncionariosAtivosResponse;
import com.tf12.trabalho_teste_software.gestao_funcionarios.funcionario.dto.response.FuncionariosInativosResponse;
import com.tf12.trabalho_teste_software.gestao_funcionarios.funcionario.dto.response.FuncionariosResponse;
import com.tf12.trabalho_teste_software.gestao_funcionarios.funcionario.entity.Funcionario;
import com.tf12.trabalho_teste_software.gestao_funcionarios.funcionario.repository.FuncionarioRepository;


@Service
@Transactional
public class FuncionarioService {
	
	private final FuncionarioRepository repository;	
	
	public FuncionarioService(FuncionarioRepository repository) {
		super();
		this.repository = repository;
	}

	public boolean criarFuncionario(FuncionarioRequest dto) {
		if (dto == null || dto.id() != null ){
			return false;
		}
		
		repository.save(FuncionarioRequest.converter(dto));
		
		return true;
	}
	
	public void atualizar(Long id) {
		
	}
	
	public void ativar(Long id) {
		repository.getReferenceById(id).setAtivo(true);
	}
	
	public void desativar(Long id) {
		
		repository.getReferenceById(id).setAtivo(false);
	}
	
	public boolean deletarFuncionario(Long id) {
		if(id == null || id <= 0){
			return false;
		}
		
		repository.deleteById(id);
		
		return true;
	}	
	
	//criar deletartodos 
	
	public FuncionariosAtivosResponse retornarAtivos() {
		List<Funcionario> ativos = repository.findByAtivoTrue();
		return FuncionariosAtivosResponse.fromEntity(ativos);
	}
	
	public FuncionariosInativosResponse retornarInativos() {
		List<Funcionario> inativos = repository.findByAtivoFalse();
		return FuncionariosInativosResponse.fromEntity(inativos);
	}
	
	public FuncionariosResponse retornarLista() {
		return FuncionariosResponse.fromEntity(repository.findAll());	
	}
	
	public FuncionarioResponse retornarFuncionario(Long id) {
		if(id == null || !repository.existsById(id)){
			return null;
		}
		
		return repository.findById(id).map(FuncionarioResponse::converter).orElse(null);
	}
}
