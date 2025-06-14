package br.ucb.teste.software.gerenciadorfuncionarios.auth.configs; // Ajuste o pacote conforme o caminho

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Indica que esta classe fornece beans de configuração
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica a todas as rotas (endpoints) da sua API
                // Permite requisições do seu frontend (ajuste conforme necessário)
                .allowedOrigins("http://localhost:3000", "http://191.176.37.36:3000", "http://200.101.63.114:3000") // <--- ADICIONE AS ORIGENS DO SEU FRONTEND
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos HTTP permitidos
                .allowedHeaders("*") // Permite todos os cabeçalhos
                .allowCredentials(true); // Permite o envio de cookies/credenciais
    }
}