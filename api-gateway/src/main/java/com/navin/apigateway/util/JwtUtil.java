package com.navin.apigateway.util;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "my$hoppin8cart"; 

    public void validateToken(String token) {
        getClaims(token); // will throw if invalid
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                   .setSigningKey(SECRET_KEY)
                   .parseClaimsJws(token)
                   .getBody();
    }
}

