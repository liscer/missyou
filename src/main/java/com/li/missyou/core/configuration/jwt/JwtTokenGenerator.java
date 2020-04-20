package com.li.missyou.core.configuration.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Slf4j
public class JwtTokenGenerator {
    private JwtProperties jwtProperties;
    private JwtTokenStorage jwtTokenStorage;
    public JwtTokenGenerator(JwtTokenStorage jwtTokenStorage,JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        this.jwtTokenStorage = jwtTokenStorage;
    }

    public JwtTokenPair jwtTokenPair(String aud) {
        String accessToken = jwtToken(aud,jwtProperties.getAccessExpire());
        String refreshToken = jwtToken(aud,jwtProperties.getRefreshExpire());
        JwtTokenPair jwtTokenPair = new JwtTokenPair(accessToken, refreshToken);
        jwtTokenStorage.put(jwtTokenPair,aud);
        return jwtTokenPair;
    }

    public String getAudFromToken(String authToken) {
        String aud;
        try {
            aud = getClaimsFromToken(authToken).getAudience();
        } catch (SignatureException e) {
            log.warn("JWT signature does not match locally computed signature", e);
            throw new BadCredentialsException("token不匹配");
        } catch (ExpiredJwtException e) {
            throw new BadCredentialsException("token过期");
        } catch (IllegalArgumentException e) {
            log.error("an error occurred during getting audience from token", e);
            throw new BadCredentialsException("获取audience失败");
        }
        return aud;
    }

    private String jwtToken(String aud, Long exp) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(Claims.ISSUER, jwtProperties.getIss());
        claims.put(Claims.SUBJECT, jwtProperties.getSub());
        claims.put(Claims.AUDIENCE, aud);
        claims.put(Claims.ISSUED_AT, new Date());
        claims.put(Claims.EXPIRATION,generateExpirationDate(exp));
        return generateToken(claims);
    }
    private String generateToken(Map<String, Object> claims) {
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecret()));
        return Jwts.builder().setClaims(claims)
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }
    private Claims getClaimsFromToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(jwtProperties.getSecret())
                .build()
                .parseClaimsJws(token).getBody();
        return claims;
    }
    private Date generateExpirationDate(Long exp) {
        return new Date(System.currentTimeMillis() + exp * 1000);
    }
}
