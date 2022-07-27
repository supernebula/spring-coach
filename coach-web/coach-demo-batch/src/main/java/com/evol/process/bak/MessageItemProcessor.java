//package com.evol.process.bak;
//
//import com.evol.domain.model.Message;
//import org.springframework.batch.item.ItemProcessor;
//import java.time.LocalDateTime;
//
//public class MessageItemProcessor  implements ItemProcessor<Message, Message> {
//
//    @Override
//    public Message process(Message item) {
//        item.setLastModifiedTime(LocalDateTime.now());
//        return item;
//    }
//}
