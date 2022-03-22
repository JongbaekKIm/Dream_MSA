package com.dream.gatewayservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
		http.authorizeExchange().pathMatchers("/menu/**", "/product/**").permitAll()
			.and().authorizeExchange().anyExchange().authenticated()
			.and().oauth2Login() // to redirect to oauth2 login page.
			.and().csrf().disable();
		return http.build();
	}
}
