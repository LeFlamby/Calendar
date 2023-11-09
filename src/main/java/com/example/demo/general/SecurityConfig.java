package com.example.demo.general;

import com.example.demo.general.JWT.filter.jwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final jwtRequestFilter jwtRequestFilter;

    public SecurityConfig(jwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configure(http))
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(
                                "/register", "/login", "/home")
                        .permitAll()
                        .requestMatchers(
                                "/user", "/user/{id}", "/note", "/note/{id}", "/event", "/event/{id}"
                                          , "calendar", "calendar/{id}", "style", "style/{id}")
                        .hasAnyRole( "USER")
                        .requestMatchers("")
                        .authenticated())



                .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .ignoringRequestMatchers("")
                        .disable())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
