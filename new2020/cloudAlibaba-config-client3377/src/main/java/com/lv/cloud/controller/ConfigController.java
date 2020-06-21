package com.lv.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RefreshScope //动态刷新，Nacos自带动态刷新，修改nacos中的配置文件，再次调用接口，就会发现配置已经刷新
public class ConfigController {
    @Value("${config.info}")
    private String configInfo;

    @RequestMapping("/nacos/config")
    @ResponseBody
    public Map<String,Object> getConfig(){
        Map<String, Object> map = new HashMap<>();
        map.put("configInfo",configInfo);
        return map;
    }
}
