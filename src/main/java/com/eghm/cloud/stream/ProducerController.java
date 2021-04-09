package com.eghm.cloud.stream;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 殿小二
 * @date 2021/4/2
 */
@RestController
public class ProducerController {

    @Autowired
    private Producer producer;

    @RequestMapping("/sendMsg")
    public String sendMsg() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("userId", "1377496792049983490");
        map.put("orgId", "1333969383692001282");
        producer.write().send(MessageBuilder.withPayload(map).setHeader("myHeader", "headValue").build());
        return "OK";
    }

    @RequestMapping("/sendMsgTag1")
    public String sendMsgTag1() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("tag1", "tag1");
        producer.write().send(MessageBuilder.withPayload(map).setHeader(RocketMQHeaders.TAGS, "tag1").build());
        return "OK";
    }

    @RequestMapping("/sendMsgHeader")
    public String sendMsgHeader() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("condition", "condition");
        producer.write().send(MessageBuilder.withPayload(map).setHeader("cond", "school").build());
        return "OK";
    }

    @RequestMapping("/sendMsgTag2")
    public String sendMsgTag2() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("tag2", "tag2");
        producer.write().send(MessageBuilder.withPayload(map).setHeader(RocketMQHeaders.TAGS, "tag2").build());
        return "OK";
    }

    @StreamListener(Consumer.CONSUMER_CHANNEL)
    public void consumerChannel(@Payload Map<String, Object> map) {
        // 没有设置tags 默认能接收全部 包含所有条件的
        System.out.println("consumerChannel:" + JSON.toJSONString(map));
    }

    @StreamListener(value = Consumer.CONSUMER_CHANNEL, condition = "headers['cond']=='school'")
    public void consumerChannelCondition(@Payload Map<String, Object> map) {
        // 只接收符合该条件的
        System.out.println("consumerChannel condition :" + JSON.toJSONString(map));
    }

    @StreamListener(Consumer.TAG_CONSUMER_CHANNEL)
    public void consumerChannel2(@Payload Map<String, Object> map) {
        // 设置tags后 只能接收包含tags数据
        System.out.println("tagConsumerChannel:" + JSON.toJSONString(map));
    }
}
