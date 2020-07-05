package com.project.cloud.controller;

import com.project.cloud.entity.CommonResult;
import com.project.cloud.entity.Payment;
import com.project.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("插入结果" + result);
        if (result > 0) {
            return new CommonResult(200, "插入成功,serverPort" + serverPort + " result:", result);
        } else {
            return new CommonResult(500, "插入失败");
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {

        Payment payment = null;
        try {
            payment = paymentService.getPaymentById(id);
        } catch (NumberFormatException e) {
            return new CommonResult(500, e.getMessage());
        }
        log.info("查询结果" + payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功,serverPort" + serverPort + "  ", payment);
        } else {
            return new CommonResult(500, "没有对应记录，查询ID：" + id);
        }

    }

    @ExceptionHandler
    public CommonResult doException(NumberFormatException e) {
        return new CommonResult(500, "数字转换异常");
    }
}
