package com.lv.spring;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SpringMain {
    public static void main(final String... args) throws Exception {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());//24小时制
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(
                "classpath:spring/rabbitmq-context.xml");
        //RabbitMQ模板
        RabbitTemplate template = ctx.getBean(RabbitTemplate.class);

        //发送消息
        Map<String, Object> msg = new HashMap<String, Object>();
        msg.put("type", "1");
        msg.put("date", date);
        template.convertAndSend("type2", JSON.toJSONString(msg));
        Thread.sleep(3000);// 休眠1秒
        ctx.destroy(); //容器销毁
    }
}
