package com.taogen.example.wechat.controller;

import com.taogen.example.wechat.service.WxMessageService;
import com.taogen.example.wechat.utils.XmlUtils;
import com.taogen.example.wechat.vo.WxTextMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Message Management
 * <p>
 * 1)XML-based Message receive from and reply to Wechat
 * 2)Send message to users
 * 3)Send template message to users
 * 4)Send temporary authorization message to an unfollowed user
 *
 * @author Taogen
 */
@RestController
public class WxMessageController extends BasicWxController {
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private WxMessageService wxMessageService;

    /**
     * Receive XML-based messages from Wechat.
     * This message receive URI is same with token auth URI, but this is HTTP post request.
     *
     * @param httpEntity
     * @return
     */
    @PostMapping("/wx")
    public String messageReceive(HttpEntity<String> httpEntity) {
        String requestBody = httpEntity.getBody();
        logger.debug("requestBody is {}", requestBody);
        WxTextMessage receiveMessage = (WxTextMessage) XmlUtils.getObjectByXmlString(requestBody);
        logger.debug("Wechat message object is: {}", receiveMessage);
        WxTextMessage replyMessage = wxMessageService.getReplayMessageByReceive(receiveMessage);
        return XmlUtils.getXmlStringByObject(replyMessage);
    }

}
