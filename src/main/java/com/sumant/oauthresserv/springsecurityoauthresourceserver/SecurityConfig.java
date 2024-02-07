package com.sumant.oauthresserv.springsecurityoauthresourceserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(new AntPathRequestMatcher("/login/**")).permitAll()
                        .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .oauth2ResourceServer( (oauth2) -> oauth2.jwt(Customizer.withDefaults()));

        return httpSecurity.build();
    }
}
