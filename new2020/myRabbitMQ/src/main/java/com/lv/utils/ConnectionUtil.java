package com.lv.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

public class ConnectionUtil {

    public  static Connection getConnection() throws IOException {
        //定义连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置服务地址
        connectionFactory.setHost("127.0.0.1");
        //端口
        connectionFactory.setPort(5672);
        //设置账号信息，用户名、密码、vhost
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        // 通过工程获取连接
        Connection connection = connectionFactory.newConnection();

        return connection;
    }
}
