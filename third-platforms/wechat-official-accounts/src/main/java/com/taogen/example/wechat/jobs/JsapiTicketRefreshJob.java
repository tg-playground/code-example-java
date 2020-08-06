package com.taogen.example.wechat.jobs;

import com.taogen.example.wechat.service.WxAccessTokenService;
import com.taogen.example.wechat.service.WxWebPageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Refresh jsapi_ticket
 * <p>
 * 生成签名之前必须先了解一下jsapi_ticket，jsapi_ticket是公众号用于调用微信JS接口的临时票据。
 * 正常情况下，jsapi_ticket的有效期为7200秒，通过access_token来获取。
 * 由于获取jsapi_ticket的api调用次数非常有限，频繁刷新jsapi_ticket会导致api调用受限，影响自身业务，
 * 开发者必须在自己的服务全局缓存jsapi_ticket。
 *
 * @author Taogen
 */
@Configuration
@EnableScheduling
public class JsapiTicketRefreshJob {
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private WxAccessTokenService wxAccessTokenService;

    @Autowired
    private WxWebPageService wxWebPageService;

    @Value("${wechat.jobs.refreshJsapiTicket.enable}")
    private boolean enable;

    @Scheduled(fixedRateString = "${wechat.jobs.refreshJsapiTicket.fixedRate}",
            initialDelayString = "${wechat.jobs.refreshJsapiTicket.initialDelay}")
    public void refreshAccessToken() {
        if (enable) {
            logger.debug("Starting refresh jsapiTicket job...");
            String newJsapiTicket = wxWebPageService.refreshJsapiTicket(wxAccessTokenService.getCurrentAccessToken());
            if (newJsapiTicket != null && !newJsapiTicket.isEmpty()) {
                logger.info("Refresh jsapiTicket successfully!");
            }
            logger.debug("End refresh jsapiTicket job.");
        }
    }
}
