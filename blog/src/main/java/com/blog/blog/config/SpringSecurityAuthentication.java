package com.blog.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SpringSecurityAuthentication {

    @Autowired
    private SecurityFilter securityFilter;
}
