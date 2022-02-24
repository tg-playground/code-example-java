package com.taogen.example.wecom.service;

import com.taogen.example.config.WeComProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author Taogen
 */
public abstract class BaseWeComService {
    @Value("${app.wecom.api.requestPrefix}")
    protected String weComApiRequestPrefix;

    @Autowired
    protected WeComProperties weComProperties;
}
