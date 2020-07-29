package com.taogen.example.wechat.service.impl;

import com.taogen.example.wechat.constant.WxEventType;
import com.taogen.example.wechat.constant.WxMessageType;
import com.taogen.example.wechat.service.WxMessageService;
import com.taogen.example.wechat.vo.WxTextMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Taogen
 */
@Service
public class WxMessageServiceImpl implements WxMessageService {
    private static final Logger logger = LogManager.getLogger();

    public WxTextMessage getReplayMessageByReceive(WxTextMessage receiveMessage) {
        WxTextMessage replyMessage = new WxTextMessage();
        replyMessage.setMsgType("text");
        replyMessage.setCreateTime(System.currentTimeMillis() / 1000 + "");
        if (receiveMessage != null) {
            replyMessage.setToUserName(receiveMessage.getFromUserName());
            replyMessage.setFromUserName(receiveMessage.getToUserName());
            String messageContent = getReplyMessageContent(receiveMessage);
            replyMessage.setContent(messageContent);
        }
        return replyMessage;
    }

    private String getReplyMessageContent(WxTextMessage receiveMessage) {
        String content = null;
        String messageType = receiveMessage.getMsgType();
        if (messageType != null) {
            logger.debug("Wechat message type is {}.", messageType);
            List<String> messageTypeList = Stream.of(WxMessageType.values()).map(Enum::name).collect(Collectors.toList());

            if (WxMessageType.EVENT.toString().equalsIgnoreCase(messageType)) {
                content = getEventReplyContent(receiveMessage);
            } else if (WxMessageType.TEXT.toString().equalsIgnoreCase(messageType)) {
                content = "Hello! Your message is " + receiveMessage.getContent();
            } else if (messageTypeList.contains(messageType.toUpperCase())) {
                content = "You send a(an) " + messageType;
            } else {
                content = "Don't support this message type!";
            }
        }
        logger.debug("Reply content is {}", content);
        return content;
    }

    private String getEventReplyContent(WxTextMessage receiveMessage) {
        String replyContent = null;
        List<String> eventList = Stream.of(WxEventType.values()).map(Enum::name).collect(Collectors.toList());
        String eventType = receiveMessage.getEventType();
        if (eventType == null) {
            replyContent = "event is null!";
        }

        if (eventList.contains(eventType.toUpperCase())) {
            if (WxEventType.CLICK.toString().equalsIgnoreCase(eventType)) {
                replyContent = "You clicked the menu!";
            } else if (WxEventType.LOCATION.toString().equalsIgnoreCase(eventType)) {
                replyContent = "You upload your location!";
            } else if (WxEventType.SCAN.toString().equalsIgnoreCase(eventType)) {
                replyContent = "You scan the QR code of the official account.";
            } else if (WxEventType.SUBSCRIBE.toString().equalsIgnoreCase(eventType)) {
                replyContent = "You followed the official account by scan QR code.";
            } else if (WxEventType.UNSUBSCRIBE.toString().equalsIgnoreCase(eventType)) {
                replyContent = "You unfollowed the official account.";
            } else if (WxEventType.VIEW.toString().equalsIgnoreCase(eventType)) {
                replyContent = "You clicked the link of menu!";
            }
        } else {
            replyContent = "Don't support this event type!";
        }
        return replyContent;
    }
}
