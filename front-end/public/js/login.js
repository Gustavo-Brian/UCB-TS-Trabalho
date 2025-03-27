document.getElementById('login-form').addEventListener('submit', function (e) {
    e.preventDefault(); // Previne o envio padrão do formulário

    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    fetch('http://localhost:3003/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email, password })
    })
    .then(response => response.json())
    .then(data => {
        console.log('Resposta do servidor:', data); // Depuração

        if (data.success) {
            // Armazena o token para manter a sessão ativa
            localStorage.setItem('userToken', data.token);
            
            document.getElementById('success-message').textContent = data.message;
            document.getElementById('error-message').textContent = '';

            // Redireciona para o dashboard após um pequeno atraso
            setTimeout(() => {
                window.location.href = 'dashboard.html';
            }, 1000);
        } else {
            document.getElementById('error-message').textContent = data.message || 'Erro ao tentar fazer login';
            document.getElementById('success-message').textContent = '';
        }
    })
    .catch(error => {
        console.error('Erro na solicitação:', error);
        document.getElementById('error-message').textContent = 'Erro ao conectar ao servidor!';
        document.getElementById('success-message').textContent = '';
    });
});
