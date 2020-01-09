package com.report.sys.DTO;

import lombok.Data;

import java.util.List;

@Data
public class MenuTreeDTO  {

    private Long id;
    private Long parentId;
    private String name;
    private String description;
    private String url;
    private String icon;
    private String type;
    private Integer orderNo;
    private String outsideUrl;
    private String title;
    private List<MenuTreeDTO> children;
}
