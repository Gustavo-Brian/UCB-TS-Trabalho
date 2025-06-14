package br.ucb.teste.software.gerenciadorfuncionarios.auth.entities;

import br.ucb.teste.software.gerenciadorfuncionarios.auth.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

// Entidade que representa um usuário no banco de dados
@Entity
@Table(name = "_user") // Nome da tabela no banco de dados
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID do usuário (chave primária)
    @Column(unique = true, nullable = false)
    private String username; // Nome de usuário (único, não nulo)
    @JsonIgnore // Ignora a senha na serialização JSON
    @Column(nullable = false)
    private String password; // Senha (não nula)
    @Enumerated(EnumType.STRING) // Mapeia o enum Role para uma String no banco
    @Column(nullable = false)
    private Role role; // Papel do usuário (administrador, etc.)

    // Construtor vazio (NoArgsConstructor)
    public User() {
    }

    // Construtor com todos os argumentos (AllArgsConstructor)
    public User(Long id, String username, String password, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // NOVO: Construtor para criação de novo usuário sem ID (gerado pelo banco)
    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters
    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    // Retorna as autoridades (permissões) do usuário
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    // Métodos da interface UserDetails
    @Override
    public boolean isAccountNonExpired() {
        return true; // Conta nunca expira
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Conta nunca é bloqueada
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Credenciais nunca expiram
    }

    @Override
    public boolean isEnabled() {
        return true; // Usuário sempre habilitado
    }

    // Método builder manual para simular o comportamento do Lombok @Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id; // Mantido para caso precise construir com ID
        private String username;
        private String password;
        private Role role;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder role(Role role) {
            this.role = role;
            return this;
        }

        public User build() {
            // Se o ID não for explicitamente definido no builder, use o construtor sem ID
            if (this.id == null) {
                return new User(username, password, role);
            }
            // Caso contrário, use o construtor com ID
            return new User(id, username, password, role);
        }
    }
}