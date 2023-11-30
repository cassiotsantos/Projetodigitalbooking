package br.com.digitalbooking.digitalbooking.infra.security;

import com.auth0.jwt.interfaces.Claim;
import io.jsonwebtoken.*;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import java.security.SignatureException;
import java.util.Date;

@Component
public class JwtUtil {

     private String SECRET_KEY = "secret";

     public String extractUserName(String token) {
         return extractUserName(token);
     }

     public Date extractExpiration (String token) {
         return extractExpiration(token);
     }

    // Comentado Thalita, estava com erro - 30-11
     /*  public Date extractClaimDate (String token) {
         Claims claims = extractAllClaims (token);
         return claims.getExpiration();
     }

    public String extractClaimUserName (String token){
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    private Claims extractAllClaims (String token) throws ExpiredJwtException, SignatureException, MalformedJwtException, UnsupportedJwtException {


           return null;//Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
    }*/


}
