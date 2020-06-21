package com.lv.cloud.Controller;

import com.lv.cloud.config.RedisConfig;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PaymentController {
    @Autowired
    public PaymentService paymentService;

    @Resource
    private DiscoveryClient client;

    @Value("${server.port}")
    private String port;

    private static final Logger lg = LoggerFactory.getLogger(RedisConfig.class);

    @RequestMapping("getAll")
    @ResponseBody
    public Map<String, Object> getAll() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<String> services = client.getServices();
        System.out.println("8001服务提供方！");
        for (String servie : services) {
            System.out.println(servie);
        }
        List<Payment> list =  paymentService.getAll();
        map.put("data",list);
        map.put("post",port);
        return map;
    }

    @RequestMapping("/getPaymentById/{id}")
    @ResponseBody
    public JsonResult getPaymentById(@PathVariable("id") Long id){
        JsonResult jsonResult = new JsonResult();
        Payment payment = paymentService.getPaymentById(id);
        jsonResult.setMsg("成功");
        jsonResult.setObj(payment);
        return jsonResult;
    }

    //@RequestBody 一定要加
    @RequestMapping("/payment/create")
    @ResponseBody
    public JsonResult addPayment(@RequestBody Payment payment){
        JsonResult jsonResult = new JsonResult();
        int add = paymentService.add(payment);
        if(add>0){
            jsonResult.setObj(payment);
        }else{
            jsonResult.setObj(null);
        }
        return  jsonResult;
    }

    @RequestMapping("/getAllTimeOut")
    @ResponseBody
    public Map<String, Object> getAllTimeOut() throws InterruptedException {
        Map<String, Object> map = new HashMap<String, Object>();
        Thread.sleep(3000);
        List<String> services = client.getServices();
        System.out.println("8001服务提供方！");
        for (String servie : services) {
            System.out.println(servie);
        }
        List<Payment> list =  paymentService.getAll();
        map.put("data",list);
        map.put("post",port);
        return map;
    }
}
