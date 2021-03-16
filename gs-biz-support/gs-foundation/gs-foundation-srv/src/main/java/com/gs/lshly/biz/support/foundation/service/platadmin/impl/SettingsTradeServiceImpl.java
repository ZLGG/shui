package com.gs.lshly.biz.support.foundation.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.foundation.entity.SettingsTrade;
import com.gs.lshly.biz.support.foundation.repository.ISettingsTradeRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISettingsTradeService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SettingsTradeDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SettingsTradeQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsTradeVO;
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
public class SettingsTradeServiceImpl implements ISettingsTradeService {

    @Autowired
    private ISettingsTradeRepository repository;

    @Override
    public void addSettingsTrade(SettingsTradeDTO.ETO eto) {
        if(null == eto.getCancelTime() || null == eto.getConfirmTime()){
            throw new BusinessException("数据不能为空");
        }
        QueryWrapper<SettingsTrade> queryWrapper = MybatisPlusUtil.query();
        SettingsTrade settingsTrade =  repository.getOne(queryWrapper);
        if(null == settingsTrade){
            settingsTrade = new SettingsTrade();
            BeanUtils.copyProperties(eto, settingsTrade);
            repository.save(settingsTrade);
        }else{
            BeanUtils.copyProperties(eto, settingsTrade);
            repository.updateById(settingsTrade);
        }
    }

    @Override
    public SettingsTradeVO.DetailVO detailSettingsTrade(BaseDTO dto) {
        QueryWrapper<SettingsTrade> queryWrapper =  MybatisPlusUtil.query();
        SettingsTrade settingsTrade =  repository.getOne(queryWrapper);
        if(null == settingsTrade){
            return null;
        }
        SettingsTradeVO.DetailVO detailVo = new SettingsTradeVO.DetailVO();
        BeanUtils.copyProperties(settingsTrade, detailVo);
        return detailVo;
    }

}
