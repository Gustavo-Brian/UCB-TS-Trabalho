import React from "react";
import useRegister from "./useRegister";
import RegisterForm from "./RegisterForm";

export default function Register() {
  const register = useRegister();

  return <RegisterForm {...register} />;
}
