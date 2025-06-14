package br.ucb.teste.software.gerenciadorfuncionarios.funcionario.repository;

import br.ucb.teste.software.gerenciadorfuncionarios.funcionario.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}