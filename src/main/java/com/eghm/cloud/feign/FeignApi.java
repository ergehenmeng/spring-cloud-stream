package com.eghm.cloud.feign;

import com.eghm.cloud.stream.UserBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 殿小二
 * @date 2021/5/22
 */
@FeignClient(name = "springCloudProducer", fallback = FeignApiImpl.class)
public interface FeignApi {

    @GetMapping("/getName")
    String getName(@RequestParam("userName") String userName);
    
    @GetMapping("/getPathVariable/{userId}")
    String getPathVariable(@PathVariable("userId") Long userId);
    
    @PostMapping("/getBody")
    String getBody(@RequestBody UserBean bean);
}
