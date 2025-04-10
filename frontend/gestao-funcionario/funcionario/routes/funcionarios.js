const express = require('express');
const router = express.Router();
const funcionarioService = require('../services/funcionariosService');



// Rota GET /geralroutes
router.get('/', async (req, res) => {
    try {


        const funcionarios = await funcionarioService.buscarFuncionarios();

        // Renderiza a view com os dados
        res.render('funcionario-consultado', { funcionarios});

    } catch (error) {
        console.error('Erro ao buscar dados:', error.message);
        if (error.response) {
            console.error('Status:', error.response.status);
            console.error('Dados:', error.response.data);
        }
        res.render('error', {
            message: 'Erro ao buscar dados dos funcionários',
            error
        });
    }
});

module.exports = router;
