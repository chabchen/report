package com.report.sys.controller;

import com.report.sys.DTO.MenuDTO;
import com.report.sys.base.dao.ResultDataDto;
import com.report.sys.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags={"菜单管理"})
@RestController
@RequestMapping("/api/sysMenu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;


    @ApiOperation(value="保存菜单")
    @PostMapping("/save")
    public ResultDataDto saveMenu(@RequestBody MenuDTO menuDTO) {
        return sysMenuService.saveMenu(menuDTO);
    }

    @ApiOperation(value="删除菜单")
    @PostMapping("/delete/{menuIds}")
    public ResultDataDto deleteMenu(@PathVariable("menuIds") String menuIds) {
        return sysMenuService.removeMenus(menuIds);
    }

    @ApiOperation(value="查询数据，返回树形形式")
    @GetMapping("/menuForTree")
    public ResultDataDto queryMenuForTree(@RequestParam(value="type",required=false,defaultValue="back") String type,
                                          @RequestParam(value="currentPage", required=true, defaultValue="1") Integer currentPage,
                                          @RequestParam(value="pageSize", required=true, defaultValue="10") Integer pageSize,
                                          @RequestParam(value="sortNames",required=false,defaultValue="") String sortNames,
                                          @RequestParam(value="sortOrder", required=true, defaultValue="DESC") String sortOrder) {
            MenuDTO dto = new MenuDTO();
            dto.setType(type.equals("back") ? type : "");
            dto.setCurrentPage(currentPage);
            dto.setPageSize(pageSize);
            dto.setSortNames(sortNames);
            dto.setSortOrder(sortOrder);
            return sysMenuService.menuForTree(dto);
    }


}
