// Função para lidar com o envio do formulário
async function submitForm(event) {
    event.preventDefault(); // Evita o envio padrão do formulário

    // Validação para garantir que as senhas coincidem
    const senha = document.getElementById('senha').value;
    const confirmaSenha = document.getElementById('confirma_senha').value;

    if (senha !== confirmaSenha) {
        document.getElementById('error-message').textContent = 'As senhas não coincidem. Por favor, digite novamente.';
        document.getElementById('success-message').textContent = ''; 
        return;
    }

    const form = document.getElementById('cadastro-form');
    const formData = new FormData(form);
    const data = {};

    formData.forEach((value, key) => {
        data[key] = value;
    });

    // Ajuste do nome da senha para "password"
    data["password"] = data["senha"];
    delete data["senha"];
    delete data["confirma_senha"];

    try {
        const response = await fetch('http://localhost:3003/cadastro', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        });

        const result = await response.json();

        if (response.ok) {
            document.getElementById('success-message').textContent = result.mensagem;
            document.getElementById('error-message').textContent = '';
        } else {
            document.getElementById('error-message').textContent = result.erro;
            document.getElementById('success-message').textContent = '';
        }
    } catch (error) {
        document.getElementById('error-message').textContent = 'Erro ao cadastrar usuário!';
        document.getElementById('success-message').textContent = '';
    }
}

// Evento de envio do formulário
document.getElementById('cadastro-form').onsubmit = submitForm;
