package com.project.cloud.service;

import org.springframework.stereotype.Component;

/**
 * @author Reisen
 * @title: PaymentFallbackService
 * @projectName cloud
 * @description: TODO
 * @date 2020/9/1 0:41
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "-----PaymentFallbackService fall back-paymentInfo_ok,o(╥﹏╥)o";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "-----PaymentFallbackService fall back-paymentInfo_TimeOut,o(╥﹏╥)o";
    }
}
