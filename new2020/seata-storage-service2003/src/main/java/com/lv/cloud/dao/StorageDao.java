package com.lv.cloud.dao;

import org.apache.ibatis.annotations.Param;

public interface StorageDao {
    int decrease(@Param("productId") Long productId, @Param("count") Integer count);
}
