package com.gs.lshly.biz.support.foundation.service.common.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.foundation.entity.DataPhone;
import com.gs.lshly.biz.support.foundation.entity.PicturesSetting;
import com.gs.lshly.biz.support.foundation.entity.SiteCustomerService;
import com.gs.lshly.biz.support.foundation.enums.CustomerEnableStateEnum;
import com.gs.lshly.biz.support.foundation.repository.IDataPhoneRepository;
import com.gs.lshly.biz.support.foundation.repository.IPicturesSettingRepository;
import com.gs.lshly.biz.support.foundation.repository.ISiteCustomerServiceRepository;
import com.gs.lshly.biz.support.foundation.service.common.ICommonSiteCustomerServiceService;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.vo.CommonSiteCustomerServiceVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataPhoneVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
* <p>
*  服务实现类
* </p>
* @author 陈奇
* @since 2020-10-12
*/
@Component
public class CommonSiteCustomerServiceServiceImpl implements ICommonSiteCustomerServiceService {

    @Autowired
    private ISiteCustomerServiceRepository repository;
    @Autowired
    private IPicturesSettingRepository picturesSettingRepository;
    @Autowired
    private IDataPhoneRepository dataPhoneRepository;

    @Override
    public CommonSiteCustomerServiceVO.ServiceVO getService(BaseDTO dto) {
        CommonSiteCustomerServiceVO.ServiceVO serviceVO = new CommonSiteCustomerServiceVO.ServiceVO();
        QueryWrapper<SiteCustomerService> queryWrapper = MybatisPlusUtil.query();
        SiteCustomerService siteCustomerService =   repository.getOne(queryWrapper);
        if(null != siteCustomerService){
            if(CustomerEnableStateEnum.启用.getCode().equals(siteCustomerService.getState())){
                serviceVO.setAccount(siteCustomerService.getAccount());
                serviceVO.setType(siteCustomerService.getType());
            }
            if(CustomerEnableStateEnum.启用.getCode().equals(siteCustomerService.getPhoneState())){
                serviceVO.setPhone(siteCustomerService.getPhone());
            }
        }
        return serviceVO;
    }

    @Override
    public String getDefaultImage(Integer imageType) {
        QueryWrapper<PicturesSetting> wrapper = MybatisPlusUtil.query();
        wrapper.eq("image_size_type",imageType);
        PicturesSetting picturesSetting = picturesSettingRepository.getOne(wrapper);
        if (null != picturesSetting && StringUtils.isNotBlank(picturesSetting.getImageUrl())){
            return picturesSetting.getImageUrl();
        }
        return null;
    }

    @Override
    public String getDataPhone(BaseDTO dto) {
        QueryWrapper<DataPhone> dataPhoneQueryWrapper = MybatisPlusUtil.query();
        DataPhone dataPhone = dataPhoneRepository.getOne(dataPhoneQueryWrapper);
        if(null == dataPhone){
            return null;
        }
        return dataPhone.getPhone();
    }
}
