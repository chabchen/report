package com.report.sys.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.report.sys.DTO.SysReportDTO;
import com.report.sys.base.dao.ResultDataDto;
import com.report.sys.domain.SysReportEntity;
import com.report.sys.mapper.SysReportMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class SysReportService {

    @Autowired
    private SysReportMapper sysReportMapper;

    public ResultDataDto getReportList(SysReportDTO dto){
        ResultDataDto resultDataDto = new ResultDataDto();
        if(!StringUtils.isEmpty(dto.getId())){
            resultDataDto.setDatas(sysReportMapper.findById(dto.getId()));
        }else if(!StringUtils.isEmpty(dto.getReportId())){
            resultDataDto.setDatas(sysReportMapper.findByReportId(dto.getReportId()));
        }else{
            String orderBy = StringUtils.isEmpty(dto.getSortNames()) ? "" : dto.getSortNames()+" "+dto.getSortOrder();
            PageHelper.startPage(dto.getCurrentPage(), dto.getPageSize(), orderBy);
            resultDataDto.setDatas(new PageInfo(sysReportMapper.findAll()));
        }
        return resultDataDto;
    }

    public ResultDataDto saveReport(SysReportDTO dto) {
        SysReportEntity bean = new SysReportEntity();
        BeanUtils.copyProperties(dto,bean);
        if(!StringUtils.isEmpty(bean.getId())){
            return new ResultDataDto(sysReportMapper.updateReport(bean)).addUpdateSuccess();
        }
        bean.setReportId(String.valueOf(UUID.randomUUID()).replaceAll("-",""));
        return new ResultDataDto(sysReportMapper.insertReport(bean)).addAddSuccess();
    }
    public ResultDataDto removeReport(String ids) {
        String[] arrIds = ids.split(",");
        for(String id : arrIds){
            sysReportMapper.deleteById(id);
        }
        return new ResultDataDto("succes").addDeleteSuccess();
    }

}
