package br.ucb.teste.software.gerenciadorfuncionarios.auth.configs;

import br.ucb.teste.software.gerenciadorfuncionarios.auth.exceptions.UsernameAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

// Classe para lidar globalmente com exceções na aplicação.
@ControllerAdvice
public class GlobalExceptionHandler {

    // Lida com exceções de validação de argumentos de método (ex: @Valid).
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        // Retorna 400 Bad Request com os detalhes dos erros de validação.
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    // Lida com a exceção personalizada UsernameAlreadyExistsException.
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<String> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException ex) {
        // Retorna 409 Conflict com a mensagem de erro.
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    // Lida com SecurityException (usada para criação de admin não autorizada).
    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<String> handleSecurityException(SecurityException ex) {
        // Retorna 403 Forbidden com a mensagem de erro.
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }

    // Lida com outras exceções genéricas.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        // Retorna 500 Internal Server Error com uma mensagem genérica de erro.
        // Em um ambiente de produção, é importante logar o erro completo e não expor detalhes internos.
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro interno no servidor.");
    }
}