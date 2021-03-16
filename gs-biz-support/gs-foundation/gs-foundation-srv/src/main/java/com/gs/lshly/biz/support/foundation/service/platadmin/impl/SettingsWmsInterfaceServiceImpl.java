package com.gs.lshly.biz.support.foundation.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.foundation.entity.SettingsWmsInterface;
import com.gs.lshly.biz.support.foundation.repository.ISettingsWmsInterfaceRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISettingsWmsInterfaceService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SettingsWmsInterfaceDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SettingsWmsInterfaceQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsWmsInterfaceVO;
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
public class SettingsWmsInterfaceServiceImpl implements ISettingsWmsInterfaceService {

    @Autowired
    private ISettingsWmsInterfaceRepository repository;

    @Override
    public void addSettingsWmsInterface(SettingsWmsInterfaceDTO.ETO eto) {
        SettingsWmsInterface settingsWmsInterface = repository.getOne(MybatisPlusUtil.query());
        if(null == settingsWmsInterface){
            settingsWmsInterface = new SettingsWmsInterface();
            BeanUtils.copyProperties(eto, settingsWmsInterface);
            repository.save(settingsWmsInterface);
        }else{
            BeanUtils.copyProperties(eto, settingsWmsInterface);
            repository.updateById(settingsWmsInterface);
        }
    }

    @Override
    public SettingsWmsInterfaceVO.DetailVO detailSettingsWmsInterface(BaseDTO dto) {
        SettingsWmsInterface settingsWmsInterface = repository.getOne(MybatisPlusUtil.query());
        if(null == settingsWmsInterface){
            return null;
        }
        SettingsWmsInterfaceVO.DetailVO detailVo = new SettingsWmsInterfaceVO.DetailVO();
        BeanUtils.copyProperties(settingsWmsInterface, detailVo);
        return detailVo;
    }

}
