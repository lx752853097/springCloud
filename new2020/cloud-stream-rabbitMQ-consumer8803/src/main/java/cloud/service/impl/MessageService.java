package cloud.service.impl;

import cloud.config.MyProcessor;
import cloud.service.IMessageService;
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
