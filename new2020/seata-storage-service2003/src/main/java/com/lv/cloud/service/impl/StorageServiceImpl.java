package com.lv.cloud.service.impl;

import com.lv.cloud.dao.StorageDao;
import com.lv.cloud.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("storageService")
public class StorageServiceImpl implements StorageService {
    @Autowired
    private StorageDao storageDao;

    @Override
    public int decrease(Long productId, Integer count) {
        return storageDao.decrease(productId,count);
    }
}
