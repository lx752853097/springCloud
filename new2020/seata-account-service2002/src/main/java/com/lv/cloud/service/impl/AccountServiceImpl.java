package com.lv.cloud.service.impl;

import com.lv.cloud.dao.AccountDao;
import com.lv.cloud.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

    @Override
    public int decrease(Long userId, BigDecimal money) {
        return accountDao.decrease(userId,money);
    }

    @Override
    public int decreaseERROR(Long userId, BigDecimal money) {
        int a= 1/0;
        return accountDao.decrease(userId,money);
    }
}
