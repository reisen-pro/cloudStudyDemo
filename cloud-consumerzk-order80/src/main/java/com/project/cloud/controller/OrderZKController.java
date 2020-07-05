package com.project.cloud.controller;

import ch.qos.logback.core.util.ExecutorServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Reisen
 * @title: OrderZKController
 * @projectName cloud
 * @description: TODO
 * @date 2020/6/21 23:28
 */
@RestController
@Slf4j
public class OrderZKController {
    public static final String INVOKE_URL = "http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/zookeeper")
    public String paymentInfo() {
        String result = restTemplate.getForObject(INVOKE_URL + "/payment/zookeeper", String.class);
        return result;
    }

    public static void main(String[] args) {
        HashMap map = new HashMap(11,0.75F);
    }
}
