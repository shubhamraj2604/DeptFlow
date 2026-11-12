package com.example.DeptFlow.Messaging.consumer;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import java.io.IOException;
import org.springframework.stereotype.Service;

import com.example.DeptFlow.Service.ImportService;

@Service 
public class Consumer {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(Consumer.class);
    
    private final ImportService importService;
    public Consumer(ImportService importService){
        this.importService = importService; 
    }
    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void consume(String message) throws IOException {
        LOGGER.info("Received Message -> {}" , message);
        importService.importEmployees(message);
        LOGGER.info("Message processed successfully: {}" , message);
    }
}
