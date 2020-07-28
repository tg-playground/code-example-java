package com.taogen.example.wechat.controller;

import com.taogen.example.wechat.config.WxConfig;
import com.taogen.example.wechat.config.WxDomains;
import com.taogen.example.wechat.config.WxUris;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Taogen
 */
@Component
public class BasicWxController {
    @Autowired
    protected WxConfig wxConfig;
    @Autowired
    protected WxUris wxUris;
    @Autowired
    protected WxDomains wxDomains;
}
