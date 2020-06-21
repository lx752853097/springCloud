package com.lv.cloud.service.fallBackFactory;

import com.lv.cloud.entity.Payment;
import com.lv.cloud.service.PaymentService;
import com.lv.cloud.utils.JsonResult;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PaymentFallback implements PaymentService {
    @Override
    public Map<String, Object> getAll() {
        return null;
    }

    @Override
    public JsonResult getPaymentById(Long id) {
        return null;
    }

    @Override
    public JsonResult addPayment(Payment payment) {
        return null;
    }

    @Override
    public Map<String, Object> getAllTimeOut() throws InterruptedException {
        return null;
    }
}
