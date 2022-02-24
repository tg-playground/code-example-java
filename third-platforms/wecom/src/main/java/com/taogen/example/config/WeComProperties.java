package com.taogen.example.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Taogen
 */
@Component
@Data
@ToString
@ConfigurationProperties(prefix = "app.wecom")
public class WeComProperties {
    private String corpId;
    private Integer appAgentId;
    private String appSecret;
}
