package com.karatay.openshop.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.karatay.openshop.model.Address;
import com.karatay.openshop.model.Cart;
import com.karatay.openshop.model.Role;
import com.karatay.openshop.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;

public class UserDetailsImp implements UserDetails {
    private static final long serialVersionUID = 1L;

    private User user;

    public UserDetailsImp(User user) {
        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        user.getRoles().forEach(r -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(r.getName().name());
            authorities.add(authority);
        });

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    public User getUser(){return user;}


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


}
