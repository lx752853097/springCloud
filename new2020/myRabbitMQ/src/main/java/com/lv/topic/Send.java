package com.lv.topic;

import com.lv.utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

//消息发送到没有队列绑定的交换机时，消息将丢失，因为，交换机没有存储消息的能力，消息只能存在在队列中。
public class Send {

    public final static String EXCHANGE_NAME = "test_exchange_fanout";

    public static void main(String[] args) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明exchange，并设置交换机类型
        //RabbitMQ常用的Exchange Type有fanout、direct、topic、headers这四种
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");

        // 消息内容
        String message = "Hello World!";
        channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();

    }
}
