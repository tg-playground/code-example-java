package com.taogen.example.wechat.jobs;

import com.taogen.example.wechat.config.AppConfig;
import com.taogen.example.wechat.service.WxAccessTokenService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Refresh Weixin API access_token
 * <p>
 * 目前access_token的有效期通过返回的expire_in来传达，目前是7200秒之内的值。
 * 中控服务器需要根据这个有效时间提前去刷新新access_token。
 * 在刷新过程中，中控服务器可对外继续输出的老access_token，此时公众平台后台会保证在5分钟内，
 * 新老access_token都可用，这保证了第三方业务的平滑过渡；
 *
 * @author Taogen
 */
@Configuration
@EnableScheduling
public class AccessTokenRefreshJob {
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private WxAccessTokenService wxAccessTokenService;

    @Autowired
    private AppConfig appConfig;

    @Value("${wechat.jobs.refreshAccessToken.enable}")
    private boolean enable;

    @Scheduled(fixedRateString = "${wechat.jobs.refreshAccessToken.fixedRate}",
            initialDelayString = "${wechat.jobs.refreshAccessToken.initialDelay}")
    public void refreshAccessToken() {
        if (enable) {
            logger.debug("Starting refresh accessToken job...");
            String newAccessToken = wxAccessTokenService.getAccessToken(appConfig.WECHAT_ENVIRONMENT);
            if (newAccessToken != null && !newAccessToken.isEmpty()) {
                logger.info("Refresh access token successfully");
            }
            logger.debug("End refresh accessToken job.");
        }
    }
}
