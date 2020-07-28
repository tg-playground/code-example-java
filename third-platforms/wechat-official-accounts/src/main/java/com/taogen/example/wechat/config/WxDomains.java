package com.taogen.example.wechat.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Taogen
 */
@Component
public class WxDomains {
    @Value("${wechat.domains.master}")
    private String WECHAT_DOMAIN_MASTER;

    @Value("${wechat.domains.backup}")
    private String WECHAT_DOMAIN_BACKUP;

    @Value("${wechat.domains.shanghai}")
    private String WECHAT_DOMAIN_SHANGHAI;

    @Value("${wechat.domains.shenzhen}")
    private String WECHAT_DOMAIN_SHENZHEN;

    @Value("${wechat.project_settings.domain}")
    private String WECHAT_DOMAIN_SETTING;

    public String getWechatDomain(){
        String currentDomain = WECHAT_DOMAIN_MASTER;
        if ("master".equals(WECHAT_DOMAIN_SETTING)){
            currentDomain = WECHAT_DOMAIN_MASTER;
        }
        if ("backup".equals(WECHAT_DOMAIN_SETTING)) {
            currentDomain = WECHAT_DOMAIN_BACKUP;
        }
        return currentDomain;
    }

}
