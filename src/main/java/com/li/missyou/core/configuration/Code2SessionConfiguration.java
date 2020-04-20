package com.li.missyou.core.configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "li")
@PropertySource(value = "classpath:config/code2session.properties")

@Component
@Getter
@Setter
@ToString
public class Code2SessionConfiguration {
    private Map<String, String> code = new HashMap<>();
    private String url;

}
