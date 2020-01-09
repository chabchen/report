package com.report.sys.DTO;

import lombok.Data;

@Data
public class DataSourceDTO {
    private String id;
    private String name;
    private String url;
    private String userName;
    private String password;
    private String driverClass;
}
