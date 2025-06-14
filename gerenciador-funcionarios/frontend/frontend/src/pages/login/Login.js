import React from "react";
import useLogin from "./useLogin";
import LoginForm from "./LoginForm";

export default function Login() {
  const login = useLogin();

  return <LoginForm {...login} />;
}
