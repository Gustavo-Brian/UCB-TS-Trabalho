import React, { useState } from "react";
import { useFuncionarios } from "./useFuncionarios"; // ajuste o caminho se necessário
import { TabelaFuncionarios } from "./TabelaFuncionarios"; // ajuste o caminho se necessário
import { FormFuncionario } from "./FormFuncionario"; // ajuste o caminho se necessário

export default function GestaoFuncionarios() {
  const {
    funcionarios,
    carregando,
    alterarFuncionario,
    inativarFuncionario,
    criarFuncionario,
  } = useFuncionarios();

  const [funcionarioSelecionado, setFuncionarioSelecionado] = useState(null);
  const [modoFormulario, setModoFormulario] = useState(false);

  function abrirNovo() {
    setFuncionarioSelecionado(null);
    setModoFormulario(true);
  }

  function abrirEditar(funcionario) {
    setFuncionarioSelecionado(funcionario);
    setModoFormulario(true);
  }

  async function salvarFuncionario(dados) {
    if (dados.id) {
      await alterarFuncionario(dados.id, dados);
    } else {
      if (funcionarios.some((f) => f.matricula === dados.matricula)) {
        alert("Já existe um funcionário com esta matrícula.");
        return;
      }
      await criarFuncionario(dados);
    }
    setModoFormulario(false);
  }

  function cancelar() {
    setModoFormulario(false);
  }

  return (
    <div style={{ padding: "20px" }}>
      <h2>Gestão de Funcionários</h2>
      {!modoFormulario && (
        <>
          <button onClick={abrirNovo} style={{ marginBottom: "10px" }}>
            Novo Funcionário
          </button>
          <TabelaFuncionarios
            funcionarios={funcionarios}
            carregando={carregando}
            aoAlterar={abrirEditar}
            aoInativar={inativarFuncionario}
          />
        </>
      )}

      {modoFormulario && (
        <FormFuncionario
          funcionario={funcionarioSelecionado}
          aoSalvar={salvarFuncionario}
          aoCancelar={cancelar}
        />
      )}
    </div>
  );
}
