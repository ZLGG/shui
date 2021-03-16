package com.gs.lshly.biz.support.foundation.service.merchadmin.pc.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.foundation.entity.Site;
import com.gs.lshly.biz.support.foundation.repository.ISiteRepository;
import com.gs.lshly.biz.support.foundation.service.merchadmin.pc.IPCMerchSiteService;
import com.gs.lshly.common.enums.SitePCShowEnum;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.PCMerchSiteVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2021-01-04
*/
@Component
public class PCMerchSiteServiceImpl implements IPCMerchSiteService {

    @Autowired
    private ISiteRepository repository;


    @Override
    public PCMerchSiteVO.LoginImageVO getLoginImageVO(BaseDTO dto) {
        QueryWrapper<Site> wrapper = MybatisPlusUtil.query();
        wrapper.eq("pc_show", SitePCShowEnum.显示.getCode());
        wrapper.select("id","merchant_login_backimage");
        Site site = repository.getOne(wrapper);
        if (null == site){
            return null;
        }
        PCMerchSiteVO.LoginImageVO loginImageVO = new PCMerchSiteVO.LoginImageVO();
        BeanUtils.copyProperties(site,loginImageVO);
        return loginImageVO;
    }
}
