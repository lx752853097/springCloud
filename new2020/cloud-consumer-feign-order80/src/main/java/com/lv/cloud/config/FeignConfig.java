package com.lv.cloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    /**
     * NONE, 默认的不显示任何日志
     * BASIC,仅记录请求方法，URL，响应状态码，执行时间
     * HEADERS,除了BASIC，还有请求头和响应头信息
     * FULL; 除了HEADERS，还有请求和响应的正文及元数据
     * @return
     */
    @Bean
    public Logger.Level feignLoggerLevel(){
        // 请求和响应的头信息,请求和响应的正文及元数据
        return Logger.Level.FULL;
    }
}
