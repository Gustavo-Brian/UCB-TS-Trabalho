import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "../styles/layout.css";

export default function Header() {
  const navigate = useNavigate();
  const [logado, setLogado] = useState(false);

  useEffect(() => {
    const token = localStorage.getItem("jwt_token");
    setLogado(!!token);
  }, []);

  const handleLogout = () => {
    localStorage.removeItem("jwt_token");
    setLogado(false);
    navigate("/login");
  };

  return (
    <header className="layout-header">
      <h1 className="logo">Gerenciador</h1>
      <nav>
        {logado && (
          <>
            <Link to="/gestao-funcionarios" className="layout-link">
              Gestão de Funcionários
            </Link>
            <button onClick={handleLogout} className="layout-link logout-button">
              Logout
            </button>
          </>
        )}
        {!logado && (
          <>
            <Link to="/" className="layout-link">Início</Link>
            <Link to="/login" className="layout-link">Login</Link>
            <Link to="/register" className="layout-link">Registrar</Link>
          </>
        )}
      </nav>
    </header>
  );
}
