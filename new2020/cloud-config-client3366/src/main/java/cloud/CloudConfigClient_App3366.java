package cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CloudConfigClient_App3366 {
    public static void main(String[] args){
        SpringApplication.run(CloudConfigClient_App3366.class,args);
    }
}
