package com.lv.cloud.service;

import com.lv.cloud.entity.Payment;

import java.util.List;


public interface PaymentService {
    public List<Payment> getAll();

    public int add(Payment payment);

    Payment getPaymentById(Long id);
}
