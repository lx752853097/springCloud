package com.lv.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.lv.cloud.dao")
@EnableDiscoveryClient
public class Cloud_consul_provider_payment8006 {
    public static void main(String[] arg){
        SpringApplication.run(Cloud_consul_provider_payment8006.class,arg);
    }

}
