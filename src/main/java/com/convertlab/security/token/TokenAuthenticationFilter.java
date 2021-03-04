package com.convertlab.security.token;

import com.convertlab.security.token.manage.TokenManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

public class TokenAuthenticationFilter extends BasicAuthenticationFilter {
    private TokenManager tokenManager;

    public TokenAuthenticationFilter(AuthenticationManager authManager, TokenManager tokenManager) {
        super(authManager);
        this.tokenManager = tokenManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        String header = req.getHeader("token");

        if (header == null) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        // token置于header里
        String token = request.getHeader("token");
        if (token != null && !"".equals(token.trim())) {
            // parse the token.
            String userName = tokenManager.getUserFromToken(token);

            if (!StringUtils.isEmpty(userName)) {
                // todo
                List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

                List<GrantedAuthority> granteds = tokenManager.getUserRoleByToken(token);

                for (int i = 0; i < granteds.size(); i++) {
                    LinkedHashMap linkedHashMap = (LinkedHashMap) granteds.get(i);
                    String roleStr = (String) linkedHashMap.get("authority");
                    GrantedAuthority authority = new SimpleGrantedAuthority(roleStr);
                    grantedAuthorities.add(authority);
                }
                return new UsernamePasswordAuthenticationToken(userName, token, grantedAuthorities);
            }
            return null;
        }
        return null;
    }
}