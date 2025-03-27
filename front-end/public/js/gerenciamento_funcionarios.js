document.addEventListener("DOMContentLoaded", function () {
    carregarFuncionarios();
});

function carregarFuncionarios() {
    fetch("/api/funcionarios")
        .then(response => response.json())
        .then(data => {
            const tabela = document.getElementById("tabelaFuncionarios").getElementsByTagName("tbody")[0];
            tabela.innerHTML = "";
            data.forEach(funcionario => {
                let linha = tabela.insertRow();
                linha.insertCell(0).textContent = funcionario.nome;
                linha.insertCell(1).textContent = funcionario.cpf;
                linha.insertCell(2).textContent = funcionario.cargo;
                let celulaAcoes = linha.insertCell(3);
                celulaAcoes.innerHTML = `
                    <button class="botao-editar" onclick="editarFuncionario('${funcionario.cpf}')">Editar</button> 
                    <button class="botao-remover" onclick="removerFuncionario('${funcionario.cpf}')">Remover</button>
                `;
            });
        })
        .catch(error => console.error("Erro ao carregar funcionários:", error));
}

function filtrarTabela() {
    const nomeFiltro = document.getElementById("buscarNome").value.toLowerCase();
    const cpfFiltro = document.getElementById("buscarCPF").value.toLowerCase();
    const cargoFiltro = document.getElementById("buscarCargo").value.toLowerCase();
    const tabela = document.getElementById("tabelaFuncionarios");
    const linhas = tabela.getElementsByTagName("tr");

    for (let i = 1; i < linhas.length; i++) {
        const celulas = linhas[i].getElementsByTagName("td");
        const nomeMatch = celulas[0].textContent.toLowerCase().includes(nomeFiltro);
        const cpfMatch = celulas[1].textContent.toLowerCase().includes(cpfFiltro);
        const cargoMatch = celulas[2].textContent.toLowerCase().includes(cargoFiltro);

        if (nomeMatch && cpfMatch && cargoMatch) {
            linhas[i].style.display = "";
        } else {
            linhas[i].style.display = "none";
        }
    }
}

function editarFuncionario(cpf) {
    window.location.href = `/editar_funcionario.html?cpf=${cpf}`;
}

function removerFuncionario(cpf) {
    if (confirm("Tem certeza que deseja remover este funcionário?")) {
        fetch(`/api/funcionarios/${cpf}`, {
            method: "DELETE"
        })
        .then(response => response.json())
        .then(data => {
            alert(data.mensagem);
            carregarFuncionarios();
        })
        .catch(error => console.error("Erro ao remover funcionário:", error));
}
}