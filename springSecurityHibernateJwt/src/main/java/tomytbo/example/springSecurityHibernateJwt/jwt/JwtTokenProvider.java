package tomytbo.example.springSecurityHibernateJwt.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tomytbo.example.springSecurityHibernateJwt.user.CustomUserDetails;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {
    //this segment JWT_SECRET is private, only aside server know
    private final String JWT_SECRET = "lodaaaaaa";

    //the time have expiration of string jwt
    private final long JWT_EXPIRATION = 604800000L;

    //creat jwt from user information
    public String generateToken(CustomUserDetails userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
        return Jwts.builder()
                .setSubject(Long.toString(userDetails.getUser().getId()))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }
    //get user information from jwt
    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}
