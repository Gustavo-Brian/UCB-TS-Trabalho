package ts.t12.gestaofuncionario.funcionario.controller;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ts.t12.gestaofuncionario.funcionario.dto.request.AtualizarFuncionarioRequest;
import ts.t12.gestaofuncionario.funcionario.dto.request.CadastrarFuncionarioRequest;
import ts.t12.gestaofuncionario.funcionario.dto.response.ConsultarFuncionarioResponse;
import ts.t12.gestaofuncionario.funcionario.dto.response.ConsultarFuncionariosResponse;
import ts.t12.gestaofuncionario.funcionario.service.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService service;

    public FuncionarioController(FuncionarioService service) {
        super();
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

    @PutMapping("{id}")
    public ResponseEntity<ConsultarFuncionarioResponse> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarFuncionarioRequest request) {
        return ResponseEntity.status(201).body(service.altualizar(id, request));
    }
}
