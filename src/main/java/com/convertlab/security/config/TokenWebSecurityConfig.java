package com.convertlab.security.config;


import com.convertlab.security.token.TokenAuthenticationFilter;
import com.convertlab.security.token.TokenLoginFilter;
import com.convertlab.security.token.handler.DefaultPasswordEncoder;
import com.convertlab.security.token.handler.TokenLogoutHandler;
import com.convertlab.security.token.handler.UnauthorizedEntryPoint;
import com.convertlab.security.token.manage.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;


@Configuration
public class TokenWebSecurityConfig extends WebSecurityConfigurerAdapter {
    private UserDetailsService userDetailsService;
    private TokenManager tokenManager;
    private DefaultPasswordEncoder defaultPasswordEncoder;

    @Autowired
    public TokenWebSecurityConfig(@Qualifier("formUserDetailsService") UserDetailsService userDetailsService, DefaultPasswordEncoder defaultPasswordEncoder,
                                  TokenManager tokenManager) {
        this.userDetailsService = userDetailsService;
        this.defaultPasswordEncoder = defaultPasswordEncoder;
        this.tokenManager = tokenManager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling().authenticationEntryPoint(new UnauthorizedEntryPoint()).and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/security/login.html").permitAll()
                .antMatchers("/public/**").permitAll()
                .antMatchers("/convertlab/employee/insert").hasAnyRole("ADMIN")
                .antMatchers("/convertlab/employee/update").hasAnyRole("ADMIN")
                .antMatchers("/convertlab/employee/delete").hasAnyRole("ADMIN")
                .antMatchers("/convertlab/employee/select").hasAnyRole("ADMIN","EMPLOYEE")
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .logout().logoutUrl("/logout")
                .addLogoutHandler(new TokenLogoutHandler(tokenManager)).and()
                .addFilter(new TokenLoginFilter(authenticationManager(), tokenManager))
                .addFilter(new TokenAuthenticationFilter(authenticationManager(), tokenManager)).httpBasic();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(defaultPasswordEncoder);
    }
}