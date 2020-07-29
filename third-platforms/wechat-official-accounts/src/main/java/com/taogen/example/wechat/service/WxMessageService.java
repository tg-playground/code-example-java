package com.taogen.example.wechat.service;

import com.taogen.example.wechat.vo.WxTextMessage;

/**
 * @author Taogen
 */
public interface WxMessageService {
    WxTextMessage getReplayMessageByReceive(WxTextMessage receiveMessage);
}
