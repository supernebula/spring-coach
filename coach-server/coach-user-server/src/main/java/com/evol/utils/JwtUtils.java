package com.evol.utils;


import com.evol.config.JwtConfig;
import com.evol.constants.ColumnNames;
import com.google.common.collect.Maps;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Base64;
import java.util.Map;

@Slf4j
@Component
public class JwtUtils {

    private JwtUtils() {

    }

    @Autowired
    private JwtConfig jwtConfig;

    /**
     * 加密
     * @param userId
     * @return
     */
    public String generateJwt(Long userId, String userType) {
        byte[] encode = Base64.getEncoder().encode(jwtConfig.getSecurityKey().getBytes());
        Map<String, Object> claims = Maps.newHashMap();
        claims.put(ColumnNames.USER_ID, userId);    //用户userId
        claims.put(ColumnNames.USER_TYPE, userType);    //用户角色
        return Jwts.builder()
                .addClaims(claims) //内容
                .setIssuedAt(TimeUtil.now()) //发行时间
                .setExpiration(TimeUtil.getExpiration(jwtConfig.getOutTime())) //超时时间
                .signWith(SignatureAlgorithm.HS512, encode)
                .compact();
    }

    /**
     * 解密
     * @param token
     * @return
     */
    public Claims extractJwt(String token) {
        try {
            byte[] encode = Base64.getEncoder().encode(jwtConfig.getSecurityKey().getBytes());
            return Jwts.parser()
                    .setSigningKey(encode)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception ex) {
            log.error("parseJWT error for {}", token);
            return null;
        }
    }
}
