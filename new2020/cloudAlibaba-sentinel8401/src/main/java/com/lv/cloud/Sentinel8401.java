package com.lv.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(value = {"com.lv.cloud"})
public class Sentinel8401 {
    public static void main(String[] args){
        SpringApplication.run(Sentinel8401.class,args);
    }
}
