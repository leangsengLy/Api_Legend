package legend.example.project_api_legend.Jwt;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtService {
   private final String secretKey = "mySecretKey"; // Secret for signing JWTs
    private final long expirationMs = 86400000; // Token validity (1 day)

    public String generateToken(String username) {
        return Jwts.builder()
                   .setSubject(username)
                   .setIssuedAt(new Date())
                   .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                   .signWith(SignatureAlgorithm.HS256, secretKey)
                   .compact(); // Creates the token
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token); // Verifies token integrity
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String extractUsername(String token) {
        Claims claims = Jwts.parser()
                            .setSigningKey(secretKey)
                            .parseClaimsJws(token)
                            .getBody();
        return claims.getSubject(); // Extracts the username
    }
}
