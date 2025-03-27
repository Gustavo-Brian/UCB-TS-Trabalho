document.addEventListener('DOMContentLoaded', function() {
    // Função para redirecionar para uma nova página
    function redirectTo(url) {
        window.location.href = url;
    }

    // Adiciona evento de clique ao botão "Cadastrar Novo Funcionário"
    const cadastrarBtn = document.querySelector('.submit-btn:nth-child(1)');
    cadastrarBtn.addEventListener('click', function() {
        redirectTo('cadastro_funcionario.html'); 
    });

    // Adiciona evento de clique ao botão "Login"
    const loginBtn = document.querySelector('.submit-btn:nth-child(2)');
    loginBtn.addEventListener('click', function() {
        redirectTo('login.html');
    });

    // Adiciona evento de clique ao botão "Recuperar Senha"
    const recuperarBtn = document.querySelector('.submit-btn:nth-child(3)');
    recuperarBtn.addEventListener('click', function() {
        redirectTo('recuperar_senha.html');
    });

    // Adiciona evento de clique ao link "Cadastre-se aqui"
    const cadastrarLink = document.querySelector('.right-panel a');
    cadastrarLink.addEventListener('click', function(event) {
        event.preventDefault();
        redirectTo('cadastro_funcionario.html'); 
    });
});