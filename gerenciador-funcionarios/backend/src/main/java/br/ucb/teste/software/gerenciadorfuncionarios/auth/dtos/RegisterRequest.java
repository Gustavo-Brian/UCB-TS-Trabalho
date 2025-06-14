package br.ucb.teste.software.gerenciadorfuncionarios.auth.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// DTO para requisição de registro de usuário
public class RegisterRequest {
    @NotBlank(message = "O nome de usuário não pode estar em branco")
    private String username; // Nome de usuário
    @NotBlank(message = "A senha não pode estar em branco")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String password; // Senha

    // Construtor vazio (NoArgsConstructor)
    public RegisterRequest() {
    }

    // Construtor com todos os argumentos (AllArgsConstructor)
    public RegisterRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Método builder manual para simular o comportamento do Lombok @Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String username;
        private String password;

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public RegisterRequest build() {
            return new RegisterRequest(username, password);
        }
    }
}