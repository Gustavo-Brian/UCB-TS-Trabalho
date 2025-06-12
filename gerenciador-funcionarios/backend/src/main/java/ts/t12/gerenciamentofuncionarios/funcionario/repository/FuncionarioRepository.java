package ts.t12.gerenciamentofuncionarios.funcionario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ts.t12.gerenciamentofuncionarios.funcionario.entity.Funcionario;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findByEmail(String email);
}
