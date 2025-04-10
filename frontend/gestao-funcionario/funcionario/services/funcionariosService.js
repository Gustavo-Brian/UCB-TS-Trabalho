const axios = require('axios');
require('dotenv').config();
const apiUrl = process.env.API_JAVA_URL;

async function buscarFuncionarios() {
    try {
        const response = await axios.get(`${apiUrl}/funcionarios`);
        return response.data.funcionarios; // ou ajuste conforme o backend
    } catch (error) {
        throw error;
    }
}

async function buscarFuncionarioEmail(email) {
    try {
        const response = await axios.get(`${apiUrl}/funcionarios/${email}`);
        return response.data;

    } catch (error) {
        throw error;
    }
}

async function cadastrarFuncionario(dados) {
    try {
        const response = await axios.post(`${apiUrl}/funcionarios`, dados);
        return response.data;

    } catch (error) {
        throw error;
    }
}



module.exports = { buscarFuncionarios, buscarFuncionarioEmail, cadastrarFuncionario };