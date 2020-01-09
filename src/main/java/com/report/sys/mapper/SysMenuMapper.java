package com.report.sys.mapper;

import com.report.sys.domain.SysMenuEntity;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
@Mapper
public interface SysMenuMapper {

    @Select("select * from sys_menu")
    @Results({@Result(column = "parent_id", property = "parentId"),
            @Result(column = "order_no", property = "orderNo"),
            @Result(column = "outside_url", property = "outsideUrl")})
    List<SysMenuEntity> findAll();

    @Select("select * from sys_menu where id = #{id}")
    @Results({@Result(column = "parent_id", property = "parentId"),
            @Result(column = "order_no", property = "orderNo"),
            @Result(column = "outside_url", property = "outsideUrl")})
    SysMenuEntity findById(String id);

    @Select("select * from sys_menu where parent_id = #{parentId}")
    @Results({@Result(column = "parent_id", property = "parentId"),
            @Result(column = "order_no", property = "orderNo"),
            @Result(column = "outside_url", property = "outsideUrl")})
    List<SysMenuEntity> findByParentId(String parentId);

    @Select("select * from sys_menu where parent_id = #{parentId} and type = #{type}")
    @Results({@Result(column = "parent_id", property = "parentId"),
            @Result(column = "order_no", property = "orderNo"),
            @Result(column = "outside_url", property = "outsideUrl")})
    List<SysMenuEntity> findByParentIdAndType(String parentId, String type);

    @Select("select * from sys_menu where type = #{type} order by order_no asc,id asc")
    @Results({@Result(column = "parent_id", property = "parentId"),
            @Result(column = "order_no", property = "orderNo"),
            @Result(column = "outside_url", property = "outsideUrl")})
    List<SysMenuEntity> findByType(String type);

    @InsertProvider(type = SysMenuProvider.class,method = "insert")
    int insertMenu(@Param("bean") SysMenuEntity menu);

    @UpdateProvider(type = SysMenuProvider.class,method = "update")
    int updateMenu(@Param("bean") SysMenuEntity menu);

    @Delete("delete from sys_menu where id = #{id}")
    void deleteById(String id);

    class SysMenuProvider{
        public String update(Map<String,Object> map) {
            SysMenuEntity menu = (SysMenuEntity)map.get("bean");
            SQL sql = new SQL();
            sql.UPDATE("sys_menu");
            if(!StringUtils.isEmpty(menu.getParentId())){sql.SET("parent_id = "+menu.getParentId());}
            if(null != menu.getOrderNo()){sql.SET("order_no = "+menu.getOrderNo());}
            if(!StringUtils.isEmpty(menu.getName())){sql.SET("name = '"+menu.getName()+"'");}
            if(!StringUtils.isEmpty(menu.getDescription())){sql.SET("description = '"+menu.getDescription()+"'");}
            if(!StringUtils.isEmpty(menu.getUrl())){sql.SET("url = '"+menu.getUrl()+"'");}
            if(!StringUtils.isEmpty(menu.getIcon())){sql.SET("icon = '"+menu.getIcon()+"'");}
            if(!StringUtils.isEmpty(menu.getType())){sql.SET("type = '"+menu.getType()+"'");}
            if(!StringUtils.isEmpty(menu.getOutsideUrl())){sql.SET("outside_url = '"+menu.getOutsideUrl()+"'");}
            sql.WHERE("id = '"+menu.getId()+"'");
            return sql.toString();
        }

        public String insert(Map<String,Object> map) {
            SysMenuEntity menu = (SysMenuEntity)map.get("bean");
            SQL sql = new SQL();
            sql.INSERT_INTO("sys_menu");
            if(StringUtils.isEmpty(menu.getId())){sql.VALUES("id","'"+String.valueOf(UUID.randomUUID()).replaceAll("-","")+"'");}
            if(!StringUtils.isEmpty(menu.getParentId())){sql.VALUES("parent_id",menu.getParentId());}
            if(null != menu.getOrderNo()){sql.VALUES("order_no", String.valueOf(menu.getOrderNo()));}
            if(!StringUtils.isEmpty(menu.getName())){sql.VALUES("name", "'"+menu.getName()+"'");}
            if(!StringUtils.isEmpty(menu.getDescription())){sql.VALUES("description", "'"+menu.getDescription()+"'");}
            if(!StringUtils.isEmpty(menu.getUrl())){sql.VALUES("url", "'"+menu.getUrl()+"'");}
            if(!StringUtils.isEmpty(menu.getIcon())){sql.VALUES("icon", "'"+menu.getIcon()+"'");}
            if(!StringUtils.isEmpty(menu.getType())){sql.VALUES("type", "'"+menu.getType()+"'");}
            if(!StringUtils.isEmpty(menu.getOutsideUrl())){sql.VALUES("outside_url", "'"+menu.getOutsideUrl()+"'");}
            return sql.toString();
        }
    }
}
