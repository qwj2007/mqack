package com.rabbitmq.mqack.CustomerMq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.mqack.config.MQConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 作者：qiwj
 * 时间：2020/4/14
 */
@Service
public class MQAckReceive {
    @RabbitListener(queues = MQConfig.ACK_QUEUE_A)
    public void process(String msg, Channel channel, Message message) throws IOException {
        System.out.println("ACK_QUEUE_A 收到  : " + msg);
        //log.info("ACK_QUEUE_A 收到  : " + msg);
        try {
            // 框架容器，是否开启手动ack按照框架配置
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            System.out.println("ACK_QUEUE_A 接受信息成功");
            //log.info("ACK_QUEUE_A 接受信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            //丢弃这条消息
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            //UE_A 接受信息异常");
            System.out.println("\"ACK_QUEUE_A 接受信息异常\"");
        }

    }

    @RabbitListener(queues = MQConfig.ACK_QUEUE_B)
    public void process2(String msg, Channel channel, Message message) throws IOException {

        System.out.println("ACK_QUEUE_B 收到  : " + msg);
        try {
            //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 重启应用后还会在发
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

            System.out.println("ACK_QUEUE_B 接受信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            //丢弃这条消息
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);

            System.out.println("ACK_QUEUE_B 接受信息异常");
        }

    }

}
