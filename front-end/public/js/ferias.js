async function solicitarFerias() {
    const funcionario = document.getElementById("funcionario").value;
    const dataInicio = document.getElementById("dataInicio").value;
    const dataFim = document.getElementById("dataFim").value;
    const mensagem = document.getElementById("mensagem");

    if (!funcionario || !dataInicio || !dataFim) {
        mensagem.innerText = "Preencha todos os campos!";
        mensagem.style.color = "red";
        return;
    }

    const response = await fetch("http://localhost:3003/ferias", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ funcionario, dataInicio, dataFim })
    });

    if (response.ok) {
        const data = await response.json();
        mensagem.innerText = data.message;
        mensagem.style.color = "green";
    } else {
        const errorData = await response.json();
        mensagem.innerText = errorData.message;
        mensagem.style.color = "red";   
    }    
}