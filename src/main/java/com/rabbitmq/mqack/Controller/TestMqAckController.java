package com.rabbitmq.mqack.Controller;

import com.rabbitmq.mqack.producerMq.MQAckSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 作者：qiwj
 * 时间：2020/4/14
 */
@RequestMapping("/mq")
@Controller
public class TestMqAckController {
    @Autowired
    private MQAckSender mqAckSender;
    @RequestMapping("/ack")
    @ResponseBody
    public void springAck() {
        try {
            for (int i=0;i<10;i++){
                mqAckSender.ackMQSender("这是第"+i+"个消息");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/info")
    @ResponseBody
    public String springInfo() {
       return "212121";
    }
}
