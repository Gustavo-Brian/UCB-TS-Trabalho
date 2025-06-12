<h2>Trabalho Acadêmico | Gerenciador de Funcionários</h2>

<h3>📝 Descrição do Projeto</h3> 

Este projeto foi desenvolvido com o objetivo de criar e testar um sistema web voltado à **gestão de funcionários**, especialmente para pequenas e médias empresas. Utilizando tecnologias como **Java (Spring Boot)**, **React** e **MySQL**, a aplicação permite o **cadastro, edição, visualização e exclusão de dados** dos colaboradores, integrando funcionalidades modernas com um plano de testes bem definido. A proposta busca **automatizar processos manuais**, melhorar a **eficiência operacional** e garantir **segurança, usabilidade e confiabilidade** nas informações gerenciadas por equipes de **Recursos Humanos**, **gestores** e **administradores**.


🛠 Pré-requisitos Antes de rodar o projeto, certifique-se de ter instalado:

<h4>Java 17+</h4>

<h4>Maven 3+</h4>

<h4>MySQL</h4>

Crie um banco de dados no MySQL (sem tabelas).

<h2>🚀 Como Rodar o Projeto</h2>

<h3>1️⃣ Clone o repositório</h3> <br>
git clone https://github.com/Gustavo-Brian/UCB-Trabalho-Teste-Software.git

cd seu-repositorio

<h3>2️⃣ Configuração</h3> <br>
<strong>Configure o arquivo application.properties:</strong>

spring.datasource.url=jdbc:mysql://localhost:3306/seu_banco <br>
spring.datasource.username=username <br>
spring.datasource.password=senha<br>
server.port=porta

<h3>3️⃣ Compilar e Executar</h3>

mvn clean install<br>
mvn spring-boot:run

<h3>4️⃣ Acessando a aplicação</h3> <br>
http://localhost:porta/...
