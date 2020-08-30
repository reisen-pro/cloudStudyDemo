package com.project.cloud.config;

import feign.Logger;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Reisen
 * @title: FeignConfig
 * @projectName cloud
 * @description: 使用 @Configuration 代替xml文件配置
 * 这里一开始用错了 @Configurable
 * @date 2020/8/30 15:34
 */
@Configuration
public class FeignConfig {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
