import { useState } from "react";
import api from "../../services/api";

export default function useRegister() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError(null);

    try {
      // Esta requisição agora irá para http://191.176.37.36:19132/auth/register
      const response = await api.post("/auth/register", { username, password });
      alert("Registro realizado com sucesso!");
      // Você pode fazer algo com a resposta, como response.data.token, se houver
      console.log("Resposta do registro:", response.data);
      // Redirecionar ou limpar campos, se quiser
      setUsername(""); // Exemplo: Limpa o campo de usuário
      setPassword(""); // Exemplo: Limpa o campo de senha

    } catch (err) {
      // Melhorar o tratamento de erros para dar feedback mais específico
      if (err.response) {
        // O servidor respondeu com um status diferente de 2xx
        console.error("Erro na resposta do servidor:", err.response.data);
        setError(err.response.data.message || "Erro no registro. Verifique os dados e tente novamente.");
      } else if (err.request) {
        // A requisição foi feita, mas nenhuma resposta foi recebida
        console.error("Nenhuma resposta recebida:", err.request);
        setError("Erro de rede. Verifique sua conexão ou o endereço do servidor.");
      } else {
        // Algo aconteceu na configuração da requisição que disparou um erro
        console.error("Erro na configuração da requisição:", err.message);
        setError("Erro inesperado no registro.");
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
    loading,
    error,
    handleSubmit,
  };
}
