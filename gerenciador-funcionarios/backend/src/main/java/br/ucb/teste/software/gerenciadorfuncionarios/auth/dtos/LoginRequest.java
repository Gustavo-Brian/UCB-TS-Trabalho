package br.ucb.teste.software.gerenciadorfuncionarios.auth.dtos;

import jakarta.validation.constraints.NotBlank;

// DTO (Data Transfer Object) para requisições de login.
public class LoginRequest {
    @NotBlank(message = "O nome de usuário não pode estar em branco")
    private String username; // Nome de usuário
    @NotBlank(message = "A senha não pode estar em branco")
    private String password; // Senha

    // Construtor vazio (NoArgsConstructor)
    public LoginRequest() {
    }

    // Construtor com todos os argumentos (AllArgsConstructor)
    public LoginRequest(String username, String password) {
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

        public LoginRequest build() {
            return new LoginRequest(username, password);
        }
    }
}