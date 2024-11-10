package com.turing.rubrica.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class JWTService {
    private static final Logger logger = Logger.getLogger(JWTService.class.getName());
    
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expirationMs}")
    private int jwtExpirationMs;
    
    public String generateToken(String username) {

        return Jwts.builder()
                .subject(username)
                .issuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationMs)))
                .signWith(generateKey())
                .compact();
    }
    
    public boolean validateJwtToken(String authToken) throws Exception {
        try {
            getClaims(authToken);
        } catch (SignatureException e) {
            logger.log(Level.SEVERE, "Invalid JWT signature: {0}", e.getMessage());
            throw new Exception("Invalid JWT signature");
        } catch (MalformedJwtException e) {
            logger.log(Level.SEVERE, "Invalid JWT token: {0}", e.getMessage());
            throw new Exception("Invalid JWT token");
        } catch (ExpiredJwtException e) {
            logger.log(Level.SEVERE, "JWT token is expired: {0}", e.getMessage());
            throw new Exception("JWT token is expired");
        } catch (UnsupportedJwtException e) {
            logger.log(Level.SEVERE, "JWT token is unsupported: {0}", e.getMessage());
            throw new Exception("JWT token is unsupported");
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "JWT claims string is empty: {0}", e.getMessage());
            throw new Exception("JWT claims string is empty");
        }
        return true;
    }
    
    public String extractUsername(String jwtToken) {
        Claims claims = getClaims(jwtToken);
        return claims.getSubject();
    }
    
    public boolean isTokenValid(String jwtToken) {
        Claims claims = getClaims(jwtToken);
        return claims.getExpiration().after(Date.from(Instant.now()));
    }
    
    private Claims getClaims(String jwt) {
        return Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }
    
    private SecretKey generateKey() {
        byte[] keyBytes = Base64.getDecoder().decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
