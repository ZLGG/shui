package com.gs.lshly.biz.support.merchant.service.bbb.pc.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.merchant.entity.MerchantSiteNavigation;
import com.gs.lshly.biz.support.merchant.repository.IMerchantSiteNavigationRepository;
import com.gs.lshly.biz.support.merchant.service.bbb.pc.IPCBbbMerchantSiteNavigationService;
import com.gs.lshly.common.struct.bbb.pc.merchant.vo.PCBbbMerchantSiteNavigationVO;
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
* @since 2021-03-09
*/
@Component
public class PCBbbMerchantSiteNavigationServiceImpl implements IPCBbbMerchantSiteNavigationService {

    @Autowired
    private IMerchantSiteNavigationRepository repository;


    @Override
    public List<PCBbbMerchantSiteNavigationVO.ListVO> listData() {
        List<PCBbbMerchantSiteNavigationVO.ListVO> listVOS = new ArrayList<>();
        QueryWrapper<MerchantSiteNavigation> wrapper = MybatisPlusUtil.query();
        wrapper.orderByDesc("cdate");
        List<MerchantSiteNavigation> siteNavigations = repository.list(wrapper);
        if (ObjectUtils.isNotEmpty(siteNavigations)){
            listVOS = ListUtil.listCover(PCBbbMerchantSiteNavigationVO.ListVO.class,siteNavigations);
        }
        return listVOS;
    }
}
