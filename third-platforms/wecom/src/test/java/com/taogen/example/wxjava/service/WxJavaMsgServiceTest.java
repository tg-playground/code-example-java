package com.taogen.example.wxjava.service;

import me.chanjar.weixin.common.error.WxErrorException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class WxJavaMsgServiceTest {

    @Autowired
    private WxJavaMsgService wxJavaMsgService;


    @Test
    public void sendTextMsgToUser() throws WxErrorException {
        wxJavaMsgService.sendTextMsgToUser("taogen", null, null, "Hello " + System.currentTimeMillis());
    }

    @Test
    public void sendTextMsgToGroup() throws WxErrorException {
        wxJavaMsgService.sendTextMsgToGroup("myGroup1", "Hello " + System.currentTimeMillis());
    }
}
