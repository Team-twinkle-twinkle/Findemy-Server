package com.founderz.findemy.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.founderz.findemy.global.security.jwt.JwtTokenProvider;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
class SecurityConfiguration {

    private final JwtTokenProvider jwtTokenProvider;

    private final ObjectMapper objectMapper;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)

                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::deny)
                        .xssProtection(HeadersConfigurer.XXssConfig::disable)
                        .contentSecurityPolicy(csp -> csp.policyDirectives("default-src 'self'; frame-ancestors 'none';"))
                )

                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint((request, response, authException) -> response
                                .sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized"))
                        .accessDeniedHandler((request, response, accessDeniedException) -> response
                                .sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied"))
                )

                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/**").permitAll()
                        //.requestMatchers("/user/signup", "/user/login", "/academy/register", "/academy/login", "/image").permitAll()
                        .anyRequest().authenticated()
                )

                .with(new SecurityFilter(jwtTokenProvider, objectMapper), Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("*")); // 모든 도메인 허용
        configuration.setAllowedMethods(Arrays.asList( "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")); // HTTP 메서드 허용
        configuration.setAllowCredentials(false);
        configuration.addAllowedHeader("*"); // 모든 헤더 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 모든 경로에 대해 위에서 설정한 CORS 설정 적용
        return source;
    }

}
