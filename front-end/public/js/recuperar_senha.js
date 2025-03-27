// Função para solicitar o código de recuperação de senha
async function solicitarCodigo() {
    const email = document.getElementById('email').value.trim();
    const erroMensagem = document.getElementById('erro-mensagem');
    erroMensagem.textContent = '';

    if (!validarEmail(email)) {
        erroMensagem.textContent = 'Por favor, insira um e-mail válido.';
        return;
    }

    try {
        const resposta = await fetch('http://localhost:3002/solicitar_codigo', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ email })
        });

        const dados = await resposta.json();

        if (resposta.ok) {
            alert('Código enviado para o seu e-mail.');
            document.getElementById('form-solicitar-codigo').style.display = 'none';
            document.getElementById('form-redefinir-senha').style.display = 'block';
        } else {
            erroMensagem.textContent = dados.erro || 'Erro ao enviar código.';
        }
    } catch (erro) {
        erroMensagem.textContent = 'Erro de conexão. Tente novamente mais tarde.';
    }
}

// Função para redefinir senha
async function redefinirSenha() {
    const email = document.getElementById('email').value.trim();
    const resetCode = document.getElementById('reset-code').value.trim();
    const newPassword = document.getElementById('new-password').value;
    const confirmPassword = document.getElementById('confirm-password').value;
    const erroMensagem = document.getElementById('erro-redefinir');

    erroMensagem.textContent = '';

    if (newPassword !== confirmPassword) {
        erroMensagem.textContent = 'As senhas não coincidem.';
        return;
    }

    try {
        const resposta = await fetch('http://localhost:3002/redefinir_senha', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ email, codigoRecuperacao: resetCode, novaSenha: newPassword })
        });

        const dados = await resposta.json();

        if (resposta.ok) {
            alert('Senha redefinida com sucesso!');
            location.reload(); // Recarrega a página após sucesso
        } else {
            erroMensagem.textContent = dados.erro || 'Erro ao redefinir a senha.';
        }
    } catch (erro) {
        erroMensagem.textContent = 'Erro de conexão. Tente novamente mais tarde.';
    }
}

// Função para validar e-mail
function validarEmail(email) {
    const re = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return re.test(email);
}
