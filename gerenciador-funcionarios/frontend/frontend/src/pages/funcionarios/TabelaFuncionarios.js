export function TabelaFuncionarios({ funcionarios, carregando, aoAlterar, aoInativar }) {
  if (carregando) {
    return <p>Carregando funcionários...</p>;
  }

  return (
    <table style={{ width: "100%", borderCollapse: "collapse" }}>
      <thead>
        <tr>
          <th style={{ border: "1px solid #ccc", padding: "8px" }}>Nome</th>
          <th style={{ border: "1px solid #ccc", padding: "8px" }}>Cargo</th>
          <th style={{ border: "1px solid #ccc", padding: "8px" }}>Matrícula</th>
          <th style={{ border: "1px solid #ccc", padding: "8px" }}>Status</th>
          <th style={{ border: "1px solid #ccc", padding: "8px" }}>Ações</th>
        </tr>
      </thead>
      <tbody>
        {funcionarios.map((func) => (
          <tr key={func.id}>
            <td style={{ border: "1px solid #ccc", padding: "8px" }}>{func.nome}</td>
            <td style={{ border: "1px solid #ccc", padding: "8px" }}>{func.cargo}</td>
            <td style={{ border: "1px solid #ccc", padding: "8px" }}>{func.matricula}</td>
            <td style={{ border: "1px solid #ccc", padding: "8px" }}>{func.setAtivo ? "Ativo" : "Inativo"}</td>
            <td style={{ border: "1px solid #ccc", padding: "8px" }}>
              <button onClick={() => aoAlterar(func)} style={{ marginRight: "5px" }}>
                Alterar
              </button>
              <button
                onClick={() => aoInativar(func.id)}
                disabled={!func.setAtivo}
              >
                {func.setAtivo ? "Inativar" : "Já inativo"}
              </button>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}
