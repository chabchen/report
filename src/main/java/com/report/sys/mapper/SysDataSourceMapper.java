package com.report.sys.mapper;

import com.report.sys.domain.DataSourceEntity;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
@Mapper
public interface SysDataSourceMapper {

    @Select("select * from sys_datasource")
    @Results({@Result(column = "user_name", property = "userName"),
              @Result(column = "driver_class", property = "driverClass")})
    List<DataSourceEntity> findAll();

    @Select("select * from sys_datasource where id = #{id}")
    @Results({@Result(column = "user_name", property = "userName"),
            @Result(column = "driver_class", property = "driverClass")})
    List<DataSourceEntity> findById(String id);

    @InsertProvider(type = SysReportProvider.class,method = "insert")
    int insert(@Param("bean") DataSourceEntity bean);

    @UpdateProvider(type = SysReportProvider.class,method = "update")
    int update(@Param("bean") DataSourceEntity bean);

    @Delete("delete from sys_datasource where id = #{id}")
    void deleteById(String id);

   class SysReportProvider{

       public String update(Map<String,Object> map) {
           DataSourceEntity entity = (DataSourceEntity)map.get("bean");
           SQL sql = new SQL();
           sql.UPDATE("sys_datasource");
           if(!StringUtils.isEmpty(entity.getName())){sql.SET("name = '"+entity.getName()+"'");}
           if(!StringUtils.isEmpty(entity.getUrl())){sql.SET("url = '"+entity.getUrl()+"'");}
           if(!StringUtils.isEmpty(entity.getUserName())){sql.SET("user_name = '"+entity.getUserName()+"'");}
           if(!StringUtils.isEmpty(entity.getPassword())){sql.SET("password = '"+entity.getPassword()+"'");}
           if(!StringUtils.isEmpty(entity.getDriverClass())){sql.SET("driver_class = '"+entity.getDriverClass()+"'");}
           sql.WHERE("id = '"+entity.getId()+"'");
           return sql.toString();
       }

       public String insert(Map<String,Object> map) {
           DataSourceEntity entity = (DataSourceEntity)map.get("bean");
           SQL sql = new SQL();
           sql.INSERT_INTO("sys_datasource");
           if(StringUtils.isEmpty(entity.getId())){sql.VALUES("id","'"+String.valueOf(UUID.randomUUID()).replaceAll("-","")+"'");}
           if(!StringUtils.isEmpty(entity.getName())){sql.VALUES("name","'"+entity.getName()+"'");}
           if(!StringUtils.isEmpty(entity.getUrl())){sql.VALUES("url","'"+entity.getUrl()+"'");}
           if(!StringUtils.isEmpty(entity.getUserName())){sql.VALUES("user_name","'"+entity.getUserName()+"'");}
           if(!StringUtils.isEmpty(entity.getPassword())){sql.VALUES("password","'"+entity.getPassword()+"'");}
           if(!StringUtils.isEmpty(entity.getDriverClass())){sql.VALUES("driver_class","'"+entity.getDriverClass()+"'");}
           return sql.toString();
       }

   }

}
