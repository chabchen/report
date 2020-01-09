package com.report.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface TestMapper {

    @Select("SELECT * FROM EMPLOYEE where id = #{id}")
    List<Map> findAll(String id);
}
