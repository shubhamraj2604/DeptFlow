package com.example.DeptFlow.Messaging.config;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration 
public class RabbitMqConfig {
    
    @Value("${rabbitmq.queue.name}")
    private String queueName;
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value ("${rabbitmq.queue.key}")
    private String key;

    @Bean
    public Queue queue(){
        return new Queue(queueName , true);
        // true means the queue survives a RabbitMQ broker restart
    }
    @Bean 
    public TopicExchange exchange(){
        return new TopicExchange(exchange , true , false);
        /*
        exchange → Exchange name.
        true → Durable.
        false → Not auto-deleted. 
        */
    }

    @Bean
    public Binding binding(){
        return BindingBuilder
        .bind(queue())
        .to(exchange())
        .with(key);
    }
}
