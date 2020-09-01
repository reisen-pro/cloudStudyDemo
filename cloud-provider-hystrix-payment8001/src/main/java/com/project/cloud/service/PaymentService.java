package com.project.cloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author Reisen
 * @title: PaymentService
 * @projectName cloud
 * @description: TODO
 * @date 2020/8/30 16:25
 */
@Service
public class PaymentService {

    /**
     * 正常访问 返回ok
     *
     * @param id id
     * @return String
     */
    public String paymentInfo_OK(Integer id) {
        return " 线程池: " + Thread.currentThread().getName() + "paymentInfo_OK,id" + id + "\t" + "O(∩_∩)O哈哈~";
    }

    /**
     * 超时访问
     * @see com.netflix.hystrix.HystrixCommandProperties
     * @param id id
     * @return String
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String paymentInfo_TimeOut(Integer id) {
        //int age = 10 / 0;
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return " 线程池: " + Thread.currentThread().getName() + "paymentInfo_TimeOut,id" + id + "\t" + "O(∩_∩)O哈哈~耗时+" + 3 + "+秒钟";
    }

    public String paymentInfo_TimeOutHandler(Integer id) {
        return " 线程池: " + Thread.currentThread().getName() + "系统繁忙或运行报错，请稍后再试,id" + id + "😭";
    }

    /**
     * 服务熔断
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//时间期窗口
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")//失败率
    })
    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("******id不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号：" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(Integer id) {
        return "id 不能为负数，请稍后再试，😭  id:" + id;
    }
}
