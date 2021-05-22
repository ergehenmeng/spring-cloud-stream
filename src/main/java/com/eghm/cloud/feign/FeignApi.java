package com.eghm.cloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 殿小二
 * @date 2021/5/22
 */
@FeignClient(name = "springCloudProducer")
public interface FeignApi {

    @GetMapping("/getName")
    String getName(@RequestParam("userName") String userName);
}
