package com.lv.cloud.controller;

import com.lv.cloud.entity.Payment;
import com.lv.cloud.utils.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lv.cloud.service.PaymentService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PaymentController {
    //private static final String REST_URL_PREFIX ="http://127.0.0.1:8080";
    //private static final String REST_URL_PREFIX ="http://CLOUD-PROVIDER-PAYMENT";
    //@Autowired
    //private RestTemplate restTemplate;

    @Resource
    private PaymentService pymentService;

    @Resource
    private DiscoveryClient client;

    private static final Logger lg = LoggerFactory.getLogger(PaymentController.class);

    @RequestMapping("getAll")
    @ResponseBody
    public Map<String, Object> getAll() {
        Map<String, Object> map = pymentService.getAll();
        return map;
    }

    @RequestMapping("/getPaymentById/{id}")
    @ResponseBody
    public JsonResult getPaymentById(@PathVariable("id") Long id){
        JsonResult jsonResult = pymentService.getPaymentById(id);
        return jsonResult;
    }

    @RequestMapping("/payment/create")
    @ResponseBody
    public JsonResult addPayment(Payment payment){
        JsonResult jsonResult = pymentService.addPayment(payment);
        return  jsonResult;
    }

    @RequestMapping("/getAllTimeOut")
    @ResponseBody
   // @HystrixCommand(fallbackMethod = "getAllTimeOut1")
    public Map<String, Object> getAllTimeOut() throws InterruptedException {
        return pymentService.getAllTimeOut();
    }
    public Map<String, Object> getAllTimeOut1(){
        Map<String, Object> map =  new HashMap<String,Object>();
        return map;
    }
}
