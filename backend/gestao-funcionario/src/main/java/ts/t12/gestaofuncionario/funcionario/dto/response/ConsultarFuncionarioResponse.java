package ts.t12.gestaofuncionario.funcionario.dto.response;

import ts.t12.gestaofuncionario.funcionario.entity.Funcionario;

import java.time.LocalDate;

public record ConsultarFuncionarioResponse(
        Long id,
        String nome,
        String cpf,
        String email,
        String localidade,
        boolean ativo,
        LocalDate dataNascimento,
        LocalDate dataAdmissao
) {

    public static ConsultarFuncionarioResponse converter(Funcionario funcionario) {
        if(funcionario == null){
            return null;
        }

        return new ConsultarFuncionarioResponse(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getCpf(),
                funcionario.getEmail(),
                funcionario.getLocalidade(),
                funcionario.isAtivo(),
                funcionario.getDataNascimento(),
                funcionario.getDataAdmissao()
        );
    }

}
