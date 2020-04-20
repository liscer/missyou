package com.li.missyou.core.configuration.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@EnableConfigurationProperties(JwtProperties.class)
@ConditionalOnProperty(prefix = "jwt",name = "enabled")
@Configuration
public class jwtConfiguration {
    @Bean
    public JwtTokenStorage jwtTokenStorage() {
        return new JwtTokenCacheStorage();
    }

    @Bean
    public JwtTokenGenerator jwtTokenGenerator(JwtTokenStorage jwtTokenStorage, JwtProperties jwtProperties) {
        return new JwtTokenGenerator(jwtTokenStorage, jwtProperties);
    }
}
