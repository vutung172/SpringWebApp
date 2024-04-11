package com.ra.web.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private final long EXPIRED = (1000 * 60 * 2);
    private final String JWT_KEY = "JWT_SECRET_KEY";

    public String generateToken(UserDetails userDetails){
        Date now = new Date();
        Date expired = new Date(now.getTime() +EXPIRED);
        String token = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expired)
                .signWith(SignatureAlgorithm.HS512, JWT_KEY)
                .compact();
        return token;
    }

    public String getUserName(String token){
        String userName = Jwts.parser()
                .setSigningKey(JWT_KEY)
                .parseClaimsJwt(token)
                .getBody().getSubject();
        return userName;
    }

    public boolean validateToken(String token){
        try{
            Jwts
                    .parser()
                    .setSigningKey(JWT_KEY)
                    .parseClaimsJwt(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
