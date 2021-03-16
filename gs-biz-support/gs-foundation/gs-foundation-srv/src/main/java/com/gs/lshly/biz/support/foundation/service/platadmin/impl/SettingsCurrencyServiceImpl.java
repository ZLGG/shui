package com.gs.lshly.biz.support.foundation.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.foundation.entity.SettingsCurrency;
import com.gs.lshly.biz.support.foundation.repository.ISettingsCurrencyRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISettingsCurrencyService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SettingsCurrencyDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SettingsCurrencyQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsCurrencyVO;
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
public class SettingsCurrencyServiceImpl implements ISettingsCurrencyService {

    @Autowired
    private ISettingsCurrencyRepository repository;


    @Override
    public void addSettingsCurrency(SettingsCurrencyDTO.ETO eto) {
        QueryWrapper<SettingsCurrency> queryWrapper =  MybatisPlusUtil.query();
        SettingsCurrency settingsCurrency = repository.getOne(queryWrapper);
        if(null == settingsCurrency){
            settingsCurrency = new SettingsCurrency();
            BeanUtils.copyProperties(eto, settingsCurrency);
            repository.save(settingsCurrency);
        }else{
            BeanUtils.copyProperties(eto, settingsCurrency);
            repository.updateById(settingsCurrency);
        }
    }

    @Override
    public SettingsCurrencyVO.DetailVO detailSettingsCurrency(BaseDTO dto) {
        QueryWrapper<SettingsCurrency> queryWrapper =  MybatisPlusUtil.query();
        SettingsCurrency settingsCurrency = repository.getOne(queryWrapper);
        SettingsCurrencyVO.DetailVO detailVo = new SettingsCurrencyVO.DetailVO();
        if(null != settingsCurrency){
            BeanUtils.copyProperties(settingsCurrency, detailVo);
        }
        return detailVo;
    }

    @Override
    public void editorStyle(SettingsCurrencyDTO.StyleETO eto) {
        QueryWrapper<SettingsCurrency> queryWrapper = MybatisPlusUtil.query();
        SettingsCurrency settingsCurrency = repository.getOne(queryWrapper);
        if(null == settingsCurrency){
            settingsCurrency = new SettingsCurrency();
            BeanUtils.copyProperties(eto, settingsCurrency);
            repository.save(settingsCurrency);
        }else{
            BeanUtils.copyProperties(eto, settingsCurrency);
            repository.updateById(settingsCurrency);
        }
    }

    @Override
    public SettingsCurrencyVO.StyleDetailVO detailStyle(BaseDTO dto) {
        QueryWrapper<SettingsCurrency> queryWrapper = MybatisPlusUtil.query();
        SettingsCurrency settingsCurrency = repository.getOne(queryWrapper);
        if(null == settingsCurrency){
            return null;
        }
        SettingsCurrencyVO.StyleDetailVO detailVo = new SettingsCurrencyVO.StyleDetailVO();
        BeanUtils.copyProperties(settingsCurrency, detailVo);
        return detailVo;
    }

}
