package com.gs.lshly.biz.support.foundation.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.foundation.entity.SiteCustomerService;
import com.gs.lshly.biz.support.foundation.repository.ISiteCustomerServiceRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISiteCustomerServiceService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteCustomerServiceDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteCustomerServiceQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteCustomerServiceVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author 陈奇
* @since 2020-10-12
*/
@Component
public class SiteCustomerServiceServiceImpl implements ISiteCustomerServiceService {

    @Autowired
    private ISiteCustomerServiceRepository repository;

    @Override
    public List<SiteCustomerServiceVO.ListVO> list(SiteCustomerServiceQTO.QTO qto) {
        QueryWrapper<SiteCustomerService> wrapper =  MybatisPlusUtil.query();
        List<SiteCustomerService> siteCustomerServiceList=repository.list(wrapper);
        return ListUtil.listCover(SiteCustomerServiceVO.ListVO.class,siteCustomerServiceList);
    }

    @Override
    public void addSiteCustomerService(SiteCustomerServiceDTO.ETO eto) {
        QueryWrapper queryWrapper =  MybatisPlusUtil.query();
        SiteCustomerService oneItem = repository.getOne(queryWrapper);
        SiteCustomerService siteCustomerService = new SiteCustomerService();
        if(null != oneItem){
            siteCustomerService.setId(oneItem.getId());
        }
        siteCustomerService.setAccount(eto.getAccount());
        siteCustomerService.setType(eto.getType());
        siteCustomerService.setState(eto.getState());
        repository.saveOrUpdate(siteCustomerService);
    }

    @Override
    public List<SiteCustomerServiceVO.PhoneVO> listPhone(SiteCustomerServiceQTO.QTO qto) {
        QueryWrapper<SiteCustomerService> wrapper =  MybatisPlusUtil.query();
        List<SiteCustomerService> siteCustomerServiceList=repository.list( wrapper);
        return ListUtil.listCover(SiteCustomerServiceVO.PhoneVO.class,siteCustomerServiceList);
    }

    @Override
    public void addSiteCustomerServicePhone(SiteCustomerServiceDTO.ETOPhone eto) {
        QueryWrapper queryWrapper =  MybatisPlusUtil.query();
        SiteCustomerService oneItem = repository.getOne(queryWrapper);
        SiteCustomerService siteCustomerService = new SiteCustomerService();
        if(null != oneItem){
            siteCustomerService.setId(oneItem.getId());
        }
        siteCustomerService.setPhone(eto.getPhone());
        siteCustomerService.setPhoneState(eto.getPhoneState());
        repository.saveOrUpdate(siteCustomerService);
    }
}
