package br.ucb.teste.software.gerenciadorfuncionarios.auth.dtos;

// DTO para a resposta de autenticação (contém o token JWT)
public class AuthResponse {
    private String token; // Token JWT

    // Construtor vazio (NoArgsConstructor)
    public AuthResponse() {
    }

    // Construtor com todos os argumentos (AllArgsConstructor)
    public AuthResponse(String token) {
        this.token = token;
    }

    // Getter para token
    public String getToken() {
        return token;
    }

    // Setter para token
    public void setToken(String token) {
        this.token = token;
    }

    // Método builder manual para simular o comportamento do Lombok @Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String token;

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public AuthResponse build() {
            return new AuthResponse(token);
        }
    }
}