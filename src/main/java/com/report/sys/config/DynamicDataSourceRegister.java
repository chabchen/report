package com.report.sys.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.report.sys.domain.DataSourceEntity;
import com.report.sys.service.DataSourceService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DynamicDataSourceRegister implements ApplicationListener,ApplicationContextAware {
    private static ApplicationContext applicationContext;
    @Autowired
    private DataSourceService dataSourceService;
    private static boolean loaded = false;

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if(loaded){return;}loaded = true;
        Map<Object, Object> dataSourceMap = new HashMap<>();
        List<DataSourceEntity> list = dataSourceService.findAll();
        if(null == list || list.size() == 0){return;}
        for(DataSourceEntity entity : list){
            dataSourceMap.put(entity.getName(), getDataSource(entity));
        }
        addDataSource(dataSourceMap);
    }

    public DataSource addDataSource( Map<Object, Object> dataSourceMap) {
        DynamicDataSource dynamicDataSource = (DynamicDataSource) this.applicationContext.getBean("dynamicDataSource");
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        dynamicDataSource.afterPropertiesSet();
        return dynamicDataSource;
    }
    public DataSource getDataSource(DataSourceEntity entity) {
        return DataSourceBuilder.create()
            .url(entity.getUrl())
            .username(entity.getUserName())
            .password(entity.getPassword())
            .driverClassName(entity.getDriverClass())
            .type(DruidDataSource.class).build();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
