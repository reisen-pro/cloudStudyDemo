package com.project.cloud.config;

import feign.Logger;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

/**
 * @author Reisen
 * @title: FeignConfig
 * @projectName cloud
 * @description: TODO
 * @date 2020/8/30 15:34
 */
@Configurable
public class FeignConfig {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
