package com.lv.cloud.service.impl;

import com.lv.cloud.dao.OrderDao;
import com.lv.cloud.entity.Order;
import com.lv.cloud.service.AccountService;
import com.lv.cloud.service.OrderService;
import com.lv.cloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private StorageService storageService;
    @Autowired
    private AccountService accountService;

    @Override
    public Order create(Order order) {
        //创建订单
        orderDao.create(order);
        //减少库存；
        storageService.decrease(order.getProductId(),order.getCount());
        //减少账户money；
        accountService.decrease(order.getUserId(),order.getMoney());
        //修改订单状态
        order.setStatus(1);
        orderDao.update(order);

        return order;
    }

    @GlobalTransactional(name = "orderERROR",rollbackFor ={Exception.class} )
    @Override
    public Order orderERROR(Order order) {
        //创建订单
        orderDao.create(order);
        //减少库存；
        storageService.decrease(order.getProductId(),order.getCount());
        //减少账户money 出错；
        accountService.decreaseERROR(order.getUserId(),order.getMoney());
        //修改订单状态
        order.setStatus(1);
        orderDao.update(order);

        return order;
    }

}
