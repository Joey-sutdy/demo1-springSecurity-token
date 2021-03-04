package com.convertlab.security.token.manage;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JwtTokenManager implements TokenManager {
    @Value("${token.expire}")
    private long tokenExpiration = 3600;
    @Value("${token.key}")
    private String tokenSignKey;

    Map<String, Object> roleMap = new HashMap<String, Object>();

    private static final String ROLE_CLAIMS = "role";

    @Override
    public String createToken(String username, List<GrantedAuthority> grantedAuthorities) {

        /*String roles = "";
        for (GrantedAuthority grantedAuthority : grantedAuthorities) {
            roles = roles + grantedAuthority.getAuthority() + ";";
        }*/
        roleMap.put(ROLE_CLAIMS, grantedAuthorities);

        String token = Jwts.builder().setClaims(roleMap).setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .signWith(SignatureAlgorithm.HS512, tokenSignKey).compressWith(CompressionCodecs.GZIP).compact();
        return token;
    }

    @Override
    public String getUserFromToken(String token) {
        String user = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token).getBody().getSubject();
        return user;
    }

    @Override
    public void removeToken(String token) {
        //jwttoken无需删除，客户端扔掉即可。
    }

    @Override
    public List<GrantedAuthority> getUserRoleByToken(String token) {
        List<GrantedAuthority> roles = (List<GrantedAuthority>) Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token).getBody().get(ROLE_CLAIMS);
        return roles;
    }

}
