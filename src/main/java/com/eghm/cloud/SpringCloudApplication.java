package com.eghm.cloud;

import com.eghm.cloud.stream.Consumer;
import com.eghm.cloud.stream.Producer;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * @author 二哥很猛
 */
@SpringBootApplication
@EnableBinding({Producer.class, Consumer.class})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.eghm.cloud.feign")
public class SpringCloudApplication {


    public static void main(String[] args) {
        new SpringApplicationBuilder().web(WebApplicationType.SERVLET).sources(SpringCloudApplication.class).run(args);
    }



}
