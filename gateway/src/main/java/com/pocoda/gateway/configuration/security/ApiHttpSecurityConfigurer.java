package com.pocoda.gateway.configuration.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public interface ApiHttpSecurityConfigurer {

    void configure(HttpSecurity httpSecurity) throws Exception;

}