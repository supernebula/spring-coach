//package com.evol.config.bak;
//
//import com.evol.domain.model.Message;
//import com.evol.process.bak.MessageItemReadListener;
//import com.evol.process.bak.MessageLineMapper;
//import com.evol.process.bak.MessageWriteListener;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.item.database.JpaItemWriter;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.json.JsonParseException;
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.io.FileSystemResource;
//
//import javax.persistence.EntityManagerFactory;
//import java.io.File;
//import java.io.Writer;
//
//public class MessageMigrationJobConfiguration {
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    public Job messageMigrationJob(@Qualifier("messageMigrationStep") Step messageMigrationStep){
//        return jobBuilderFactory.get("messageMigrationJob")
//                .start(messageMigrationStep)
//                .build();
//    }
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Step messageMigrationStep(@Qualifier("jsonMessageReader") FlatFileItemReader<Message> jsonMessageReader,
//                                     @Qualifier("messageItemWriter") JpaItemWriter<Message> messageItemWriter,
//                                     @Qualifier("errorWriter") Writer errorWriter) {
//        return stepBuilderFactory.get("messageMigrationStep")
//                .<Message, Message>chunk(BatchConstant.CHUNK_SIZE)
//                .reader(jsonMessageReader).faultTolerant().skip(JsonParseException.class).skipLimit(BatchConstant.SKIP_LIMIT)
//                .listener(new MessageItemReadListener(errorWriter))
//                .writer(messageItemWriter).faultTolerant().skip(Exception.class).skipLimit(BatchConstant.SKIP_LIMIT)
//                .listener(new MessageWriteListener())
//                .build();
//    }
//
//    @Bean
//    public FlatFileItemReader<Message> jsonMessageReader() {
//        FlatFileItemReader<Message> reader = new FlatFileItemReader<>();
//        reader.setResource(new FileSystemResource(new File(BatchConstant.MESSAGE_FILE)));
//        reader.setLineMapper(new MessageLineMapper());
//        return reader;
//    }
//
//
//    @Autowired
//    private EntityManagerFactory entityManager;
//
//    @Bean
//    public JpaItemWriter<Message> messageItemWriter() {
//        JpaItemWriter<Message> writer = new JpaItemWriter<>();
//        writer.setEntityManagerFactory(entityManager);
//        return writer;
//    }
//
//
//
//
//}
