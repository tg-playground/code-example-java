package com.taogen.example.wechat.controller;

import com.taogen.example.wechat.config.AppConfig;
import com.taogen.example.wechat.config.WxConfig;
import com.taogen.example.wechat.service.WxAccessTokenService;
import com.taogen.example.wechat.service.WxWebPageService;
import com.taogen.example.wechat.utils.StringUtils;
import com.taogen.example.wechat.vo.WxJsapiConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Taogen
 */
@Controller
@RequestMapping("/wxPage")
public class WxWebPageController {
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private WxAccessTokenService wxAccessTokenService;
    @Autowired
    private WxWebPageService wxWebPageService;
    @Autowired
    private WxConfig wxConfig;
    @Autowired
    private AppConfig appConfig;

    /**
     * Go to weixin-index.jsp
     *
     * @return jsp file name
     */
    @GetMapping("/index")
    public String toWeixinIndexPage(ModelMap modelMap, HttpServletRequest request) {
        WxJsapiConfig wxJsapiConfig = getPrepareJsapiSign(request);
        String signature = wxWebPageService.getSignature(wxJsapiConfig);
        wxJsapiConfig.setSignature(signature);
        wxJsapiConfig.setAppId(wxConfig.getAppId());

        modelMap.addAttribute("jsapiConfig", wxJsapiConfig);
        return "weixin-index";
    }

    private WxJsapiConfig getPrepareJsapiSign(HttpServletRequest request) {
        String jsapiTicket = wxWebPageService.getJsapiTicket(wxAccessTokenService.getCurrentAccessToken());
        int randomStringLength = 16;
        String nonceStr = StringUtils.getRandomStringByCharAndNumber(randomStringLength);
        String url = appConfig.APP_DOMAIN + request.getRequestURI();
        logger.debug("url is {}", url);
        int timestamp = (int) (System.currentTimeMillis() / 1000);
        return new WxJsapiConfig(jsapiTicket, nonceStr, timestamp, url);
    }
}
