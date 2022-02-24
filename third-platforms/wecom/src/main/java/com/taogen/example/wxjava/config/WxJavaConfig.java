package com.taogen.example.wxjava.config;

import com.taogen.example.config.WeComProperties;
import me.chanjar.weixin.cp.api.WxCpChatService;
import me.chanjar.weixin.cp.api.WxCpMessageService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.impl.WxCpChatServiceImpl;
import me.chanjar.weixin.cp.api.impl.WxCpMessageServiceImpl;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Taogen
 */
@Configuration
public class WxJavaConfig {
    @Bean
    public WxCpService wxCpService(WeComProperties weComProperties) {
        System.out.println("weComProperties: " + weComProperties);
        WxCpService wxCpService = new WxCpServiceImpl();
        WxCpDefaultConfigImpl config = new WxCpDefaultConfigImpl();
        config.setCorpId(weComProperties.getCorpId());      // 设置微信企业号的appid
        config.setAgentId(weComProperties.getAppAgentId());     // 设置微信企业号应用ID
        config.setCorpSecret(weComProperties.getAppSecret());  // 设置微信企业号的app corpSecret
        wxCpService.setWxCpConfigStorage(config);
        return wxCpService;
    }

    @Bean
    public WxCpMessageService wxCpMessageService(WxCpService wxCpService) {
        return new WxCpMessageServiceImpl(wxCpService);
    }

    @Bean
    public WxCpChatService wxCpChatService(WxCpService wxCpService) {
        return new WxCpChatServiceImpl(wxCpService);
    }
}
