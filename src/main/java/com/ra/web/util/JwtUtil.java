package com.ra.web.util;

import com.ra.web.model.dto.adapter.AccAdapter;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final long EXPIRED = (1000 * 60  * 60 *2);
    @Value("${JwtToken.key}")
    private String JWT_KEY;

    public String generateToken(UserDetails userDetails){
        Date now = new Date();
        Date expired = new Date(now.getTime() +EXPIRED);
        String token = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expired)
                .signWith(SignatureAlgorithm.HS512, generateKey(JWT_KEY))
                .compact();
        return token;
    }

    public String generateTokenAndEmail(AccAdapter userDetails){
        Date now = new Date();
        Date expired = new Date(now.getTime() +EXPIRED);
        Map<String,Object> user = new HashMap<>();
        user.put("userName",userDetails.getUsername());
        user.put("password",userDetails.getPassword());
        String token = Jwts.builder().addClaims(user)
                .setIssuedAt(now)
                .setExpiration(expired)
                .signWith(SignatureAlgorithm.HS512,generateKey(JWT_KEY))
                .compact();
        return token;
    }

    public String getUserName(String token){
        String userName = Jwts.parser()
                .setSigningKey(generateKey(JWT_KEY))
                .parseClaimsJws(token)
                .getBody().getSubject();
        return userName;
    }

    public boolean validateToken(String token){
        try{
            Jwts
                    .parser()
                    .setSigningKey(generateKey(JWT_KEY))
                    .parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    SecretKey getSigningKey() {
        byte[] apiKeySecretBytes = Decoders.BASE64.decode(JWT_KEY);
        return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS512.getJcaName());
    }

    private String generateKey(String key) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            // Đưa chuỗi và digest để tạo băm
            byte[] hashedBytes = digest.digest(key.getBytes());
            // Tạo secret key từ byte[] đã băm
            SecretKeySpec secretKeySpec = new SecretKeySpec(hashedBytes, "ASE");
            // Chuyển key thành chuỗi base 64
            String base64Key = base64Key(secretKeySpec.getEncoded());
            return base64Key;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    private String base64Key(byte[] keys){
        String base64Key = Base64.getEncoder().encodeToString(keys);
        System.out.println("SECRET_KEY: " + base64Key);
        return base64Key;
    }
    //tạo key đủ mạnh
}
