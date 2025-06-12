<h2>Trabalho Acadêmico | Gerenciador de Funcionários</h2>

<h3>⚠️ Recomendação de Sistema Operacional</h3>
<p>Este sistema é recomendado para uso no <strong>Windows</strong>, pois os componentes utilizados foram especificados e testados principalmente nessa plataforma.</p>

<h3>📝 Descrição do Projeto</h3>
<p>Este projeto foi desenvolvido com o objetivo de criar e testar um sistema web voltado à gestão de funcionários. Utilizando tecnologias como <strong>Java (Spring Boot)</strong>, <strong>JavaScript (React)</strong> e <strong>MySQL</strong>, a aplicação permite total manipulação de dados de funcionários, como: cadastro, edição, visualização e exclusão, integrando funcionalidades com um plano de testes bem definido. A proposta busca facilitar processos manuais, melhorar a visualização de dados e aumentar a eficiência operacional.</p>

<h3>🛠 Pré-requisitos</h3>
<p>Antes de rodar o projeto, certifique-se de ter os seguintes itens instalados em sua máquina:</p>
<ul>
  <li><strong>Java 17 ou superior</strong></li>
  <li><strong>Maven 3 ou superior</strong></li>
  <li><strong>MySQL</strong></li>
</ul>

<h4>Instalação recomendada do MySQL</h4>
<p>Recomendamos a instalação do <strong>MySQL Installer completo</strong>, que inclui:</p>
<ul>
  <li>MySQL Server</li>
  <li>MySQL Workbench (interface gráfica)</li>
  <li>MySQL Shell</li>
  <li>Ferramentas de linha de comando</li>
</ul>
<p>📥 Baixe o instalador completo aqui:<br>
<a href="https://dev.mysql.com/downloads/file/?id=541637" target="_blank" rel="noopener noreferrer">https://dev.mysql.com/downloads/file/?id=541637</a></p>
<p>Durante a instalação, defina um usuário e uma senha de acesso. Guarde essas informações, pois você precisará utilizá-las na configuração do projeto.</p>

<h3>💻 Criando o banco de dados via terminal</h3>

<p>Após instalar o MySQL (utilizando o instalador completo recomendado), você precisará criar o banco de dados que o sistema usará. Siga os passos abaixo com atenção:</p>

<h4>1️⃣ Tente acessar o MySQL via terminal</h4>
<p>Abra o Prompt de Comando ou PowerShell no Windows e digite:</p>

<pre><code>mysql -u root -p</code></pre>

<p>Se o comando funcionar, digite sua senha quando solicitado e pule para a seção <strong>3️⃣ Criando o banco de dados</strong>.</p>

<h4>❗ Caso receba o erro abaixo:</h4>

<pre><code>'mysql' não é reconhecido como um comando interno
ou externo, um programa operável ou um arquivo em lotes.</code></pre>

<p>Isso significa que o MySQL não está configurado no PATH do sistema, e o terminal não consegue encontrar o comando.</p>

<h4>2️⃣ Adicionando MySQL ao PATH via terminal</h4>

<p>Para resolver isso, execute o seguinte comando no mesmo terminal (atenção ao caminho, adapte caso seu MySQL esteja instalado em outro local):</p>

<pre><code>setx PATH "%PATH%;C:\Program Files\MySQL\MySQL Server 8.0\bin"</code></pre>

<p>Após executar, feche o terminal. Em seguida, volte para a seção <strong>1️⃣ Tente acessar o MySQL via terminal</strong>.

<h4>3️⃣ Criando o banco de dados</h4>

<p>Depois de acessar o prompt do MySQL (indicado por <code>mysql&gt;</code>), crie o banco que será usado pelo projeto substituindo <code>nome_do_banco</code> pelo nome que desejar:</p>

<pre><code>CREATE DATABASE nome_do_banco;</code></pre>

<p>Para conferir se o banco foi criado corretamente, execute:</p>

<pre><code>SHOW DATABASES;</code></pre>

<p>Finalize saindo do prompt MySQL com:</p>

<pre><code>EXIT;</code></pre>

<p><strong>🔒 Guarde o nome do banco de dados que você criou</strong>. Ele será necessário na próxima etapa, onde configuraremos o projeto para se conectar a esse banco.</p>

<hr>

<h3>🚀 Como Rodar o Projeto</h3>

<h4>1️⃣ Clonar o Repositório</h4>
<p>Escolha a pasta onde deseja instalar o projeto no seu computador.</p>
<p>Abra o terminal na pasta escolhida e execute o comando para clonar o repositório:</p>

<pre><code>git clone https://github.com/Gustavo-Brian/UCB-TS-Trabalho.git</code></pre>

<p>Após clonar, acesse a pasta do projeto:</p>

<pre><code>cd UCB-TS-Trabalho</code></pre>

<h4>2️⃣ Configurar o Projeto</h4>
<p>Abra o arquivo <code>application.properties</code>, localizado em <code>src/main/resources/</code>, e configure-o com as credenciais do seu banco de dados MySQL:</p>

<pre><code>spring.datasource.url=jdbc:mysql://localhost:3306/nome_do_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
server.port=porta_desejada
</code></pre>

<p>O Spring Boot será responsável por gerar automaticamente as tabelas no banco assim que a aplicação for executada.</p>

<h4>3️⃣ Compilar e Executar o Projeto</h4>
<p>Execute os seguintes comandos no terminal:</p>

<pre><code>mvn clean install
mvn spring-boot:run
</code></pre>

<h4>4️⃣ Acessar a Aplicação</h4>
<p>Abra seu navegador e acesse:</p>

<pre><code>http://localhost:porta/</code></pre>

<hr>

<h3>📌 Considerações Finais</h3>
<ul>
  <li>Este projeto foi desenvolvido com foco acadêmico e visa demonstrar boas práticas de desenvolvimento com testes automatizados e arquitetura em camadas.</li>
  <li>Ele oferece uma base sólida para a construção de sistemas reais de gestão de funcionários.</li>
  <li>É possível expandi-lo com recursos como autenticação de usuários, relatórios personalizados, integrações externas, entre outros.</li>
</ul>
