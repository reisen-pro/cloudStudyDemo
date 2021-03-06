package com.project.cloud;

import com.reisen.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author Reisen
 * @title: OrderMain80
 * @projectName cloud
 * @description: TODO
 * @date 2020/6/19 0:08
 * 开启自定义的负载策略 @RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = MySelfRule.class)
 */
@SpringBootApplication
@EnableEurekaClient
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class, args);
    }
}
