package br.ucb.teste.software.gerenciadorfuncionarios.funcionario.controller;

import br.ucb.teste.software.gerenciadorfuncionarios.funcionario.dto.FuncionarioRequestDTO;
import br.ucb.teste.software.gerenciadorfuncionarios.funcionario.dto.FuncionarioResponseDTO;
import br.ucb.teste.software.gerenciadorfuncionarios.funcionario.service.FuncionarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    public ResponseEntity<FuncionarioResponseDTO> criarFuncionario(@RequestBody FuncionarioRequestDTO requestDTO) {
        FuncionarioResponseDTO newFuncionario = funcionarioService.criarFuncionario(requestDTO);
        return new ResponseEntity<>(newFuncionario, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioResponseDTO>> listarTodosFuncionarios() {
        List<FuncionarioResponseDTO> funcionarios = funcionarioService.listarTodosFuncionarios();
        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> buscarFuncionarioPorId(@PathVariable Long id) {
        return funcionarioService.buscarFuncionarioPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> atualizarFuncionario(@PathVariable Long id, @RequestBody FuncionarioRequestDTO requestDTO) {
        try {
            FuncionarioResponseDTO updatedFuncionario = funcionarioService.atualizarFuncionario(id, requestDTO);
            return ResponseEntity.ok(updatedFuncionario);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFuncionario(@PathVariable Long id) {
        funcionarioService.deletarFuncionario(id);
        return ResponseEntity.noContent().build();
    }
}