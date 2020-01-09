package com.report.sys.service;

import com.report.sys.DTO.DataSourceDTO;
import com.report.sys.base.dao.ResultDataDto;
import com.report.sys.domain.DataSourceEntity;
import com.report.sys.mapper.SysDataSourceMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataSourceService {

    @Autowired
    private SysDataSourceMapper sysDataSourceMapper;

    public ResultDataDto saveDataSource(DataSourceDTO dto){
        DataSourceEntity bean = new DataSourceEntity();
        BeanUtils.copyProperties(dto,bean);
        if(!StringUtils.isEmpty(dto.getId())){
            sysDataSourceMapper.update(bean);
            return new ResultDataDto().addUpdateSuccess();
        }
        sysDataSourceMapper.insert(bean);
        return new ResultDataDto().addAddSuccess();
    }

    public ResultDataDto removeDataSource(String ids) {
        String[] arrIds = ids.split(",");
        for(String id : arrIds){
            sysDataSourceMapper.deleteById(id);
        }
        return new ResultDataDto("success").addDeleteSuccess();
    }

    public List<DataSourceEntity> findAll(){
        return sysDataSourceMapper.findAll();
    }

    public ResultDataDto getDataSourceList(DataSourceDTO dto){
        ResultDataDto resultDataDto = new ResultDataDto();
        if(!StringUtils.isEmpty(dto.getId())){
            resultDataDto.setDatas(sysDataSourceMapper.findById(dto.getId()));
        }else{
            resultDataDto.setDatas(sysDataSourceMapper.findAll());
        }
        resultDataDto.setMessage("success");
        return resultDataDto;
    }
}
