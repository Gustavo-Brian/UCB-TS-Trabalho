package br.ucb.teste.software.gerenciadorfuncionarios.auth.controllers;

import br.ucb.teste.software.gerenciadorfuncionarios.auth.dtos.LoginRequest;
import br.ucb.teste.software.gerenciadorfuncionarios.auth.dtos.AuthResponse;
import br.ucb.teste.software.gerenciadorfuncionarios.auth.dtos.RegisterRequest;
import br.ucb.teste.software.gerenciadorfuncionarios.auth.exceptions.UsernameAlreadyExistsException;
import br.ucb.teste.software.gerenciadorfuncionarios.auth.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Controlador REST para operações de autenticação.
@RestController
@RequestMapping("/auth") // Define o caminho base para todos os endpoints neste controlador.
public class AuthController {

    private final AuthService authService;

    // Construtor com injeção de dependência do AuthService.
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Endpoint para registrar novos usuários.
    // POST /auth/register
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        try {
            // Chama o serviço para registrar o usuário e obter o token JWT.
            AuthResponse response = authService.register(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response); // Retorna 201 Created com o token.
        } catch (UsernameAlreadyExistsException e) {
            // Se o nome de usuário já existir, retorna 409 Conflict.
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (SecurityException e) {
            // Se a criação de administrador não for autorizada, retorna 403 Forbidden.
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (Exception e) {
            // Para outras exceções, retorna 500 Internal Server Error.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoint para autenticar usuários e obter um token JWT.
    // POST /auth/login
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        try {
            // Chama o serviço para autenticar o usuário e obter o token JWT.
            AuthResponse response = authService.login(request);
            return ResponseEntity.ok(response); // Retorna 200 OK com o token.
        } catch (Exception e) {
            // Em caso de falha na autenticação (ex: credenciais inválidas), retorna 401 Unauthorized.
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}