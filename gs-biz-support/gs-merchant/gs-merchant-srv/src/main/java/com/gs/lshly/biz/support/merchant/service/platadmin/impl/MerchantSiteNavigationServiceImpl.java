package com.gs.lshly.biz.support.merchant.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.merchant.entity.MerchantSiteNavigation;
import com.gs.lshly.biz.support.merchant.repository.IMerchantSiteNavigationRepository;
import com.gs.lshly.biz.support.merchant.service.platadmin.IMerchantSiteNavigationService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantSiteNavigationDTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantSiteNavigationVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
public class MerchantSiteNavigationServiceImpl implements IMerchantSiteNavigationService {

    @Autowired
    private IMerchantSiteNavigationRepository repository;


    @Override
    public List<MerchantSiteNavigationVO.ListVO> listSiteNavigation() {
        List<MerchantSiteNavigationVO.ListVO> listVOS = new ArrayList<>();
        QueryWrapper<MerchantSiteNavigation> wrapper = MybatisPlusUtil.query();
        wrapper.orderByDesc("cdate");
        List<MerchantSiteNavigation> siteNavigations = repository.list(wrapper);
        if (ObjectUtils.isNotEmpty(siteNavigations)){
            listVOS = ListUtil.listCover(MerchantSiteNavigationVO.ListVO.class,siteNavigations);
        }
        return listVOS;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveMerchantSiteNavigation(List<MerchantSiteNavigationDTO.ETO> etos) {
        //删除之前的配置
        QueryWrapper<MerchantSiteNavigation> wrapper = MybatisPlusUtil.query();
        repository.remove(wrapper);

        if (ObjectUtils.isNotEmpty(etos)){
            List<MerchantSiteNavigation> siteNavigations = new ArrayList<>();
            for (MerchantSiteNavigationDTO.ETO eto : etos){
                if (StringUtils.isBlank(eto.getName())){
                    throw new BusinessException("链接名称不能为空！！");
                }
                MerchantSiteNavigation siteNavigation = new MerchantSiteNavigation();
                BeanUtils.copyProperties(eto,siteNavigation);
                if (ObjectUtils.isEmpty(eto.getIdx())){
                    siteNavigation.setIdx(1);
                }
                siteNavigation.setId("");
                siteNavigations.add(siteNavigation);
            }
            repository.saveBatch(siteNavigations);
        }
    }
}
