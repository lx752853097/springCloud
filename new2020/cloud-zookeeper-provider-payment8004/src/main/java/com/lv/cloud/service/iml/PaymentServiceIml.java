package com.lv.cloud.service.iml;


import com.lv.cloud.dao.PaymentDao;
import com.lv.cloud.entity.Payment;
import com.lv.cloud.service.PaymentService;
import com.sun.org.apache.bcel.internal.generic.JSR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import sun.net.www.content.text.Generic;

import javax.swing.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Service("paymentService")
public class PaymentServiceIml implements PaymentService {
    @Autowired
    private PaymentDao paymentDao;

    //这个跟注解 不是同一个数据库名字，所以查询的数据都不一样；
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private CacheManager cacheManager;

    /*通过@EnableCaching注解自动化配置合适的缓存管理器（CacheManager），
    Spring Boot根据下面的顺序去侦测缓存提供者：

    Generic
    JCache (JSR-107)
    EhCache 2.x
    Hazelcast
    Infinispan
    Redis
    Guava
    Simple
    可以通过配置属性spring.cache.type来强制指定*/

    //value：指非关系数据库名字；
    //cacheManager:默认是ConcurrentMapCacheManager组件；会去指定的cacheManager中得到cache来进行查找
    //keyGenerator主键生成策略默认是simplekeyGenerator；如keyGenerator="simplekeyGenerator";也可以是自定义的生成器 keyGenerator="mykeyGenerator"；keyGenerator= "注入容器的bean的id"
    //key、keyGenerator二选一
    @Cacheable(value = "shiro-activeSessionCache")
    @Override
    public List<Payment> getAll() {
        Iterator<String> iterator = cacheManager.getCacheNames().iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        return paymentDao.getAll();
    }

    @Override
    public int add(Payment payment) {
        return paymentDao.add(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
