package com.lv.cloud.controller;

import com.lv.cloud.service.IMassageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MessageController {

    @Resource
    private IMassageService messageService;

    @RequestMapping("/sendMessage")
    @ResponseBody
    public Map<String,Object> sendMessage(){
        Map<String,Object> map = new HashMap<String,Object>();
        messageService.send();
        return map;
    }
    @RequestMapping("/sendMyProcessorMessage")
    @ResponseBody
    public Map<String,Object> sendMyProcessorMessage(){
        Map<String,Object> map = new HashMap<String,Object>();
        messageService.sendMyProcessor();
        return map;
    }
}
