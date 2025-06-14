import "../../styles/auth.css";

export default function RegisterForm({
  username,
  password,
  setUsername,
  setPassword,
  loading,
  error,
  handleSubmit,
}) {
  return (
    <form onSubmit={handleSubmit} className="auth-form">
      <h1 className="auth-title">Registro</h1>

      <input
        type="text"
        placeholder="Usuário"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
        className="auth-input"
        disabled={loading}
      />

      <input
        type="password"
        placeholder="Senha"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        className="auth-input"
        disabled={loading}
      />

      {error && <p className="auth-error">{error}</p>}

      <button
        type="submit"
        className="auth-button"
        disabled={loading}
      >
        {loading ? "Registrando..." : "Registrar"}
      </button>
    </form>
  );
}
