package org.trams.hello.web.common.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class EncryptionUtils {

    public static final String JWT_KEY = "Trams*@";

    /**
     * Encrypt with jwt
     * @param subject
     * @return
     */
    public static String jwtBuild(Object subject) {
        return Jwts.builder()
                .setSubject(JsonUtils.toString(subject))
                .signWith(SignatureAlgorithm.HS256, JWT_KEY.getBytes())
                .compact();
    }
 

    /**
     * Decrypt with jwt
     * and convert to klass T
     * @param jwtToken
     * @param klass
     * @param <T>
     * @return
     */
    public static <T> T jwtParse(String jwtToken, Class<T> klass) {
        String parse = Jwts.parser()
                .setSigningKey(JWT_KEY.getBytes())
                .parseClaimsJws(jwtToken)
                .getBody()
                .getSubject();
        return JsonUtils.from(parse, klass);
    }
    
   
}