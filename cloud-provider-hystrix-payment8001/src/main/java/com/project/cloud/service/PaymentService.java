package com.project.cloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

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
     *
     * @param id id
     * @return String
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_TimeOut(Integer id) {
        int timeNumber = 5;
        //int age = 10 / 0;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return " 线程池: " + Thread.currentThread().getName() + "paymentInfo_TimeOut,id" + id + "\t" + "O(∩_∩)O哈哈~耗时+" + timeNumber + "+秒钟";
    }

    public String paymentInfo_TimeOutHandler(Integer id) {
        return " 线程池: " + Thread.currentThread().getName() + "paymentInfo_TimeOutHandler,id" + id + "😭";
    }
}
