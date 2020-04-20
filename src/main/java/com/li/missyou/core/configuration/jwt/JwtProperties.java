package com.li.missyou.core.configuration.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private boolean enabled;
    private Long accessExpire;
    private Long refreshExpire;
    private String iss;
    private String sub;
    private String secret;
    private String tokenHeader;
    private String tokenHead;
}
