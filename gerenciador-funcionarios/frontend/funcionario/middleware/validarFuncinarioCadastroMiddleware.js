module.exports = (req, res, next) => {
    const { nome, email, senha, confirma_senha, cpf, localidade, dataNascimento} = req.body;


    if (!nome || !email || !cpf || !dataNascimento || !localidade || !dataNascimento) {
        return res.status(400).render('cadastro_funcionario', {
            error: 'Todos os campos são obrigatórios.',
            success: null
        });
    }

    // Validação simples de CPF
    const cpfRegex = /^\d{3}\.\d{3}\.\d{3}-\d{2}$/;
    if (!cpfRegex.test(cpf)) {
        return res.status(400).render('cadastro_funcionario', {
            error: 'CPF inválido. Use o formato 000.000.000-00.',
            success: null
        });
    }

    const numeros = cpf.replace(/[.-]/g, '');
    if (/^(\d)\1{10}$/.test(numeros)) {
        return res.status(400).render('cadastro_funcionario', {
            error: 'CPF inválido: todos os dígitos são iguais.',
            success: null

        });
    }

    next();
};