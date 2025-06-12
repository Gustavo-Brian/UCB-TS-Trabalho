package ts.t12.gestaofuncionario.funcionario.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ts.t12.gestaofuncionario.funcionario.dto.request.AtualizarFuncionarioRequest;
import ts.t12.gestaofuncionario.funcionario.dto.request.CadastrarFuncionarioRequest;
import ts.t12.gestaofuncionario.funcionario.dto.response.ConsultarFuncionarioResponse;
import ts.t12.gestaofuncionario.funcionario.dto.response.ConsultarFuncionariosResponse;
import ts.t12.gestaofuncionario.funcionario.entity.Funcionario;
import ts.t12.gestaofuncionario.funcionario.repository.FuncionarioRepository;

import java.util.List;

@Service
@Transactional
public class FuncionarioService {

    public final FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository){
        super();
        this.repository = repository;
    }

    public ConsultarFuncionarioResponse consultarPorEmail(String email){
        if(email == null || email.isEmpty()){
            return null;
        }

        return repository.findByEmail(email).map(ConsultarFuncionarioResponse::converter).orElse(null);
    }

    public ConsultarFuncionariosResponse consultarTodos(){
        List<Funcionario> funcionarios = repository.findAll();
        return ConsultarFuncionariosResponse.converter(funcionarios);
    }

    public ConsultarFuncionarioResponse cadastrar(CadastrarFuncionarioRequest request) {
        return ConsultarFuncionarioResponse.converter(
                repository.save(
                        CadastrarFuncionarioRequest.converter(request)
                )
        );
    }

<<<<<<< HEAD
    public ConsultarFuncionarioResponse atualizar(Long id, AtualizarFuncionarioRequest request) {
=======
    public ConsultarFuncionarioResponse altualizar(Long id, AtualizarFuncionarioRequest request) {
>>>>>>> 6821171011a635a4f931936bb1d8a61fdb295cf3
        Funcionario funcionario = repository.findById(id).orElse(null);

        if(funcionario == null){
            return null;
        }

        if(request.nome() != null && !request.nome().isEmpty()){
            funcionario.setNome(request.nome());
        }

        if(request.cpf() != null && !request.cpf().isEmpty()){
            funcionario.setCpf(request.cpf());
        }

        if(request.email() != null && !request.email().isEmpty()){
            funcionario.setEmail(request.email());
        }

        if(request.localidade() != null && !request.localidade().isEmpty()){
            funcionario.setLocalidade(request.localidade());
        }

        if(request.ativo() != null) {
            funcionario.setAtivo(request.ativo());
        }

        if(request.dataNascimento() != null) {
            funcionario.setDataNascimento(request.dataNascimento());
        }

        if(request.dataAdmissao() != null) {
            funcionario.setDataAdmissao(request.dataAdmissao());
        }

        return ConsultarFuncionarioResponse.converter(repository.save(funcionario));
    }
<<<<<<< HEAD

    public boolean deletar(Long id) {
        if(!repository.existsById(id)){
            return false;
        }

        repository.deleteById(id);
        return true;
    }
=======
>>>>>>> 6821171011a635a4f931936bb1d8a61fdb295cf3
}
