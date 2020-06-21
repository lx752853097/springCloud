package com.lv.cloud.service;

import com.lv.cloud.entity.Order;

public interface OrderService {
    //创建一个订单 状态status为0
    public Order create(Order oder);

    public Order orderERROR(Order order);
}
