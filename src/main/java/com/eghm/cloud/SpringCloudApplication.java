package com.eghm.cloud;

import com.eghm.cloud.stream.Consumer;
import com.eghm.cloud.stream.Producer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * @author 二哥很猛
 */
@SpringBootApplication
@EnableBinding({Producer.class, Consumer.class})
@EnableDiscoveryClient
public class SpringCloudApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringCloudApplication.class, args);
    }



}
