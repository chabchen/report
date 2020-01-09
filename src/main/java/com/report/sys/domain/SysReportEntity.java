package com.report.sys.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysReportEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String reportId;
    private String name;
    private String type;
    private String config;
}
