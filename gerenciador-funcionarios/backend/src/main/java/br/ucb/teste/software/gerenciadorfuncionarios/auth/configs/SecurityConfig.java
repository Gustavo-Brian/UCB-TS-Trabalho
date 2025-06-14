package br.ucb.teste.software.gerenciadorfuncionarios.auth.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// Classe de configuração para a segurança da aplicação.
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    // Construtor com injeção de dependências.
    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter, AuthenticationProvider authenticationProvider) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationProvider = authenticationProvider;
    }

    // Define a cadeia de filtros de segurança HTTP.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desabilita a proteção CSRF (comum para APIs RESTful).
                .authorizeHttpRequests(auth -> auth
                        // Permite acesso público ao endpoint de registro (/auth/register).
                        .requestMatchers("/auth/register", "/auth/login").permitAll()
                        // Todas as outras requisições requerem autenticação.
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        // Define a política de criação de sessão como STATELESS (sem estado),
                        // pois usamos tokens JWT para autenticação.
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider) // Define o provedor de autenticação.
                // Adiciona o filtro JWT antes do filtro de autenticação de nome de usuário e senha.
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build(); // Constrói e retorna a cadeia de filtros de segurança.
    }
}