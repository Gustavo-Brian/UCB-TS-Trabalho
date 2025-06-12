package ts.tf12.gerenciamentofuncionarios.funcionario.dto.request;

import jakarta.validation.constraints.Email;
import ts.tf12.gerenciamentofuncionarios.funcionario.entity.Funcionario;

import java.time.LocalDate;

public record AtualizarFuncionarioRequest(
        String nome,
        String cpf,
        @Email
        String email,
        String localidade,
        Boolean ativo,
        LocalDate dataNascimento,
        LocalDate dataAdmissao
) {
    public static Funcionario converter(AtualizarFuncionarioRequest request) {
        if (request == null) {
            return null;
        }
        return new Funcionario(
                null,
                request.nome(),
                request.cpf(),
                request.email(),
                request.localidade(),
                request.ativo(),
                request.dataNascimento(),
                request.dataAdmissao()
        );
    }
}
