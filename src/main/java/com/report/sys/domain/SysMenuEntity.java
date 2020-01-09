package com.report.sys.domain;

import lombok.Data;
import java.io.Serializable;

@Data
public class SysMenuEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String parentId;
    private String name;
    private String description;
    private String url;
    private String icon;
    private String type;
    private Integer orderNo;
    private String outsideUrl;
}
