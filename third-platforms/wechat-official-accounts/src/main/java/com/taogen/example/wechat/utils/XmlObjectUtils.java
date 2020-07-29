package com.taogen.example.wechat.utils;

import com.taogen.example.wechat.vo.WxTextMessage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * @author Taogen
 */
public class XmlObjectUtils {
    public static Object getObjectByXmlString(String requestBody) {
        Object returnObject = null;
        JAXBContext jaxbContext = getJaxbContext(WxTextMessage.class);
        Unmarshaller unmarshaller = null;
        try {
            unmarshaller = jaxbContext.createUnmarshaller();
            returnObject = unmarshaller.unmarshal(
                    new StringReader(requestBody));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return returnObject;
    }

    public static String getXmlStringByObject(Object object) {
        JAXBContext jaxbContext = getJaxbContext(WxTextMessage.class);
        StringWriter stringWriter = new StringWriter();
        try {
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty("com.sun.xml.bind.xmlHeaders", "");
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            marshaller.marshal(object, stringWriter);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return stringWriter.toString();
    }

    private static JAXBContext getJaxbContext(Class clazz){
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(clazz);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return jaxbContext;
    }
}
