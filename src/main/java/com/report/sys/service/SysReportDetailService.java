package com.report.sys.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.report.sys.DTO.SysReportDetailDTO;
import com.report.sys.base.dao.ResultDataDto;
import com.report.sys.domain.SysReportDetailEntity;
import com.report.sys.domain.SysReportEntity;
import com.report.sys.mapper.SysReportDetailMapper;
import com.report.sys.mapper.SysReportMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysReportDetailService {

    @Autowired
    private SysReportDetailMapper sysReportDetailMapper;

    @Autowired
    private SysReportMapper sysReportMapper;

    public ResultDataDto getReportList(SysReportDetailDTO dto){
        ResultDataDto resultDataDto = new ResultDataDto();
        if(!StringUtils.isEmpty(dto.getId())){
            resultDataDto.setDatas(sysReportDetailMapper.findById(dto.getId()));
        }else if(!StringUtils.isEmpty(dto.getParentId())){
            String orderBy = StringUtils.isEmpty(dto.getSortNames()) ? "" : dto.getSortNames()+" "+dto.getSortOrder();
            PageHelper.startPage(dto.getCurrentPage(), dto.getPageSize(), orderBy);
            resultDataDto.setDatas(sysReportDetailMapper.findByParentId(dto.getParentId()));
        }else{
            String orderBy = StringUtils.isEmpty(dto.getSortNames()) ? "" : dto.getSortNames()+" "+dto.getSortOrder();
            PageHelper.startPage(dto.getCurrentPage(), dto.getPageSize(), orderBy);
            resultDataDto.setDatas(new PageInfo(sysReportDetailMapper.findAll()));
        }
        return resultDataDto;
    }

    public ResultDataDto saveReport(SysReportDetailDTO dto) {
        SysReportDetailEntity bean = new SysReportDetailEntity();
        BeanUtils.copyProperties(dto,bean);
        if(!StringUtils.isEmpty(bean.getId())){
            return new ResultDataDto(sysReportDetailMapper.updateReport(bean)).addUpdateSuccess();
        }
        List<SysReportEntity>  list = sysReportMapper.findByReportId(dto.getParentId());
        if(null != list && list.size() > 0){
            bean.setName(list.get(0).getName());
        }
        bean.setParentId(dto.getParentId());
        return new ResultDataDto(sysReportDetailMapper.insertReport(bean)).addAddSuccess();
    }
    public ResultDataDto removeReport(String ids) {
        String[] arrIds = ids.split(",");
        for(String id : arrIds){
            sysReportDetailMapper.deleteById(id);
        }
        return new ResultDataDto("succes").addDeleteSuccess();
    }

}
