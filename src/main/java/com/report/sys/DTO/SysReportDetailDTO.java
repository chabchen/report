package com.report.sys.DTO;

import com.report.sys.base.dao.Pager;
import lombok.Data;

@Data
public class SysReportDetailDTO extends Pager {

    private String id;
    private String parentId;
    private String name;
    private String url;
    private String config;
    private String sqls;
    private String sql2;
    private String sql3;
    private String dataSource;
    private String remark;
    private Long orderNumber;
}
