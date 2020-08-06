package com.taogen.example.wechat.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Taogen
 */
@Component
public class AppConfig {
    @Value("${app.domain}")
    public String APP_DOMAIN;

    @Value("${wechat.project_settings.environment}")
    public String WECHAT_ENVIRONMENT;
}
