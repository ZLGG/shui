package com.gs.lshly.biz.support.foundation.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.foundation.entity.SettingsReport;
import com.gs.lshly.biz.support.foundation.repository.ISettingsReportRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISettingsReportService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SettingsReportDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SettingsReportQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsReportVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

/**
* <p>
*  服务实现类
* </p>
* @author 陈奇
* @since 2020-10-12
*/
@Component
public class SettingsReportServiceImpl implements ISettingsReportService {

    @Autowired
    private ISettingsReportRepository repository;

    @Override
    public void addSettingsReport(SettingsReportDTO.ETO eto) {
        SettingsReport settingsReport =  repository.getOne(MybatisPlusUtil.query());
        if(null == settingsReport){
            settingsReport = new SettingsReport();
            BeanUtils.copyProperties(eto, settingsReport);
            repository.save(settingsReport);
        }else {
            BeanUtils.copyProperties(eto, settingsReport);
            repository.updateById(settingsReport);
        }
    }

    @Override
    public SettingsReportVO.DetailVO detailSettingsReport(BaseDTO dto) {
        SettingsReport settingsReport =  repository.getOne(MybatisPlusUtil.query());
        if(null == settingsReport){
            return null;
        }
        SettingsReportVO.DetailVO detailVo = new SettingsReportVO.DetailVO();
        BeanUtils.copyProperties(settingsReport, detailVo);
        return detailVo;
    }

}
