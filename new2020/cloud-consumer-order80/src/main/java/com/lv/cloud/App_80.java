package com.lv.cloud;

import com.lv.cloud.config.ApplicationContextConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient

//在启动该微服务的时候就能去加载我们的自定义Ribbon配置类，从而使配置生效,并指定某个服务使用这个自定义负载均衡策略
//@RibbonClient(name = "CLOUD-PROVIDER-PAYMENT",configuration = ApplicationContextConfig.class)
public class App_80 {
    public static void main(String[] arg){
        SpringApplication.run(App_80.class,arg);
    }

}

