package com.report.sys.base.dao;

import lombok.Data;

/**
 * author：ccf
 */
@Data
public class Pager {
    //当前页
    private Integer currentPage = 1;
    //每页显示，默认:10
    private Integer pageSize = 10;
    //排序字段:以逗号，隔开
    private String sortNames;
    //排序(asc/desc)
    private String sortOrder = "desc";

}
