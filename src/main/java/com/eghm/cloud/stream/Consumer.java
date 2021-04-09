package com.eghm.cloud.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author 殿小二
 * @date 2021/3/31
 */
public interface Consumer {

    String CONSUMER_CHANNEL = "consumerChannel";

    String TAG_CONSUMER_CHANNEL = "tagConsumerChannel";

    @Input(CONSUMER_CHANNEL)
    SubscribableChannel consumer();

    @Input(TAG_CONSUMER_CHANNEL)
    SubscribableChannel tagConsumer();
}
