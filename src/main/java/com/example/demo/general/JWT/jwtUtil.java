package com.example.demo.general.JWT;

import com.example.demo.User.User;

import com.example.demo.User.UserDTO;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import java.util.function.Function;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;



@Component
public class jwtUtil {

    private static Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode("SECRETKEYquiEstSuperLongSaGrandJeTeLeDisMoiMonAmi");
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private static String createToken(Map<String, Object> claims, UserDTO user) {
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60  ))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact() ;
    }


    public static String generateToken(UserDTO user){ // on crée un token avec les claims, les claims sont les
        // données que l'on veut stocker dans le token ***** A REMPLACER AVEC UN DTO
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());   /// WTF!!!
        claims.put("role", "ROLE_USER");
        return createToken(claims, user);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);

    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String extractPseudo(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    public Boolean validateToken(String token,
                                 CustomUserDetails userDetails) {
        final String pseudo = extractPseudo(token);
        return (pseudo.equals(userDetails.getUsername()) &&
                !isTokenExpired(token));
    }

}
