package com.lv.cloud.service.impl;

import com.lv.cloud.config.MyProcessor;
import com.lv.cloud.service.IMessageService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;


@EnableBinding(MyProcessor.class)
public class MessageService implements IMessageService {

    @Override
    @StreamListener(MyProcessor.INPUT)
    public void reciveMessages(String message) {
        System.out.println(message);
    }
}
