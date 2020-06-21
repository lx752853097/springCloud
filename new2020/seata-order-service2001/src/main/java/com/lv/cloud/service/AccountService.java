package com.lv.cloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Map;

@FeignClient("seata-account-service")
public interface AccountService {
    @RequestMapping("decrease")
    @ResponseBody
    public Map<String,Object> decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
    @RequestMapping("decreaseERROR")
    @ResponseBody
    public Map<String,Object>  decreaseERROR(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
