<h2>Trabalho Acadêmico | Gerenciador de Funcionários</h2>

<h3>📝 Descrição do Projeto</h3> 

<p>
Este projeto foi desenvolvido com o objetivo de criar e testar um sistema web voltado à <strong>gestão de funcionários</strong>. Utilizando tecnologias como <strong>Java (Spring Boot)</strong>, <strong>JavaScript (React)</strong> e <strong>MySQL</strong>, a aplicação permite total manipulação de dados de funcionários, como: <em>cadastro, edição, visualização e exclusão</em>, integrando funcionalidades com um plano de testes bem definido. A proposta busca <strong>facilitar processos manuais</strong>, melhorar a visualização de dados e aumentar a <strong>eficiência operacional</strong>.
</p>

<h3>🛠 Pré-requisitos</h3>

<p>Antes de rodar o projeto, certifique-se de ter os seguintes itens instalados em sua máquina:</p>

<ul>
  <li><strong>Java 17 ou superior</strong></li>
  <li><strong>Maven 3 ou superior</strong></li>
  <li><strong>MySQL</strong></li>
</ul>

<h4>Instalação recomendada do MySQL</h4>

<p>
Recomendamos a instalação do <strong>MySQL Installer completo</strong>, que inclui:
</p>
<ul>
  <li>MySQL Server</li>
  <li>MySQL Workbench (interface gráfica)</li>
  <li>MySQL Shell</li>
  <li>Ferramentas de linha de comando</li>
</ul>

<p>
📥 <strong>Baixe o instalador completo aqui:</strong><br>
<a href="https://dev.mysql.com/downloads/file/?id=541637" target="_blank">
https://dev.mysql.com/downloads/file/?id=541637
</a>
</p>

<p>
Durante a instalação, defina um usuário e uma senha de acesso ao banco de dados. Guarde essas informações, pois você precisará utilizá-las na configuração do projeto.
</p>

<h4>💻 Criando o banco de dados via terminal</h4>

<p>Após a instalação do MySQL, você pode criar o banco de dados via terminal com os seguintes comandos:</p>

<pre><code>
mysql -u root -p
</code></pre>

<p>Depois de inserir sua senha, execute:</p>

<pre><code>
CREATE DATABASE nome_do_banco;
SHOW DATABASES;
EXIT;
</code></pre>

<hr>

<h3>🚀 Como Rodar o Projeto</h3>

<h4>1️⃣ Clonar o Repositório</h4>

<p>Escolha a pasta onde deseja instalar o projeto no seu computador.</p>

<p>Abra o terminal na pasta escolhida e execute o seguinte comando para clonar o repositório:</p>

<pre><code>
git clone https://github.com/Gustavo-Brian/UCB-TS-Trabalho.git
</code></pre>

<p>Após clonar, acesse a pasta do projeto com o comando:</p>

<pre><code>
cd UCB-TS-Trabalho
</code></pre>

<h4>2️⃣ Configurar o Projeto</h4>

<p>Abra o arquivo <code>application.properties</code>, localizado em <code>src/main/resources/</code>, e configure-o com as credenciais do seu banco de dados MySQL:</p>

<pre><code>
spring.datasource.url=jdbc:mysql://localhost:3306/nome_do_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
server.port=porta_desejada
</code></pre>

<p>O Spring Boot será responsável por gerar automaticamente as tabelas no banco assim que a aplicação for executada.</p>

<h4>3️⃣ Compilar e Executar o Projeto</h4>

<p>Execute os seguintes comandos no terminal:</p>

<pre><code>
mvn clean install
mvn spring-boot:run
</code></pre>

<h4>4️⃣ Acessar a Aplicação</h4>

<p>Abra seu navegador e acesse:</p>

<pre><code>
http://localhost:porta/
</code></pre>

<hr>

<h3>📌 Considerações Finais</h3>

<ul>
  <li>Este projeto foi desenvolvido com foco acadêmico e visa demonstrar boas práticas de desenvolvimento com testes automatizados e arquitetura em camadas.</li>
  <li>Ele oferece uma base sólida para a construção de sistemas reais de gestão de funcionários.</li>
  <li>É possível expandi-lo com recursos como autenticação de usuários, relatórios personalizados, integrações externas, entre outros.</li>
</ul>
