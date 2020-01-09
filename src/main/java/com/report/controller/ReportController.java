package com.report.controller;

import com.report.service.ReportService;
import com.report.sys.base.dao.ResultDataDto;
import com.report.sys.base.service.exception.ErrorCode;
import com.report.sys.base.service.exception.ServiceException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(tags={"报表管理"})
@RestController
@RequestMapping("/api/report")
public class ReportController {
    private Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    private ReportService reportService;

    @ApiOperation(value="查询presto数据")
    @PostMapping("/list")
    public ResultDataDto getReportList(@RequestBody Map params){
        //return reportService.getReportData(params.get("params").toString());
        return reportService.getReportData2(params.get("params").toString(),params.get("dataSource").toString());
    }

    @ApiOperation(value="带权限查询presto数据")
    @PostMapping("/data")
    public ResultDataDto getReportListByAuth(@RequestBody Map params){
        if(StringUtils.isEmpty(params.get("token"))){
            throw new ServiceException("token不能为空",ErrorCode.BAD_REQUEST);
        }
        if(StringUtils.isEmpty(params.get("params"))){
            throw new ServiceException("sql不能为空",ErrorCode.BAD_REQUEST);
        }
        return reportService.getReportData(params.get("params").toString());
    }

}
