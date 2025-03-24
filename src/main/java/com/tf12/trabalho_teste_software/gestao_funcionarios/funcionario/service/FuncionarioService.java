package com.tf12.trabalho_teste_software.gestao_funcionarios.funcionario.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tf12.trabalho_teste_software.gestao_funcionarios.funcionario.dto.request.FuncionarioRequest;
import com.tf12.trabalho_teste_software.gestao_funcionarios.funcionario.dto.response.FuncionarioResponse;
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
		if (dto == null || dto.id() != null) {
			return false;
		}

		repository.save(FuncionarioRequest.converter(dto));

		return true;
	}

	public boolean atualizarCadastro(FuncionarioRequest dto) {
		if (dto == null || dto.dataAdmissao() != null || dto.id() == null) {
			return false;
		}

		return repository.findById(dto.id()).map(cadastro -> {
			boolean atualizado = false;

			if (dto.nome() != null && !dto.nome().isBlank()) {
				cadastro.setNome(dto.nome());

				atualizado = true;
			}

			if (dto.cpf() != 0 /* colocar lofica pra validar no banco, jaja eu testo isso */) {
				cadastro.setCpf(dto.cpf());

				atualizado = true;
			}

			if (dto.localidade() != null && !dto.localidade().isBlank()) {
				cadastro.setLocalidade(dto.localidade());

				atualizado = true;
			}

			if (!atualizado) {
				return false;
			}

			repository.save(cadastro);

			return true;
		}).orElse(false);
	}

	public void ativar(Long id) {
		// fazer teste para ver se o cadastro ja esta ativo antes de dar update
		repository.getReferenceById(id).setAtivo(true);
	}

	public void desativar(Long id) {
		// fazer teste para ver se o cadastro ja esta inaativo antes de dar update
		repository.getReferenceById(id).setAtivo(false);
	}

	public boolean deletarFuncionario(Long id) {
		if (id == null || id <= 0) {
			return false;
		}

		repository.deleteById(id);

		return true;
	}

	// criar deletartodos

	public List<FuncionarioResponse> retornarFuncionariosAtivos() {
		List<Funcionario> ativos = repository.findByAtivoTrue();
		return ativos.stream().map(FuncionarioResponse::converter).toList();
	}

	public List<FuncionarioResponse> retornarFuncionariosInativos() {
		List<Funcionario> inativos = repository.findByAtivoFalse();
		return inativos.stream().map(FuncionarioResponse::converter).toList();
	}

	public List<FuncionarioResponse> retornarFuncionarios() {
		List<Funcionario> funcionarios = repository.findAll();

		return funcionarios.stream().map(FuncionarioResponse::converter).toList();
	}

	public FuncionarioResponse retornarFuncionario(Long id) {
		if (id == null || !repository.existsById(id)) {
			return null;
		}

		return repository.findById(id).map(FuncionarioResponse::converter).orElse(null);
	}
}
