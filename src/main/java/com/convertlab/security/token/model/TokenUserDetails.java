package com.convertlab.security.token.model;

import com.convertlab.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TokenUserDetails extends User implements UserDetails {


    /**
     *
     */
    private static final long serialVersionUID = 6272869114201567325L;

    private List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

    public TokenUserDetails(User appUser, List<GrantedAuthority> grantedAuthorities) {
        super(appUser);
        this.grantedAuthorities = grantedAuthorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (CollectionUtils.isEmpty(grantedAuthorities)) {
            return grantedAuthorities;
        } else {
            return AuthorityUtils.createAuthorityList("USER");
        }
    }

    @Override
    public String getUsername() {
        return super.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    public List<GrantedAuthority> getGrantedAuthorities() {
        return grantedAuthorities;
    }

    public void setGrantedAuthorities(List<GrantedAuthority> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
    }
}
