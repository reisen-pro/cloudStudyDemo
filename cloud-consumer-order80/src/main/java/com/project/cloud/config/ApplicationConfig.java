package com.project.cloud.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author Reisen
 * @title: ApplicationConfig
 * @projectName cloud
 * @description: TODO
 * @date 2020/6/19 0:17
 */
@SpringBootConfiguration
public class ApplicationConfig {
    @Bean
    //@LoadBalanced//开启负载均衡
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
