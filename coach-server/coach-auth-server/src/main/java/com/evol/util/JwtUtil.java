package com.evol.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    private static long expire = 604800;

    private static String secret = "HSyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9";

    public String generateToken(String userId) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + expire);
        return Jwts.builder().setHeaderParam("type", "JWT")
                .setSubject(userId).setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    /**
     * 解析token
     */
    public Claims getClaimsByToken(String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            log.error("validate is token error");
            return null;
        }
    }

    /**
     * 判断 token 是否过期
     */
    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }
}
