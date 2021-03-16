package com.gs.lshly.biz.support.foundation.service.fy.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.foundation.entity.SiteCustomerService;
import com.gs.lshly.biz.support.foundation.enums.CustomerEnableStateEnum;
import com.gs.lshly.biz.support.foundation.repository.ISiteCustomerServiceRepository;
import com.gs.lshly.biz.support.foundation.service.fy.IFyCustomerServiceService;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.fy.fundation.vo.FyCustomerServiceVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
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
public class FyCustomerServiceServiceImpl implements IFyCustomerServiceService {

    @Autowired
    private ISiteCustomerServiceRepository repository;

    @Override
    public FyCustomerServiceVO.ServiceVO getService(BaseDTO dto) {
        FyCustomerServiceVO.ServiceVO serviceVO = new FyCustomerServiceVO.ServiceVO();
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
}
