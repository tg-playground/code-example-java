package com.taogen.example.wecom;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class WeComMsgServiceTest {

    @Autowired
    private WeComMsgService weComMsgService;

    @Test
    void sendMsgToUser() throws IOException {
        String json = "{\n" +
                "  \"touser\": \"JiaTaoGen\",\n" +
                "  \"toparty\": \"\",\n" +
                "  \"totag\": \"\",\n" +
                "  \"msgtype\": \"text\",\n" +
                "  \"agentid\": 1000002,\n" +
                "  \"text\": {\n" +
                "    \"content\": \"你的快递已到，请携带工卡前往邮件中心领取。\\n出发前可查看<a href=\\\"http://work.weixin.qq.com\\\">邮件中心视频实况</a>，聪明避开排队。\"\n" +
                "  },\n" +
                "  \"safe\": 0,\n" +
                "  \"enable_id_trans\": 0,\n" +
                "  \"enable_duplicate_check\": 0\n" +
                "}";
        System.out.println(weComMsgService.sendMsgToUser(json));
    }
}
