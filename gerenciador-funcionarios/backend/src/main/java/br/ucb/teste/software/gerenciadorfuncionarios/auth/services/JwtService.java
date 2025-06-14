package br.ucb.teste.software.gerenciadorfuncionarios.auth.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

// Serviço para operações relacionadas a JWT (JSON Web Tokens).
@Service
public class JwtService {

    // A chave secreta para assinar e verificar os tokens JWT.
    // É injetada do arquivo de propriedades (application.properties/yml).
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    // Tempo de expiração do token JWT em milissegundos.
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

    // Extrai o nome de usuário (subject) do token JWT.
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extrai uma claim específica do token JWT usando um resolvedor de função.
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token); // Extrai todas as claims do token.
        return claimsResolver.apply(claims); // Aplica o resolvedor para obter a claim desejada.
    }

    // Gera um token JWT para um UserDetails.
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails); // Gera o token sem claims extras.
    }

    // Gera um token JWT com claims extras e para um UserDetails.
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return buildToken(extraClaims, userDetails, jwtExpiration); // Constrói o token com tempo de expiração.
    }

    // Constrói o token JWT.
    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims) // Adiciona as claims extras.
                .setSubject(userDetails.getUsername()) // Define o nome de usuário como subject.
                .setIssuedAt(new Date(System.currentTimeMillis())) // Define a data de emissão.
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // Define a data de expiração.
                .signWith(getSignInKey(), SignatureAlgorithm.HS256) // Assina o token com a chave e algoritmo HS256.
                .compact(); // Compacta o token em uma string.
    }

    // Valida um token JWT em relação aos detalhes do usuário.
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token); // Extrai o nome de usuário do token.
        // Verifica se o nome de usuário corresponde e se o token não expirou.
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    // Verifica se o token JWT expirou.
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date()); // Compara a data de expiração com a data atual.
    }

    // Extrai a data de expiração do token JWT.
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extrai todas as claims do token JWT.
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey()) // Define a chave de assinatura para o parser.
                .build()
                .parseClaimsJws(token) // Faz o parse do token.
                .getBody(); // Retorna o corpo das claims.
    }

    // Retorna a chave de assinatura decodificada.
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey); // Decodifica a chave secreta de Base64.
        return Keys.hmacShaKeyFor(keyBytes); // Gera uma chave HMAC a partir dos bytes.
    }
}