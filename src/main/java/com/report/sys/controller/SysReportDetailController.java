package com.report.sys.controller;

import com.report.sys.DTO.SysReportDetailDTO;
import com.report.sys.base.dao.ResultDataDto;
import com.report.sys.service.SysReportDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags={"报表配置明细管理"})
@RestController
@RequestMapping("/api/sysReportDetail")
public class SysReportDetailController {

    @Autowired
    private SysReportDetailService sysReportDetailService;

    @ApiOperation(value="保存报表配置")
    @PostMapping("/save")
    public ResultDataDto saveMenu(@RequestBody SysReportDetailDTO dto) {
        return sysReportDetailService.saveReport(dto);
    }

    @ApiOperation(value="删除报表配置")
    @PostMapping("/delete/{ids}")
    public ResultDataDto deleteMenu(@PathVariable("ids") String ids) {
        return sysReportDetailService.removeReport(ids);
    }

    @ApiOperation(value="查询数据")
    @GetMapping("/list")
    public ResultDataDto getReportList(@RequestParam(value="id",required=false) String id,
                                       @RequestParam(value="parentId",required=false) String parentId,
                                       @RequestParam(value="currentPage", required=true, defaultValue="1") Integer currentPage,
                                       @RequestParam(value="pageSize", required=true, defaultValue="10") Integer pageSize,
                                       @RequestParam(value="sortNames",required=false,defaultValue="") String sortNames,
                                       @RequestParam(value="sortOrder", required=true, defaultValue="ASC") String sortOrder) {
        SysReportDetailDTO dto = new SysReportDetailDTO();
        dto.setId(id);
        dto.setParentId(parentId);
        dto.setCurrentPage(currentPage);
        dto.setPageSize(pageSize);
        dto.setSortNames(sortNames);
        dto.setSortOrder(sortOrder);
        return sysReportDetailService.getReportList(dto);
    }


}
