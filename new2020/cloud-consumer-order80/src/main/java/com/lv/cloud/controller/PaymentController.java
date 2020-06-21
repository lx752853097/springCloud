package com.lv.cloud.controller;

import com.lv.cloud.entity.Payment;
import com.lv.cloud.utils.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Map;

@Controller
public class PaymentController {
    //private static final String REST_URL_PREFIX ="http://127.0.0.1:8080";
    private static final String REST_URL_PREFIX ="http://CLOUD-PROVIDER-PAYMENT";
    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient client;

    private static final Logger lg = LoggerFactory.getLogger(PaymentController.class);

    @RequestMapping("getAll")
    @ResponseBody
    public Map<String, Object> getAll() {
        Map map = restTemplate.getForObject(REST_URL_PREFIX + "/getAll", Map.class);
        System.out.println("服务调用方！");
        return map;
    }

    @RequestMapping("/getPaymentById/{id}")
    @ResponseBody
    public JsonResult getPaymentById(@PathVariable("id") Long id){
        JsonResult jsonResult = restTemplate.getForObject(REST_URL_PREFIX + "/getPaymentById/" + id, JsonResult.class);
        return jsonResult;
    }

    @RequestMapping("/payment/create")
    @ResponseBody
    public JsonResult addPayment(Payment payment){
        JsonResult jsonResult = restTemplate.postForObject(REST_URL_PREFIX + "/payment/create", payment, JsonResult.class);
        return  jsonResult;
    }
}
