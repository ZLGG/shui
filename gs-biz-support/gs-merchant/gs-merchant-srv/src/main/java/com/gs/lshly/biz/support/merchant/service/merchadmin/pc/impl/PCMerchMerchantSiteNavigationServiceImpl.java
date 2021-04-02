package com.gs.lshly.biz.support.merchant.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.merchant.entity.MerchantSiteNavigation;
import com.gs.lshly.biz.support.merchant.repository.IMerchantSiteNavigationRepository;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchMerchantSiteNavigationService;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantSiteNavigationVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2021-03-08
*/
@Component
public class PCMerchMerchantSiteNavigationServiceImpl implements IPCMerchMerchantSiteNavigationService {

    @Autowired
    private IMerchantSiteNavigationRepository repository;


    @Override
    public List<PCMerchMerchantSiteNavigationVO.ListVO> listData() {
        List<PCMerchMerchantSiteNavigationVO.ListVO> listVOS = new ArrayList<>();
        QueryWrapper<MerchantSiteNavigation> wrapper = MybatisPlusUtil.query();
        wrapper.orderByAsc("idx","id");
        List<MerchantSiteNavigation> siteNavigations = repository.list(wrapper);
        if (ObjectUtils.isNotEmpty(siteNavigations)){
            listVOS = ListUtil.listCover(PCMerchMerchantSiteNavigationVO.ListVO.class,siteNavigations);
        }
        return listVOS;
    }
}
