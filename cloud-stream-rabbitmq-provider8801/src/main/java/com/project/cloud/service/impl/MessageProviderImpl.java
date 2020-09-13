package com.project.cloud.service.impl;

import cn.hutool.core.lang.UUID;
import com.project.cloud.service.MessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;

/**
 * @author Reisen
 * @title: MessageProviderImpl
 * @projectName cloud
 * @description: @EnableBinding(Source.class) 定义消息的推送管道
 * @date 2020/9/13 16:05
 */
@EnableBinding(Source.class)
@Slf4j
public class MessageProviderImpl implements MessageProvider {

    /**
     * 消息发送管道
     */
    @Resource
    private MessageChannel output;

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        log.info("*****serial:" + serial);
        return null;
    }
}
