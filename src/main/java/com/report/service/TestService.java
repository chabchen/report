package com.report.service;

import com.report.mapper.TestMapper;
import com.report.sys.base.annotation.DataSource;
import com.report.sys.config.DataSourceEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TestService {

    @Autowired
    private TestMapper testMapper;

    @Cacheable(value="redisCache",key="'redis_user_'+#id")
    @DataSource(DataSourceEnum.THIRD_DATA_SOURCE_NAME)
    public List<Map> findAll(String id) {
        return testMapper.findAll(id);
    }

}
