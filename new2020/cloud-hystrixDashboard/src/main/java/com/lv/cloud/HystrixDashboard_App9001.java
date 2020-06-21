package com.lv.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboard_App9001 {
    public static void main(String[] arg){
        SpringApplication.run(HystrixDashboard_App9001.class,arg);
    }
}
