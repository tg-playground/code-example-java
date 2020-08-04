package com.taogen.example.wechat.controller;

import com.taogen.example.wechat.service.WxOauthService;
import com.taogen.example.wechat.vo.WxJsapiOauthParam;
import com.taogen.example.wechat.vo.WxUserInfo;
import okhttp3.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Accept Wechat Callback
 *
 * @author Taogen
 */
@Controller
@RequestMapping("/wxOauth")
public class WxOauthController {
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private WxOauthService wxOauthService;

    /**
     *
     * @param code code作为换取access_token的票据，每次用户授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期。
     * @param state 用于接受重定向之前传递的参数。重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
     * @return
     */
    @GetMapping("/callback")
    public ModelAndView oauth(@RequestParam("code") String code,
                        @RequestParam("state") String state, HttpSession session, ModelMap modelMap){
        logger.debug("JSAPI oauth callback...");
        WxJsapiOauthParam wxJsapiOauthParam = wxOauthService.getJsapiAccessToken(code);
        WxUserInfo wxUserInfo = wxOauthService.getWxUserInfo(wxJsapiOauthParam);
        session.setAttribute("wxUserInfo", wxUserInfo);
        session.setAttribute("openId", wxUserInfo.getOpenId());
        return new ModelAndView("redirect:" + state, modelMap);
    }

}
