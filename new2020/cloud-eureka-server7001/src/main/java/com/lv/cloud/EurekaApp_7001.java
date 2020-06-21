package com.lv.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaApp_7001 {

    public static void main(String[] arg){
        SpringApplication.run(EurekaApp_7001.class,arg);
    }
}
