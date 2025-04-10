package ts.t12.gestaofuncionario.funcionario.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ts.t12.gestaofuncionario.funcionario.entity.Funcionario;

import java.time.LocalDate;

public record CadastrarFuncionarioRequest(
        @NotBlank String nome,
        @NotBlank String cpf,
        @NotBlank String email,
        @NotBlank String localidade,
        @NotNull LocalDate dataNascimento
) {
    public static Funcionario converter(CadastrarFuncionarioRequest request) {
        if (request == null) {
            return null;
        }
        return new Funcionario(
                null,
                request.nome(),
                request.cpf(),
                request.email(),
                request.localidade(),
                null,
                request.dataNascimento(),
                null
        );
    }
}
