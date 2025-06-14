import "../../styles/auth.css";

export default function LoginForm({
  username,
  password,
  setUsername,
  setPassword,
  loading,
  error,
  successMessage,
  handleSubmit,

}) {
  return (
    <form onSubmit={handleSubmit} className="auth-form">
      <h1 className="auth-title">Login</h1>

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
      {successMessage && <p className="auth-success">{successMessage}</p>}

      <button
        type="submit"
        className="auth-button"
        disabled={loading}
      >
        {loading ? "Entrando..." : "Entrar"}
      </button>
    </form>
  );
}
