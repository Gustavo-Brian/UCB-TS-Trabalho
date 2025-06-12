
const express = require('express');
const router = express.Router();
const funcionarioService = require('../services/funcionariosService');
const validarFuncionarioCadastroMiddleware = require('../middleware/validarFuncinarioCadastroMiddleware');

// Rota para renderizar a página de cadastro
router.get('/cadastrar', (req, res) => {
  // Renderiza o arquivo EJS com mensagens iniciais
  res.render('cadastro_funcionario', {
    error: null, // Nenhuma mensagem de erro inicial
    success: null, // Nenhuma mensagem de sucesso inicial
  });
});

router.post('/cadastrar',validarFuncionarioCadastroMiddleware, async (req, res) => {

  try {

    await funcionarioService.cadastrarFuncionario(req.body)


    res.render('cadastro_funcionario', {
      success: 'Funcionário cadastrado com sucesso!',
      error: null
    });

  } catch (error) {
    console.error("Erro ao enviar para o backend:", error.response?.data || error.message);

    res.render('cadastro_funcionario', {
      error: 'Erro ao cadastrar funcionário.',
      success: null
    });
  }
});

// Exporta o router para uso em outros arquivos
module.exports = router;
