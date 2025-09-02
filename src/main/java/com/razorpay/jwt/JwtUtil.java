package com.razorpay.jwt;

import com.razorpay.config.JwtProperties;
import com.razorpay.exception.CustomException;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.AuthenticationException;

import java.util.Date;
import java.util.List;

public class JwtUtil {
    private final JwtParser jwtParser;
    private final JwtProperties jwtProperties;

    public JwtUtil(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        this.jwtParser = Jwts.parser().setSigningKey(jwtProperties.getSecreteKey());
    }

//    public String createToken(User user) {
//        Claims claims = Jwts.claims().setSubject(user.getEmail());
//        claims.put("firstName",user.getFirstName());
//        claims.put("lastName",user.getLastName());
//        Date tokenCreateTime = new Date();
//        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(jwtProperties.getTokenValidity()));
//        return Jwts.builder()
//                .setClaims(claims)
//                .setExpiration(tokenValidity)
//                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecreteKey())
//                .compact();
//    }

    private Claims parseJwtClaims(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }

    public Claims resolveClaims(HttpServletRequest req) {
        try {
            String token = resolveToken(req);
            if (token != null) {
                return parseJwtClaims(token);
            }
            return null;
        } catch (ExpiredJwtException ex) {
            req.setAttribute("expired", ex.getMessage());
            throw new CustomException(ex.getMessage());
        } catch (Exception ex) {
            req.setAttribute("invalid", ex.getMessage());
            throw new CustomException(ex.getMessage());
        }
    }

    public String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader(jwtProperties.getTokenHeader());
        String tokenPrefix = jwtProperties.getTokenPrefix();
        if (bearerToken != null && bearerToken.startsWith(tokenPrefix)) {
            return bearerToken.substring(tokenPrefix.length());
        }
        return null;
    }

    public boolean validateClaims(Claims claims) throws AuthenticationException {
        try {
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    public String getEmail(Claims claims) {
        return claims.getSubject();
    }

    private List<String> getRoles(Claims claims) {
        return (List<String>) claims.get("roles");
    }
}
