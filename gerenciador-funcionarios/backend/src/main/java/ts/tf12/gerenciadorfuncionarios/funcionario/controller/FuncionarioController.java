package ts.tf12.gerenciadorfuncionarios.funcionario.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ts.tf12.gerenciadorfuncionarios.funcionario.dto.request.AtualizarFuncionarioRequest;
import ts.tf12.gerenciadorfuncionarios.funcionario.dto.request.CadastrarFuncionarioRequest;
import ts.tf12.gerenciadorfuncionarios.funcionario.dto.response.ConsultarFuncionarioResponse;
import ts.tf12.gerenciadorfuncionarios.funcionario.dto.response.ConsultarFuncionariosResponse;
import ts.tf12.gerenciadorfuncionarios.funcionario.service.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService service;

    public FuncionarioController(FuncionarioService service) {
        this.service = service;
    }

    @GetMapping("/{email}")
    public ResponseEntity<ConsultarFuncionarioResponse> consultarPorEmail(@PathVariable String email) {
        ConsultarFuncionarioResponse response = service.consultarPorEmail(email);
        return (response == null) ? ResponseEntity.notFound().build() : ResponseEntity.status(200).body(response);
    }

    @GetMapping
    public ResponseEntity<ConsultarFuncionariosResponse> consultarTodos() {
        ConsultarFuncionariosResponse response = service.consultarTodos();
        return (response == null) ? ResponseEntity.noContent().build() : ResponseEntity.status(200).body(response);
    }

    @PostMapping
    public ResponseEntity<ConsultarFuncionarioResponse> cadastrar(@RequestBody @Valid CadastrarFuncionarioRequest request) {
        return ResponseEntity.status(201).body(service.cadastrar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultarFuncionarioResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid AtualizarFuncionarioRequest request) {

        ConsultarFuncionarioResponse response = service.atualizar(id, request);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (service.deletar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}