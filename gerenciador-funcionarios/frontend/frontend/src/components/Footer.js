import React from "react";
import "../styles/layout.css";

export default function Footer() {
  return (
    <footer className="layout-footer">
      <p>&copy; {new Date().getFullYear()} Gerenciador de Funcionários</p>
    </footer>
  );
}
