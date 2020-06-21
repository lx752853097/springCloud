package com.lv.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient //开启注册，服务发现者
@MapperScan("com.lv.cloud.dao")
public class Provider_payment9002 {
    public static void main(String[] args){
        SpringApplication.run(Provider_payment9002.class,args);
    }
}
