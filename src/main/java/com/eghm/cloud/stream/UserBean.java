package com.eghm.cloud.stream;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NumberSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 殿小二
 * @date 2021/4/13
 */
@Data
public class UserBean {

    private String name;

    private Integer age;
    
    private BigDecimal score = BigDecimal.valueOf(0.07);
    
    public UserBean() {
    }
    
    public UserBean(BigDecimal score) {
        this.score = score;
    }
    
    public void initMethod() {
        System.out.println(name + ":" + age);
    }
}
