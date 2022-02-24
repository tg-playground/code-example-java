package com.taogen.example.wxjava;

import com.taogen.example.config.WeComProperties;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpChatService;
import me.chanjar.weixin.cp.api.WxCpMessageService;
import me.chanjar.weixin.cp.bean.message.WxCpAppChatMessage;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author Taogen
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class WxJavaMsgTest {
    @Autowired
    private WxCpMessageService wxCpMessageService;

    @Autowired
    private WxCpChatService wxCpChatService;

    @Autowired
    private WeComProperties weComProperties;

    @Test
    public void sendTextMsgToUser() throws WxErrorException {
        wxCpMessageService.send(WxCpMessage
                .TEXT()
                .agentId(weComProperties.getAppAgentId())
                .toUser("taogen")
//            .toParty("非必填，PartyID列表，多个接受者用‘|’分隔。当touser为@all时忽略本参数")
//            .toTag("非必填，TagID列表，多个接受者用‘|’分隔。当touser为@all时忽略本参数")
                .content("Hello " + System.currentTimeMillis())
                .build());
    }

    @Test
    public void sendTextMsgToGroup() throws WxErrorException {
        wxCpChatService.sendMsg(WxCpAppChatMessage.builder()
                .chatId("myGroup1")
                .msgType("text")
                .content("Hello " + System.currentTimeMillis())
                .build());
    }
}
