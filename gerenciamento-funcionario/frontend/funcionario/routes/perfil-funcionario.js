const express = require('express');
const router = express.Router();
const funcionarioService = require('../services/funcionariosService');

// Rota para visualizar o perfil completo de um funcionário
router.get('/:email/perfil', async (req, res) => {
    try {
        const funcionario =await funcionarioService.buscarFuncionarioEmail(req.params.email);

        // Renderiza a página com os dados recebidos
        res.render('perfil-funcionario', { funcionario });

    } catch (error) {
        console.error('Erro ao carregar perfil do funcionário:', error.message);

        // Renderiza uma página de erro customizada
        res.render('error', {
            message: 'Erro ao carregar perfil do funcionário',
            error
        });
    }
});

module.exports = router;
