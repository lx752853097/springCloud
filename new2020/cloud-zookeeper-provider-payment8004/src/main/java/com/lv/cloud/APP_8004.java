package com.lv.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.lv.cloud.dao")
@EnableDiscoveryClient //该注解用于向使用 consul或者zookeeper作为注册中心时注册服务
public class APP_8004 {
    public static void main(String[] arg){
        SpringApplication.run(APP_8004.class,arg);
    }

}
