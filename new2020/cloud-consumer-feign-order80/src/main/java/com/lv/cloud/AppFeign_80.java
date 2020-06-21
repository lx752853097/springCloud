package com.lv.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;


//扫描与控制器在同一个包下以及其子包下的@Component注解，以及能将指定注解的类自动注册为Bean的@Service@Controller和@ Repository，
@SpringBootApplication
@EnableEurekaClient
//指定扫描的路径FeignClient注解的路径，如果不加，这个加了FeignClient的接口必须放在AppFeign_80主启动类同一包下或以及子包下；
@EnableFeignClients(value = {"com.lv.cloud"})
//在启动该微服务的时候就能去加载我们的自定义Ribbon配置类，从而使配置生效,并指定某个服务使用这个自定义负载均衡策略
//@RibbonClient(name = "CLOUD-PROVIDER-PAYMENT",configuration = ApplicationContextConfig.class)
@EnableHystrix
public class AppFeign_80 {
    public static void main(String[] arg){
        SpringApplication.run(AppFeign_80.class,arg);
    }

}

