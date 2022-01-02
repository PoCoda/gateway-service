package com.pocoda.gateway.configuration.security;

import com.pocoda.gateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private static final String[] SWAGGER_RESOURCES = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/swagger-ui/#",
            "/swagger-ui/",
            "/swagger-ui/*",
            "/v2/api-docs",
            "/webjars/**",
            "/actuator/**"
    };

    private static final String[] GUEST_URLS = {
            "/test",
            "/info"
    };

    private static final String USER_REGISTRATION_URL = "/user/registration";

    private static final String USER_LOGIN_URL = "/user/login";

    @Autowired
    private ApiHttpSecurityConfigurer apiHttpSecurityConfigurer;

    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().requestMatchers().antMatchers("/**/*").and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        apiHttpSecurityConfigurer.configure(http);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(SWAGGER_RESOURCES);
    }

    @Bean
    @ConditionalOnMissingBean(ApiHttpSecurityConfigurer.class)
    public ApiHttpSecurityConfigurer defaultApiHttpSecurityConfigurer() {
        return http -> http
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userService))
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**/*").permitAll()
                .antMatchers(USER_REGISTRATION_URL).permitAll()
                .antMatchers(USER_LOGIN_URL).permitAll()
                .antMatchers(HttpMethod.GET, GUEST_URLS).permitAll()
                .antMatchers("/**/*").authenticated()
                .and().csrf().disable();
    }
}
