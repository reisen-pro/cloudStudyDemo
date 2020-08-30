package com.project.cloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author Reisen
 * @title: LoadBalancer
 * @projectName cloud
 * @description: TODO
 * @date 2020/8/30 8:18
 */
public interface LoadBalancer {
    /**
     * 得到所有实例
     * @param serviceInstances 服务
     * @return ServiceInstance
     */
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
