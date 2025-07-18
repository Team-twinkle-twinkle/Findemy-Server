package com.founderz.findemy.global.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public record JwtProperties(
        String header,
        String prefix,
        String secret,
        Long accessExpiration,
        Long refreshExpiration,
        String academySecret,
        String userSecret
) {
}