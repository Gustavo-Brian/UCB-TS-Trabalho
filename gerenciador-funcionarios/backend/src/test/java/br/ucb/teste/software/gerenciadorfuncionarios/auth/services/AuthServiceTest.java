package br.ucb.teste.software.gerenciadorfuncionarios.auth.services;

import br.ucb.teste.software.gerenciadorfuncionarios.auth.dtos.AuthResponse;
import br.ucb.teste.software.gerenciadorfuncionarios.auth.dtos.LoginRequest;
import br.ucb.teste.software.gerenciadorfuncionarios.auth.dtos.RegisterRequest;
import br.ucb.teste.software.gerenciadorfuncionarios.auth.entities.User;
import br.ucb.teste.software.gerenciadorfuncionarios.auth.enums.Role;
import br.ucb.teste.software.gerenciadorfuncionarios.auth.exceptions.UsernameAlreadyExistsException;
import br.ucb.teste.software.gerenciadorfuncionarios.auth.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("AuthService Unit Tests")
class AuthServiceTest {

    @Mock private UserRepository userRepository;
    @Mock private PasswordEncoder passwordEncoder;
    @Mock private JwtService jwtService;
    @Mock private AuthenticationManager authenticationManager;

    @InjectMocks private AuthService authService;

    @BeforeEach
    void setUp() {
        SecurityContextHolder.clearContext();
    }

    // Método auxiliar para criar authorities
    private List<GrantedAuthority> createAuthorities(Role role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.name()));
        return authorities;
    }

    @Test
    @DisplayName("Should register first user as ROLE_ADMIN_ROOT when no root admin exists")
    void register_firstUser_asAdminRoot_success() {
        RegisterRequest request = new RegisterRequest("newadmin", "password123");
        when(userRepository.findByUsername(request.getUsername())).thenReturn(Optional.empty());
        when(userRepository.existsByRole(Role.ROLE_ADMIN_ROOT)).thenReturn(false);
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");
        when(jwtService.generateToken(any())).thenReturn("jwt.token");

        AuthResponse response = authService.register(request);

        assertNotNull(response.getToken());
        verify(userRepository).save(argThat(user ->
                user.getRole() == Role.ROLE_ADMIN_ROOT));
    }

    @Test
    @DisplayName("Should register new ROLE_ADMIN by an authenticated ROLE_ADMIN_ROOT")
    void register_newAdmin_byAuthenticatedAdminRoot_success() {
        // Configuração do contexto de segurança
        Authentication auth = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);

        when(auth.isAuthenticated()).thenReturn(true);
        when(auth.getAuthorities()).thenAnswer(inv -> createAuthorities(Role.ROLE_ADMIN_ROOT));
        when(securityContext.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(securityContext);

        // Teste principal
        RegisterRequest request = new RegisterRequest("newadmin", "password123");
        when(userRepository.findByUsername(request.getUsername())).thenReturn(Optional.empty());
        when(userRepository.existsByRole(Role.ROLE_ADMIN_ROOT)).thenReturn(true);
        when(passwordEncoder.encode(anyString())).thenReturn("encoded");
        when(jwtService.generateToken(any())).thenReturn("jwt.token");

        AuthResponse response = authService.register(request);

        assertNotNull(response.getToken());
        verify(userRepository).save(argThat(user ->
                user.getRole() == Role.ROLE_ADMIN));
    }

    @Test
    @DisplayName("Should throw UsernameAlreadyExistsException if username already exists")
    void register_usernameAlreadyExists_throwsException() {
        RegisterRequest request = new RegisterRequest("existing", "password");
        when(userRepository.findByUsername(request.getUsername())).thenReturn(Optional.of(new User()));

        assertThrows(UsernameAlreadyExistsException.class, () ->
                authService.register(request));
    }

    @Test
    @DisplayName("Should throw SecurityException when non-ROLE_ADMIN_ROOT tries to register admin")
    void register_nonAdminRootTriesToRegister_throwsSecurityException() {
        // Configura contexto com ROLE_ADMIN normal
        Authentication auth = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);

        when(auth.isAuthenticated()).thenReturn(true);
        when(auth.getAuthorities()).thenAnswer(inv -> createAuthorities(Role.ROLE_ADMIN));
        when(securityContext.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(securityContext);

        RegisterRequest request = new RegisterRequest("newadmin", "password");
        when(userRepository.existsByRole(Role.ROLE_ADMIN_ROOT)).thenReturn(true);

        assertThrows(SecurityException.class, () ->
                authService.register(request));
    }

    @Test
    @DisplayName("Should successfully login a user and return a JWT token")
    void login_success() {
        LoginRequest request = new LoginRequest("user", "pass");
        User user = new User(1L, "user", "encoded", Role.ROLE_ADMIN);

        when(authenticationManager.authenticate(any())).thenReturn(mock(Authentication.class));
        when(userRepository.findByUsername(request.getUsername())).thenReturn(Optional.of(user));
        when(jwtService.generateToken(user)).thenReturn("jwt.token");

        AuthResponse response = authService.login(request);

        assertNotNull(response.getToken());
    }

    @Test
    @DisplayName("Should throw BadCredentialsException when login fails")
    void login_invalidCredentials_throwsBadCredentialsException() {
        LoginRequest request = new LoginRequest("user", "wrong");
        when(authenticationManager.authenticate(any()))
                .thenThrow(new BadCredentialsException("Invalid"));

        assertThrows(BadCredentialsException.class, () ->
                authService.login(request));
    }
}