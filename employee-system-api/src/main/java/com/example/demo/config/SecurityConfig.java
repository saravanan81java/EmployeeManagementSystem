package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;

@Configuration
public class SecurityConfig {
	
	/*
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests().anyRequest().permitAll();
		return http.build();
	}
	
	 /*
	@Bean
    public SecurityFilterChain securityFilterChain(HttpEntity http) throws Exception {
          http
              .authorizeHttpRequests(authorize -> authorize
                  .anyRequest().authenticated()
              )
              .httpBasic(withDefaults())
              .csrf(csrf -> csrf.disable()); // Disable CSRF for simplicity

          return http.build();
    }
    
    */
}
