package com.taogen.example.wechat.controller;

import com.taogen.example.wechat.config.WxConfig;
import com.taogen.example.wechat.config.WxUrls;
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
    protected WxUrls wxUrls;
}
