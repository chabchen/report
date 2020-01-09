package com.report.sys.mapper;

import com.report.sys.domain.SysReportEntity;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
@Mapper
public interface SysReportMapper {

    @Select("select * from sys_report")
    @Results({@Result(column = "report_id", property = "reportId")})
    List<SysReportEntity> findAll();

    @Select("select * from sys_report where id = #{id}")
    @Results({@Result(column = "report_id", property = "reportId")})
    List<SysReportEntity> findById(String id);

    @Select("select * from sys_report where report_id = #{reportId}")
    @Results({@Result(column = "report_id", property = "reportId")})
    List<SysReportEntity> findByReportId(String reportId);

    @InsertProvider(type = SysReportProvider.class,method = "insert")
    int insertReport(@Param("bean") SysReportEntity bean);

    @UpdateProvider(type = SysReportProvider.class,method = "update")
    int updateReport(@Param("bean") SysReportEntity bean);

    @Delete("delete from sys_report where id = #{id}")
    void deleteById(String id);

   class SysReportProvider{

       public String update(Map<String,Object> map) {
           SysReportEntity entity = (SysReportEntity)map.get("bean");
           SQL sql = new SQL();
           sql.UPDATE("sys_report");
           if(!StringUtils.isEmpty(entity.getName())){sql.SET("name = '"+entity.getName()+"'");}
           if(!StringUtils.isEmpty(entity.getType())){sql.SET("type = '"+entity.getType()+"'");}
           sql.WHERE("id = '"+entity.getId()+"'");
           return sql.toString();
       }

       public String insert(Map<String,Object> map) {
           SysReportEntity entity = (SysReportEntity)map.get("bean");
           SQL sql = new SQL();
           sql.INSERT_INTO("sys_report");
           if(StringUtils.isEmpty(entity.getId())){sql.VALUES("id","'"+String.valueOf(UUID.randomUUID()).replaceAll("-","")+"'");}
           if(!StringUtils.isEmpty(entity.getReportId())){sql.VALUES("report_id","'"+entity.getReportId()+"'");}
           if(!StringUtils.isEmpty(entity.getName())){sql.VALUES("name","'"+entity.getName()+"'");}
           if(!StringUtils.isEmpty(entity.getType())){sql.VALUES("type","'"+entity.getType()+"'");}
           return sql.toString();
       }

   }

}
