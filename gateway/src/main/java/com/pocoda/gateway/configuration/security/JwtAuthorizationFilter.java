package com.pocoda.gateway.configuration.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.pocoda.gateway.model.User;
import com.pocoda.gateway.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.pocoda.gateway.helper.AuthorizationUtils.JWT_SECRET;
import static com.pocoda.gateway.helper.AuthorizationUtils.USER_ID;
import static java.lang.Long.parseLong;
import static java.util.Collections.singletonList;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private static final String TOKEN_HEADER = "Authorization";

    private static final String TOKEN_PREFIX = "Bearer ";

    private final UserService userService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserService userService) {
        super(authenticationManager);
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        if (authentication == null) {
            filterChain.doFilter(request, response);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_HEADER);
        if (token != null && token.startsWith(TOKEN_PREFIX)) {
            String userId = JWT.require(Algorithm.HMAC256(JWT_SECRET))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""))
                    .getClaim(USER_ID).asString();
            Optional<User> user = userService.findById(parseLong(userId));
            if (user.isPresent()) {
                GrantedAuthority authority = new TypeAuthority();
                return new UsernamePasswordAuthenticationToken(userId, null, singletonList(authority));
            }
        }
        return null;
    }
}
