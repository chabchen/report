package com.report.sys.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.report.sys.DTO.MenuDTO;
import com.report.sys.DTO.MenuTreeDTO;
import com.report.sys.base.dao.ResultDataDto;
import com.report.sys.base.service.exception.ErrorCode;
import com.report.sys.base.service.exception.ServiceException;
import com.report.sys.domain.SysMenuEntity;
import com.report.sys.mapper.SysMenuMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    public ResultDataDto saveMenu(MenuDTO menuDTO) {
        SysMenuEntity bean = new SysMenuEntity();
        BeanUtils.copyProperties(menuDTO,bean);
        SysMenuEntity sysMenu = sysMenuMapper.findById(menuDTO.getParentId());
        if(null != sysMenu){
            bean.setParentId(sysMenu.getId());
        }
        if(!StringUtils.isEmpty(bean.getId())){
            return new ResultDataDto(sysMenuMapper.updateMenu(bean)).addUpdateSuccess();
        }
        return new ResultDataDto(sysMenuMapper.insertMenu(bean)).addAddSuccess();
    }

    public ResultDataDto removeMenus(String menuIds) {
        String[] ids = menuIds.split(",");
        for(String id : ids){
            List<SysMenuEntity> list = sysMenuMapper.findByParentId(id);
            if(list.size() > 0){
                SysMenuEntity menu = sysMenuMapper.findById(id);
                throw new ServiceException(menu.getName() + "有子菜单，请先删除子菜单", ErrorCode.BUSINESS_BAD);
            }
            sysMenuMapper.deleteById(id);
        }
        return new ResultDataDto("succes").addDeleteSuccess();
    }

    public ResultDataDto menuForTree(MenuDTO dto) {
        ResultDataDto resultDataDto = new ResultDataDto();
        String orderBy = StringUtils.isEmpty(dto.getSortNames()) ? "" : dto.getSortNames()+" "+dto.getSortOrder();
        List<SysMenuEntity> data;
        if(StringUtils.isEmpty(dto.getType())){
            PageHelper.startPage(dto.getCurrentPage(), dto.getPageSize(), orderBy);
            data = sysMenuMapper.findAll();
            resultDataDto.setDatas(new PageInfo(data));
        }else{
            data = sysMenuMapper.findByType(dto.getType());
            resultDataDto.setDatas(getMenuTree(data));
        }
        return resultDataDto;
    }


    public List<MenuTreeDTO> getMenuTree(List<SysMenuEntity> data){
        List<MenuTreeDTO> tmpList = new ArrayList<>();
        MenuTreeDTO target;
        for (SysMenuEntity source : data) {
            target = new MenuTreeDTO();
            BeanUtils.copyProperties(source,target);
            target.setTitle(source.getName());
            target.setChildren(new ArrayList<>());
            target.setUrl(null != source.getUrl() ? source.getUrl() : "");
            tmpList.add(target);
        }
        List<MenuTreeDTO> newList = new ArrayList<>();
        for(MenuTreeDTO node1 : tmpList){
            boolean mark = false;
            for(MenuTreeDTO node2 : tmpList){
                if(null == node1.getParentId() || !node1.getParentId().equals(node2.getId())){continue;}
                mark = true;
                if(node2.getChildren() == null){node2.setChildren(new ArrayList<>());}
                node2.getChildren().add(node1);
                break;
            }
            if(!mark){ newList.add(node1);}
        }
        return newList;
    }

}
