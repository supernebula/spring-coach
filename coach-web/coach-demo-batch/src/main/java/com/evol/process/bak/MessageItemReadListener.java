//package com.evol.process.bak;
//
//import com.evol.domain.model.Message;
//import lombok.SneakyThrows;
//import org.springframework.batch.core.ItemReadListener;
//
//import java.io.Writer;
//
//public class MessageItemReadListener implements ItemReadListener<Message> {
//    private Writer errorWriter;
//
//    public MessageItemReadListener(Writer errorWriter) {
//        this.errorWriter = errorWriter;
//    }
//
//    @Override
//    public void beforeRead() {
//    }
//
//    @Override
//    public void afterRead(Message item) {
//    }
//
//    @SneakyThrows
//    @Override
//    public void onReadError(Exception ex) {
//        errorWriter.write(String.format("%s%n", ex.getMessage()));
//    }
//}
