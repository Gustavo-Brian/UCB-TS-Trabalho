package br.ucb.teste.software.gerenciadorfuncionarios.funcionario.dto;

public class FuncionarioRequestDTO {
    private String nome;
    private String cargo;
    private String matricula;
    private boolean ativo;

    // Construtor padrão
    public FuncionarioRequestDTO() {
    }

    // Construtor com todos os argumentos
    public FuncionarioRequestDTO(String nome, String cargo, String matricula, boolean ativo) {
        this.nome = nome;
        this.cargo = cargo;
        this.matricula = matricula;
        this.ativo = ativo;
    }

    // Getters
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