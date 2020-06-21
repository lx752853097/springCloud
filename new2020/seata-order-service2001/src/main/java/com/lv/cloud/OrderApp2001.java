package com.lv.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})//禁用默认的自动配置数据源类,采用自己的代理数据源
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.lv.cloud.dao")
public class OrderApp2001 {
    public static void main(String[] args){
        SpringApplication.run(OrderApp2001.class,args);
    }
}
