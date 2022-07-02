package com.vompany.liberyspring.util;

import com.vompany.liberyspring.enums.StudentRole;
import com.vompany.liberyspring.exps.BadRequestException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.naming.NoPermissionException;
import java.util.Date;

public class JwtUtil {


    private static final String secretKey = "Ichovri";

    public static String encode(Integer id) {
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setIssuedAt(new Date()); // 18:58:00
        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (60 * 60 * 1000))); // 19:58:00
        jwtBuilder.setIssuer("Mazgi production");
        jwtBuilder.signWith(SignatureAlgorithm.HS256, secretKey);
        jwtBuilder.claim("id", id);

        String jwt = jwtBuilder.compact();
        return jwt;
    }

    public static Integer decode(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        Integer id = (Integer) claims.get("id");
        return id;
    }

//yangi
    public static String encode1(Integer id, StudentRole role) {
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setIssuedAt(new Date()); // 18:58:00
        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (60 * 60 * 1000))); // 19:58:00
        jwtBuilder.setIssuer("Mazgi production");
        jwtBuilder.signWith(SignatureAlgorithm.HS256, secretKey);
        jwtBuilder.claim("id", id);
        jwtBuilder.claim("role", role.name());

        String jwt = jwtBuilder.compact();
        return jwt;
    }

    public static Integer decode1(String token, StudentRole requiredRole) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        Integer id = (Integer) claims.get("id");
        String role = (String) claims.get("role");

        if (!requiredRole.equals(StudentRole.valueOf(role))) {
            throw new BadRequestException("Not Access");
        }
        return id;
    }

}
