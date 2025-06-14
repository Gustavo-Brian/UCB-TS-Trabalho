import React, { useState, useEffect } from "react";

export function FormFuncionario({ funcionario, aoSalvar, aoCancelar }) {
  const [nome, setNome] = useState("");
  const [cargo, setCargo] = useState("");
  const [matricula, setMatricula] = useState("");
  const [setAtivo, setSetAtivo] = useState(true);
  const [erro, setErro] = useState("");

  useEffect(() => {
    if (funcionario) {
      setNome(funcionario.nome || "");
      setCargo(funcionario.cargo || "");
      setMatricula(funcionario.matricula || "");
      setSetAtivo(funcionario.setAtivo !== undefined ? funcionario.setAtivo : true);
    } else {
      setNome("");
      setCargo("");
      setMatricula("");
      setSetAtivo(true);
    }
    setErro("");
  }, [funcionario]);

  function validarMatricula(mat) {
    return mat && !isNaN(mat);
  }

  function handleSubmit(e) {
    e.preventDefault();
    if (!nome.trim()) {
      setErro("Nome é obrigatório.");
      return;
    }
    if (!cargo.trim()) {
      setErro("Cargo é obrigatório.");
      return;
    }
    if (!validarMatricula(matricula)) {
      setErro("Matrícula inválida (deve ser número).");
      return;
    }
    setErro("");
    aoSalvar({
      id: funcionario?.id,
      nome: nome.trim(),
      cargo: cargo.trim(),
      matricula: Number(matricula),
      setAtivo,
    });
  }

  return (
    <form onSubmit={handleSubmit} style={{ marginTop: "20px" }}>
      <h3>{funcionario ? "Editar Funcionário" : "Novo Funcionário"}</h3>
      {erro && <p style={{ color: "red" }}>{erro}</p>}

      <div>
        <label>Nome:</label><br />
        <input value={nome} onChange={(e) => setNome(e.target.value)} />
      </div>

      <div>
        <label>Cargo:</label><br />
        <input value={cargo} onChange={(e) => setCargo(e.target.value)} />
      </div>

      <div>
        <label>Matrícula:</label><br />
        <input
          type="number"
          value={matricula}
          onChange={(e) => setMatricula(e.target.value)}
          disabled={!!funcionario} // opcional: não permitir editar matrícula
        />
      </div>

      <div>
        <label>
          <input
            type="checkbox"
            checked={setAtivo}
            onChange={(e) => setSetAtivo(e.target.checked)}
          />
          Ativo
        </label>
      </div>

      <button type="submit">{funcionario ? "Salvar" : "Cadastrar"}</button>
      <button type="button" onClick={aoCancelar} style={{ marginLeft: "10px" }}>
        Cancelar
      </button>
    </form>
  );
}
