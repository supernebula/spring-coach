package com.evol.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * xml 对象转换工具
 * @author admin
 */
public class XmlUtil {

    /**
     * 对象转xml
     * @param obj
     * @return
     */
    public String object2Xml(Object obj){
        try {
            String xmlStr = (new XmlMapper()).writeValueAsString(obj);
            return xmlStr;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * xml转泛型对象
     * @param xmlStr
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T xml2Object(String xmlStr, Class<T> clazz){
        try {
            T obj = (new XmlMapper()).readValue(xmlStr, clazz);
            return obj;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }


}
