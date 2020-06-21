package com.lv.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableConfigServer //这个包含了@EnableEurekaClient注解
//@EnableEurekaClient
public class Config_App3344 {
    public static void main(String[] agrg){
        SpringApplication.run(Config_App3344.class,agrg);
    }
}
