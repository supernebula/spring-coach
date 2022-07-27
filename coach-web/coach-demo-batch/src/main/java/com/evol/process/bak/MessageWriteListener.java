//package com.evol.process.bak;
//
//import com.evol.domain.model.Message;
//import lombok.SneakyThrows;
//import org.springframework.batch.core.ItemWriteListener;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.io.Writer;
//import java.util.List;
//
//public class MessageWriteListener implements ItemWriteListener<Message> {
//
//    @Autowired
//    private Writer errorWriter;
//
//    @Override
//    public void beforeWrite(List<? extends Message> items) {
//    }
//
//    @Override
//    public void afterWrite(List<? extends Message> items) {
//    }
//
//    @SneakyThrows
//    @Override
//    public void onWriteError(Exception exception, List<? extends Message> items) {
//        errorWriter.write(String.format("%s%n", exception.getMessage()));
//        for (Message message : items) {
//            errorWriter.write(String.format("Failed writing message id: %s", message.getObjectId()));
//        }
//    }
//}
