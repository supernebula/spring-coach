package com.evol.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * JSON工具类
 */
@Slf4j
public class JsonUtil {

    public static String ParseString(Object obj) {

        String jsonStr = null;

        try{
            ObjectMapper mapper = new ObjectMapper();
            jsonStr = mapper.writeValueAsString(obj);
        }catch (Exception ex){
            log.error("jackson序列化失败", ex);
        }
        return jsonStr;
    }

    public  static<T> T ParseObject(String jsonStr, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        T obj = null;
        try {
            obj = mapper.readValue(jsonStr, clazz);
        }catch (Exception ex){
            log.error("jackson反序列化失败", ex);
        }
        return obj;
    }
}
