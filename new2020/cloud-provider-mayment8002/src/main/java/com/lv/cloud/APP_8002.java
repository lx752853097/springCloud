package com.lv.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("com.lv.cloud.dao")
@EnableEurekaClient
public class APP_8002 {
    public static void main(String[] arg){
        SpringApplication.run(APP_8002.class,arg);
    }

}
