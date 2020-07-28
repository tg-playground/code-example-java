package com.taogen.example.wechat.controller;

import com.taogen.example.wechat.vo.WxTextMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;

/**
 * @author Taogen
 */
@RestController
public class WxMessageController extends BasicWxController {
    private static final Logger logger = LogManager.getLogger();

    SAXParserFactory factory = SAXParserFactory.newInstance();

    /**
     * Receive XML-based messages from Wechat.
     * This message receive URI is same with token auth URI, but this is HTTP post request.
     * @param httpEntity
     * @return
     */
    @PostMapping("/wx")
    public String messageReceive(HttpEntity<String> httpEntity){
        String requestBody = httpEntity.getBody();
        logger.debug("requestBody is {}", requestBody);

        JAXBContext jaxbContext = null;
        Unmarshaller unmarshaller = null;
        WxTextMessage receiveMessage = null;
        try {
            jaxbContext = JAXBContext.newInstance(WxTextMessage.class);
            unmarshaller = jaxbContext.createUnmarshaller();
            receiveMessage = (WxTextMessage) unmarshaller.unmarshal(
                    new StringReader(requestBody));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        logger.debug("Received wxTextMessage is: {}", receiveMessage);

        //reply
        WxTextMessage replyMessage = new WxTextMessage();
        if (receiveMessage != null) {
            replyMessage.setToUserName(receiveMessage.getFromUserName());
            replyMessage.setFromUserName(receiveMessage.getToUserName());
            String content = receiveMessage.getContent();
            replyMessage.setContent("hello! Your message is "+content);
        }
        replyMessage.setCreateTime(System.currentTimeMillis()/1000 + "");
        replyMessage.setMsgType("text");

        StringWriter stringWriter = new StringWriter();
        try {
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty("com.sun.xml.bind.xmlHeaders", "");
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            marshaller.marshal(replyMessage, stringWriter);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return stringWriter.toString();
    }
}
