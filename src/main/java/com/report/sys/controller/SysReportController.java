package com.report.sys.controller;

import com.report.sys.DTO.SysReportDTO;
import com.report.sys.base.dao.ResultDataDto;
import com.report.sys.service.SysReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags={"报表配置管理"})
@RestController
@RequestMapping("/api/sysReport")
public class SysReportController {

    @Autowired
    private SysReportService sysReportService;

    @ApiOperation(value="保存报表配置")
    @PostMapping("/save")
    public ResultDataDto saveMenu(@RequestBody SysReportDTO dto) {
        return sysReportService.saveReport(dto);
    }

    @ApiOperation(value="删除报表配置")
    @PostMapping("/delete/{ids}")
    public ResultDataDto deleteMenu(@PathVariable("ids") String ids) {
        return sysReportService.removeReport(ids);
    }

    @ApiOperation(value="查询数据")
    @GetMapping("/list")
    public ResultDataDto getReportList(@RequestParam(value="id",required=false) String id,
                                       @RequestParam(value="reportId",required=false) String reportId,
                                       @RequestParam(value="currentPage", required=true, defaultValue="1") Integer currentPage,
                                       @RequestParam(value="pageSize", required=true, defaultValue="10") Integer pageSize,
                                       @RequestParam(value="sortNames",required=false,defaultValue="") String sortNames,
                                       @RequestParam(value="sortOrder", required=true, defaultValue="DESC") String sortOrder) {
        SysReportDTO dto = new SysReportDTO();
        dto.setId(id);
        dto.setReportId(reportId);
        dto.setCurrentPage(currentPage);
        dto.setPageSize(pageSize);
        dto.setSortNames(sortNames);
        dto.setSortOrder(sortOrder);
        return sysReportService.getReportList(dto);
    }


}
