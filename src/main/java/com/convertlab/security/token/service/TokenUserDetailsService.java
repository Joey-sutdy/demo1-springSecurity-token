package com.convertlab.security.token.service;

import com.convertlab.entity.User;
import com.convertlab.security.token.model.TokenUserDetails;
import com.convertlab.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("formUserDetailsService")
public class TokenUserDetailsService implements UserDetailsService {
    @Autowired
    private UserInfoService userInfoService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user;
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        try {
            user = userInfoService.getUserInfoByUserName(userName);
        } catch (Exception e) {
            throw new UsernameNotFoundException("user select fail");
        }
        if (user == null) {
            throw new UsernameNotFoundException("no user found");
        } else {
            try {
                // 权限
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getJobRole());
                grantedAuthorities.add(grantedAuthority);
                return new TokenUserDetails(user, grantedAuthorities);
            } catch (Exception e) {
                throw new UsernameNotFoundException("user role select fail");
            }
        }
    }

}
