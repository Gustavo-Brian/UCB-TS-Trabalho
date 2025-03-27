 // Função para verificar se o usuário está autenticado
 function estaAutenticado() {
    const token = localStorage.getItem('userToken') || sessionStorage.getItem('userToken');
    return token !== null;
}

// Função para redirecionamento seguro
function navegarPara(caminho) {
    if (!estaAutenticado()) {
        alert("Você precisa estar autenticado para acessar esta página.");
        window.location.href = '/login.html'; // Redireciona para login
    } else {
        window.location.href = caminho;
    }
}

// Função para logout do usuário
function logout() {
    localStorage.removeItem('userToken'); // Remove token do armazenamento
    sessionStorage.removeItem('userToken');
    alert("Você saiu do sistema!");
    window.location.href = 'login.html';
}

// Carregar informações do dashboard (simulado)
function carregarInformacoesDashboard() {
    const dashboardInfo = document.getElementById('dashboard-info');
    
    setTimeout(() => {
        dashboardInfo.innerHTML = `
            <p><strong>Total de Funcionários:</strong> 50</p>
            <p><strong>Total de Cargos:</strong> 10</p>
            <p><strong>Férias Pendentes:</strong> 5</p>
        `;
    }, 1000);
}

// Evento que roda ao carregar a página
document.addEventListener('DOMContentLoaded', function() {
    if (!estaAutenticado()) {
        alert("Você precisa estar autenticado para acessar o dashboard.");
        window.location.href = 'login.html';
    } else {
        carregarInformacoesDashboard();
    }
});