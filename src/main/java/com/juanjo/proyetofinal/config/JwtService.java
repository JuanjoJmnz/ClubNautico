package com.juanjo.proyetofinal.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    // Key secreta
    private static final String SECRET_KEY = "f477da0311f75064423ba41026be3375bc88c1219feb6638640286ad00bb7dc58ef3ffcf0462075edbf3430cae98883d9e40ac96bdf816059ed167b51b4045301c51ec13eb029492e029e74a9e53d85d82f82d79a4c9b7e4dd0762494df4b8ea6eca8e4dd279d5b2bee46f01063e0c710171e3b86eaefaca6e2e7e7cdf75aa3fb1e2b898f7fca85c6b3c1f7d6c43cfaa93822b6c78be86afe88410a4243d505ea3e0e20ecc69e4bddb50d522b27a1528a8857ddb4d535fa25419beade16d5bf6d9bf6293ad467396ab7cffe71c85e83950df5f8d5ef32bcc5e0a1b58da0c502f83a2f0c0dc0ac85934b3dc13e1fe9837af40c5c4821cdcb2092e85327d08acb6";

    // Generamos un token JWT sin claims adicionales.
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    // Generamos un token JWT con claims adicionales.
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Extrae el nombre de usuario del token.
    public String getUserName(String token) {
        return getClaim(token, Claims::getSubject);
    }

    // Extrae un claim del token.
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extrae todos los claims del token.
    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
    }

    // Extrae la clave del token y la decodifica con base64
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Valida el token comprobando el usuario y que el token no esté caducado.
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // Comprueba la fecha del token con la actual para ver si ha caducado.
    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

    // Extrae la fecha de caducidad del token.
    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }
}
