package com.lv.cloud.controller;

import com.lv.cloud.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class StorageController {
    @Autowired
    private StorageService storageService;

    @RequestMapping("decrease")
    @ResponseBody
    public Map<String,Object> decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count){
        Map<String, Object> map = new HashMap<>();
        storageService.decrease(productId,count);
        map.put("success",true);
        return map;
    }
}
