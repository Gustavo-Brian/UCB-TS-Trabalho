package br.ucb.teste.software.gerenciadorfuncionarios.funcionario.service;

import br.ucb.teste.software.gerenciadorfuncionarios.funcionario.entity.Funcionario;
import br.ucb.teste.software.gerenciadorfuncionarios.funcionario.dto.FuncionarioRequestDTO;
import br.ucb.teste.software.gerenciadorfuncionarios.funcionario.dto.FuncionarioResponseDTO;
import br.ucb.teste.software.gerenciadorfuncionarios.funcionario.repository.FuncionarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public FuncionarioResponseDTO criarFuncionario(FuncionarioRequestDTO requestDTO) {
        Funcionario funcionario = new Funcionario();
        // Copia as propriedades do DTO para a entidade manualmente ou usando BeanUtils
        funcionario.setNome(requestDTO.getNome());
        funcionario.setCargo(requestDTO.getCargo());
        funcionario.setMatricula(requestDTO.getMatricula());
        funcionario.setAtivo(requestDTO.isAtivo());

        Funcionario savedFuncionario = funcionarioRepository.save(funcionario);
        return convertToResponseDTO(savedFuncionario);
    }

    public List<FuncionarioResponseDTO> listarTodosFuncionarios() {
        return funcionarioRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<FuncionarioResponseDTO> buscarFuncionarioPorId(Long id) {
        return funcionarioRepository.findById(id)
                .map(this::convertToResponseDTO);
    }

    public FuncionarioResponseDTO atualizarFuncionario(Long id, FuncionarioRequestDTO requestDTO) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com ID: " + id));

        // Copia as propriedades do DTO para a entidade, excluindo o ID
        funcionario.setNome(requestDTO.getNome());
        funcionario.setCargo(requestDTO.getCargo());
        funcionario.setMatricula(requestDTO.getMatricula());
        funcionario.setAtivo(requestDTO.isAtivo());

        Funcionario updatedFuncionario = funcionarioRepository.save(funcionario);
        return convertToResponseDTO(updatedFuncionario);
    }

    public void deletarFuncionario(Long id) {
        funcionarioRepository.deleteById(id);
    }

    private FuncionarioResponseDTO convertToResponseDTO(Funcionario funcionario) {
        FuncionarioResponseDTO responseDTO = new FuncionarioResponseDTO();
        responseDTO.setId(funcionario.getId());
        responseDTO.setNome(funcionario.getNome());
        responseDTO.setCargo(funcionario.getCargo());
        responseDTO.setMatricula(funcionario.getMatricula());
        responseDTO.setAtivo(funcionario.isAtivo());
        return responseDTO;
    }
}