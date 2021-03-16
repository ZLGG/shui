package com.gs.lshly.biz.support.foundation.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.foundation.entity.Settings;
import com.gs.lshly.biz.support.foundation.repository.ISettingsRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISettingsService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsCategoryDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SettingsDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SettingsQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsCategoryRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.springframework.transaction.annotation.Transactional;

/**
* <p>
*  服务实现类
* </p>
* @author 陈奇
* @since 2020-10-12
*/
@Component
public class SettingsServiceImpl implements ISettingsService {

    @Autowired
    private ISettingsRepository repository;

    @DubboReference
    private IGoodsCategoryRpc iGoodsCategoryRpc;


    @Override
    public SettingsVO.ListVO detail(BaseDTO dto) {
        Settings settings = repository.getOne(MybatisPlusUtil.query());
        if(null == settings){
            return null;
        }
        SettingsVO.ListVO listVO = new SettingsVO.ListVO();
        BeanUtils.copyProperties(settings,listVO);
        return listVO;
    }

    @Override
    public void editorSettings(SettingsDTO.ETO eto) {
        QueryWrapper<Settings> queryWrapper = MybatisPlusUtil.query();
        Settings settings =  repository.getOne(queryWrapper);
        if(null == settings){
            settings = new Settings();
            BeanUtils.copyProperties(eto, settings);
            repository.save(settings);
        }else{
            BeanUtils.copyProperties(eto, settings);
            repository.updateById(settings);
        }
    }

    @Override
    public SettingsVO.GetPayVO detailGetPay(BaseDTO dto) {
        Settings settings = repository.getOne(MybatisPlusUtil.query());
        if(null == settings){
            return null;
        }
        SettingsVO.GetPayVO getPayVO = new SettingsVO.GetPayVO();
        BeanUtils.copyProperties(settings,getPayVO);
        return getPayVO;
    }

    @Override
    public void editorGetPay(SettingsDTO.GetPayETO eto) {
        QueryWrapper<Settings> queryWrapper = MybatisPlusUtil.query();
        Settings settings =  repository.getOne(queryWrapper);
        if(null == settings){
            settings = new Settings();
            BeanUtils.copyProperties(eto, settings);
            repository.save(settings);
        }else{
            BeanUtils.copyProperties(eto, settings);
            repository.updateById(settings);
        }
    }

    @Override
    @Transactional
    public void rightsSettings(SettingsDTO.RightsSetting dto) {
        QueryWrapper<Settings> queryWrapper = MybatisPlusUtil.query();
        Settings settings =  repository.getOne(queryWrapper);
        if (ObjectUtils.isNotEmpty(dto.getIsRightsTimeLimit())){
            if (dto.getIsRightsTimeLimit()==10){
                //开启
                if (ObjectUtils.isEmpty(dto.getRightsDays())){
                    throw new BusinessException("请输入售后天数");
                }
                if (ObjectUtils.isEmpty(dto.getRightsType())){
                    throw new BusinessException("请选择售后类型");
                }
                if (dto.getRightsType()==10){
                    //退货
                    settings.setIsRefundTimeLimit(10);
                    settings.setRefundDays(dto.getRightsDays());
                    GoodsCategoryDTO.RightsSetting rightsSetting = new GoodsCategoryDTO.RightsSetting();
                    rightsSetting.setRefundDays(dto.getRightsDays());
                    iGoodsCategoryRpc.innerRightsCategorySettings(rightsSetting);
                }else if(dto.getRightsType()==20){
                    settings.setIsReturnTimeLimit(10);
                    settings.setReturnDays(dto.getRightsDays());
                    GoodsCategoryDTO.RightsSetting rightsSetting = new GoodsCategoryDTO.RightsSetting();
                    rightsSetting.setReturnDays(dto.getRightsDays());
                    iGoodsCategoryRpc.innerRightsCategorySettings(rightsSetting);
                }
            }else if(dto.getIsRightsTimeLimit()==20){
                //关闭
                if (dto.getRightsType()==10){
                    //退货
                    settings.setIsRefundTimeLimit(20);
                    settings.setReturnDays(0);
                    GoodsCategoryDTO.RightsSetting rightsSetting = new GoodsCategoryDTO.RightsSetting();
                    rightsSetting.setRefundDays(0);
                    iGoodsCategoryRpc.innerRightsCategorySettings(rightsSetting);
                }else if(dto.getRightsType()==20){
                    settings.setIsReturnTimeLimit(20);
                    settings.setReturnDays(0);
                    GoodsCategoryDTO.RightsSetting rightsSetting = new GoodsCategoryDTO.RightsSetting();
                    rightsSetting.setReturnDays(0);
                    iGoodsCategoryRpc.innerRightsCategorySettings(rightsSetting);
                }

            }
        }
        repository.updateById(settings);

    }

    @Override
    public SettingsVO.Rights rightsSettingsView() {
        QueryWrapper<Settings> queryWrapper = MybatisPlusUtil.query();
        Settings settings =  repository.getOne(queryWrapper);
        SettingsVO.Rights rights = new SettingsVO.Rights();
        BeanUtils.copyProperties(settings,rights);
        return rights;
    }

    @Override
    public void activityStartRemind(SettingsDTO.ActivityStartRemind dto) {
        QueryWrapper<Settings> queryWrapper = MybatisPlusUtil.query();
        Settings settings =  repository.getOne(queryWrapper);
       if (ObjectUtils.isNotEmpty(settings)){
           if (ObjectUtils.isNotEmpty(dto.getMessageCount())){
               settings.setActivityMessageCount(dto.getMessageCount());
               repository.updateById(settings);
           }
       }
    }


}
