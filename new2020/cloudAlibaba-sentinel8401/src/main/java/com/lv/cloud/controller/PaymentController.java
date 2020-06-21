package com.lv.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;


import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.lv.cloud.entity.Payment;
import com.lv.cloud.service.PaymentService;
import com.lv.cloud.utils.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PaymentController {
    //private static final String REST_URL_PREFIX ="http://127.0.0.1:8080";
    @Value("${service-url.nacos-payment-service}")
    private  String REST_URL_PREFIX;
    @Resource
    private PaymentService paymentService;


    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient client;

    private static final Logger lg = LoggerFactory.getLogger(PaymentController.class);

    @RequestMapping("getAll")
    @ResponseBody
    public Map<String, Object> getAll() {
        //Map map = restTemplate.getForObject(REST_URL_PREFIX + "/getAll", Map.class);
        Map<String, Object> map = paymentService.getAll();
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


    @RequestMapping("/testA")
    @ResponseBody
    //进行QPS流限制降级，无法对异常进行降级
    @SentinelResource(value = "testA",blockHandler = "blockExceptionBack")
    public Map<String, Object> testA() {
        Map map = restTemplate.getForObject(REST_URL_PREFIX + "/getAll", Map.class);
        System.out.println("服务调用方！");
        return map;
    }
    public Map<String, Object> blockExceptionBack(BlockException e) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("massage", e.getClass().getCanonicalName());
        System.out.println("服务调用方！");
        return map;
    }


    @RequestMapping("/testB")
    @ResponseBody
    //对异常进行降级,每秒请求大于5次，且大于阈值才会生效
    //异常降级仅针对业务异常，对 Sentinel 限流降级本身的异常（BlockException）不生效;两者是分开的，blockHandler针对sentinel监控平台设置的，fallback针对运行时异常
    //fallback只针对业务异常,即运行时异常
    //这两个一起配不冲突，一个针对QPS，一个针对线程；  如果QPS达到了阈值就BlockException，如果QPS没有达到阈值且线程运行时异常就fallback
    //exceptionsToIgnore 忽略某个异常，这个异常该抛出抛出
    @SentinelResource(value = "testB" , fallback = "exceptionBack",exceptionsToIgnore = {ArithmeticException.class})
    public Map<String, Object> testB() {
        int i = 1/0;
        Map map = restTemplate.getForObject(REST_URL_PREFIX + "/getAll", Map.class);
        System.out.println("服务调用方！");
        return map;
    }
    public Map<String, Object> exceptionBack(Throwable e) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("massage", e.getClass().getCanonicalName());
        System.out.println("服务调用方！");
        return map;
    }
}
