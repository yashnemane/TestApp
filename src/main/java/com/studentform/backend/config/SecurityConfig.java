package com.studentform.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Disable CSRF protection
            .authorizeRequests()
                .antMatchers("/api/students/**").permitAll() // Permit access to /api/students/**
                .anyRequest().authenticated(); // Require authentication for all other requests
    }
}