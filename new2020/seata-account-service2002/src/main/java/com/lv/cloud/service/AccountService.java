package com.lv.cloud.service;

import java.math.BigDecimal;
import java.util.Map;

public interface AccountService {
    public int decrease(Long userId, BigDecimal money);

    public int decreaseERROR(Long userId, BigDecimal money);
}
