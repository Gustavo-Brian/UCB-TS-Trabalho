<h1>Trabalho-Final-Teste-de-Software</h1>

🛠 Pré-requisitos Antes de rodar o projeto, certifique-se de ter instalado:

Java 17+ (ou a versão suportada pelo projeto)

Maven 3+

(Opcional) Banco de dados específico, como MySQL, PostgreSQL, etc.

🚀 Como Rodar o Projeto

1️⃣ Clone o repositório<br>
git clone https://github.com/Gustavo-Brian/UCB-Trabalho-Teste-Software.git

cd seu-repositorio

2️⃣ Configuração <br>
Configure o arquivo application.properties: <br>
spring.datasource.url=jdbc:mysql://localhost:3306/seu_banco <br>
spring.datasource.username=username <br>
spring.datasource.password=senha server.port=porta

3️⃣ Compilar e Executar 🔹 Usando Maven

mvn clean install mvn spring-boot:run

4️⃣ Acessando a aplicação http://localhost:8080/...
