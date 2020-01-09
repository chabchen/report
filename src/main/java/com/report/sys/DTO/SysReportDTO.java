package com.report.sys.DTO;

import com.report.sys.base.dao.Pager;
import lombok.Data;

@Data
public class SysReportDTO extends Pager {
    private String id;
    private String reportId;
    private String name;
    private String type;
    private String config;
}
