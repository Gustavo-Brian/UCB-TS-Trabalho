package ts.t12.gestaofuncionario.funcionario.dto.response;

import ts.t12.gestaofuncionario.funcionario.entity.Funcionario;

import java.util.List;
import java.util.stream.Collectors;

public record ConsultarFuncionariosResponse(List<ConsultarFuncionarioResponse> funcionarios) {

    public static ConsultarFuncionariosResponse converter(List<Funcionario> funcionarios) {
        return new ConsultarFuncionariosResponse(funcionarios.stream().map(ConsultarFuncionarioResponse::converter).toList());
    }
}
