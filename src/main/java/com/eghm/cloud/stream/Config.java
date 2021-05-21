package com.eghm.cloud.stream;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
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
    
    /**
     * 对象是BigDecimal时如果转为字符串形式的json,
     * 第一种方式: 在配置文件中generator添加配置
     * 第二种方式: 创建Jackson2ObjectMapperBuilderCustomizer的bean进行定制化类型的特殊处理(推荐)
     * 第三种方式: 直接设置ObjectMapper的配置属性(不推荐)
     */
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        // mapper.configure(JsonWriteFeature.WRITE_NUMBERS_AS_STRINGS.mappedFeature(), true);
        SimpleModule module = new SimpleModule();
        module.addSerializer(BigDecimal.class, ToStringSerializer.instance);
        mapper.registerModule(module);
        UserBean bean = new UserBean();
        bean.setAge(12);
        System.out.println(mapper.writeValueAsString(bean));
    }
}
