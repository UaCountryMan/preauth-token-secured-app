package com.zemlyak.web.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class SimpleDetails implements UserDetails {
    private Collection<? extends GrantedAuthority> authorities;
    private String ekbId;

    public SimpleDetails(String ekbId, Collection<? extends GrantedAuthority> authorities){
        this.ekbId = ekbId;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return "password";
    }

    @Override
    public String getUsername() {
        return "userName";
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

    public String getEkbId() {
        return ekbId;
    }

    public void setEkbId(String ekbId) {
        this.ekbId = ekbId;
    }
}
