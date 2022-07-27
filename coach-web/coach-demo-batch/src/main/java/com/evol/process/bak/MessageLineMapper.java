//package com.evol.process.bak;
//
//import com.evol.domain.model.Message;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.databind.MappingJsonFactory;
//import org.springframework.batch.item.file.LineMapper;
//
//import java.util.Map;
//
//public class MessageLineMapper implements LineMapper<Message> {
//    private MappingJsonFactory factory = new MappingJsonFactory();
//
//    @Override
//    public Message mapLine(String line, int lineNumber) throws Exception {
//        JsonParser parser = factory.createParser(line);
//        Map<String, Object> map = (Map) parser.readValueAs(Map.class);
//        Message message = new Message();
//        //... // 转换逻辑
//        return message;
//    }
//}
