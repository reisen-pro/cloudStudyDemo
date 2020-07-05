package com.project.cloud.service;


import com.project.cloud.entity.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author Reisen
 */
public interface PaymentService {
    /**
     * create a new payment object
     * @param payment object
     * @return count
     */
    int create(Payment payment);

    /**
     * query a payment by id
     * @param id id
     * @return payment
     */
    Payment getPaymentById(@Param("id") Long id);
}
