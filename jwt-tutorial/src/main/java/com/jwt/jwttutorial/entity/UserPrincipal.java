package com.jwt.jwttutorial.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class UserPrincipal implements UserDetails, OAuth2User {

    private User user;
//    private List<GrantedAuthority> authorities;

    private Map<String, Object> oauthUserAttributes;

    // UserDetails -> UserPrincipal
    public UserPrincipal(User user) {
        this.user=user;
    }

    // 일반 로그인
    // oauth 로그인

    // from UserDetails
    @Override
    public String getUsername() {
        return String.valueOf(user.getUsername());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
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

    // from OAuth2User
    @Override
    public String getName() {
        return String.valueOf(user.getUsername());
    }

    @Override
    public Map<String, Object> getAttributes() {
        return Collections.unmodifiableMap(oauthUserAttributes);
    }


}
