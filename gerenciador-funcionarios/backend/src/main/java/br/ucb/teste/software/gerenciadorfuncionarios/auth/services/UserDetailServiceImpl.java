package br.ucb.teste.software.gerenciadorfuncionarios.auth.services;

import br.ucb.teste.software.gerenciadorfuncionarios.auth.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// Implementação do UserDetailsService do Spring Security para carregar detalhes do usuário.
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    // Injeção de dependência do UserRepository.
    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Carrega os detalhes do usuário pelo nome de usuário.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca o usuário no banco de dados. Se não encontrar, lança uma exceção.
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
    }
}