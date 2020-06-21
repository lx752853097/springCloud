package com.lv.topic;

import com.lv.utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;


public class Recv {
    private final static String QUEUE_NAME = "test_queue_work1";

    private final static String EXCHANGE_NAME = "test_exchange_fanout";
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        // 绑定队列到交换机,并设置一个路由key，相当于匹配规则，如果发送者发送过来的key与这个路由key匹配就把消息放进与交换机绑定的队列；匹配路由规则是否生效，依赖于交换机类型
        //比如fanout类型的Exchange就会无视binding key，而是将消息路由到所有绑定到该Exchange的Queue。
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");

        // 同一时刻服务器只会发一条消息给消费者
        channel.basicQos(1);
        // 定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 监听队列，手动返回完成
        channel.basicConsume(QUEUE_NAME,false,consumer);

        while (true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [Recv] Received '" + message + "'");
            Thread.sleep(10);

            //应答rabbitMQ服务器
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        }
    }
}
