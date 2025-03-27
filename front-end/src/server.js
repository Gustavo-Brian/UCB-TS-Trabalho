const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');
const bcrypt = require('bcryptjs');
const crypto = require('crypto');
const nodemailer = require('nodemailer');
const dotenv = require('dotenv');
const path = require('path');

// Carregar variáveis de ambiente
dotenv.config();

// Configuração do servidor Express
const app = express();
const porta = 3003;

// Middleware
app.use(bodyParser.json());

// Configuração aprimorada do CORS <!-- Concluido, gustavo avaliar e fazer alterações necessarias -->
const corsOptions = {
    origin: 'http://localhost:3000', // Garantir que a origem esteja correta
    methods: 'GET,POST,PUT,DELETE',
    allowedHeaders: 'Content-Type,Authorization'
};
app.use(cors(corsOptions));

// Configuração de arquivos estáticos <!-- Concluido, gustavo avaliar e fazer alterações necessarias -->
app.use(express.static(path.join(__dirname, '../public')));

// Simulando um banco de dados <!-- Concluido, gustavo avaliar e fazer alterações necessarias -->
let usuarios = [
    { email: 'carlos.pereira@email.com', senha: bcrypt.hashSync('Senha123', 10), cpf: '123.456.789-00', cargo: 'Gerente', setor: 'Financeiro', data_admissao: '2020-01-15' },
    { email: 'joao.silva@email.com', senha: bcrypt.hashSync('MinhaSenha123', 10), cpf: '987.654.321-00', cargo: 'Analista', setor: 'TI', data_admissao: '2021-03-10' },
    { email: 'ana.souza@email.com', senha: bcrypt.hashSync('SenhaAna123', 10), cpf: '111.222.333-44', cargo: 'Assistente', setor: 'RH', data_admissao: '2025-03-17' }
];

// Função para enviar código de recuperação de senha  <!-- Concluido, gustavo avaliar e fazer alterações necessarias -->
async function enviarEmailRecuperacao(email, codigoRecuperacao) {
    const transporter = nodemailer.createTransport({
        host: process.env.MAIL_HOST,
        port: process.env.MAIL_PORT,
        auth: {
            user: process.env.MAIL_USER,
            pass: process.env.MAIL_PASS,
        },
    });

    const mailOptions = {
        from: process.env.MAIL_USER,
        to: email,
        subject: 'Recuperação de Senha',
        html: `<h2>Solicitação de recuperação de senha</h2>
               <p>Use o código abaixo para redefinir sua senha:</p>
               <p><strong>${codigoRecuperacao}</strong></p>`,
    };

    try {
        await transporter.sendMail(mailOptions);
        console.log('E-mail de recuperação enviado com sucesso!');
    } catch (erro) {
        console.error('Erro ao enviar e-mail:', erro);
    }
}

// Rota para Dashboard <!-- AINDA TEM QUE SER FEITA HTML, CSS E JS - RASCUNHO -->
app.get('/dashboard', (req, res) => {
    res.sendFile(path.join(__dirname, 'public', 'dashboard.html'));  // Certifique-se de que o arquivo está na pasta public
});

// Rota para Cadastro de Funcionário <!-- AINDA TEM QUE SER FEITA HTML, CSS E JS -->
app.get('/cadastro_funcionario', (req, res) => {
    res.sendFile(path.join(__dirname, 'public', 'cadastro_funcionario.html'));  // Corrigir para enviar o arquivo correto
});

// Rota para Listar Funcionários <!-- AINDA TEM QUE SER FEITA HTML, CSS E JS -->
app.get('/listar_funcionario', (req, res) => {
    res.sendFile(path.join(__dirname, 'public', 'listar_funcionario.html'));  // Corrigir para enviar o arquivo correto
});

// Rota para Gerenciar Cargos <!-- AINDA TEM QUE SER FEITA HTML, CSS E JS -->
app.get('/cargos', (req, res) => {
    res.sendFile(path.join(__dirname, 'public', 'cargos.html'));  // Corrigir para enviar o arquivo correto
});


// Rota para obter todas as solicitações de férias
app.get('/ferias', (req, res) => {
    db.query('SELECT * FROM ferias', (err, result) => {
        if (err) {
            res.status(500).send(err);
        } else {
            res.json(result);
        }
    });
});

// Rota para criar uma nova solicitação de férias
app.post('/ferias', (req, res) => {
    const { funcionario_id, data_inicio, data_fim } = req.body;
    db.query(
        'INSERT INTO ferias (funcionario_id, data_inicio, data_fim, status) VALUES (?, ?, ?, ?)',
        [funcionario_id, data_inicio, data_fim, 'Pendente'],
        (err, result) => {
            if (err) {
                res.status(500).send(err);
            } else {
                res.json({ message: 'Solicitação de férias criada com sucesso!' });
            }
        }
    );
});

// Rota para aprovar ou recusar uma solicitação
app.put('/ferias/:id', (req, res) => {
    const { status } = req.body;
    const id = req.params.id;
    db.query(
        'UPDATE ferias SET status = ? WHERE id = ?',
        [status, id],
        (err, result) => {
            if (err) {
                res.status(500).send(err);
            } else {
                res.json({ message: 'Status atualizado com sucesso!' });
            }
        }
    );
});

// Rota para cadastrar um novo funcionário <!-- Concluido, gustavo avaliar e fazer alterações necessarias -->
app.post('/cadastro', (req, res) => {
    const { email, password, nome, cpf, cargo, setor, data_admissao } = req.body;

    // Validação de campos obrigatórios
    if (!email || !password || !nome || !cpf || !cargo || !setor || !data_admissao) {
        return res.status(400).json({ erro: 'Todos os campos são obrigatórios!' });
    }

    // Verificação se o e-mail já existe
    const emailExistente = usuarios.find(usuario => usuario.email === email);
    if (emailExistente) {
        return res.status(400).json({ erro: 'E-mail já cadastrado!' });
    }

    // Validação de CPF
    const cpfValido = /[0-9]{3}\.[0-9]{3}\.[0-9]{3}-[0-9]{2}/.test(cpf);
    if (!cpfValido) {
        return res.status(400).json({ erro: 'CPF inválido!' });
    }

    // Criptografia da senha
    const senhaCriptografada = bcrypt.hashSync(password, 10);
    const novoUsuario = { email, senha: senhaCriptografada, nome, cpf, cargo, setor, data_admissao };
    usuarios.push(novoUsuario);

    return res.status(201).json({ mensagem: 'Usuário cadastrado com sucesso!' });
});

// Rota para login de usuários <!-- Concluido, gustavo avaliar e fazer alterações necessarias -->
app.post('/login', (req, res) => {
    const { email, password } = req.body;

    const usuario = usuarios.find(u => u.email === email);

    if (!usuario) {
        return res.status(401).json({ success: false, message: 'E-mail não encontrado!' });
    }

    const senhaValida = bcrypt.compareSync(password, usuario.senha);

    if (!senhaValida) {
        return res.status(401).json({ success: false, message: 'Senha incorreta!' });
    }

    return res.json({ success: true, message: 'Login bem-sucedido!' });
});

// Rota para solicitar recuperação de senha <!-- Concluido, gustavo avaliar e fazer alterações necessarias -->
app.post('/solicitar_codigo', (req, res) => {
    const { email } = req.body;

    const usuario = usuarios.find(u => u.email === email);

    if (!usuario) {
        return res.status(404).json({ erro: 'E-mail não encontrado' });
    }

    const codigoRecuperacao = crypto.randomInt(100000, 999999);
    
    enviarEmailRecuperacao(email, codigoRecuperacao);

    usuario.codigoRecuperacao = codigoRecuperacao;
    usuario.expiracaoCodigo = Date.now() + 15 * 60 * 1000;

    return res.status(200).json({ mensagem: 'Código de recuperação enviado!' });
});

// Rota para redefinir senha <!-- Concluido, gustavo avaliar e fazer alterações necessarias -->
app.post('/redefinir_senha', (req, res) => {
    const { email, codigoRecuperacao, novaSenha } = req.body;

    const usuario = usuarios.find(u => u.email === email);

    if (!usuario) {
        return res.status(404).json({ erro: 'E-mail não encontrado' });
    }

    if (usuario.codigoRecuperacao !== codigoRecuperacao || Date.now() > usuario.expiracaoCodigo) {
        return res.status(400).json({ erro: 'Código de recuperação inválido ou expirado' });
    }

    usuario.senha = bcrypt.hashSync(novaSenha, 10);
    usuario.codigoRecuperacao = undefined;
    usuario.expiracaoCodigo = undefined;

    return res.status(200).json({ mensagem: 'Senha redefinida com sucesso!' });
});



// Iniciar servidor
app.listen(porta, () => {
    console.log(`Servidor rodando em http://localhost:${porta}`);
});
