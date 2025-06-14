package br.ucb.teste.software.gerenciadorfuncionarios.auth.repositories;

import br.ucb.teste.software.gerenciadorfuncionarios.auth.entities.User;
import br.ucb.teste.software.gerenciadorfuncionarios.auth.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Repositório para operações de persistência da entidade User.
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Busca um usuário pelo nome de usuário.
    Optional<User> findByUsername(String username);

    // Verifica se existe algum usuário com um papel específico.
    boolean existsByRole(Role role);
}