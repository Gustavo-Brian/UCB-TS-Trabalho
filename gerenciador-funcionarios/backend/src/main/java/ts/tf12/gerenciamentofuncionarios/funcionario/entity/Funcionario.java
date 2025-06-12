package ts.tf12.gerenciamentofuncionarios.funcionario.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, name = "funcionario_nome")
    private String nome;

    @Column(nullable = false, unique = true, length = 14, name = "funcionario_cpf")
    private String cpf;

    @Column(nullable = false, unique = true, length = 100, name = "funcionario_email")
    private String email;

    @Column(length = 100, name = "funcionario_localidade")
    private String localidade;

    @Column(nullable = false, name = "funcionario_ativo")
    private Boolean ativo;

    @Column(name = "funcionario_data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "funcionario_data_admissao")
    private LocalDate dataAdmissao;

    public Funcionario() {
    }

    public Funcionario(Long id, String nome, String cpf, String email,
                       String localidade, Boolean ativo,
                       LocalDate dataNascimento, LocalDate dataAdmissao) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.localidade = localidade;
        this.ativo = ativo;
        this.dataNascimento = dataNascimento;
        this.dataAdmissao = dataAdmissao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public Boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    @PrePersist
    private void DefinirPadroes() {
        this.dataAdmissao = LocalDate.now();
        this.ativo = true;
    }
}