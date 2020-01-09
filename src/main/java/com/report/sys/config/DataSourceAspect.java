package com.report.sys.config;

import com.report.sys.base.annotation.DataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class DataSourceAspect{

    /**
     * 在进入Service方法之前执行
     * @param point 切面对象
     */
    @Before("@annotation(com.report.sys.base.annotation.DataSource)")
    public void before(JoinPoint point) {
        MethodSignature signature = (MethodSignature)point.getSignature();
        Method method = signature.getMethod();
        Object[] args = point.getArgs();
        DataSource annotation = method.getAnnotation(DataSource.class);
        if(null == annotation){
            DynamicDataSourceHolder.setDB(DataSourceEnum.PRIMARY_DATA_SOURCE_NAME);
        }else{
            //DynamicDataSourceHolder.setDB(annotation.value());
            DynamicDataSourceHolder.setDB(args[1].toString());
        }
    }

    @After("@annotation(com.report.sys.base.annotation.DataSource)")
    public void afterSwitchDS(JoinPoint point){
        DynamicDataSourceHolder.clearDB();
    }
}
