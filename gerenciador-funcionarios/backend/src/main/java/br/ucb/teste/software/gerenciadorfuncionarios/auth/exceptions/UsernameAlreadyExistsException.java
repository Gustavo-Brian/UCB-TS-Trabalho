package br.ucb.teste.software.gerenciadorfuncionarios.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Exceção personalizada para quando um nome de usuário já existe no sistema.
@ResponseStatus(HttpStatus.CONFLICT) // Mapeia esta exceção para o status HTTP 409 Conflict.
public class UsernameAlreadyExistsException extends RuntimeException {

    // Construtor que aceita uma mensagem de erro.
    public UsernameAlreadyExistsException(String message) {
        super(message); // Chama o construtor da classe pai (RuntimeException).
    }
}