import { useState } from "react";
import api from "../../services/api"; // Confirme o caminho
import { useNavigate } from "react-router-dom"; // Importa o hook

export default function useLogin() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [successMessage, setSuccessMessage] = useState("");

  const navigate = useNavigate(); // Inicializa o navigate

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError(null);
    setSuccessMessage("");

    try {
      const response = await api.post("/auth/login", { username, password });
      const token = response.data.token;

      if (token) {
        localStorage.setItem("jwt_token", token);
        setSuccessMessage("Login bem-sucedido! Token salvo.");

        // Redireciona para a página de funcionários
        navigate("/gestao-funcionarios");

        // Limpa os campos
        setUsername("");
        setPassword("");
      } else {
        setError("M003: Erro inesperado. Favor entrar em contato com o administrador do sistema. Tente novamente mais tarde.");
      }
    } catch (err) {
      if (err.response) {
        if (err.response.status === 401) {
          setError("M001: Usuário ou senha digitados estão inválidos! Tente novamente.");
        } else if (err.response.data && err.response.data.message) {
          setError(`M003: Erro inesperado: ${err.response.data.message}. Favor entrar em contato com o administrador do sistema.`);
        } else {
          setError("M003: Erro inesperado. Favor entrar em contato com o administrador do sistema. Tente novamente mais tarde.");
        }
      } else if (err.request) {
        setError("M014: Não foi possível conectar ao servidor. Verifique sua conexão.");
      } else {
        setError("M003: Ocorreu um erro inesperado. Tente novamente.");
      }
    } finally {
      setLoading(false);
    }
  };

  return {
    username,
    setUsername,
    password,
    setPassword,
    handleSubmit,
    loading,
    error,
    successMessage,
  };
}
