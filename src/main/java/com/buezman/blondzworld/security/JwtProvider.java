package com.buezman.blondzworld.security;

import com.buezman.blondzworld.enums.AppConstants;
import com.buezman.blondzworld.exception.AppApiException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Date;

@Component
public class JwtProvider {

    private final String jwtSecret = getSHA512(AppConstants.JWT_SECRET);

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        int jwtExpTime = AppConstants.JWT_EXP_TIME;
        Date expiryTime = new Date(currentDate.getTime() + jwtExpTime);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiryTime)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUsernameFomJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException
                | MalformedJwtException
                | ExpiredJwtException
                | UnsupportedJwtException
                | IllegalArgumentException e) {
            throw new AppApiException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public String getSHA512(String input){

        String toReturn = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.reset();
            digest.update(input.getBytes(StandardCharsets.UTF_8));
            toReturn = String.format("%0128x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return toReturn;
    }
}

