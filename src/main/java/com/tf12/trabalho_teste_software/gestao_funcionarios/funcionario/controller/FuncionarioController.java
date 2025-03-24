package com.tf12.trabalho_teste_software.gestao_funcionarios.funcionario.controller;

import java.util.List;

//teste
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.tf12.trabalho_teste_software.gestao_funcionarios.funcionario.dto.request.FuncionarioRequest;

import com.tf12.trabalho_teste_software.gestao_funcionarios.funcionario.dto.response.FuncionarioResponse;

import com.tf12.trabalho_teste_software.gestao_funcionarios.funcionario.service.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

	private final FuncionarioService service;

	public FuncionarioController(FuncionarioService service) {
		super();
		this.service = service;
	}

	@PostMapping("/criarFuncionario")
	public ResponseEntity<Boolean> criarFuncionario(@RequestBody FuncionarioRequest dto) {
		return ResponseEntity.status(201).body(service.criarFuncionario(dto));
	}

	@GetMapping("/retornarFuncionario/{id}")
	public ResponseEntity<FuncionarioResponse> retornarFuncionario(@PathVariable Long id) {
		FuncionarioResponse dto = service.retornarFuncionario(id);

		return (dto == null) ? ResponseEntity.notFound().build() : ResponseEntity.status(200).body(dto);
	}

	@GetMapping("/retornarFuncionarios")
	public ResponseEntity<List<FuncionarioResponse>> retornarFuncionarios() {
		return ResponseEntity.status(200).body(service.retornarFuncionarios());
	}

	@GetMapping("/retornarFuncionariosAtivos")
	public ResponseEntity<List<FuncionarioResponse>> RetornarFuncionariosAtivos() {
		return ResponseEntity.status(200).body(service.retornarFuncionariosAtivos());
	}

	@GetMapping("/retornarFuncionariosInativos")
	public ResponseEntity<List<FuncionarioResponse>> RetornarFuncionariosInativos() {
		return ResponseEntity.status(200).body(service.retornarFuncionariosInativos());
	}

	@PutMapping("/desativarFuncionario/{id}")
	public ResponseEntity<String> desativar(@PathVariable Long id) {
		service.desativar(id);
		return ResponseEntity.status(200).body("Funcionario Desativado com sucesso");
	}

	@PutMapping("/ativarFuncionario/{id}")
	public ResponseEntity<String> ativar(@PathVariable Long id) {
		service.ativar(id);
		return ResponseEntity.status(200).body("Funcionario ativado com sucesso");
	}

	@DeleteMapping("/deletarFuncionario/{id}")
	public ResponseEntity<Boolean> deletar(@PathVariable Long id) {
		return ResponseEntity.status(200).body(service.deletarFuncionario(id));
	}

}
