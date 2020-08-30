package com.project.cloud.controller;

import com.project.cloud.entity.CommonResult;
import com.project.cloud.entity.Payment;
import com.project.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Reisen
 */
@RestController
@Slf4j
public class PaymentController {

    public static void main(String[] args) {
    }

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

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

    @GetMapping(value = "/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("*****element" + element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getInstanceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return serverPort;
    }
}
