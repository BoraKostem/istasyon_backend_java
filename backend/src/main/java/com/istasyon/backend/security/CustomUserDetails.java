package com.istasyon.backend.security;

import com.istasyon.backend.entities.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails extends User implements UserDetails {
    private String email;
    @Getter
    private String password;
    @Getter
    private Long userId;
    Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(User user, Collection<? extends GrantedAuthority> authorities) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.userId = user.getUserId();
        this.authorities = authorities;
        this.setEmail(user.getEmail());
        this.setPassword(user.getPassword());
        this.setUserId(user.getUserId());
        this.setName(user.getName());
        this.setSurname(user.getSurname());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
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
}
