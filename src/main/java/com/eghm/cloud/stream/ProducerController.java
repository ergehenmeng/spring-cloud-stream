package com.eghm.cloud.stream;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * @author 殿小二
 * @date 2021/4/2
 */
@RestController
@Slf4j
public class ProducerController {

    @Autowired
    private Producer producer;

    @Autowired
    private DefaultMQProducer mqProducer;

    @GetMapping("/sendMsg/{path}/")
    public String sendMsgPath(@PathVariable("path") Long path) {
        log.info("path [{}]", path);
        return "OK";
    }

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
        log.info("consumerChannel [{}] 接收时间 [{}]", JSON.toJSONString(map), DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
    }

    @StreamListener(value = Consumer.CONSUMER_CHANNEL, condition = "headers['cond']=='school'")
    public void consumerChannelCondition(@Payload Map<String, Object> map) {
        // 只接收符合该条件的
        log.info("consumerChannel condition [{}]", JSON.toJSONString(map));
    }

    @StreamListener(Consumer.TAG_CONSUMER_CHANNEL)
    public void consumerChannel2(@Payload Map<String, Object> map) {
        // 设置tags后 只能接收包含tags数据
        log.info("tagConsumerChannel: [{}]" , JSON.toJSONString(map));
    }

    @RequestMapping("/sendMsgDelay")
    public String sendMsgDelay(@RequestParam("level") Integer level) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        // 1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
        Map<String, Object> map = Maps.newHashMap();
        map.put("delay", "delay");
        Message message = new Message();
        message.setTopic("outputTopic");
        message.setBody(JSON.toJSONString(map).getBytes());
        message.setDelayTimeLevel(level);
        mqProducer.send(message);
        log.info("消息发送时间 [{}]", DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        return "OK";
    }
}
