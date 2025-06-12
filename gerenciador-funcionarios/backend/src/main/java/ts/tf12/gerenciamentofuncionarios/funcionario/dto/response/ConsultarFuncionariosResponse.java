package ts.tf12.gerenciamentofuncionarios.funcionario.dto.response;

import ts.tf12.gerenciamentofuncionarios.funcionario.entity.Funcionario;

import java.util.List;

public record ConsultarFuncionariosResponse(List<ConsultarFuncionarioResponse> funcionarios) {

    public static ConsultarFuncionariosResponse converter(List<Funcionario> funcionarios) {
        return new ConsultarFuncionariosResponse(funcionarios.stream().map(ConsultarFuncionarioResponse::converter).toList());
    }
}
