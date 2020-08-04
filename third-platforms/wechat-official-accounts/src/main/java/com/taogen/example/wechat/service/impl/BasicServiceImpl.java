package com.taogen.example.wechat.service.impl;

import com.taogen.example.wechat.config.WxConfig;
import com.taogen.example.wechat.config.WxDomains;
import com.taogen.example.wechat.config.WxUris;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Taogen
 */
@Service
public class BasicServiceImpl {

    @Value("${wechat.project_settings.environment}")
    protected String WECHAT_ENVIRONMENT;

    @Autowired
    protected WxConfig wxConfig;

    @Autowired
    protected WxUris wxUris;

    @Autowired
    protected WxDomains wxDomains;


}
