package com.lv.cloud.dao;

import com.lv.cloud.entity.Payment;

import java.util.List;

public interface PaymentDao {
    //获取全部
    public List<Payment> getAll();

    public int add(Payment payment);

    Payment getPaymentById(Long id);
}
