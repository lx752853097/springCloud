package com.lv.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosConfig_Client3377 {
    public static void main(String[] args){
        SpringApplication.run(NacosConfig_Client3377.class,args);
    }
}
