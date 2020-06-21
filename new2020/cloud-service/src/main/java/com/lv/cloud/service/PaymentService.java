package com.lv.cloud.service;

import com.lv.cloud.entity.Payment;
import com.lv.cloud.service.fallBackFactory.ClientServiceFallbackFactory;
import com.lv.cloud.service.fallBackFactory.PaymentFallback;
import com.lv.cloud.utils.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

//@Component
//@FeignClient(value = "CLOUD-PROVIDER-PAYMENT",fallbackFactory = ClientServiceFallbackFactory.class)
//注册进nacos
@FeignClient(value = "nacos-payment-privider",fallbackFactory = ClientServiceFallbackFactory.class)
//@FeignClient(value = "CLOUD-PROVIDER-PAYMENT",fallback = PaymentFallback.class)
public interface PaymentService {

    @RequestMapping("/getAll")
    @ResponseBody
    public Map<String, Object> getAll();

    @RequestMapping("/getPaymentById/{id}")
    @ResponseBody
    public JsonResult getPaymentById(@PathVariable("id") Long id);

    @RequestMapping("/payment/create")
    @ResponseBody
    public JsonResult addPayment(Payment payment);

    @RequestMapping("/getAllTimeOut")
    @ResponseBody
    public Map<String, Object> getAllTimeOut() throws InterruptedException;
}
