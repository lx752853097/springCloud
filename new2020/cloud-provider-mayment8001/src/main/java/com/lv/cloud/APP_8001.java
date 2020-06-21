package com.lv.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("com.lv.cloud.dao")
@EnableEurekaClient
//@EnableCircuitBreaker//开启熔断
public class APP_8001 {
    public static void main(String[] arg){
        SpringApplication.run(APP_8001.class,arg);
    }

}
