package com.eghm.cloud.stream;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.math.BigDecimal;

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
    public UserBean userDefault() {
        UserBean bean = new UserBean();
        bean.setName("四哥");
        bean.setAge(4);
        log.error("[{}]", bean);
        return bean;
    }

    @Bean
    public static UserBean userBean(UserBean user) {
        UserBean bean = new UserBean();
        bean.setName("二哥");
        bean.setAge(2);
        log.error("[{}] -- [{}]", bean, user);
        return bean;
    }
    
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer bigDecimalJackson2ObjectMapperBuilderCustomizer () {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.serializerByType(BigDecimal.class, ToStringSerializer.instance);
    }
}
