package br.ucb.teste.software.gerenciadorfuncionarios.auth.configs;

import br.ucb.teste.software.gerenciadorfuncionarios.auth.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// Classe de configuração para beans relacionados à segurança e autenticação.
@Configuration
public class ApplicationConfig {

    private final UserRepository userRepository;

    // Construtor com injeção de dependência do UserRepository.
    public ApplicationConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Define um bean para o UserDetailsService, que carrega detalhes do usuário.
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
    }

    // Define um bean para o AuthenticationProvider, que cuida da autenticação.
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService()); // Define o UserDetailsService.
        authProvider.setPasswordEncoder(passwordEncoder()); // Define o PasswordEncoder.
        return authProvider;
    }

    // Define um bean para o AuthenticationManager, que gerencia o processo de autenticação.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // Define um bean para o PasswordEncoder, que é usado para criptografar senhas.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Usa BCrypt para criptografia de senhas.
    }
}