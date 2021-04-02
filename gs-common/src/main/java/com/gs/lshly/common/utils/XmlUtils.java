package com.gs.lshly.common.utils;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class XmlUtils {
    /**
     * 解析从微信服务器来的请求，将消息或者事件返回出去
     *
     * @param request http请求对象
     * @return 微信消息或者事件Map
     */
    public static Map<String, Object> parseXml(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();

        InputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader reader = factory.createXMLEventReader(inputStream);
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                if (event.isStartElement()) {
                    String tagName = event.asStartElement().getName().toString();
                    if (!"xml".equals(tagName)) {
                        String text = reader.getElementText();
                        map.put(tagName, text);
                        log.info(tagName+":"+text);
                    }
                }
            }
        } catch (IOException e) {
            log.error("IO出现异常", e);
        } catch (XMLStreamException e) {
            log.error("XML解析出现异常", e);
        } catch (Exception e) {
            log.error("其他异常", e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                log.error("IO出现异常", e);
            }
        }
        return map;
    }
}
