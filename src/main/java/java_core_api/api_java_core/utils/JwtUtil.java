package java_core_api.api_java_core.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java_core_api.api_java_core.domain.Usuario;

import java.util.Date;
import java.security.Key;

public class JwtUtil {

    private static final String SECRET = "minha-super-chave-secreta-jwt-de-32-caracteres";
    private static final Key SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    public static String generateToken(Usuario usuario) {
        return Jwts.builder()
                .setSubject(String.valueOf(usuario.getId()))
                .claim("email", usuario.getEmail())
                .claim("role", usuario.getRole())
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 30)) // 30 days
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }
}
