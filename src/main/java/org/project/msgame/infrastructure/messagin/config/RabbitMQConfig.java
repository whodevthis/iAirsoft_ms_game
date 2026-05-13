package org.project.msgame.infrastructure.messagin.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory; // ✅ correcto
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;            // ✅ correcto
import org.springframework.beans.factory.annotation.Value;                     // ✅ correcto
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${app.rabbitmq.exchange}")
    private String exchange;

    @Value("${app.rabbitmq.queue}")
    private String queue;

    @Value("${app.rabbitmq.routing-key}")
    private String routingKey;

    @Bean
    public TopicExchange auditExchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    public Queue playerCreationQueue() {
        return QueueBuilder.durable(queue).build();
    }

    @Bean
    public Binding binding(Queue playerCreationQueue, TopicExchange auditExchange) {
        return BindingBuilder
                .bind(playerCreationQueue)
                .to(auditExchange)
                .with(routingKey);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
}