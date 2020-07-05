package com.project.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Reisen
 * @title: Eureka7001
 * @projectName cloud
 * @description: Eureka自我保护 某一时刻某一个微服务不可用了，Eureka不会立刻清理，依旧会对该微服务的信息进行保存 属于CAP 中 AP的思想 可以关闭
 * @date 2020/6/20 23:29
 */
@SpringBootApplication
@EnableEurekaServer
public class Eureka7001 {
    public static void main(String[] args) {
        SpringApplication.run(Eureka7001.class,args);
    }
}
