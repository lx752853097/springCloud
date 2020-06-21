package com.lv.simpleQueue;

import com.lv.utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

public class Recv {
    public final  static String  QUEUE_NAME = "q_test_01";

    public static void main(String[] args) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        // 定义队列的消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        // 定义队列的消费者
        // 监听队列，false表示手动返回完成状态，true表示自动
        channel.basicConsume(QUEUE_NAME,true,queueingConsumer);
        // 获取消息
        while (true){
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            byte[] body = delivery.getBody();
            String message = new String(body);
            System.out.println(" [x] Received '" + message + "'");
        }
    }
}
