<h2>Trabalho Acadêmico | Gerenciador de Funcionários</h2>

<h3>⚠️ Recomendação de Sistema Operacional</h3>
<p>Este sistema é recomendado para uso no <strong>Windows</strong>, pois os componentes utilizados foram especificados e testados principalmente nessa plataforma.</p>

<h3>📝 Descrição do Projeto</h3>
<p>Este projeto foi desenvolvido com o objetivo de criar e testar um sistema web voltado à gestão de funcionários. Utilizando tecnologias como <strong>Java (Spring Boot)</strong>, <strong>JavaScript (React)</strong> e <strong>MySQL</strong>, a aplicação permite total manipulação de dados de funcionários, como: cadastro, edição, visualização e exclusão, integrando funcionalidades com um plano de testes bem definido. A proposta busca facilitar processos manuais, melhorar a visualização de dados e aumentar a eficiência operacional.</p>

<h3>🛠 Pré-requisitos</h3>
<p>Antes de rodar o projeto, certifique-se de ter os seguintes itens instalados em sua máquina:</p>
<ul>
  <li><strong>Java 17 ou superior</strong> – <a href="https://www.oracle.com/java/technologies/downloads/#jdk17-windows" target="_blank" rel="noopener noreferrer">https://www.oracle.com/java/technologies/downloads/#jdk17-windows</a></li>
  <li><strong>Maven 3 ou superior</strong> – <a href="https://maven.apache.org/download.cgi" target="_blank" rel="noopener noreferrer">https://maven.apache.org/download.cgi</a></li>
  <li><strong>MySQL</strong> – <a href="https://dev.mysql.com/downloads/file/?id=541637" target="_blank" rel="noopener noreferrer">https://dev.mysql.com/downloads/file/?id=541637</a> (instalador completo recomendado)</li>
  <li><strong>Git</strong> – <a href="https://git-scm.com/downloads" target="_blank" rel="noopener noreferrer">https://git-scm.com/downloads</a></li>
</ul>

<h4>Instalação recomendada do MySQL</h4>
<p>Recomendamos a instalação do <strong>MySQL completo</strong>, que inclui:</p>
<ul>
  <li>MySQL Server</li>
  <li>MySQL Workbench (interface gráfica)</li>
  <li>MySQL Shell</li>
  <li>Ferramentas de linha de comando</li>
</ul>

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

<p>Após executar, feche o terminal. Em seguida, volte para a seção <strong>1️⃣ Tente acessar o MySQL via terminal</strong>.</p>

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
<p>Escolha o diretório onde deseja instalar o projeto no seu computador.</p>
<p>Abra o terminal no diretório escolhido e execute o comando para clonar o repositório:</p>

<pre><code>git clone https://github.com/Gustavo-Brian/UCB-TS-Trabalho.git</code></pre>

<h4>2️⃣ Configurar o Projeto</h4>
<p>Abra o arquivo <code>application.properties</code>, localizado em <code>gerenciador-funcionarios/backend/src/main/resources</code>, e configure-o com as credenciais do seu banco de dados MySQL e a porta desejada:</p>

<pre><code>spring.datasource.url=jdbc:mysql://localhost:3306/nome_do_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
server.port=porta_desejada
</code></pre>

<p>O Spring Boot será responsável por gerar automaticamente as tabelas no banco assim que a aplicação for executada.</p>

<h4>3️⃣ Compilar e Executar o Projeto</h4>

<p>
Navegue até o diretório <code>backend</code> do projeto, onde está localizado o arquivo <code>pom.xml</code>. 
É essencial que o terminal esteja apontando para esse diretório, pois o Maven precisa encontrar esse arquivo para compilar e executar corretamente a aplicação.
</p>

<p>
No terminal, com ele aberto no diretório onde está localizado o diretório <code>UCB-TS-Trabalho</code>, execute o comando abaixo para navegar até o diretório correto (ajuste o caminho conforme a localização do seu projeto):
</p>

<pre><code>cd UCB-TS-Trabalho/gerenciador-funcionarios/backend</code></pre>

<p>Em seguida, execute os comandos abaixo separadamente:</p>

<ol>
  <li>
    <strong>Compile o projeto:</strong>
    <pre><code>mvn clean install</code></pre>
  </li>
  <li>
    <strong>Após a compilação bem-sucedida, inicie o servidor Spring Boot:</strong>
    <pre><code>mvn spring-boot:run</code></pre>
  </li>
</ol>

<p>Se tudo estiver configurado corretamente, a aplicação será iniciada e você verá as mensagens de inicialização do Spring Boot no terminal.</p>

<p><strong>Atenção:</strong> Caso você feche o terminal que está executando o servidor, a aplicação será encerrada automaticamente, pois o servidor depende da sessão ativa do terminal para continuar em execução.</p>

<h4>4️⃣ Acessar a Aplicação</h4>
<p>Abra seu navegador e acesse:</p>

<pre><code>http://localhost:porta/</code></pre>

<hr>

<h3>🚀 Roteiro de Instalação e Execução do Projeto Frontend</h3>

<h4>📝 Passo 1️⃣ — Instalar o Node.js (se ainda não tiver)</h4>
<p>1. Acesse: <a href="https://nodejs.org" target="_blank">https://nodejs.org</a></p>
<p>2. Baixe a versão <strong>LTS</strong> e instale.</p>
<p>✅ Após instalar, verifique no terminal/cmd:</p>
<pre><code>node -v
npm -v</code></pre>
<p>Se os comandos funcionarem, vá direto para o <strong>Passo 3️⃣</strong>.</p>
<p>❗ Caso apareça "comando desconhecido", siga para o <strong>Passo 2️⃣</strong> para configurar as variáveis de ambiente.</p>

<h4>📝 Passo 2️⃣ — Configurar variáveis de ambiente (Windows)</h4>
<p>1. Vá em <strong>Painel de Controle &gt; Sistema &gt; Configurações Avançadas do Sistema &gt; Variáveis de Ambiente</strong></p>
<p>2. Em <strong>Path</strong>, adicione o caminho onde o Node.js foi instalado (normalmente):</p>
<pre><code>C:\Program Files\nodejs\</code></pre>
<p>3. Feche e abra o terminal novamente.</p>
<p>4. Volte ao <strong>Passo 1️⃣</strong> e verifique novamente com:</p>
<pre><code>node -v
npm -v</code></pre>

<h4>📝 Passo 3️⃣ — Instalar e rodar o projeto</h4>
<p>1. Navegue até o diretório <code>frontend</code> do projeto (onde está o arquivo <code>package-lock.json</code>).</p>
<p>2. Execute:</p>
<pre><code>npm install</code></pre>
<p>❗ Se der erro, verifique se o Node está instalado corretamente com:</p>
<pre><code>npm -v</code></pre>
<p>Se o <code>npm</code> não estiver instalado, refaça o <strong>Passo 2️⃣</strong>.</p>
<p>3. Para iniciar o projeto:</p>
<pre><code>npm start</code></pre>
<p>Por padrão, o servidor será iniciado na porta <strong>3000</strong>.</p>

<h4>⚠️ Caso a porta 3000 já esteja em uso</h4>
<p>Você pode:</p>
<ul>
  <li>Finalizar o processo que está usando a porta 3000</li>
  <li><strong>OU</strong> criar um arquivo <code>.env</code> para definir uma nova porta</li>
</ul>

<h4>Como criar o <code>.env</code></h4>
<p>1. Na raiz do projeto (onde está o <code>package.json</code>), crie um arquivo chamado:</p>
<pre><code>.env</code></pre>
<p>2. Adicione dentro dele:</p>
<pre><code>PORT=3001</code></pre>
<p>(Troque <code>3001</code> pela porta que quiser.)</p>
<p>3. Salve o arquivo.</p>
<p>4. Rode novamente:</p>
<pre><code>npm start</code></pre>

<h4>4️⃣ Acessar a Aplicação</h4>
<p>Abra seu navegador e acesse:</p>

<pre><code>http://localhost:porta/</code></pre>

<hr>
<h3>📌 Considerações Finais</h3>
<ul>
  <li>Este projeto foi desenvolvido com foco acadêmico e visa demonstrar boas práticas de desenvolvimento com testes e arquitetura em camadas.</li>
  <li>Ele oferece uma base para a construção de sistemas reais de gestão de funcionários.</li>
  <li>O projeto foi desenvolvido de forma modular, permitindo que o backend (API) e o frontend sejam utilizados de forma independente. Isso facilita futuras expansões, integrações ou substituições de tecnologias em cada camada.</li>
</ul>
