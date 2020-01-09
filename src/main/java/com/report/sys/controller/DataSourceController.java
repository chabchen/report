package com.report.sys.controller;

import com.report.sys.DTO.DataSourceDTO;
import com.report.sys.base.dao.ResultDataDto;
import com.report.sys.service.DataSourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags={"数据源配置管理"})
@RestController
@RequestMapping("/api/dataSource")
public class DataSourceController {

    @Autowired
    private DataSourceService dataSourceService;

    @ApiOperation(value="保存数据源配置")
    @PostMapping("/save")
    public ResultDataDto saveDataSource(@RequestBody DataSourceDTO dto) {
        return dataSourceService.saveDataSource(dto);
    }

    @ApiOperation(value="删除数据源配置")
    @PostMapping("/delete/{ids}")
    public ResultDataDto removeDataSource(@PathVariable("ids") String ids) {
        return dataSourceService.removeDataSource(ids);
    }

    @ApiOperation(value="查询数据")
    @GetMapping("/list")
    public ResultDataDto getDataSourceList(@RequestParam(value="id",required=false) String id) {
        DataSourceDTO dto = new DataSourceDTO();
        dto.setId(id);
        return dataSourceService.getDataSourceList(dto);
    }


}
