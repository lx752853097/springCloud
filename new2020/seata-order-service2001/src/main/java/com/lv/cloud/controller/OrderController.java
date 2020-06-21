package com.lv.cloud.controller;

import com.lv.cloud.entity.Order;
import com.lv.cloud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/orderOK")
    @ResponseBody
    public Map<String,Object> orderOK(){
        Map<String, Object> map = new HashMap<>();
        Order order = new Order();
        order.setProductId(1L);
        order.setCount(20);
        order.setMoney(new BigDecimal(30));
        order.setUserId(1L);
        order.setStatus(0);
        orderService.create(order);
        map.put("success",true);
        map.put("msg",order);
        return map;
    }

    @RequestMapping("/orderERROR")
    @ResponseBody
    public Map<String,Object> orderERROR(){
        Map<String, Object> map = new HashMap<>();
        Order order = new Order();
        order.setProductId(1L);
        order.setCount(1);
        order.setMoney(new BigDecimal(1));
        order.setUserId(1L);
        order.setStatus(0);
        orderService.orderERROR(order);
        map.put("success",true);
        map.put("msg",order);
        return map;
    }
}
