package com.taogen.example.wechat.filter;

import com.taogen.example.wechat.config.AppConfig;
import com.taogen.example.wechat.config.WxConfig;
import com.taogen.example.wechat.config.WxUris;
import com.taogen.example.wechat.constant.WxOauthScope;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author Taogen
 */
@Component
public class WeixinJsapiFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private AppConfig appConfig;
    @Autowired
    private WxConfig wxConfig;
    @Autowired
    private WxUris wxUris;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String servletPath = request.getServletPath();
        logger.debug("servletPath is {}", servletPath);
//        if (doesFilterThisRequest(servletPath)) {
        if (servletPath.startsWith("/wxPage")) {
            Object openId = session.getAttribute("openId");
            logger.debug("Session openId is {}", openId);
            if (openId != null) {
                if (session.getAttribute("wxUserInfo") != null) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    redirectToWeChatUserAuthPage(request, response);
                    return;
                }
            } else {
                redirectToWeChatUserAuthPage(request, response);
                return;
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
//        } else {
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
    }

    private boolean doesFilterThisRequest(String currentServletPath) {
        String[] ignorePrefixList = {
                "/wxOauth",
                "/wxApi",
                // validate token and Weixin message
                "/wxMsg",
                "/wxpay/notify/pay",
                "/wxpay/notify/refund",
                "/hello"
        };
        boolean doesFilter = true;
        for (String uri : ignorePrefixList) {
            if (currentServletPath.startsWith(uri)) {
                doesFilter = false;
                break;
            }
        }
        logger.debug("doesFilter is {}", doesFilter);
        return doesFilter;
    }

    private void redirectToWeChatUserAuthPage(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Redirect to WeChat user authentication page...");
        String callbackUrl = URLEncoder.encode(appConfig.APP_DOMAIN + "/wxOauth/callback");
        String redirectUrl = String.format(wxUris.JSAPI_OAUTH_GET_CODE_URL, wxConfig.getAppId(),
                callbackUrl, WxOauthScope.SNSAPI_USERINFO.toString().toLowerCase(), request.getRequestURI());
        logger.debug("redirectUrl is {}", redirectUrl);
        try {
            response.sendRedirect(redirectUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
