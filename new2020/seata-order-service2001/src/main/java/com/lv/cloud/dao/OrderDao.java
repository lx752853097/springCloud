package com.lv.cloud.dao;

import com.lv.cloud.entity.Order;

public interface OrderDao {
    //创建一个订单 状态status为0
    public int create(Order oder);

    //修改订单
    public int update(Order oder);
}
