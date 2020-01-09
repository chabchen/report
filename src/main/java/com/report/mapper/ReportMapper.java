package com.report.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface ReportMapper {

    @SelectProvider(type = ReportProvider.class, method = "select")
    public List<Map> select(@Param("baseSql") String baseSql);

    @SelectProvider(type = ReportProvider.class, method = "select")
    public List<Map> selectByPermission(@Param("baseSql") String baseSql, @Param("extendSql") String extendSql);

    class ReportProvider{

        public String select(Map<String,Object> map) {
            return map.get("baseSql").toString();
        }
    }
}
