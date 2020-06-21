package com.lv.cloud.dao;

import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

public interface AccountDao {
    int decrease(@Param("userId") Long userId,@Param("money") BigDecimal money);
}
