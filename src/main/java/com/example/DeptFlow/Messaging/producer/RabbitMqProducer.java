package com.example.DeptFlow.Messaging.producer;

import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service 
public class RabbitMqProducer {
    private final RabbitTemplate rabbitTemplate;
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(RabbitMqProducer.class);
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value ("${rabbitmq.queue.key}")
    private String key;

    public RabbitMqProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }
    

    public void sendMessage(String message){
      LOGGER.info(String.format("message sent-> %s", message));
      rabbitTemplate.convertAndSend(exchange , key , message);
      LOGGER.info("Message sent to the queue: " + message);
    }
}
