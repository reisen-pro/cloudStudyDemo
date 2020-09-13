package com.project.cloud.controller;

import com.project.cloud.service.MessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Reisen
 * @title: SendMessageController
 * @projectName cloud
 * @description: 发送消息的控制器
 * @date 2020/9/13 16:15
 */
@RestController
public class SendMessageController {

    @Resource
    private MessageProvider messageProvider;

    @GetMapping(value = "/sendMessage")
    public String sendMessage() {
        return messageProvider.send();
    }
}
