package com.report.sys.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysReportDetailEntity implements Serializable {
    private static final long serialVersionUID = 1L;
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
