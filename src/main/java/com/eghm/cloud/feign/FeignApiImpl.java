package com.eghm.cloud.feign;

import com.eghm.cloud.stream.UserBean;
import org.springframework.stereotype.Component;

/**
 * @author 殿小二
 * @date 2021/5/24
 */
@Component
public class FeignApiImpl implements FeignApi {
    
    @Override
    public String getName(String userName) {
        return "error + " + userName;
    }
    
    @Override
    public String getPathVariable(Long userId) {
        return "error + " + userId;
    }
    
    @Override
    public String getBody(UserBean bean) {
        return "error + " + bean;
    }
}
