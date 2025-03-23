<h1>Trabalho-Final-Teste-de-Software</h1>

🛠 Pré-requisitos Antes de rodar o projeto, certifique-se de ter instalado:

<h3>Java 17+</h3>

<h3>Maven 3+</h3>

<h3>MySQL</h3>

Crie um banco de dados no MySQL (sem tabelas) e configure as propriedades de conexão.

<h1>🚀 Como Rodar o Projeto</h1>

<h2>1️⃣ Clone o repositório</h2> <br>
git clone https://github.com/Gustavo-Brian/UCB-Trabalho-Teste-Software.git

cd seu-repositorio

<h2>2️⃣ Configuração</h2> <br>
Configure o arquivo application.properties: <br>
spring.datasource.url=jdbc:mysql://localhost:3306/seu_banco <br>
spring.datasource.username=username <br>
spring.datasource.password=senha server.port=porta

<h2>3️⃣ Compilar e Executar</h2>

mvn clean install mvn spring-boot:run

<h2>4️⃣ Acessando a aplicação</h2> <br>
http://localhost:porta/...
