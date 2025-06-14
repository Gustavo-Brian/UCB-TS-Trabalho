import { useState, useEffect } from "react";
import api from "../../services/api"; // caminho correto do seu arquivo api.js

const URL_BASE = "/api/funcionarios"; // baseURL já está configurada no axios

export function useFuncionarios() {
  const [funcionarios, setFuncionarios] = useState([]);
  const [carregando, setCarregando] = useState(false);
  const [erro, setErro] = useState(null);

  const listarFuncionarios = async () => {
    setCarregando(true);
    try {
      const resposta = await api.get(URL_BASE);
      setFuncionarios(resposta.data);
      setErro(null);
    } catch (e) {
      setErro("Erro ao carregar funcionários");
    } finally {
      setCarregando(false);
    }
  };

  const criarFuncionario = async (dadosFuncionario) => {
    setCarregando(true);
    try {
      const resposta = await api.post(URL_BASE, dadosFuncionario);
      setFuncionarios((prev) => [...prev, resposta.data]);
      setErro(null);
    } catch (e) {
      setErro("Erro ao criar funcionário");
    } finally {
      setCarregando(false);
    }
  };

  const alterarFuncionario = async (id, dadosFuncionario) => {
    setCarregando(true);
    try {
      const resposta = await api.put(`${URL_BASE}/${id}`, dadosFuncionario);
      setFuncionarios((prev) =>
        prev.map((f) => (f.id === id ? resposta.data : f))
      );
      setErro(null);
    } catch (e) {
      setErro("Erro ao alterar funcionário");
    } finally {
      setCarregando(false);
    }
  };

  const desativarFuncionario = async (id) => {
    setCarregando(true);
    try {
      const funcionario = funcionarios.find((f) => f.id === id);
      if (!funcionario) {
        setErro("Funcionário não encontrado");
        setCarregando(false);
        return;
      }
      const dadosAtualizados = { ...funcionario, setAtivo: false };
      const resposta = await api.put(`${URL_BASE}/${id}`, dadosAtualizados);

      setFuncionarios((prev) =>
        prev.map((f) => (f.id === id ? resposta.data : f))
      );
      setErro(null);
    } catch (e) {
      setErro("Erro ao desativar funcionário");
    } finally {
      setCarregando(false);
    }
  };

  useEffect(() => {
    listarFuncionarios();
  }, []);

  return {
    funcionarios,
    carregando,
    erro,
    criarFuncionario,
    alterarFuncionario,
    desativarFuncionario,
  };
}
