package com.report.sys.mapper;

import com.report.sys.domain.SysReportDetailEntity;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
@Mapper
public interface SysReportDetailMapper {

    @Select("select * from sys_report_detail")
    @Results({@Result(column = "parent_id", property = "parentId"),
            @Result(column = "data_source", property = "dataSource"),
            @Result(column = "order_number", property = "orderNumber")})
    List<SysReportDetailEntity> findAll();

    @Select("select * from sys_report_detail where id = #{id}")
    @Results({@Result(column = "parent_id", property = "parentId"),
            @Result(column = "data_source", property = "dataSource"),
            @Result(column = "order_number", property = "orderNumber")})
    List<SysReportDetailEntity> findById(String id);

    @Select("select * from sys_report_detail where parent_id = #{parentId}")
    @Results({@Result(column = "parent_id", property = "parentId"),
            @Result(column = "data_source", property = "dataSource"),
            @Result(column = "order_number", property = "orderNumber")})
    List<SysReportDetailEntity> findByParentId(String parentId);

    @InsertProvider(type = SysReportDetailProvider.class,method = "insert")
    int insertReport(@Param("bean") SysReportDetailEntity bean);

    @UpdateProvider(type = SysReportDetailProvider.class,method = "update")
    int updateReport(@Param("bean") SysReportDetailEntity bean);

    @Delete("delete from sys_report_detail where id = #{id}")
    void deleteById(String id);

    class SysReportDetailProvider{

        public String update(Map<String,Object> map) {
            SysReportDetailEntity entity = (SysReportDetailEntity)map.get("bean");
            SQL sql = new SQL();
            sql.UPDATE("sys_report_detail");
            if(!StringUtils.isEmpty(entity.getName())){sql.SET("name = '"+entity.getName()+"'");}
            if(!StringUtils.isEmpty(entity.getConfig())){sql.SET("config = '"+entity.getConfig()+"'");}
            if(!StringUtils.isEmpty(entity.getUrl())){sql.SET("url = '"+entity.getUrl()+"'");}
            if(!StringUtils.isEmpty(entity.getSqls())){sql.SET("sqls = '"+entity.getSqls()+"'");}
            if(!StringUtils.isEmpty(entity.getSql2())){sql.SET("sql2 = '"+entity.getSql2()+"'");}
            if(!StringUtils.isEmpty(entity.getSql3())){sql.SET("sql3 = '"+entity.getSql3()+"'");}
            if(!StringUtils.isEmpty(entity.getDataSource())){sql.SET("data_source = '"+entity.getDataSource()+"'");}
            if(!StringUtils.isEmpty(entity.getRemark())){sql.SET("remark = '"+entity.getRemark()+"'");}
            if(null != entity.getOrderNumber() && 0 != entity.getOrderNumber()){sql.SET("order_number = '"+entity.getOrderNumber()+"'");}
            sql.WHERE("id = '"+entity.getId()+"'");
            return sql.toString();
        }

        public String insert(Map<String,Object> map) {
            SysReportDetailEntity entity = (SysReportDetailEntity)map.get("bean");
            SQL sql = new SQL();
            sql.INSERT_INTO("sys_report_detail");
            if(StringUtils.isEmpty(entity.getId())){sql.VALUES("id","'"+String.valueOf(UUID.randomUUID()).replaceAll("-","")+"'");}
            if(!StringUtils.isEmpty(entity.getParentId())){sql.VALUES("parent_id","'"+entity.getParentId()+"'");}
            if(!StringUtils.isEmpty(entity.getName())){sql.VALUES("name","'"+entity.getName()+"'");}
            if(!StringUtils.isEmpty(entity.getConfig())){sql.VALUES("config","'"+entity.getConfig()+"'");}
            if(!StringUtils.isEmpty(entity.getUrl())){sql.VALUES("url","'"+entity.getUrl()+"'");}
            if(!StringUtils.isEmpty(entity.getSqls())){sql.VALUES("sqls","'"+entity.getSqls()+"'");}
            if(!StringUtils.isEmpty(entity.getSql2())){sql.VALUES("sql2","'"+entity.getSql2()+"'");}
            if(!StringUtils.isEmpty(entity.getSql3())){sql.VALUES("sql3","'"+entity.getSql3()+"'");}
            if(!StringUtils.isEmpty(entity.getDataSource())){sql.VALUES("data_source","'"+entity.getDataSource()+"'");}
            if(!StringUtils.isEmpty(entity.getRemark())){sql.VALUES("remark","'"+entity.getRemark()+"'");}
            if(null != entity.getOrderNumber() && 0 != entity.getOrderNumber()){sql.VALUES("order_number","'"+entity.getOrderNumber()+"'");}
            return sql.toString();
        }

    }
}
