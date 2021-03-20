package com.evol.utils;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * User: rizenguo
 * Date: 2014/11/1
 * Time: 14:06
 */
public class XMLParser {


    public static Map<String,Object> getMapFromXML(String xmlString) throws ParserConfigurationException, IOException, SAXException, IOException {

        //这里用Dom的方式解析回包的最主要目的是防止API新增回包字段
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        //InputStream is =  Util.getStringStream(xmlString);

        ByteArrayInputStream tInputStringStream = null;
        if (xmlString != null && !xmlString.trim().equals("")) {
            return new HashMap<String, Object>();
        }
        InputStream is = tInputStringStream = new ByteArrayInputStream(xmlString.getBytes("UTF-8"));
        //


        Document document = builder.parse(is);

        //获取到document里面的全部结点
        NodeList allNodes = document.getFirstChild().getChildNodes();
        Node node;
        HashMap<String, Object> map = new HashMap<String, Object>();

        int i=0;
        while (i < allNodes.getLength()) {
            node = allNodes.item(i);
            if(node instanceof Element){
                map.put(node.getNodeName(),node.getTextContent());
            }
            i++;
        }

        return map;
    }

    public static Map<String,String> getSortMapFromXML(String xmlString) throws IOException, SAXException, ParserConfigurationException {
        Map<String, Object> map = getMapFromXML(xmlString);
        SortedMap<String, String> sortMap = new TreeMap<>();
        map.entrySet().stream()
                .filter((e) -> e.getValue() != null && !StringUtils.EMPTY.equals(e.getValue()))
                .forEach((e) -> sortMap.put((String)e.getKey(), (String)e.getValue()));
        return sortMap;

    }





    public static String getXMLFromMap(Map<String, Object> map)throws Exception{
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");

        Set<String> set = map.keySet();
        Iterator<String> it = set.iterator();
        while(it.hasNext()){
            String key = it.next();
            sb.append("<"+key+">").append(map.get(key)).append("</"+key+">");
        }

        sb.append("</xml>");
        return sb.toString();
    }



}
