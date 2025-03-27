package com.tf12.trabalho_teste_software.gestao_funcionarios.cadastro.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tf12.trabalho_teste_software.gestao_funcionarios.cadastro.dto.request.CadastroRequest;
import com.tf12.trabalho_teste_software.gestao_funcionarios.cadastro.dto.response.CadastroResponse;
import com.tf12.trabalho_teste_software.gestao_funcionarios.cadastro.service.CadastroService;

@RestController
@RequestMapping("/cadastro")
public class CadastroController {
	
	private final CadastroService service;
	
	public CadastroController(CadastroService service) {
		super();
		this.service = service;
	}

	@PostMapping("/criarCadastro")
	public ResponseEntity<Boolean> criarCadastro(@RequestBody CadastroRequest dto) {
		return ResponseEntity.status(201).body(service.criarCadastro(dto)) ;
	}
	
	@GetMapping("/retornarCadastro/{id}")
	public ResponseEntity<CadastroResponse> retornarCadastro(@PathVariable Long id) {
		CadastroResponse dto = service.retornarCadastro(id);
		
		return (dto == null) ? ResponseEntity.notFound().build() : ResponseEntity.status(200).body(dto);
	}

	@GetMapping("/retornarCadastros")
	public ResponseEntity<List<CadastroResponse>> retornarCadastros() {
		return ResponseEntity.status(200).body(service.retornarCadastros());
	}
	
	@PutMapping("/atualizarCadastro")
	public ResponseEntity<Boolean> atualizarCadastro(@RequestBody CadastroRequest dto) {
		return ResponseEntity.status(200).body(service.atualizarCadastro(dto));
	}
	
	@DeleteMapping("/deletarCadastro/{id}")
	public ResponseEntity<Boolean> deletarCadastro(@PathVariable Long id) {
		return ResponseEntity.status(200).body(service.deletarCadastro(id));
	}
	
	@DeleteMapping("/deletarCadastros")
	public ResponseEntity<Boolean> deletarCadastro() {
		return ResponseEntity.status(200).body(service.deletarCadastros());
	}
}