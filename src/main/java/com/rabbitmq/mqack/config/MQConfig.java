package com.rabbitmq.mqack.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
/**
 * 作者：qiwj
 * 时间：2020/4/14
 */
public class MQConfig {
    /**
     * ACK 测试
     */
    public static final String ACK_QUEUE_A = "ack.test.queue.A";
    public static final String ACK_QUEUE_B = "ack.test.queue.B";
    public static final String ACK_EXCHANGE = "ack.test.exchange";

    /**
     * ACK TEST
     */
    @Bean
    public Queue ackQueueA() {
        return new Queue(ACK_QUEUE_A);
    }

    @Bean
    public Queue ackQueueB() {
        return new Queue(ACK_QUEUE_B);
    }

    @Bean
    public FanoutExchange ackFanoutExchange() {
        return new FanoutExchange(ACK_EXCHANGE);
    }

    @Bean
    public Binding ackBindingA() {
        return BindingBuilder.bind(ackQueueA()).to(ackFanoutExchange());
    }

    @Bean
    public Binding ackBindingB() {
        return BindingBuilder.bind(ackQueueB()).to(ackFanoutExchange());

    }
}
