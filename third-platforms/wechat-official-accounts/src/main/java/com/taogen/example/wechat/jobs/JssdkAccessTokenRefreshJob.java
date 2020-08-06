//package com.taogen.example.wechat.jobs;
//
//import com.taogen.example.wechat.service.WxAccessTokenService;
//import com.taogen.example.wechat.service.WxOauthService;
//import com.taogen.example.wechat.service.WxWebPageService;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//
///**
// * Refresh JSSDK user authority access_token
// * <p>
// * 由于access_token拥有较短的有效期，当access_token超时后，可以使用refresh_token进行刷新，
// * refresh_token有效期为30天，当refresh_token失效之后，需要用户重新授权。
// *
// * @author Taogen
// */
//@Configuration
//@EnableScheduling
//public class JssdkAccessTokenRefreshJob {
//    private static final Logger logger = LogManager.getLogger();
//
//    @Autowired
//    private WxAccessTokenService wxAccessTokenService;
//
//    @Autowired
//    private WxWebPageService wxWebPageService;
//
//    @Autowired
//    private WxOauthService wxOauthService;
//
//    @Scheduled(fixedRateString = "${wechat.jobs.refreshJssdkAccessToken.fixedRate}",
//            initialDelayString = "${wechat.jobs.refreshJssdkAccessToken.initialDelay}")
//    public void refreshAccessToken() {
//        logger.debug("Starting refresh jssdkAccessToken job...");
//        String newAccessToken = wxOauthService.refreshAccessToken();
//        if (newAccessToken != null && !newAccessToken.isEmpty()) {
//            logger.debug("Refresh Jssdk accessToken successfully!");
//        }
//        logger.debug("End refresh jssdkAccessToken job.");
//    }
//}
