package br.ucb.teste.software.gerenciadorfuncionarios.auth.controllers;

import br.ucb.teste.software.gerenciadorfuncionarios.auth.dtos.LoginRequest;
import br.ucb.teste.software.gerenciadorfuncionarios.auth.dtos.AuthResponse;
import br.ucb.teste.software.gerenciadorfuncionarios.auth.dtos.RegisterRequest;
import br.ucb.teste.software.gerenciadorfuncionarios.auth.exceptions.UsernameAlreadyExistsException;
import br.ucb.teste.software.gerenciadorfuncionarios.auth.services.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

// Anotação para estender com o Mockito para habilitar a injeção de mocks.
@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    // Cria um mock da classe AuthService, que é uma dependência do AuthController.
    @Mock
    private AuthService authService;

    // Injeta os mocks criados (authService) no AuthController.
    // Isso cria uma instância real de AuthController e injeta as dependências mockadas.
    @InjectMocks
    private AuthController authController;

    // Método executado antes de cada teste. Pode ser usado para configurações iniciais,
    // embora para este caso específico, as anotações @Mock e @InjectMocks já lidem com a inicialização.
    @BeforeEach
    void setUp() {
        // Nada a fazer aqui, MockitoExtension cuida da inicialização.
    }

    // --- Testes para o endpoint /auth/register ---

    @Test
    @DisplayName("Deve registrar um novo usuário com sucesso e retornar 201 Created")
    void register_Success_ReturnsCreated() {
        // Cenário: Requisição de registro válida.
        // O construtor de RegisterRequest agora aceita apenas username e password.
        RegisterRequest request = new RegisterRequest("novoUsuario", "senha123");
        // Cenário: Resposta esperada do serviço.
        AuthResponse expectedResponse = new AuthResponse("jwtToken123");

        // Mocking: Quando authService.register() for chamado com qualquer RegisterRequest,
        // ele deve retornar a expectedResponse.
        when(authService.register(any(RegisterRequest.class))).thenReturn(expectedResponse);

        // Ação: Chama o método register do controlador.
        ResponseEntity<AuthResponse> responseEntity = authController.register(request);

        // Asserções:
        // Verifica se o status HTTP retornado é 201 Created.
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        // Verifica se o corpo da resposta não é nulo.
        assertNotNull(responseEntity.getBody());
        // Verifica se o corpo da resposta é o esperado.
        assertEquals(expectedResponse, responseEntity.getBody());
        // Verifica se o método register do authService foi chamado exatamente uma vez.
        verify(authService, times(1)).register(request);
    }

    @Test
    @DisplayName("Deve retornar 409 Conflict quando o nome de usuário já existe no registro")
    void register_UsernameAlreadyExists_ReturnsConflict() {
        // Cenário: Requisição de registro com nome de usuário já existente.
        // O construtor de RegisterRequest agora aceita apenas username e password.
        RegisterRequest request = new RegisterRequest("usuarioExistente", "senhaExistente");

        // Mocking: Quando authService.register() for chamado, ele deve lançar uma
        // UsernameAlreadyExistsException.
        when(authService.register(any(RegisterRequest.class)))
                .thenThrow(new UsernameAlreadyExistsException("Usuário já existe"));

        // Ação: Chama o método register do controlador.
        ResponseEntity<AuthResponse> responseEntity = authController.register(request);

        // Asserções:
        // Verifica se o status HTTP retornado é 409 Conflict.
        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
        // Verifica se o corpo da resposta é nulo, como esperado para um conflito.
        assertEquals(null, responseEntity.getBody());
        // Verifica se o método register do authService foi chamado exatamente uma vez.
        verify(authService, times(1)).register(request);
    }

    @Test
    @DisplayName("Deve retornar 403 Forbidden quando a criação de administrador não é autorizada no registro")
    void register_SecurityException_ReturnsForbidden() {
        // Cenário: Uma tentativa que levaria a uma SecurityException no serviço.
        // O construtor de RegisterRequest agora aceita apenas username e password.
        RegisterRequest request = new RegisterRequest("adminUser", "adminPass");

        // Mocking: Quando authService.register() for chamado, ele deve lançar uma SecurityException.
        when(authService.register(any(RegisterRequest.class)))
                .thenThrow(new SecurityException("Ação não autorizada"));

        // Ação: Chama o método register do controlador.
        ResponseEntity<AuthResponse> responseEntity = authController.register(request);

        // Asserções:
        // Verifica se o status HTTP retornado é 403 Forbidden.
        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
        // Verifica se o corpo da resposta é nulo.
        assertEquals(null, responseEntity.getBody());
        // Verifica se o método register do authService foi chamado exatamente uma vez.
        verify(authService, times(1)).register(request);
    }

    @Test
    @DisplayName("Deve retornar 500 Internal Server Error para outras exceções no registro")
    void register_GenericException_ReturnsInternalServerError() {
        // Cenário: Uma exceção inesperada ocorre durante o registro.
        // O construtor de RegisterRequest agora aceita apenas username e password.
        RegisterRequest request = new RegisterRequest("qualquerUsuario", "qualquerSenha");

        // Mocking: Quando authService.register() for chamado, ele deve lançar uma RuntimeException genérica.
        when(authService.register(any(RegisterRequest.class)))
                .thenThrow(new RuntimeException("Erro inesperado no serviço"));

        // Ação: Chama o método register do controlador.
        ResponseEntity<AuthResponse> responseEntity = authController.register(request);

        // Asserções:
        // Verifica se o status HTTP retornado é 500 Internal Server Error.
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        // Verifica se o corpo da resposta é nulo.
        assertEquals(null, responseEntity.getBody());
        // Verifica se o método register do authService foi chamado exatamente uma vez.
        verify(authService, times(1)).register(request);
    }

    // --- Testes para o endpoint /auth/login ---

    @Test
    @DisplayName("Deve autenticar um usuário com sucesso e retornar 200 OK")
    void login_Success_ReturnsOk() {
        // Cenário: Requisição de login válida.
        LoginRequest request = new LoginRequest("usuario", "senha");
        // Cenário: Resposta esperada do serviço após login.
        AuthResponse expectedResponse = new AuthResponse("loggedInJwtToken");

        // Mocking: Quando authService.login() for chamado com qualquer LoginRequest,
        // ele deve retornar a expectedResponse.
        when(authService.login(any(LoginRequest.class))).thenReturn(expectedResponse);

        // Ação: Chama o método login do controlador.
        ResponseEntity<AuthResponse> responseEntity = authController.login(request);

        // Asserções:
        // Verifica se o status HTTP retornado é 200 OK.
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        // Verifica se o corpo da resposta não é nulo.
        assertNotNull(responseEntity.getBody());
        // Verifica se o corpo da resposta é o esperado.
        assertEquals(expectedResponse, responseEntity.getBody());
        // Verifica se o método login do authService foi chamado exatamente uma vez.
        verify(authService, times(1)).login(request);
    }

    @Test
    @DisplayName("Deve retornar 401 Unauthorized para falha de autenticação no login")
    void login_AuthenticationFailure_ReturnsUnauthorized() {
        // Cenário: Requisição de login com credenciais inválidas.
        LoginRequest request = new LoginRequest("usuarioInvalido", "senhaInvalida");

        // Mocking: Quando authService.login() for chamado, ele deve lançar uma RuntimeException
        // (simulando falha de autenticação).
        when(authService.login(any(LoginRequest.class)))
                .thenThrow(new RuntimeException("Credenciais inválidas"));

        // Ação: Chama o método login do controlador.
        ResponseEntity<AuthResponse> responseEntity = authController.login(request);

        // Asserções:
        // Verifica se o status HTTP retornado é 401 Unauthorized.
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        // Verifica se o corpo da resposta é nulo.
        assertEquals(null, responseEntity.getBody());
        // Verifica se o método login do authService foi chamado exatamente uma vez.
        verify(authService, times(1)).login(request);
    }
}