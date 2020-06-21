package com.lv.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient //该注解用于向使用 consul或者zookeeper作为注册中心时注册服务
//在启动该微服务的时候就能去加载我们的自定义Ribbon配置类，从而使配置生效,并指定某个服务使用这个自定义负载均衡策略
//@RibbonClient(name = "CLOUD-PROVIDER-PAYMENT",configuration = ApplicationContextConfig.class)
public class Cloud_zookeeper_consumer_order80 {
    public static void main(String[] arg){
        SpringApplication.run(Cloud_zookeeper_consumer_order80.class,arg);
    }

}

