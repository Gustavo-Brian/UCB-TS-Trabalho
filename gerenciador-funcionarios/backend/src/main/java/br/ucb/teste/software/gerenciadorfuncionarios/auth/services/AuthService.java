package br.ucb.teste.software.gerenciadorfuncionarios.auth.services;

import br.ucb.teste.software.gerenciadorfuncionarios.auth.dtos.LoginRequest;
import br.ucb.teste.software.gerenciadorfuncionarios.auth.dtos.AuthResponse;
import br.ucb.teste.software.gerenciadorfuncionarios.auth.dtos.RegisterRequest;
import br.ucb.teste.software.gerenciadorfuncionarios.auth.entities.User;
import br.ucb.teste.software.gerenciadorfuncionarios.auth.enums.Role;
import br.ucb.teste.software.gerenciadorfuncionarios.auth.exceptions.UsernameAlreadyExistsException;
import br.ucb.teste.software.gerenciadorfuncionarios.auth.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// Serviço para a lógica de negócio de autenticação e registro de usuários.
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    // Construtor com injeção de dependências.
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    // Método para registrar um novo usuário.
    public AuthResponse register(RegisterRequest request) {
        // Verifica se já existe um usuário com o nome de usuário fornecido.
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("Usuário '" + request.getUsername() + "' já existe.");
        }

        Role roleToAssign;
        // Lógica para atribuir o papel (ROLE_ADMIN_ROOT ou ROLE_ADMIN).
        // Se não houver nenhum ROLE_ADMIN_ROOT, o primeiro usuário será ROLE_ADMIN_ROOT.
        if (!userRepository.existsByRole(Role.ROLE_ADMIN_ROOT)) {
            roleToAssign = Role.ROLE_ADMIN_ROOT;
        } else {
            // Para usuários subsequentes, verifica se o usuário autenticado é ROLE_ADMIN_ROOT.
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated() || authentication.getAuthorities().stream()
                    .noneMatch(a -> a.getAuthority().equals(Role.ROLE_ADMIN_ROOT.name()))) {
                throw new SecurityException("Apenas usuários ROLE_ADMIN_ROOT podem criar novos administradores.");
            }
            roleToAssign = Role.ROLE_ADMIN;
        }

        // Cria um novo objeto User.
        var user = new User(
                request.getUsername(),
                passwordEncoder.encode(request.getPassword()), // Criptografa a senha.
                roleToAssign
        );
        userRepository.save(user); // Salva o usuário no banco de dados.

        // Gera um token JWT para o novo usuário.
        var jwtToken = jwtService.generateToken(user);
        return new AuthResponse(jwtToken); // Retorna o token.
    }

    // Método para autenticar um usuário existente.
    public AuthResponse login(LoginRequest request) {
        // Tenta autenticar o usuário com as credenciais fornecidas.
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        // Se a autenticação for bem-sucedida, busca o usuário e gera um JWT.
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado.")); // Esta exceção não deve ser alcançada se a autenticação foi bem-sucedida.

        var jwtToken = jwtService.generateToken(user); // Gera o token JWT.
        return new AuthResponse(jwtToken); // Retorna o token.
    }
}