package com.eghm.cloud.stream;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 殿小二
 * @date 2021/5/19
 */
@RestController
@RefreshScope
public class RefreshController implements ApplicationContextAware {
    
    @Value("${campus}")
    private String campus;
    
    @Value("${school}")
    private String school;
    
    
    @RequestMapping("/refreshValue")
    public String refreshValue() {
        return campus + school;
    }
    
    @GetMapping("/userBean")
    public UserBean userBean () {
        return new UserBean();
    }
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        String campus3 = applicationContext.getEnvironment().getProperty("campus");
        System.out.println(campus3);
    }
}
