package br.ucb.teste.software.gerenciadorfuncionarios.funcionario.dto;

public class FuncionarioResponseDTO {
    private Long id;
    private String nome;
    private String cargo;
    private String matricula;
    private boolean ativo;

    // Construtor padrão
    public FuncionarioResponseDTO() {
    }

    // Construtor com todos os argumentos
    public FuncionarioResponseDTO(Long id, String nome, String cargo, String matricula, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
        this.matricula = matricula;
        this.ativo = ativo;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCargo() {
        return cargo;
    }

    public String getMatricula() {
        return matricula;
    }

    public boolean isAtivo() {
        return ativo;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}