package com.lv.cloud.service.iml;

import com.lv.cloud.config.MyProcessor;
import com.lv.cloud.service.IMassageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
@EnableBinding(MyProcessor.class)
public class MessageService implements IMassageService {

    //@Autowired
    //MessageChannel output; //这个通道名称和配置文件中的通道要一样
    //@Autowired
    //MessageChannel log_output;//这个通道名称和配置文件中的通道要一样

    @Resource
    MyProcessor myProcessor;

    @Override

    public void send() {
        myProcessor.output().send(MessageBuilder.withPayload("你好Source").setHeader("partitionKey", "partitionKey").build());
        System.out.println("你好Source");
        //messageChannel.send(MessageBuilder.withPayload("你好").setHeader("partitionKey", "partitionKey").build());
    }

    @Override
    public void sendMyProcessor() {
        myProcessor.logOutput().send(MessageBuilder.withPayload("你好myProcessor").setHeader("partitionKey", "partitionKey").build());
        System.out.println("你好myProcessor");
    }
}
