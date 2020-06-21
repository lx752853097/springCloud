package com.lv.cloud.service.fallBackFactory;

import com.lv.cloud.entity.Payment;
import com.lv.cloud.service.PaymentService;
import com.lv.cloud.utils.JsonResult;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ClientServiceFallbackFactory implements FallbackFactory<PaymentService> {

    @Override
    public PaymentService create(Throwable throwable) {
        return new PaymentService() {
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
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("data","没有数据");
                return map;
            }
        };
    }
}
