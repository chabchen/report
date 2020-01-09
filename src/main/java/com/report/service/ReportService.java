package com.report.service;

import com.report.mapper.ReportMapper;
import com.report.sys.base.annotation.DataSource;
import com.report.sys.base.dao.ResultDataDto;
import com.report.sys.base.service.exception.ErrorCode;
import com.report.sys.base.service.exception.ServiceException;
import com.report.sys.config.DataSourceEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {
    private static Logger logger = LoggerFactory.getLogger(ReportService.class);

    @Value("${spring.slowQueryThreshold}")
    private long slowQueryThreshold;

    @Autowired
    private ReportMapper reportMapper;


    public String getDataAuth(Map params){
        if(StringUtils.isEmpty(params.get("token"))){
            throw new ServiceException("token不能为空",ErrorCode.BAD_REQUEST);
        }
        if(StringUtils.isEmpty(params.get("params"))){
            throw new ServiceException("sql不能为空",ErrorCode.BAD_REQUEST);
        }
        String token = params.get("token").toString();
        String sql = params.get("params").toString();
        return sql;
    }

    @DataSource(DataSourceEnum.THIRD_DATA_SOURCE_NAME)
    public ResultDataDto getReportData2(String sql, String dataSource){
        if(StringUtils.isEmpty(sql)){return getAuthorized();}
        return queryReportData(sql);
    }

    @DataSource(DataSourceEnum.THIRD_DATA_SOURCE_NAME)
    public ResultDataDto getReportData(String sql){
        if(StringUtils.isEmpty(sql)){return getAuthorized();}
        return queryReportData(sql);
    }

    @DataSource(DataSourceEnum.FOURTH_DATA_SOURCE_NAME)
    public ResultDataDto getReportKylinData(String sql){
        if(StringUtils.isEmpty(sql)){return getAuthorized();}
        return queryReportData(sql);
    }

    public ResultDataDto queryReportData(String sql){
        logger.info(sql);
        ResultDataDto resultDataDto = new ResultDataDto();
        long start = System.currentTimeMillis();
        List<Map> list = reportMapper.select(sql);
        resultDataDto.setDatas(list);
        long end = System.currentTimeMillis();
        if(end - start > slowQueryThreshold){
            logger.warn("slowQuery: "+sql+";");
        }
        return resultDataDto;
    }

    public Map<String,String> setMap (String label,String value){
        Map<String,String> map = new HashMap<>();
        map.put("label",label);
        map.put("value",value);
        return map;
    }

    public ResultDataDto getAuthorized(){
        ResultDataDto resultDataDto = new ResultDataDto();
        resultDataDto.setCode(ResultDataDto.CODE_ERROR_WEB);
        resultDataDto.setMessage("NotAuthorized");
        return resultDataDto;
    }
}
