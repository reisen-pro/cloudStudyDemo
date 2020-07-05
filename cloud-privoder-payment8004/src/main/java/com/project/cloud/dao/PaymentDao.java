package com.project.cloud.dao;

import com.project.cloud.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Reisen
 */
@Mapper
public interface PaymentDao {
    /**
     * create new
     * @param payment object
     * @return result flag
     */
    int create(Payment payment);

    /**
     * query payment by id
     * @param id key
     * @return payment if not null
     */
    Payment getPaymentById(@Param("id") Long id);
}
