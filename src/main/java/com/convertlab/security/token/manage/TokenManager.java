package com.convertlab.security.token.manage;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public interface TokenManager {
    /**
     *
     * @param username
     * @param grantedAuthorities
     * @return
     */
    public String createToken(String username , List<GrantedAuthority> grantedAuthorities);

    /**
     *
     * @param token
     * @return
     */
    public String getUserFromToken(String token);

    /**
     *
     * @param token
     */

    public void removeToken(String token);

    /**
     * 根据token获取用户角色
     * @param token
     */
    public List<GrantedAuthority> getUserRoleByToken(String token);
}
