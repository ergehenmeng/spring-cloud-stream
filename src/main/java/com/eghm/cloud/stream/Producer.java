package com.eghm.cloud.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author 殿小二
 * @date 2021/3/31
 */
public interface Producer {

    @Output("producerChannel")
    MessageChannel write();

}
