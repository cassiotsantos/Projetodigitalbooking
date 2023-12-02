package br.com.digitalbooking.digitalbooking.infra.security;

import com.auth0.jwt.interfaces.Claim;
import io.jsonwebtoken.*;
import org.hibernate.annotations.Comment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

     private String SECRET_KEY = "secret";

     public String extractUserName (String token) {
         return extractUserName(token);
     }

     public Date extractExpiration (String token) {
         return extractExpiration(token);
     }

      public Date extractClaimDate (String token) {
         Claims claims = extractAllClaims (token);
         return claims.getExpiration();
     }

    public String extractClaimUserName (String token){
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    private Claims extractAllClaims (String token) throws ExpiredJwtException, SignatureException, MalformedJwtException, UnsupportedJwtException {
           return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken (String token, UserDetails userDetails){
            final String username = extractUserName(token);
            return(username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
         return extractExpiration(token).before(new Date());
    }

}
