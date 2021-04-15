package com.eghm.cloud.stream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 殿小二
 * @date 2021/4/13
 */
@Configuration
@Slf4j
public class Config {

    @Bean
    public UserBean user() {
        UserBean bean = new UserBean();
        bean.setName("三哥");
        bean.setAge(2);
        log.error("[{}]", bean);
        return bean;
    }

    @Bean
    public static UserBean userBean() {
        UserBean bean = new UserBean();
        bean.setName("二哥");
        bean.setAge(2);
        log.error("[{}]", bean);
        return bean;
    }
}
