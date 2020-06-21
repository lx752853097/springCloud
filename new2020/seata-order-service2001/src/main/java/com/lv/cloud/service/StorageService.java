package com.lv.cloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@FeignClient("seata-storage-service")
public interface StorageService {
    @RequestMapping("decrease")
    @ResponseBody
    public Map<String,Object> decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
