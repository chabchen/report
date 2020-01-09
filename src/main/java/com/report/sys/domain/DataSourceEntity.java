package com.report.sys.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class DataSourceEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String url;
    private String userName;
    private String password;
    private String driverClass;
}
