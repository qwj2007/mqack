package com.rabbitmq.mqack.producerMq;

import com.rabbitmq.mqack.config.MQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 作者：qiwj
 * 时间：2020/4/14
 */
@Component
public class MQAckSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void ackMQSender(String msg) {
        //log.info("send ack message :" + msg);
        // 生产者发送消息到exchange后没有绑定的queue时将消息退回
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            //log.info("ackMQSender 发送消息被退回" + exchange + routingKey);
            System.out.println("ackMQSender 发送消息被退回" + exchange + routingKey);
        });
        // 生产者发送消息confirm检测
        this.rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (!ack) {
                System.out.println("ackMQSender 消息发送失败" + cause + correlationData.toString());
                //log.info("ackMQSender 消息发送失败" + cause + correlationData.toString());
            } else {
                //log.info("ackMQSender 消息发送成功 ");
                System.out.println("ackMQSender 消息发送成功 ");
            }
        });
        this.rabbitTemplate.convertAndSend(MQConfig.ACK_EXCHANGE, "", msg);
    }

}
