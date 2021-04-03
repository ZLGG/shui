package com.gs.lshly.common.utils;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.StringUtils;
import org.xml.sax.InputSource;

/**
 * XML解析
 *
 * 
 * @author yingjun
 * @date 2021年3月18日 下午8:45:58
 */
public class XmlUtil {

    /**
     * 解析xml数据
     * */
    public static Map<String,Object> parseXml(byte[] xmlBytes, String charset) {
        SAXReader reader = new SAXReader(false);
        InputSource source = new InputSource(new ByteArrayInputStream(xmlBytes));
        source.setEncoding(charset);
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            Document doc = reader.read(source);
            Iterator<Element> iter = doc.getRootElement().elementIterator();
            while (iter.hasNext()) {
                Element e = iter.next();
                if (!e.elementIterator().hasNext()) {
                    map.put(e.getName(),e.getTextTrim());
                    continue;
                }
                Iterator<Element> iterator = e.elementIterator();
                Map<String,String> param = new HashMap<String, String>();
                while (iterator.hasNext()) {
                    Element el = iterator.next();
                    param.put(el.getName(),el.getTextTrim());
                }
                map.put(e.getName(),param);
            }
        }catch (Exception e) {
//            LogUtils.error("XmlParseError",e);
        }
        return map;
    }

    public static Map<String,Object> parseXml(String xml) {
        if (xml==null||"".equals(xml)) {
            return null;
        }
        Map<String,Object> result = new HashMap<String, Object>();
        try {
            Map<String,Object> map = parseXml(xml.getBytes("UTF-8"), "UTF-8");
            for (String key:map.keySet()) {
                Object value = map.get(key);
                result.put(key, value);
            }
            return result;
        }catch (Exception e) {
//            LogUtils.error("parse_xml_error", e);
        }
        return null;
    }
    
    public static void main(String args[]){
    	String str ="<?xml version=\"1.0\" encoding=\"UTF-8\"?><Result><Status>0</Status><ResultMsg>success</ResultMsg><OrderDetail><OrderID>2021031614263765836770167</OrderID><ProductCode>453890258,</ProductCode><validDate>2021-05-15</validDate><SMSMsg>【手机电子消费券】密码:453890258,你成功订购电子消费券测试产品,数量1张,有效期到2021年5月15日，客服:4001870118</SMSMsg></OrderDetail></Result>";
    	Map map = XmlUtil.parseXml(str);
    	System.out.println(map.get("Status").toString());
    	Map detail = (Map) map.get("OrderDetail");
    	System.out.println(detail.get("ProductCode").toString());
    	System.out.println(map);
    }
}
