package br.ucb.teste.software.gerenciadorfuncionarios.auth.enums;

import org.springframework.security.core.GrantedAuthority;

// Enum que define os diferentes papéis (roles) dos usuários no sistema.
public enum Role implements GrantedAuthority {
    ROLE_ADMIN_ROOT, // Papel de administrador raiz, com permissões máximas.
    ROLE_ADMIN;      // Papel de administrador comum.

    @Override
    public String getAuthority() {
        return name(); // Retorna o nome da role como a autoridade concedida.
    }
}