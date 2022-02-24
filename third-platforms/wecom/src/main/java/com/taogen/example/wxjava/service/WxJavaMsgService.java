package com.taogen.example.wxjava.service;

import com.taogen.example.config.WeComProperties;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpChatService;
import me.chanjar.weixin.cp.api.WxCpMessageService;
import me.chanjar.weixin.cp.bean.message.WxCpAppChatMessage;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Taogen
 */
@Service
public class WxJavaMsgService {
    @Autowired
    private WeComProperties weComProperties;

    @Autowired
    private WxCpMessageService wxCpMessageService;

    @Autowired
    private WxCpChatService wxCpChatService;

    public void sendTextMsgToUser(String userId, String party, String tag, String content) throws WxErrorException {
        wxCpMessageService.send(WxCpMessage
                .TEXT()
                .agentId(weComProperties.getAppAgentId())
                .toUser(userId)
                .toParty(party)
                .toTag(tag)
                .content(content)
                .build());
    }

    public void sendTextMsgToGroup(String group, String content) throws WxErrorException {
        wxCpChatService.sendMsg(WxCpAppChatMessage.builder()
                .chatId(group)
                .msgType("text")
                .content(content)
                .build());
    }
}
