package com.rdas

import com.rdas.model.Message
import com.rdas.repo.InMemoryMessageRepository
import com.rdas.repo.MessageRepository
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.convert.converter.Converter

/**
 * Created by rdas on 04/07/2016.
 */
@SpringBootApplication
class WebUiApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebUiApplication.class, args)
    }

    @Bean
    public MessageRepository messageRepository() {
        return new InMemoryMessageRepository()
    }

    @Bean
    public Converter<String, Message> messageConverter() {
        return new Converter<String, Message>() {
            @Override
            public Message convert(String id) {
                return messageRepository().findMessage(Long.valueOf(id))
            }
        };
    }
}
