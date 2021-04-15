package com.eghm.cloud.stream;

import lombok.Data;

/**
 * @author 殿小二
 * @date 2021/4/13
 */
@Data
public class UserBean {

    private String name;

    private Integer age;

    public void initMethod() {
        System.out.println(name + ":" + age);
    }
}
