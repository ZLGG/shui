package com.gs.lshly.biz.support.foundation.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.foundation.entity.SettingsIntegral;
import com.gs.lshly.biz.support.foundation.repository.ISettingsIntegralRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISettingsIntegralService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SettingsIntegralDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SettingsIntegralQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsIntegralVO;
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
public class SettingsIntegralServiceImpl implements ISettingsIntegralService {

    @Autowired
    private ISettingsIntegralRepository repository;

    @Override
    public void addSettingsIntegral(SettingsIntegralDTO.ETO eto) {
        SettingsIntegral settingsIntegral = repository.getOne(MybatisPlusUtil.query());
        if(null == settingsIntegral){
            settingsIntegral = new SettingsIntegral();
            BeanUtils.copyProperties(eto, settingsIntegral);
            repository.save(settingsIntegral);
        }else {
            BeanUtils.copyProperties(eto, settingsIntegral);
            repository.updateById(settingsIntegral);
        }
    }

    @Override
    public SettingsIntegralVO.DetailVO detailSettingsIntegral(BaseDTO dto) {
        SettingsIntegral settingsIntegral = repository.getOne(MybatisPlusUtil.query());
        if(null == settingsIntegral){
            return null;
        }
        SettingsIntegralVO.DetailVO detailVo = new SettingsIntegralVO.DetailVO();
        BeanUtils.copyProperties(settingsIntegral, detailVo);
        return detailVo;
    }

}
