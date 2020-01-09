package com.report.sys.DTO;

import com.report.sys.base.dao.Pager;
import lombok.Data;

@Data
public class MenuDTO extends Pager {
    private String id;
    private String parentId;
    private String name;
    private String description;
    private String url;
    private String icon;
    private String type;
    private int orderNo;
    private String outsideUrl;
}
