package com.pocoda.gateway.configuration.security;

import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@NoArgsConstructor
public class TypeAuthority implements GrantedAuthority {
    public final String USER = "User";
    public final String ADMIN = "Admin";

    private final boolean isAdmin = false;

    @Override
    public String getAuthority() {
        return isAdmin ? ADMIN : USER;
    }
}
