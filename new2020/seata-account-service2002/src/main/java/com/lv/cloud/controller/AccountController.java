package com.lv.cloud.controller;

import com.lv.cloud.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping("decrease")
    @ResponseBody
    public Map<String,Object> decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money){
        Map<String, Object> map = new HashMap<>();
        accountService.decrease(userId, money);
        map.put("success",true);
        return map;
    }
    @RequestMapping("decreaseERROR")
    @ResponseBody
    public Map<String,Object>  decreaseERROR(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money){
        Map<String, Object> map = new HashMap<>();
        accountService.decreaseERROR(userId, money);
        map.put("success",true);
        return map;
    }
}
