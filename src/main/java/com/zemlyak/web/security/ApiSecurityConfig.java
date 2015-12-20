package com.zemlyak.web.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

import java.util.Arrays;

@Configuration
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Log LOG = LogFactory.getLog(ApiSecurityConfig.class);

    @Autowired
    private AuthenticationManager manager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http    .csrf().disable()
                .requestMatchers()
                    .antMatchers("/rest/**")
                    .and()
                .addFilterBefore(customPreAuthenticatedProcessingFilter(), RequestHeaderAuthenticationFilter.class)
                .authenticationProvider(preAuthenticatedAuthenticationProvider())
                .authorizeRequests()
                    .anyRequest().authenticated();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(preAuthenticatedAuthenticationProvider());
    }

    @Bean
    public CustomPreAuthenticatedProcessingFilter customPreAuthenticatedProcessingFilter(){
        CustomPreAuthenticatedProcessingFilter filter = new CustomPreAuthenticatedProcessingFilter();
        filter.setAuthenticationManager(manager);
        return filter;
    }

    @Bean
    public PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider(){
        PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
        provider.setPreAuthenticatedUserDetailsService(
                new UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken>(userDetailsService()));
        return provider;
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String ekbId) throws UsernameNotFoundException {
                LOG.debug("Try to loadUserByUsername: " + ekbId);
                return new SimpleDetails(ekbId, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
            }
        };
    }
}
