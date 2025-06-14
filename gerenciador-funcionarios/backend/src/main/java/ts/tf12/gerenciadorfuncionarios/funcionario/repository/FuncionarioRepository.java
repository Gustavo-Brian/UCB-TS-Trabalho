package ts.tf12.gerenciadorfuncionarios.funcionario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ts.tf12.gerenciadorfuncionarios.funcionario.entity.Funcionario;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findByEmail(String email);
}
